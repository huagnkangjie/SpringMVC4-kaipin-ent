package com.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.common.constants.Constant;
import com.util.PropUtil;
import com.web.annex.AnnexCache;



public class FTPUtil {

	public FTPClient ftp;
	
	/**
	 * 
	 * @param path
	 *            上传到ftp服务器哪个路径下
	 * @param addr
	 *            地址
	 * @param port
	 *            端口号
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws Exception
	 */
	public boolean connect(String path, String addr, int port, String username,
			String password) throws Exception {
		boolean result = false;
		ftp = new FTPClient();
		int reply;
		ftp.connect(addr, port);
		ftp.login(username, password);
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			return result;
		}
		ftp.changeWorkingDirectory(path);
		result = true;
		return result;
	}
	
	/**
	 * 不带参数的构造方法
	 * @return
	 * @throws Exception
	 */
	public boolean connect() throws Exception {
		PropUtil pro = new PropUtil(Constant.PRO_FILE_PATH);
		boolean result = false;
		ftp = new FTPClient();
		int reply;
		ftp.connect(pro.getValue(Constant.PRO_FTP_IP), Integer.valueOf(pro.getValue(Constant.PRO_FTP_PORT)));
		ftp.login(pro.getValue(Constant.PRO_FTP_USRNAME), 
				pro.getValue(Constant.PRO_FTP_PASSWORD));
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		ftp.setDefaultTimeout(1000*60);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			return result;
		}
		ftp.changeWorkingDirectory(pro.getValue(Constant.PRO_FTP_FILE_PATH));
		result = true;
		return result;
	}
	
	/**
	 * 上传图片
	 * @param fileDir
	 * @param fileType
	 * @param fileName
	 * @param input
	 */
	public boolean uploadImg(String fileDir, String fileType,String type, String fileName, InputStream input) {
		try {
			ftp.makeDirectory(type);//切换到企业 学校  学生
			ftp.changeWorkingDirectory(type);
			ftp.makeDirectory(fileDir);//切换到企业的账号下去
			ftp.changeWorkingDirectory(fileDir);
			ftp.makeDirectory(fileType);//切换到图片还是视频目录下
			ftp.changeWorkingDirectory(fileType);
			boolean flag = ftp.storeFile(fileName, input);
			ftp.logout();
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		
	}
	
	
	/**
	 * 
	 * @param path c/d/e/f
	 * @param fileName
	 * @param input
	 * @return
	 */
	public boolean upload(String path, String fileName, InputStream input) {
		try {
			String pathArray[] =  path.split("/");
			for (String paths : pathArray) {
				ftp.makeDirectory(paths);
				ftp.changeWorkingDirectory(paths);
			}
			boolean flag = ftp.storeFile(fileName, input);
			ftp.logout();
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		
	}


	/**
	 * FTP上传单个文件
	 * @param request 
	 * @param response
	 * @param fileSize	文件大小
	 * @param fileDir	文件需要上到的的目录
	 * @param fileType	文件类型 视频或者图片
	 * @param fileName	文件名称
	 * @param input	输入流
	 */
	public void uploadVedio(HttpServletRequest request,HttpServletResponse response, 
			long fileSize,String fileDir, String fileType,
			String fileName, InputStream input) {
		try {
			String sessionId = request.getSession().getId();
			ftp.makeDirectory(fileDir);//切换到企业的账号下去
			ftp.changeWorkingDirectory(fileDir);
			ftp.makeDirectory(fileType);//切换到图片还是视频目录下
			ftp.changeWorkingDirectory(fileType);
//			ftp.makeDirectory("test");//切换到图片还是视频目录下
//			ftp.changeWorkingDirectory("test");
//			ftp.storeFile(fileName, input);
//			ftp.logout();
			
	        long process = 0;   
	        long localreadbytes = 0L; 
	        long processBytes = 0L;
	        
			OutputStream out = ftp.appendFileStream(new String(fileName.getBytes("GBK"),"iso-8859-1"));  
			byte[] bytes = new byte[1024];   
	        int c = -1;
	        long writeTime = 0;//写文件次数
	        long writeFlag = 1024;
	        // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位 
            numberFormat.setMaximumFractionDigits(2);
            HashMap<String,Object> map = new HashMap<String, Object>();
            String percent;
            String hasLoad;
	        while((c = input.read(bytes))!= -1){  
	        	//写入磁盘
	            out.write(bytes,0,c);  
	            processBytes = processBytes + 1024; 
	            percent = numberFormat.format((float) processBytes / (float) fileSize * 100);
	            //System.out.println(percent + " %");
	            hasLoad = numberFormat.format((float) processBytes /(1024*1024));
	            writeTime++;
	            if( writeTime == writeFlag){
	            	writeTime = 0;
	            	map.clear();
	            	map.put(Constant.PERCENT, percent);
	            	map.put(Constant.HASLOAD, hasLoad);
	            	//获取缓存当前进度
	            	AnnexCache.addCache(sessionId, map);
	            	
	 	            response.getWriter().print("1");
	 	            response.getWriter().flush();
	 	            
	            }
	            
	           // response.getWriter().print(result + "%");
	           
	            if(localreadbytes / fileSize != process){
//	            	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>视频上传完成");
//	            	AnnexCache.removeCacheByKey(sessionId);
	            }  
	        }  
        	AnnexCache.removeCacheByKey(sessionId);
	        out.flush();   
	        input.close();   
	        out.close();
	        ftp.logout();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * FTP 文件下载
	 * 
	 * @param response
	 * @param request
	 * @param name
	 *            所需下载文件的名字
	 * @throws Exception
	 */
	public void testDownload(HttpServletResponse response,
			HttpServletRequest request, String name) throws Exception {
		OutputStream os = response.getOutputStream();
		;
		ftp.setControlEncoding("GBK");
		FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
		conf.setServerLanguageCode("zh");
		int reply = ftp.getReplyCode();
		boolean b = FTPReply.isPositiveCompletion(reply);
		if (!b) {
			throw new Exception("连接服务器失败");
		}
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		// 此句代码尤为重要
		response.setHeader("Content-disposition", "attachment;filename="
				+ URLEncoder.encode(name, "utf-8"));
		ftp.retrieveFile(new String(name.getBytes("GBK"), "ISO-8859-1"), os);
		os.flush();
		os.close();
		ftp.logout();

	}

	/**
	 * FTP下载
	 * 
	 * @param ip
	 * @param port
	 * @param username
	 * @param password
	 * @param remotePath
	 * @param fileName
	 * @param outputStream
	 * @param response
	 */
	public void downFile(String ip, int port, String username, String password,
			String remotePath, String fileName,String fileRealName, OutputStream outputStream,
			HttpServletResponse response) {
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(ip, port);
			// 下面三行代码必须要，而且不能改变编码格式
			ftp.setControlEncoding("GBK");
			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
			conf.setServerLanguageCode("zh");
			// 如果采用默认端口，可以使用ftp.connect(url) 的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return;
			}
			//System.out.println("登陆成功。。。。");
			ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles(); // 得到目录的相应文件列表
			// System.out.println(fs.length);//打印列表长度
			for (int i = 0; i < fs.length; i++) {
				FTPFile ff = fs[i];
				if (ff.getName().equals(fileRealName)) {
					// 这个就就是弹出下载对话框的关键代码
					response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
					// 将文件保存到输出流outputStream中
					ftp.retrieveFile(new String(ff.getName().getBytes("GBK"),"ISO-8859-1"), outputStream);
					outputStream.flush();
					outputStream.close();
				}
			}
			ftp.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
	}
	/**
	 * 
	 * @param path
	 * @param addr ip
	 * @param port 端口
	 * @param username 用户名
	 * @param password 密码
	 * @param remoteFileName ftp上的文件名字
	 * @param remoteDownLoadPath ftp真实路径
	 * @return
	 */
    public InputStream loadFile(String path, String addr, int port, String username,
			String password,String remoteFileName, String remoteDownLoadPath) {
        BufferedOutputStream buffOut = null;
        try {
        	ftp = new FTPClient();
    		int reply;
    		ftp.connect(addr, port);
    		ftp.login(username, password);
    		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
    		reply = ftp.getReplyCode();
    		if (!FTPReply.isPositiveCompletion(reply)) {
    			ftp.disconnect();
    		}
        	//进入需下载文件工作目录
    	    boolean flag = ftp.changeWorkingDirectory(remoteDownLoadPath);
    	    if(flag){
    	    	return ftp.retrieveFileStream(remoteFileName);
    	    }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffOut != null){
                	buffOut.close();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return null;
    }

	/**
	 * 上传图片	压缩图片裁剪回显
	 * @param fileDir
	 * @param fileType
	 * @param fileName
	 * @param input
	 */
	public void uploadImgCut(String fileName, InputStream input, String type) {
		try {
			ftp.makeDirectory(Constant.TEMP_DIR);//切换到图片临时文件夹
			ftp.changeWorkingDirectory(Constant.TEMP_DIR);
			if(type.equals("zoom")){
				ftp.makeDirectory(Constant.TEMP_ZOOM_DIR);//切换到图片压缩文件夹
				ftp.changeWorkingDirectory(Constant.TEMP_ZOOM_DIR);
			}
			boolean f = ftp.storeFile(fileName, input);
			ftp.logout();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * 删除图片  用于裁剪上传的临时图片
	 * @param path c/d/e/f
	 * @param fileName
	 * @return
	 */
	public boolean deleteFile(String path, String fileName){
		boolean flag = false;
		try {
			if(StringUtil.isEmpty(path)){
				return false;
			}
			String pathArray[] = path.split("/");
			for (String paths : pathArray) {
				ftp.changeWorkingDirectory(paths);
			}
			flag = ftp.deleteFile(fileName);
			ftp.logout();
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
					return flag;
				}
			}
		}
	}
	
	/**
	 * 删除指定文件夹下的指定文件
	 * @param dest
	 * @param fileName
	 * @return
	 */
	public boolean delete(String dest, String fileName){
		boolean flag = false;
		try {
			ftp.changeWorkingDirectory(dest);
			flag = ftp.deleteFile(fileName);
			ftp.logout();
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			return flag;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
					return flag;
				}
			}
		}
	}
	
	
	/**
	 * 获取file对象
	 * @param fileName
	 * @param path   c/d/e/f
	 * @return
	 */
	public InputStream getInputStream (String path, String fileName){
		try {
			InputStream  ins = null;
			if(StringUtil.isNotEmpty(path)){
				String pathArray[] =  path.split("/");
				for (String paths : pathArray) {
					ftp.makeDirectory(paths);
					ftp.changeWorkingDirectory(paths);
				}
				ins = ftp.retrieveFileStream(fileName);
				//ftp.logout();
				return ins;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

}
