package com.service.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.common.constants.Constant;
import com.mapper.company.CompanyInfoMapper;
import com.mapper.company.CompanyUserInfoMapper;
import com.mapper.user.UserMapper;
import com.model.company.CompanyInfo;
import com.model.company.CompanyUserInfo;
import com.model.company.EntBaseUser;
import com.service.user.IUserService;
import com.util.TimeUtil;

@Service("userService")
@Repository
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserMapper mapper;
	@Autowired
	private CompanyInfoMapper infoMapper;
	@Autowired
	private CompanyUserInfoMapper userMapper;
	
	
	@Override
	public int insertSelective(EntBaseUser record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public int createEntUser(CompanyUserInfo userInfo, CompanyInfo info) {
		try {
			long createTime = TimeUtil.currentTimeMillis();
			//关联表
			EntBaseUser baseUser = new EntBaseUser();
			baseUser.setCompanyId(info.getId());
			baseUser.setCompanyUserId(userInfo.getId());
			baseUser.setCreateTime(createTime);
			baseUser.setIsSystem(Byte.valueOf(Constant.VALUE_ONE));//是否管理员
			int flag1 = mapper.insertSelective(baseUser);
			
			if(flag1 == 1){
				//user表
				userInfo.setEnable(Byte.valueOf(Constant.VALUE_ONE));//是否禁用
				userInfo.setCreateTime(createTime);
				userInfo.setIsCheckPhone(Byte.valueOf(Constant.VALUE_ONE));
				int falg2 = userMapper.insertSelective(userInfo);
				
				//基本信息表
				if(falg2 == 1){
					info.setCreateTime(createTime);
					infoMapper.insertSelective(info);
				}
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public List<Map<String, Object>> emailValidata(Map<String, Object> map) {
		return mapper.emailValidata(map);
	}


	@Override
	public List<Map<String, Object>> login(Map<String, Object> map) {
		return mapper.login(map);
	}


	@Override
	public List<Map<String, Object>> cheackmail(Map<String, Object> map) {
		return mapper.cheackmail(map);
	}




	@Override
	public List<Map<String, Object>> validataPhone(Map<String, Object> map) {
		return mapper.validataPhone(map);
	}


	@Override
	public List<Map<String, Object>> getUserAndEntInfo(Map<String, Object> map) {
		return mapper.getUserAndEntInfo(map);
	}


	@Override
	public List<Map<String, Object>> againMail(Map<String, Object> map) {
		return mapper.againMail(map);
	}


	@Override
	public List<Map<String, Object>> getFeedbackList(Map<String, Object> map) {
		return mapper.getFeedbackList(map);
	}


	@Override
	public List<Map<String, Object>> validataInviteCode(Map<String, Object> map) {
		return mapper.validataInviteCode(map);
	}

}

