package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FTPTest {

	public static void main(String[] args) throws Exception {
		FTPUtil util = new FTPUtil();
		boolean flag = util.connect();
		if(flag){
			File file = new File("d:\\c\\1.jpg");
			InputStream ins = new FileInputStream(file);
			util.uploadImg("ce", "se", "test.ini", ins);
		}
		
	}
}
