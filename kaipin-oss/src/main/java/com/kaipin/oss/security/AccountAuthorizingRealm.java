package com.kaipin.oss.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.oss.model.platform.PlatformOrganization;
import com.kaipin.oss.model.platform.PlatformPermission;
import com.kaipin.oss.model.platform.PlatformRole;
import com.kaipin.oss.model.platform.PlatformUser;
import com.kaipin.oss.service.platform.PlatformUserService;

/**
 * 自定义DB Realm
 * 
 */
public class AccountAuthorizingRealm extends AuthorizingRealm {

	@Autowired
	private PlatformUserService platformUserService;

//	@Override
//	public String getName() {
//		return getClass().getName();
//	}

	/**
	 * 登录认证
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		PlatformUser user = platformUserService.findByUsername(token.getUsername());

		if (user != null) {
			ShiroUser shiroUser = new ShiroUser(user.getId(), user.getUserName());
		//	shiroUser.setUser(user);
		
		//	SecurityUtils.getSubject().isPermitted("");
			return new SimpleAuthenticationInfo(shiroUser, user.getUserPassword()
			, getName());
			
			
	//		return new SimpleAuthenticationInfo(shiroUser, user.getUserPassword(), getName());
			
			
		} else {
			return null;
		}
	}

	/**
	 * 授权
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//String username = (String) principals.getPrimaryPrincipal();

		Collection<?> collection = principals.fromRealm(getName());
		if (collection == null || collection.isEmpty()) {
			return null;
		}
		
		ShiroUser shiroUser = (ShiroUser) collection.iterator().next();

		String username =	shiroUser.getLoginName();
		
		PlatformUser user = platformUserService.findByUsername(username);

		SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
		
		shiroUser.setUser(user);
		
		if (user != null) {
			auth=buildAuthorizationInfo(shiroUser);
		}
		return auth;
	}

	public void removeUserAuthorizationInfoCache(String username) {
		SimplePrincipalCollection pc = new SimplePrincipalCollection();
		pc.add(username, super.getName());
		super.clearCachedAuthorizationInfo(pc);
	}

	private SimpleAuthorizationInfo buildAuthorizationInfo(ShiroUser shiroUser) {

		Collection<String> hasPermissions = hasPermissions = new HashSet<String>();
		
		Collection<String> hasRoles = hasPermissions = new HashSet<String>();

		if (shiroUser.getUser().superType()) {// 超级管理员

			hasPermissions.clear();

			hasPermissions.add("*");

		} else {

			PlatformOrganization organization = shiroUser.getUser().getOrganization();

			PlatformRole role = shiroUser.getUser().getRole();

			List<PlatformRole> organizationRoles = null;

			if (organization != null) {

				organizationRoles = organization.getOrganizationRoles();

			}

			Collection<PlatformRole> roles = mergeUserRoles(role, organizationRoles);

			hasRoles = makeRoles(roles,shiroUser);
			
			hasPermissions = makePermissions(roles,shiroUser);

		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(hasRoles);
		info.addStringPermissions(hasPermissions);
		return info;

	}

	private Collection<PlatformRole> mergeUserRoles(PlatformRole role, List<PlatformRole> organizationRoles) {
		Set<PlatformRole> roles = new HashSet<PlatformRole>();

		if (organizationRoles != null) {
			for (PlatformRole userRole : organizationRoles) {
				roles.add(userRole);
			}
		}
		if (role != null) {

			roles.add(role);
		}

		return roles;
	}

	/**
	 * 组装角色权限
	 * 
	 * @param roles
	 * @param shiroUser
	 * @return
	 */
	private Collection<String> makeRoles(Collection<PlatformRole> roles,ShiroUser shiroUser) {
		Collection<String> hasRoles = new HashSet<String>();
		for (PlatformRole role : roles) {
			hasRoles.add(role.getName());
		}

		return hasRoles;
	}

	/**
	 * 组装资源操作权限
	 * 
	 * @param roles
	 * @param shiroUser
	 * @return
	 */
	private Collection<String> makePermissions(Collection<PlatformRole> roles,ShiroUser shiroUser) {

		Collection<String> stringPermissions = new ArrayList<String>();
		for (PlatformRole role : roles) {
			List<PlatformPermission> rolePermissions = role.getRolePermissions();

			for (PlatformPermission permission : rolePermissions) {

				String resource = permission.getModule().getSn();

				String operate = permission.getSn();

				StringBuilder builder = new StringBuilder();
				
				builder.append(resource + ":" + operate);
				
				shiroUser.getHasModules().put(resource, permission.getModule());
  
				stringPermissions.add(builder.toString());
			}
		}

		return stringPermissions;
	}
}
