package com.common.MultipartResolver;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

 

import org.apache.commons.fileupload.ProgressListener;


 

public class MyProgressListener implements ProgressListener {

 

 private HttpSession session;

 

 public MyProgressListener(HttpServletRequest request) {

        this.session=request.getSession();  

        Progress status = new Progress();

        session.setAttribute("picStatus", status);  

 }



@Override
public void update(long pBytesRead, long pContentLength, int pItems) {

	  Progress status = (Progress) session.getAttribute("picStatus");

	  status.setBytesRead(pBytesRead);

	  status.setContentLength(pContentLength);

	  status.setItems(pItems);

	  session.setAttribute("picStatus",status);

	  session.setAttribute("percent",status.getPercent());

	 }
}

