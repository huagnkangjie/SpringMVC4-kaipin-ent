package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//复制图片
public class FileInputStreamDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\0.jpg");
        FileInputStream fis = new FileInputStream(file); //创建一个输入流
        //创建一个输出流，后面一个参数true表示追加，原有内容不会被清除,默认为false
        FileOutputStream fos = new FileOutputStream("F:\\0.jpg",false);
        int ch = 0;
        //方式一
        /*while((ch=fis.read()) != -1){
            fos.write(ch);
        }*/
        //方式二
       byte[] b = new byte[1024];
        while((ch=fis.read(b)) != -1){
            fos.write(b,0,ch);
        }
        //方式三
//        byte[] b = new byte[fis.available()];
//        fis.read(b); //首先把fis的内容读到字节数组b里面
//        fos.write(b);//再把字节数组b的内容通过输出流写到指定文件
        //关闭流
        fos.close();
        fis.close();
    }

}