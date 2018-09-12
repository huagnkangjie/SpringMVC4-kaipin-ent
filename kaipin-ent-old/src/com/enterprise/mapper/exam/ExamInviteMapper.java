package com.enterprise.mapper.exam;

import com.enterprise.model.exam.ExamInvite;

public interface ExamInviteMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExamInvite record);

    int insertSelective(ExamInvite record);

    ExamInvite selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExamInvite record);

    int updateByPrimaryKey(ExamInvite record);
}