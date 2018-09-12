package com.web.tag;

import java.util.Date;

import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

public class TagTest extends TagSupport {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public int doStartTag() throws JspException {    
    try {
      HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
      JspWriter out = pageContext.getOut();      
      
      if (parameter.compareToIgnoreCase("filename") == 0){
    	  out.print(request.getServletPath());
      }else if(parameter.compareToIgnoreCase("test") == 0){
    	  out.print("test");
      }
      else {
    	  out.print("asadasd3332");
      }
        
      
    } catch (java.io.IOException e) {
      throw new JspTagException(e.getMessage());
    }
    
    return SKIP_BODY;
  }
  
  private String parameter = "date";
  
  public void setParameter(String parameter) {
    this.parameter = parameter;
  } 
  
  public String getParameter() {
    return parameter;
  }
}