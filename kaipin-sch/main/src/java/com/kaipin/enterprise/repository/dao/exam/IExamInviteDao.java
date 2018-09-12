package com.kaipin.enterprise.repository.dao.exam;

import com.kaipin.enterprise.model.exam.ExamInvite;

public interface IExamInviteDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(ExamInvite record);

    ExamInvite selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExamInvite record);
}