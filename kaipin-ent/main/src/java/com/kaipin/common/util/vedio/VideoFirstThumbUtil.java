package com.kaipin.common.util.vedio;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 得到第一秒（也是第一帧）图片
 * @author Mr-H
 *
 */
public class VideoFirstThumbUtil extends VideoThumb
{
    public VideoFirstThumbUtil(String ffmpegApp)
    {
        super(ffmpegApp);
    }

    public boolean getThumb(String videoFilename, String thumbFilename, int width,
            int height) 
    {
    	try {
    		super.getThumb(videoFilename, thumbFilename, width, height, 0, 0, 1);
    		return true;
		} catch (Exception e) {
			System.out.println(">>>> 获取视频第一帧图片失败");
			return false;
		}
        
    }
    
    public static void main(String[] args) {
    	VideoFirstThumbUtil videoThumbTaker = new VideoFirstThumbUtil("G:\\c\\1\\ffmpeg.exe");
         try
         {
//             boolean s = videoThumbTaker.getThumb("G:\\c\\1\\mp42.mp4", "G:\\c\\1\\thumbTestFirst.png",    800, 600);
//             System.out.println("over");
//             
             File file = new File("http://kaipin.com:8083:82/655/vedio/15e964aa-c491-4c86-af52-81164a09291f.mp4");
             if(file.exists()){
            	 System.out.println(">>>>>>>>>>>文件存在");
             }else{
            	 System.out.println(">>>>>>>>>>>文件  bu 存在");
             }
             boolean s = isNetFileAvailable("http://kaipin.com:8083:82/655/vedio/15e964aa-c491-4c86-af52-81164a09291f.mp4");
             
             if(s){
            	 System.out.println(">>>>>>>>>>> 11 文件存在");
             }else{
            	 System.out.println(">>>>>>>>>>>11 文件  bu 存在");
             }
             
         } catch (Exception e)
         {
             e.printStackTrace();
         }
	}
    
    
    /**
    * 检测网络资源是否存在　
    * 
    * @param strUrl
    * @return
    */
    public static boolean isNetFileAvailable(String strUrl) {
    InputStream netFileInputStream = null;
    try {
    URL url = new URL(strUrl);
    URLConnection urlConn = url.openConnection();
    netFileInputStream = urlConn.getInputStream();
    if (null != netFileInputStream) {
    return true;
    } else {
    return false;
    }
    } catch (IOException e) {
    return false;
    } finally {
    try {
    if (netFileInputStream != null)
    netFileInputStream.close();
    } catch (IOException e) {
    }
    }
    }
}

