package com.kaipin.oss.common.spring;
import org.apache.ibatis.plugin.Interceptor;  
import org.apache.log4j.Logger;  
import org.mybatis.spring.SqlSessionFactoryBean;
public class  PackagesSqlSessionFactoryBean extends SqlSessionFactoryBean{  
    
  private final static Logger log = Logger.getLogger(PackagesSqlSessionFactoryBean.class);  
    
  @Override  
  public void setTypeAliasesPackage(String typeAliasesPackage) {  
	  
	   super.setTypeAliasesPackage(typeAliasesPackage);
//      List<String> list = PackageUtils.getPackages(typeAliasesPackage);  
//      if(list!=null&&list.size()>0){  
//          super.setTypeAliasesPackage(Utils.join(list.toArray(), ","));  
//      }else{  
//          log.warn("参数typeAliasesPackage:"+typeAliasesPackage+"，未找到任何包");  
//      }  
  }  

  @Override  
  public void setPlugins(Interceptor[] plugins) {  
      // TODO Auto-generated method stub  
      super.setPlugins(plugins);  
  }  
}  