package com.test.img;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

public class ImgCut {
	
	public static void main(String[] args) {
		try {
			//cutImage("D:\\c\\1\\7.png", "D:\\c\\1\\007.jpg", 12, 14, 92, 65);
//			zoomImage("D:\\c\\0.jpg", "D:\\c\\003.jpg", 360, 270);
			//System.out.println(ImgCut.class.getClass().getResource("/").getPath() );
			System.out.println(System.getProperty("user.dir")  );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 根据尺寸图片居中裁剪
	 */
	 public static void cutCenterImage(String src,String dest,int w,int h) throws IOException{ 
		 Iterator iterator = ImageIO.getImageReadersByFormatName("jpg"); 
         ImageReader reader = (ImageReader)iterator.next(); 
         InputStream in=new FileInputStream(src);
         ImageInputStream iis = ImageIO.createImageInputStream(in); 
         reader.setInput(iis, true); 
         ImageReadParam param = reader.getDefaultReadParam(); 
         int imageIndex = 0; 
         Rectangle rect = new Rectangle((reader.getWidth(imageIndex)-w)/2, (reader.getHeight(imageIndex)-h)/2, w, h);  
         param.setSourceRegion(rect); 
         BufferedImage bi = reader.read(0,param);   
         ImageIO.write(bi, "jpg", new File(dest));           
  
	 }
	/*
	 * 图片裁剪二分之一
	 */
	 public static void cutHalfImage(String src,String dest) throws IOException{ 
		 Iterator iterator = ImageIO.getImageReadersByFormatName("jpg"); 
         ImageReader reader = (ImageReader)iterator.next(); 
         InputStream in=new FileInputStream(src);
         ImageInputStream iis = ImageIO.createImageInputStream(in); 
         reader.setInput(iis, true); 
         ImageReadParam param = reader.getDefaultReadParam(); 
         int imageIndex = 0; 
         int width = reader.getWidth(imageIndex)/2; 
         int height = reader.getHeight(imageIndex)/2; 
         Rectangle rect = new Rectangle(width/2, height/2, width, height); 
         param.setSourceRegion(rect); 
         BufferedImage bi = reader.read(0,param);   
         ImageIO.write(bi, "jpg", new File(dest));   
	 }
	/*
	 * 图片裁剪通用接口
	 */

    public static void cutImage(String src,String dest,int x,int y,int w,int h) throws IOException{ 
           Iterator iterator = ImageIO.getImageReadersByFormatName("png"); 
           ImageReader reader = (ImageReader)iterator.next(); 
           InputStream in=new FileInputStream(src);
           ImageInputStream iis = ImageIO.createImageInputStream(in); 
           reader.setInput(iis, true); 
           ImageReadParam param = reader.getDefaultReadParam(); 
           Rectangle rect = new Rectangle(x, y, w,h);  
           param.setSourceRegion(rect); 
           BufferedImage bi = reader.read(0,param);   
           ImageIO.write(bi, "png", new File(dest));           

    } 
    /*
     * 图片缩放
     */
    public static void zoomImage(String src,String dest,int w,int h) throws Exception {
		double wr=0,hr=0;
		File srcFile = new File(src);
		File destFile = new File(dest);
		
		
		InputStream in = new FileInputStream(srcFile);

		BufferedImage bufImg = ImageIO.read(in);
		
		//BufferedImage bufImg = ImageIO.read(srcFile);
		Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);
		wr=0.2;
		hr=0.6;
//		wr=w*1.0/bufImg.getWidth();
//		hr=h*1.0 / bufImg.getHeight();
		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
		Itemp = ato.filter(bufImg, null);
		
	    
		try {
//			ImageIO.write((BufferedImage) Itemp,dest.substring(dest.lastIndexOf(".")+1), destFile);
			
			
			
//			File  files = ImageIO.getCacheDirectory();
//			 //拿到输出流，同时重命名上传的文件  
            FileOutputStream os = new FileOutputStream("D:/test3.jpg");  
//            //拿到上传文件的输入流  
//            FileInputStream ins =  new FileInputStream(dest);
            
            InputStream is = null;
    		BufferedImage bi =(BufferedImage) Itemp;
    		
    		ByteArrayOutputStream bs = new ByteArrayOutputStream();
    	    ImageOutputStream imOut;
    	    imOut = ImageIO.createImageOutputStream(bs);
    	    ImageIO.write(bi, "jpg",imOut);
    	    is= new ByteArrayInputStream(bs.toByteArray());
    	    InputStream ins =  is;  
    	    
            //以写字节的方式写文件  
            int b = 0; 
            long bytes = 0;
            while((b=ins.read()) != -1){
            	bytes = bytes + b;
                os.write(b); 
                System.out.println(">>>>>>>>>>>>>>"+bytes);
            }  
            System.out.println();
            System.out.println();
            System.out.println("<><><><>上传完成<><><><>");
            os.flush();  
            os.close();  
            ins.close(); 
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
