package com.kaipin.university.importdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kaipin.common.service.common.IBaseCodeService;
import com.kaipin.common.util.InvitationCodeUtil;
import com.kaipin.common.util.StringUtil;
import com.kaipin.student.model.user.StuUser;
import com.kaipin.student.service.user.IStuUserService;
import com.kaipin.university.JUnitBase;
import com.kaipin.university.model.user.UserLocalauth;
import com.kaipin.university.service.user.IUserLocalauthService;

/**
 * 用户数据导入
 * @author Mr-H
 *
 */
public class UserImportTest extends JUnitBase {
	
	@Autowired
	private IBaseCodeService service;
	@Autowired
	private IUserLocalauthService localUserService;
	@Autowired
	private IStuUserService stuService;
	
	@Test
	public void improtUser(){
		
//		improtUserEnt();//企业数据导入
		
//		improtUserStu();//学生数据导入
		//1、先导入数据
		//2、处理邮箱，把与企业邮箱相同的处理为null
		//3、处理手机，把与企业手机相同的直接删除掉
		//4、更新学生禁用状态 和 用户表状态相反
		//devale@163.com
		//1448014358@qq.com 1055121521@qq.com ?? devale@163.com 邮箱制空
		
		//修改学生姓名
		//加字段stu_user  surname miss_surname
//		updateStuName();
		
		//更新学生禁用状态 update user_localauth set `enable` = 1 where id LIKE 'stu%';

		

		
	}
	
	
	public void improtUserEnt(){
		String sql = "  select a.company_id, a.company_user_id , c.*"+ 
					"  	from "+
					"  		company_base_user a,"+
					"  		company_info b,"+
					"  		company_user_info c"+
					"  	where a.company_id = b.id"+
					"  		and a.company_user_id = c.id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sql);
		List<Map<String, Object>> list = service.getBaseList(map);
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			//	list.get(i).get("")+""
			UserLocalauth user = new UserLocalauth();
			user.setId(list.get(i).get("company_user_id")+"");
			user.setOrganizationId(list.get(i).get("company_id")+"");
			String phone = list.get(i).get("phone")+"";
			if(StringUtil.isNotEmpty(phone)){
				user.setPhone(phone);
			}
			
			user.setEmail(list.get(i).get("email")+"");
			String encode_password = list.get(i).get("encode_password")+"";
			if(StringUtil.isNotEmpty(encode_password)){
				user.setEncodePassword(encode_password);
			}
			user.setPassword(list.get(i).get("password")+"");
			user.setIsActivePhone(Byte.valueOf(list.get(i).get("is_check_phone")+""));
			user.setIsActiveEmail(Byte.valueOf(list.get(i).get("is_check_mail")+""));
			user.setCategoryId("11");
			user.setInvitationCode(InvitationCodeUtil.createRandCode());
			user.setCreateTime(Long.valueOf(list.get(i).get("create_time")+""));
			user.setIsDel(Byte.valueOf(list.get(i).get("is_del")+""));
			user.setEnable(Byte.valueOf(list.get(i).get("enable")+""));
			
			int flag =	localUserService.insertSelective(user);
			if(flag == 0){
				break;
			}
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println(">>>>>>>>>>>>>>> 总耗时" + (endTime - startTime));
	}
	
	
	
	public void improtUserStu(){
		String sql = "select t1.*, b.invitation_code,b.invitation_up_code from"+ 
					"	("+
					"		select * from stu_user a"+
					"	) t1"+
					" LEFT JOIN "+
					" stu_user_invitation b ON t1.id = b.stu_user_id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sql);
		List<Map<String, Object>> list = service.getBaseList(map);
		long startTime = System.currentTimeMillis();
		try {
			for (int i = 0; i < list.size(); i++) {
				//	list.get(i).get("")+""
				UserLocalauth user = new UserLocalauth();
				user.setId(list.get(i).get("id")+"");
				user.setOrganizationId(list.get(i).get("id")+"");
				String phone = list.get(i).get("phone")+"";
				if(StringUtil.isNotEmpty(phone)){
					user.setPhone(phone);
				}
//				user.setIsActivePhone(Byte.valueOf(list.get(i).get("is_check_phone")+""));
				user.setIsActivePhone(Byte.valueOf("1"));
				
				String email = list.get(i).get("email")+"";
				if(StringUtil.isNotEmpty(email)){
					user.setEmail(email);
				}
				
				String password = list.get(i).get("password")+"";
				if(StringUtil.isNotEmpty(password)){
					user.setPassword(list.get(i).get("password")+"");
				}
				
				String encode_password = list.get(i).get("encode_password")+"";
				if(StringUtil.isNotEmpty(encode_password)){
					user.setEncodePassword(encode_password);
				}
				
				
				
				user.setIsActiveEmail(Byte.valueOf(list.get(i).get("is_check_email")+""));
				user.setCategoryId("10");
				
				user.setInvitationCode(InvitationCodeUtil.createRandCode());
				String invitationParentCode = list.get(i).get("invitation_up_code")+"";
				if(StringUtil.isNotEmpty(invitationParentCode)){
					user.setInvitationParentCode(invitationParentCode);
				}
				
				
				user.setCreateTime(Long.valueOf(list.get(i).get("create_time")+""));
				user.setIsDel(Byte.valueOf(list.get(i).get("is_del")+""));
				user.setEnable(Byte.valueOf(list.get(i).get("enable")+""));
				
				int flag =	localUserService.insertSelective(user);
				if(flag == 0){
					break;
				}
			}
			
			long endTime = System.currentTimeMillis();
			System.out.println(">>>>>>>>>>>>>>> 总耗时" + (endTime - startTime));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateStuName(){
		String sql = "select * from stu_user";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sql);
		List<Map<String, Object>> list = service.getBaseList(map);
		for(int i = 0; i < list.size(); i++){
			String userName = list.get(i).get("nick_name") + "";
			if(StringUtil.isNotEmpty(userName)){
				StuUser user = new StuUser();
				user.setId(list.get(i).get("id") + "");
				if(userName.contains(",")){
					user.setSurname(userName.split(",")[0]);
					user.setMissSurname(userName.split(",")[1]);
				}else{
					user.setSurname(userName);
				}
				stuService.updateByPrimaryKeySelective(user);
			}
		}
	}
	
	
}
