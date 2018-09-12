package com.kaipin.search.group;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

public class Test {

	public static class Man {
		private String position_name;

		public Man(String position_name) {
			this.position_name = position_name;
		}

		public String getPosition_name() {
			return position_name;
		}

	
	}

	public static void main(String[] args) {

		Man man = new Man("test");

		try {
			System.out.println(PropertyUtils.getProperty(man, "position_name"));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
