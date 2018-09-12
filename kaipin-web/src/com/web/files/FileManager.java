package com.web.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping("/files")
public class FileManager {

	@RequestMapping(value = "/upload",method={RequestMethod.GET, RequestMethod.POST})
	public void upload(@RequestParam("upfile") MultipartFile upfile,HttpServletRequest request, HttpServletResponse response) throws IOException {
		String result = "";
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
                        File localFile = new File("E://apache-tomcat-8.0.30(3)/webapps/SpringMVC3/images/uploadIamges/"+myFileName);
                        file.transferTo(localFile); 
                        result = "{\"name\":\""+ myFileName +"\", \"originalName\": \""+ file.getOriginalFilename() +"\", \"size\": "+ file.getSize() +", \"state\": \""+ "SUCCESS" +"\", \"type\": \""+ ".jpg" +"\", \"url\": \""+ myFileName +"\"}";
                        result = result.replaceAll( "\\\\", "\\\\" );
                    }  
                }  
            }  
            
        } 
        response.getWriter().println(result);
        //return result;
	}
}
