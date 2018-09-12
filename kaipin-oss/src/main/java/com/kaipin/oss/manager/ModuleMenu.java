package com.kaipin.oss.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import com.kaipin.oss.model.platform.PlatformModule;
import com.kaipin.oss.model.platform.PlatformPermission;
import com.kaipin.oss.security.SecurityUtils;


public class ModuleMenu {

	public ModuleMenu() {

	}

	public  PlatformModule  getTreeMenu(List<PlatformModule> list) {

		Subject subject = null;// SecurityUtils.getSubject();

		PlatformModule root = getRoot(list);

 
		if (root != null) {
			buildCheckPermission(root, list, subject);

		}

		return root;

	}

	public PlatformModule getTree(List<PlatformModule> list) {

		PlatformModule root = getRoot(list);

		if (root != null) {
			
			
			buildTree(root, list);
			

		}

		return root;

	}

	private PlatformModule getRoot(List<PlatformModule> list) {

		for (PlatformModule platformModule : list) {

			if (platformModule.getParentId() == null) {

				return platformModule;

			}

		}
		return null;
	}

	private void buildCheckPermission(PlatformModule root, List<PlatformModule> list, Subject subject) {

		for (PlatformModule platformModule : list) {

			// 只加入拥有show权限的Module
			// if (subject.isPermitted(platformModule.getSn() + ":" +
			// PlatformPermission.PERMISSION_SHOW)) {

			if ((root.getId() != platformModule.getId()) && root.getId() == platformModule.getParentId()) {

				if (isParent(root, list)) {
					buildCheckPermission(platformModule, list, subject);
				}
				root.getChildren().add(platformModule);
			}

			// }

		}

	}

	private void buildTree(PlatformModule root, List<PlatformModule> list) {

		for (PlatformModule platformModule : list) {

			if ((root.getId() != platformModule.getId()) && root.getId() == platformModule.getParentId()) {

				if (isParent(root, list)) {
					buildTree(platformModule, list);
				}
				root.getChildren().add(platformModule);
			}

		}

	}

	private boolean isParent(PlatformModule root, List<PlatformModule> list) {
		boolean isParent = false;
		for (PlatformModule action : list) {
			if ((root.getId() != (action.getId()) && root.getId() == action.getParentId())) {
				isParent = true;
				break;
			}
		}
		return isParent;
	}

}
