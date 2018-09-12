package com.test.img;


import java.io.IOException;
/**
 * 图片按比例进行转换大小
 * 
 * @author Mr-H
 *
 */

public class TestResizeImage {
	
	public static void main(String []args){
		try {
			ImageResizer.resizeImage("D:\\0.jpg",
					"D:\\00.png",100,100);
		} catch (IOException e) {
			System.out.println("图片转换出现异常！");
		}
		
	}

}