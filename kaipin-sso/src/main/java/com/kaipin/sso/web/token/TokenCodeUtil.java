package com.kaipin.sso.web.token;

public class TokenCodeUtil {

	public final int MODE = 128;
	public final int NS = 4;

	private static TokenCodeUtil instance;

	public static TokenCodeUtil getInstance() {
		if (instance == null) {
			instance = new TokenCodeUtil();
			return instance;
		}

		return instance;
	}

	/**
	 * 解码
	 * 
	 * @param str
	 *            待解码字符串
	 * @return
	 * @throws Exception
	 */
	public String decode(String str) throws Exception {

		int len = str.length() / NS;

		StringBuffer str_buf = new StringBuffer();
		String str_value;

		int value;
		int a1;
		int n1;
		for (int pos = 0; pos < len; pos++) {

			str_value = str.substring(pos * NS, NS*(pos+1));
			
			if(str_value.equals("")){
				throw new Exception("编码数据出现问题");
			}
			
			value = Integer.parseInt(str_value, 16);

			a1 = value -1;

			n1 = a1 / (getKey(value));
			
			str_buf.append(n1+"");

			System.out
		  .println("a1=" + a1 + ",n1="+n1);
			
		}
		return str_buf.toString();

		// int[] rst = this.config.ipv4ToInt();
		// int a1 = Integer.parseInt(str.substring(0, NS), 16) - 1;
		// int a2 = Integer.parseInt(str.substring(NS, NS * 2), 16) - 1;
		//
		// int a3 = Integer.parseInt(str.substring(NS * 2, NS * 3), 16) - 1;
		//
		// int a4 = Integer.parseInt(str.substring(NS * 3, str.length()), 16) -
		// 1;
		//
		// int n1 = a1
		// / (getKey(assiicValue(String.valueOf(Math.abs((int) rst[0])))));
		// int n2 = a2
		// / (getKey(assiicValue(String.valueOf(Math.abs((int) rst[1])))));
		//
		// int n3 = a3
		// / (getKey(assiicValue(String.valueOf(Math.abs((int) rst[2])))));
		// int n4 = a4
		// / (getKey(assiicValue(String.valueOf(Math.abs((int) rst[3])))));
		//
		// System.out
		// .println("a1=" + a1 + ",a2=" + a2 + ",a3=" + a3 + ",a4=" + a4);
		// String ip = n1 + "." + n2 + "." + n3 + "." + n4;
		//
		// return ip;

		// return "";

	}

	/**
	 * 编码实现
	 * 
	 * @return
	 */
	public String encode(String pwd) {
		StringBuffer str_rst =new StringBuffer();
		try {
			char rst[] = pwd.toCharArray();

			for (int i = 0, len = rst.length; i < len; i++) {

				str_rst.append( builder((int) rst[i]));

			}
		} catch (Exception e) {

		}
		return str_rst.toString();
	}



	/**
	 * 返回每段Ip编码后的字符串
	 * 
	 * @param b
	 *            每段ip值
	 * @return
	 */
	public String builder(int b) {
		b = Math.abs(b);
		int value = assiicValue(String.valueOf(b));
		int key = getKey(value);

		String b_str = buildData(key, b);

		return b_str;
	}

	/**
	 * 生成的（key*b+1）转换为16进制字符串长度小于4则补0对齐
	 * 
	 * @param key
	 * @param b
	 *            原始ip段值
	 * @return
	 */
	public String buildData(int key, int b) {
		String str = new String(Integer.toHexString((key * b) + 1)).toUpperCase();
	
		// System.out.println("(key*b)="+(key*b));
		if (str.length() < NS) {
			int len = NS - str.length();
			String s = "";
			for (int i = 0; i < len; i++) {
				s += "0";
			}
			str = s + str;
		}
		return str;

		// return "";
	}

	/**
	 * 根据 某段的和求模 +1作为key返回
	 * 
	 * @param num
	 *            assiicValue 函数返回
	 * @return
	 */
	private int getKey(int num) {
		int key = num % MODE + 1;
		
		System.out.println("(key)="+(key ));
		return key;
	}

	/**
	 * 每段数值求和
	 * 
	 * @param id
	 * @return
	 */
	private int assiicValue(String id) {
		// String id=this.config.getCollectionId();
		int len = id.length();
		int sum2 = 0;
		// System.out.println("id="+id);
		for (int i = 0; i < len; i++) {
			sum2 += Integer.parseInt(id.substring(i, i + 1));
		}

		return sum2;
	}

	// public String encode(int str){
	// String CODEC =
	// "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	// byte pos;
	// pos = (byte) (((str & 3) << 4) + ((str >> 4) & 15));
	//
	//
	//
	// return "";
	//
	//
	//
	// }

	public static void main(String[] args) {
		String encode = TokenCodeUtil.getInstance().encode("123");

		System.out.println(encode);

		//System.out.println("672daffd333s".substring (4,8));
		
		try {
System.out.println(TokenCodeUtil.getInstance().decode(encode));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
