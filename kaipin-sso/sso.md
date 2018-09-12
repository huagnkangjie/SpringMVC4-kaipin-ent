
#用户类型

	[10,11,12] 

	10 web学生
	11 web企业
	12 web学校
 


#Cookies

	token=  
	uid=	用户id
	u_type= 用户类型
	group_id=用户组织id

	token生成 des加密

	DES(
	{
	redirect_uri:"跳转url",
	ip:"登陆Ip",
	random:"session随机数",
	type:"用户类型",
	uid:"用户id",
	group_id:"用户组织id"
	}#随机数

	)+私钥
	
#Token数据结构
		{
	redirect_uri:"跳转url",
	ip:"登陆Ip",
	random:"session随机数",
	type:"用户类型",
	uid:"用户id",
	group_id:"用户组织id"
	}	

#用户登陆 
	${url}/web/auth/login

	输入参数,提交类型 get/post,类型 text/html

	username 登陆用户，手机/邮箱
	password 密码

	输出参数 类型 text/json
	

	code编码，
	0 	成功
	-1 	登陆错误，
	10 	用户被禁止
	100	用户没角色

	


	失败
	{
	code:""，
	message:""
	}

	成功
	 {
	   	 code: "0",
	   	 message: "登陆成功",
	   	 redirect_uri: "http://s.kaipin.tv",
	   	 status: 0, 登陆成功后 记录用户状态 扩展用
	   	 uid: "9543282f-cde3-4331-9aa3-1e5dd8fb81a5"
	   	 goup_id:"用户所属组织id"
		}





#用户登出

	${url}/web/auth/logout


	功能描述：
	
	清理用户登陆信息->跳转到用户指定 redirect_uri
	redirect_uri 如果为空 将跳转到系统默认地址												     
													
 
	输入参数,提交类型 get,类型 text/html
	
	redirect_uri  退出后跳转的地址

	输出参数
	 无



#用户检查

	 ${url}/web/login_check
	
		输入参数 无 ,提交类型 get
	
	
		输出参数 类型 text/json
	
		code
		150	token信息未找到
		

		{
			code:""，
			message:""
	
			data:{
		
			见#Token数据结构
		
			}
		}
			


