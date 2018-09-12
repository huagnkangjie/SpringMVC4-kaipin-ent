package junit.platform;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath*:application-context.xml","classpath*:kaipin-context.xml","classpath*:shiro-context.xml" })
//

public class TestAuthorization {
	 //===========================================
    // 自定义Realm进行授权测试：角色授权、资源授权
    //===========================================
    @Test
    public void testAuthorizationCustomRealm(){
        //创建一个SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        //创建SecurityManager 
        SecurityManager sm = factory.getInstance();

        //将SecurityManager设置到系统运行环境，和Spring整合后将SecurityManager配置到Spring容器中，一般单例管理
        SecurityUtils.setSecurityManager(sm);

        //创建Subject
        Subject subject = SecurityUtils.getSubject();

        //创建token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "11111");

        //执行认证
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

        System.out.println("认证状态：" + subject.isAuthenticated());


        //认证通过后执行授权

        //===========================================
        // 基于角色授权
        //===========================================
        //hasRole方法传入的是角色标识
//      boolean isHasRole = subject.hasRole("role1");
//      System.out.println("正确角色，角色有权限--->" + isHasRole);

//      //传入错误的角色
//      boolean isHasRole2 = subject.hasRole("role12");
//      System.out.println("错误角色，角色有权限--->" + isHasRole2);

//      //判断多个角色
//      boolean hasAllRoles = subject.hasAllRoles(Arrays.asList("role1","role2"));
//      System.out.println("多个角色判断--->" + hasAllRoles);

        //使用check方法进行授权，如果不通过，会抛出异常
//      try {
//          subject.checkRole("role12");
//      } catch (AuthorizationException e) {
//          // TODO 自动生成的 catch 块
//          e.printStackTrace();
//      }


        //===========================================
        // 基于资源授权
        //===========================================
        //调用isPermitted()，就会调用CustomRealm从数据库查数据权限数据
        //isPermitted()传入权限标识符，判断user:create:1是否在CustomRealm查询到权限数据之内
        boolean isPermited = subject.isPermitted("user:create:1");
        System.out.println("资源有权限，单个权限判断--->" + isPermited);

        //传入的是资源标识符，多个权限判断
        boolean isPermitedAll = subject.isPermittedAll("user:create","user:update","user:delete");
        System.out.println("资源有权限，多个权限判断--->" + isPermitedAll);

        //资源权限也支持check，如果不通过，也会抛异常
        try {
            subject.checkPermission("item:add");
        } catch (AuthorizationException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            System.out.println(e.toString());
        }


        subject.getPrincipal();

    }//method
}
