package com.util;

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
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.apache.log4j.Logger;

import com.pojo.ImgBean;



/**
 * 图片裁剪工具类
 * @author Mr-H
 *
 */
public class ImageCutUtil {
	
	public static final int width = 360; //默认图片宽
	public static final int height = 270;//默认图片高
	
	static Logger log = Logger.getLogger(ImageCutUtil.class.getName());

	/**
	 * 图片裁剪
	 * @param in 输入流
	 * @param dest 目标文件
	 */
	@SuppressWarnings("static-access")
	public static void imgZoom(InputStream in, String fileNewName){
		try {
			int w = 0;
			int h = 0;
			
			BufferedImage bufImg = ImageIO.read(in);
			
			w = bufImg.getWidth();
			h = bufImg.getHeight();
			
			Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);
			
			double percent = getZoomPercent(w, h);
			
			AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(percent, percent), null);
			Itemp = ato.filter(bufImg, null);
			
            InputStream is = null;
    		BufferedImage bi =(BufferedImage) Itemp;
    		
    		//BufferedImage 转换成 InputStream
    		String suffix = fileNewName.substring(fileNewName.lastIndexOf(".")+1, fileNewName.length()); 
    		suffix = StringUtil.replaceBlank(suffix);
    		InputStream ins =  getInputStream(bi, suffix); 
    	    
    	    //把压缩好的图片存放到ftp的temp临时文件夹
    	    FTPUtil util = new FTPUtil();//获取ftp链接
            boolean flag;
			try {
				flag = util.connect();
				if(flag){
        			util.uploadImgCut(
        					fileNewName, //文件名字
        					ins,"zoom");
        		}
			} catch (Exception e) {
				e.printStackTrace();
				log.info(LogUtil.getTrace(e));;
			}
    	    
            ins.close(); 
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断是否按 360 * 270 比例进行压缩
	 * @param w 宽 
	 * @param h 高 
	 * @return 返回百分比
	 */
	public static double getZoomPercent(int w, int h){
		double percent = 1.0;
		try {
			if(w >= width || h >= height){//w h 任意一个大于固定值则进行压缩
				if(w >= width && h >= height){//w h 同时大于固定值，再比较w h 哪个大
					if((w - width) > (h - height)){// w 大于估计值的比例 大于  h大于估计值 
						percent = width * 1.0 / w;
					}else{
						percent = height * 1.0 / h;
					}
				}else if(w >= width && h <= height){ // 按照 w 的比例进行缩放
					percent = width * 1.0 / w;
				}else if(w <= width && h >= height){ // 按照 h 的比例进行缩放
					percent = height * 1.0 / h;
				}
				return percent;
			}else{
				return percent;
			}
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return percent;
		}
	}
	
	
	/**
	 * 图片裁剪
	 * @param datas
	 * @param fileName
	 */
	public static InputStream imgCut(String datas, String fileName, InputStream input){
		try {
			InputStream ins = null;
			if(StringUtil.isEmpty(datas)){
				return null;
			}
			ImgBean img = JsonUtil.jsonToObj(datas, ImgBean.class);
			int x = 0,y = 0,w = 0,h = 0;
			if(img == null){
				return null;
			}
			x = (new   Double(Double.valueOf(img.getX()))).intValue();
			y = (new   Double(Double.valueOf(img.getY()))).intValue();
			x = x > 0 ? x : 0;
			y = y > 0 ? y : 0;
			w = (new   Double(Double.valueOf(img.getWidth()))).intValue();
			h = (new   Double(Double.valueOf(img.getHeight()))).intValue();
			FTPUtil ftpUtil = new FTPUtil();
			boolean flag = ftpUtil.connect();
			if(flag){
				//String filePath = Constant.TEMP_DIR;
				//InputStream in = null;
				//in = ftpUtil.getInputStream(filePath,fileName);
			    String suffix = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());  
			    suffix = suffix.toLowerCase();
			    suffix = StringUtil.replaceBlank(suffix);
				if(input != null){
					//图片裁剪
					Iterator iterator = ImageIO.getImageReadersByFormatName(suffix); 
		            ImageReader reader = (ImageReader)iterator.next(); 
		            ImageInputStream iis = ImageIO.createImageInputStream(input); 
		            reader.setInput(iis, true); 
		            ImageReadParam param = reader.getDefaultReadParam(); 
		            Rectangle rect = new Rectangle(x, y, w,h);  
		            param.setSourceRegion(rect); 
		            BufferedImage bi = reader.read(0,param);  
		            
		          //BufferedImage 转换成 InputStream
		    	    ins =  getInputStream(bi, suffix);

				}
			}
 			return ins;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取InputStream
	 * @param bi
	 * @return
	 */
	public static InputStream getInputStream(BufferedImage bi, String suffix){
		try {
			//BufferedImage 转换成 InputStream
			InputStream is = null;
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
		    ImageOutputStream imOut;
		    imOut = ImageIO.createImageOutputStream(bs);
		    ImageIO.write(bi, suffix,imOut);
		    is= new ByteArrayInputStream(bs.toByteArray());
		    InputStream ins =  is;
		    return ins;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
 