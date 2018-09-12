package com.enterprise.service.meeting.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.enterprise.mapper.meeting.EntMeetingNoticeMapper;
import com.enterprise.model.EntMeetingNotice;
import com.enterprise.service.meeting.IEntMeetingNoticeService;

@Service("entMeetingNoticeService")
@Repository
public class EntMeetingNoticeServiceImpl implements IEntMeetingNoticeService{
	
	@Autowired
	private EntMeetingNoticeMapper mapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			mapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int insertSelective(EntMeetingNotice record) {
		try {
			mapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public EntMeetingNotice selectByPrimaryKey(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EntMeetingNotice record) {
		try {
			mapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<EntMeetingNotice> getList(Map<String, Object> map) {
		return mapper.getList(map);
	}

	@Override
	public Map<String, Object> getMap(Map<String, Object> map) {
		return mapper.getMap(map);
	}

	@Override
	public List<Map<String, Object>> getListCounts(Map<String, Object> map) {
		return mapper.getListCounts(map);
	}

}
