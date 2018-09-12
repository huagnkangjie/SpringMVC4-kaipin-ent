package com.kaipin.oss.presentation.taglib;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;

import com.kaipin.oss.model.platform.PlatformModule;

/**
 * 左侧折叠菜单
 */
public class MenuAccordionTag extends AbstractExecuteContext {
	
 private PlatformModule  menu;//菜单
 
 
 private  String urlPrefix;//打开的域名
 
 
 
 
 
	@Override
	public void doTag() throws JspException, IOException {

		
		if(menu!=null&& menu.getChildren().size()>0){
			
			
			//String str=link(menu.getChildren());
			
			
			
			
			
		}
		
		
		
		
		
		
	}
	private String getSubMenu(List<PlatformModule> list){
		
		
		
		
		
		
		return null;
		
	}
	
	
	private String getSubMenu1(List<PlatformModule> list){
		
		
		
		
		
		return null;
		
	}
	
	private String getSubMenu2(List<PlatformModule> list){
		
		
		
		
		
		return null;
		
	}
	
	
	
	
	public PlatformModule getMenu() {
		return menu;
	}
	public void setMenu(PlatformModule menu) {
		this.menu = menu;
	}
	public String getUrlPrefix() {
		return urlPrefix;
	}
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}
	
	
	
}
