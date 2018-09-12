package com.util;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ObjectUtil {

	/***
	 *实现两个实例对象之间的对拷， 把第一个对象中不为空的数据复制到第二个对象中
	 * 
	 * @param obj1
	 *            源数据对象
	 * @param obj2
	 *            接受数据对象
	 */

	@SuppressWarnings("unchecked")
	public static void copeField(Object obj1, Object obj2) {
		// 对obj1反射
		Class obj1Class = obj1.getClass();
		Method[] obj1methods = obj1Class.getDeclaredMethods();
		// 对obj2反射
		Class obj2Class = obj2.getClass();
		Method[] obj2methods = obj2Class.getDeclaredMethods();

		// 把obj2的方法影射到MAP中，方便调用
		Map obj2MeMap = new HashMap();
		for (int i = 0; i < obj2methods.length; i++) {
			Method method = obj2methods[i];
			obj2MeMap.put(method.getName(), method);
		}
		for (int i = 0; i < obj1methods.length; i++) {
			String methodName = obj1methods[i].getName();
			if (methodName != null && methodName.startsWith("get")) {
				// 取得对象里的FIELD
				try {
					// 调用obj1实例中的getXXXX方法
					Object returnObj = obj1methods[i].invoke(obj1,
							new Object[0]);
					// 获取obj2的set方法
					Method obj2method = (Method) obj2MeMap.get("set"
							+ methodName.split("get")[1]);
					// 调用obj2实例中的setXXX方法
					if (returnObj != null && obj2method != null) {
						obj2method.invoke(obj2, returnObj);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 把对象属性值清空
	 * 
	 * */
	public static void clearObjectAttributes(Object obj, String[] strArr) {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (strArr != null) {
					boolean flag = false;
					for (int i = 0; i < strArr.length; i++) {
						if (field.getName().equals(strArr[i])) {
							flag = true;
						}
					}
					if (flag) {
						field.set(obj, null);
					}
				} else {
					field.set(obj, null);
				}

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

	}

}
