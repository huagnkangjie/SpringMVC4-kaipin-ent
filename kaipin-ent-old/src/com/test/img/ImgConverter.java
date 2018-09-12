package com.test.img;
import java.awt.image.BufferedImage;  
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;  
  
/** 
 *  
 */  
  
/** 
 * Created on 2011-5-24 Discription:[convert GIF->JPG GIF->PNG PNG->GIF(X) 
 * PNG->JPG ] 
 *  
 * @param source 
 * @param formatName 
 * @param result 
 * @author:dx[hzdengxu@163.com] 
 */  
  
public class ImgConverter {  
    private String[] args;  
  
    public static void convert(String source, String formatName, String result) {  
        try {  
            File f = new File(source);
            InputStream in = new FileInputStream(f);
            f.canRead();  
            BufferedImage src = ImageIO.read(in); 
            ImageIO.write(src, formatName, new File(result)); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public ImgConverter(String[] args) {  
        // TODO Auto-generated constructor stub  
        this.args = args;  
    }  
  
    public void run() {  
        if (this.args.length > 2) {  
            convert(this.args[0], this.args[1], this.args[2]);  
        }  
    }  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
    	String test[] = {"D:/c/1/7.png","jpg","D:/c/1/007.jpg"};
        new ImgConverter(test).run();  
  
    }  
} 