package com.web.annex;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.common.constants.Constant;
import com.common.pojo.Json;
import com.util.FTPUtil;
import com.util.ImageCutUtil;
import com.util.LogUtil;
import com.util.PropUtil;
import com.util.StringUtil;
import com.util.UuidUtil;


/**
 * 文件操作类
 * @author Mr-H
 *
 */
@Controller
@RequestMapping("/annex")
public class AnnexController {

	Logger log = Logger.getLogger(AnnexController.class.getName());
	
	/**
	 * 上传图片
	 * @param request
	 * @param response
	 * @param key
	 * @param key2
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/uploadImg")  
    public void uploadImg(HttpServletRequest request,HttpServletResponse response,
    		String key,String key2,String orgId, String type) throws IllegalStateException, IOException {  
        //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        //重命名上传后的文件名  
                    	PropUtil prop = new PropUtil();
                    	String filePath = prop.getValue("filePath");
                    	String ip = prop.getValue("ip");
                    	//用户文件存放目录
                    	String rule = orgId + "/images";//定义的规则，存放在http:// ip / 规则  / 文件名
                    	String fileDir = filePath + rule;
                    	File targetFileDir = new File(fileDir);
            			if (!targetFileDir.exists()) {
            				targetFileDir.mkdirs();
            			}
                        String fileName = file.getOriginalFilename();  
                        //获取后后缀名
                        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length()); 
                        String uuid = UuidUtil.getUUID();
                        //定义上传路径  
                        String path = targetFileDir+ "/" + uuid + suffix;  
                        FTPUtil util = new FTPUtil();
                        //File localFile = new File(path);
                        boolean flag;
						try {
							flag = util.connect();
							if(flag){
								InputStream input =  file.getInputStream();
	                			util.uploadImg(orgId, //企业id
	                					Constant.IMAGES,//切换到images目录
	                					type,
	                					uuid + suffix, //文件名字
	                					input);
	                		}
						} catch (Exception e) {
							e.printStackTrace();
							log.error(e);
						}
                		
                        //spring 文件上传
                        //file.transferTo(localFile); 
                        String http = "http://" + ip + "/" + type + "/" + orgId + "/images" + "/" + uuid + suffix;
                        response.getWriter().println(http +","+uuid);
                    }  
                }  
            }  
              
        }  
    }  
	
	/**
	 * 上传视频
	 * @param request
	 * @param response
	 * @param key
	 * @param key2
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/uploadVideo"  )  
	public void uploadVideo(HttpServletRequest request,HttpServletResponse response,
			String key,String key2, String orgId) throws IllegalStateException, IOException { 
		response.setDateHeader("expries", 1);  
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("pragma", "no-cache");  
		 //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        //重命名上传后的文件名  
                    	 PropUtil prop = new PropUtil();
                    	 String filePath = prop.getValue("filePath");
                    	 String ip = prop.getValue("ip");
                    	//用户文件存放目录
                    	String rule = orgId + "/" + Constant.VEDIO;//定义的规则，存放在http:// ip / 规则  / 文件名
                    	String fileDir = filePath + rule;
                    	File targetFileDir = new File(fileDir);
            			if (!targetFileDir.exists()) {
            				targetFileDir.mkdirs();
            			}
                        String fileName = file.getOriginalFilename();  
                        //获取后后缀名
                        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length()); 
                        String uuid = UuidUtil.getUUID();
                        //定义上传路径  
                        String path = targetFileDir+ "/" + uuid + suffix;  
                        FTPUtil util = new FTPUtil();
                        //File localFile = new File(path);
                        FileInputStream fin = null;
                        InputStream input =  file.getInputStream();
						try {
							if (input instanceof FileInputStream) {
							    fin = (FileInputStream) input;
							} else { // 否则，转型失败
							}
						} catch (Exception e) {
						}
                        boolean flag;
						try {
							flag = util.connect();
							if(flag){
								
	                			util.uploadVedio(
	                					request,
	                					response,
	                					file.getSize(),
	                					orgId, //企业id
	                					Constant.VEDIO,//切换到images目录
	                					uuid + suffix, //文件名字
	                					input);
	                		}
						} catch (Exception e) {
							e.printStackTrace();
							log.error(e);
						}
                		
                        //spring 文件上传
                        //file.transferTo(localFile); 
                        //返回前台
//                        CommonAnnex annex =  new CommonAnnex();
//                        annex.setId(uuid);
//                        annex.setUrl("/" + rule + "/" + uuid + suffix);
//                        annex.setAnnexName(fileName);
//                        annex.setSuffix(suffix);
//                        annex.setIp(ip);
//                        annex.setCreateTime(TimeUtil.getDate());
//                        annexService.insertSelective(annex);
                        String http = ",http://" + ip + "/" + orgId + "/"+Constant.VEDIO + "/" + uuid + suffix;
                        response.getWriter().println(http +","+fileName);
                    }  
                }  
            }  
        }
	}
	
	/**
	 * 字节流读取
	 * @param files
	 * @param request
	 */
	@RequestMapping("/upload")  
    public void addUser(@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest request){  
          
        for(int i = 0;i<files.length;i++){  
            System.out.println("fileName---------->" + files[i].getOriginalFilename());  
          
            if(!files[i].isEmpty()){  
                int pre = (int) System.currentTimeMillis();  
                try {  
                    //拿到输出流，同时重命名上传的文件  
                    FileOutputStream os = new FileOutputStream("D:/" + new Date().getTime() + files[i].getOriginalFilename());  
                    //拿到上传文件的输入流  
                    FileInputStream in = (FileInputStream) files[i].getInputStream();  
                      
                    //以写字节的方式写文件  
                    int b = 0; 
                    long bytes = 0;
                    while((b=in.read()) != -1){
                    	bytes = bytes + b;
                        os.write(b); 
                        System.out.println(">>>>>>>>>>>>>>"+bytes);
                    }  
                    os.flush();  
                    os.close();  
                    in.close();  
                    int finaltime = (int) System.currentTimeMillis();  
                    System.out.println(finaltime - pre);  
                      
                } catch (Exception e) {  
                    e.printStackTrace();  
                    System.out.println("上传出错");  
                    log.info(LogUtil.getTrace(e));;
                }  
        }  
        }  
    }  
	
	
	/**
	 * 上传图片  提供裁剪的压缩图片
	 * @param request
	 * @param response
	 * @param key
	 * @param key2
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/uploadImgCut")  
    public void uploadImgCut(HttpServletRequest request,HttpServletResponse response,
    		String key,String key2,String entId) throws IllegalStateException, IOException {  
        //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        String fileName = file.getOriginalFilename();  
                        //获取后后缀名
                        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length()); 
                        String uuid = UuidUtil.getUUID();
                        FTPUtil util = new FTPUtil();//获取ftp链接
                        boolean flag;
						try {
							//第一步	ftp文件上传
							flag = util.connect();
							if(flag){
								String fileNewName = uuid + suffix;//构建新的文件名
								InputStream input =  file.getInputStream();
								//本地转换jpg
								InputStream in = changeLocalFileJPG(request, input, uuid, suffix);
								//ftp上传
	                			util.uploadImgCut(
	                					fileNewName, //文件名字
	                					in,"img_load");
	                			//第二步	图片压缩
								//ImageCutUtil.imgZoom(file.getInputStream(),fileNewName);
	                			in.close();
	                			
	                		}
							
						} catch (Exception e) {
							e.printStackTrace();
							log.info(LogUtil.getTrace(e));
						}

						PropUtil prop = new PropUtil();
						String ip = prop.getValue("ip");
						String url = "/" + Constant.TEMP_DIR + "/";
						//String url = "/" + Constant.TEMP_DIR + "/" + Constant.TEMP_ZOOM_DIR + "/";
                        String http = "http://" + ip + url + uuid + suffix;
                        response.getWriter().println(http +","+uuid+ suffix);
                    }  
                }  
            }  
              
        }  
    } 
	
	/**
	 * 图片裁剪
	 * @param fileName
	 * @param dest
	 * @return
	 */
	@RequestMapping("/imgCut")  
	@ResponseBody
	public Json imgCut(HttpServletRequest request, String fileName,
			String datas,String orgId, String cutType){
		Json json = new Json();
		try {
			if(StringUtil.isNotEmpty(fileName)){
				fileName = StringUtil.replaceBlank(fileName);
			}
			//图片裁剪
			InputStream input = getLocalInputStream(request, fileName);
			InputStream ins = ImageCutUtil.imgCut(datas, fileName, input);
			input.close();
			if(ins != null){
				String path = "";
				path = orgId + "/" +Constant.IMAGES + "/" + cutType + "/0";//图片存放路径
				FTPUtil util = new FTPUtil();//获取ftp链接
				boolean flag;
				boolean fUpload = false;
				try {
					flag = util.connect();
					if(flag){
						fUpload = util.upload(
									path,//切换到  企业/images/info
	            					fileName, //文件名字
	            					ins);
						if(fUpload){
							//保存临时文件夹的 原图
							String  origPath = path.substring(0,path.lastIndexOf("/")+1);
							saveTempPic(Constant.TEMP_DIR, origPath,fileName);
							//删除临时文件夹的 原图
							deleteTempPic(Constant.TEMP_DIR, fileName);
							deleteLocalTempPic(request, fileName);
							//删除压缩图片
							//deleteTempPic(Constant.TEMP_DIR + "/" + Constant.TEMP_ZOOM_DIR, fileName);
						}
						PropUtil prop = new PropUtil();
						String ip = prop.getValue("ip");
						json.setObj("http://" + ip + "/" + path + "/" + fileName );
						json.setSuccess(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.info(LogUtil.getTrace(e));;
				}
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return json;
		}
	}
	/**
	 * 删除文件
	 * @param fileName 文件名
	 * @param dest 文件夹
	 * @return
	 */
	@RequestMapping("/deleteFile"  )  
	@ResponseBody
	public Json deleteFile(String fileName, String dest){
		Json json = new Json();
		try {
			FTPUtil util = new FTPUtil();//获取ftp链接
			boolean flag;
			boolean deleteFlag =false;
			try {
				flag = util.connect();
				if(flag){
					deleteFlag = util.deleteFile("temp",fileName);
					if(deleteFlag){
						json.setSuccess(true);
					}
	     		}
			} catch (Exception e) {
				e.printStackTrace();
				log.info(LogUtil.getTrace(e));;
			}
			return json;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return json;
		}
	}
	
	/**
	 * 删除临时图片
	 * @param path
	 * @param fileName
	 */
	public void deleteTempPic(String path, String fileName){
		try {
			FTPUtil util = new FTPUtil();//获取ftp链接
			boolean flag;
			try {
				flag = util.connect();
				if(flag){
					flag = util.deleteFile(path,fileName);
					System.out.println(">>>>>>>> 删除服务器临时图片 " + flag);
	     		}
			} catch (Exception e) {
				e.printStackTrace();
				log.info(LogUtil.getTrace(e));;
			}
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存临时图片
	 * @param path
	 * @param fileName
	 */
	public InputStream saveTempPic(String path, String origPath, String fileName){
		try {
			InputStream ins = null;
			FTPUtil util = new FTPUtil();//获取ftp链接
			boolean flag;
			try {
				flag = util.connect();
				if(flag){//获取源文件inputstream
					ins = util.getInputStream(Constant.TEMP_DIR, fileName);
	     		}
				if(ins != null){//上传文件
					boolean s = uploadFile(origPath, fileName, ins);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info(LogUtil.getTrace(e));;
			}
			return ins;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 上传文件
	 * @param path
	 * @param fileName
	 * @param ins
	 * @return
	 */
	public boolean uploadFile(String path, String fileName, InputStream ins){
		boolean saveFlag = false;
		try {
			FTPUtil util = new FTPUtil();//获取ftp链接
			boolean flag;
			try {
				flag = util.connect();
				if(flag){
					saveFlag = util.upload(path, fileName, ins);
	     		}
			} catch (Exception e) {
				e.printStackTrace();
				log.info(LogUtil.getTrace(e));;
			}
			return saveFlag;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 
	 * @param input 文件输入流
	 * @param fileNewName 文件名
	 */
	public InputStream changeLocalFileJPG(HttpServletRequest request, 
			InputStream input, String fileNewName, String suffix){
		InputStream in = null;
		try {
			String path = request.getSession().getServletContext().getRealPath("/");
			path += Constant.TEMP_DIR;
			File fileDir = new File(path);
			if(!fileDir.exists()){
				fileDir.mkdir();
			}
            BufferedImage src = ImageIO.read(input); 
            String destFile = path  + "/" + fileNewName + suffix;
            boolean flag = ImageIO.write(src, suffix.substring(1), new File(destFile));
            if(flag){
            	in = new FileInputStream(destFile);
            }
            return in;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return in;
		}
	}
	
	/**
	 * 获取本地inputstream
	 * @param request
	 * @param fileName
	 * @return
	 */
	public InputStream getLocalInputStream(HttpServletRequest request, String fileName){
		InputStream in = null;
		try {
			String path = request.getSession().getServletContext().getRealPath("/");
			path += Constant.TEMP_DIR;
			String destFile = path  + "/" + fileName;
			in = new FileInputStream(destFile);
			return in;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return in;
		}
	}
	
	/**
	 * 删除本地临时文件
	 * @param request
	 * @param fileName
	 * @return
	 */
	public boolean deleteLocalTempPic(HttpServletRequest request, String fileName){
		try {
			String path = request.getSession().getServletContext().getRealPath("/");
			path += Constant.TEMP_DIR;
			String destFile = path  + "/" + fileName;
			File file = new File(destFile);
			boolean flag = false;
			if(file.exists()){
				flag = file.delete();
				System.out.println(">>>>>>>> 删除本地临时图片 " + flag);
			}
			return flag;
		} catch (Exception e) {
			log.info(LogUtil.getTrace(e));;
			e.printStackTrace();
			return false;
		}
		
	}
}
