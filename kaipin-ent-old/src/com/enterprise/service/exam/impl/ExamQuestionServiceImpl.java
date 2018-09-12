package com.enterprise.service.exam.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.common.Constant;
import com.enterprise.mapper.exam.ExamMapper;
import com.enterprise.mapper.exam.ExamPaperMapper;
import com.enterprise.mapper.exam.ExamPaperPositionMapper;
import com.enterprise.mapper.exam.ExamQuestionDbMapper;
import com.enterprise.mapper.exam.ExamQuestionMapper;
import com.enterprise.mapper.position.PositionInfoMapper;
import com.enterprise.model.PositionInfo;
import com.enterprise.model.exam.ExamBean;
import com.enterprise.model.exam.ExamPaper;
import com.enterprise.model.exam.ExamPaperPosition;
import com.enterprise.model.exam.ExamQuestion;
import com.enterprise.model.exam.ExamQuestionDb;
import com.enterprise.pojo.ExamListBean;
import com.enterprise.service.exam.IExamQuestionService;
import com.util.StringUtil;
import com.util.TimeUtil;
import com.util.UuidUtil;

@Service("examQuestionService")
@Repository
public class ExamQuestionServiceImpl implements IExamQuestionService{
	
	@Autowired
	private ExamQuestionMapper questionMapper;//题目表
	@Autowired
	private ExamQuestionDbMapper dbMapper;//题库
	@Autowired
	private ExamPaperMapper paperMapper;//试卷
	@Autowired
	private ExamMapper mapper;//考试接口库
	@Autowired
	private ExamPaperPositionMapper paperPositionMapper;//卷子和职位绑定表
	@Autowired
	private PositionInfoMapper positionMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		try {
			questionMapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int insertSelective(ExamQuestion record) {
		try {
			questionMapper.insertSelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ExamQuestion selectByPrimaryKey(String id) {
		return questionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ExamQuestion record) {
		try {
			questionMapper.updateByPrimaryKeySelective(record);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int insertExamList(ExamListBean examList, String companyId, String positionId) {
		try {
			/**
			 * 1、创建题库 
			 * 2、创建试卷 
			 * 3、试卷 题目关联 
			 * 4、新增所有的题目 
			 * 5、职位和试卷关联
			 */
			String dbId = UuidUtil.getUUID();//题库id
			String paperId = UuidUtil.getUUID();//试卷id
			
			boolean DbFlag = createDb(dbId, companyId, positionId);
			boolean PaperFlag = createPaper(paperId, companyId, dbId);
			if(DbFlag){//创建题库
				if(PaperFlag){//创建试卷
					//创建 职位、企业、试卷关联关系
					ExamPaperPosition paperPosition = new ExamPaperPosition();
					paperPosition.setId(UuidUtil.getUUID());
					paperPosition.setCompanyId(companyId);
					paperPosition.setPositionId(positionId);
					paperPosition.setPaperId(paperId);
					int flag = paperPositionMapper.insertSelective(paperPosition);
					if(flag == 1){
						int i = 1;
						//创建所有的试题
						for (ExamBean bean : examList.getExamList()) {
							if(bean != null){
								//创建问题
								String examQuestionId = UuidUtil.getUUID();
								ExamQuestion examQuestion = new ExamQuestion();
								examQuestion.setId(examQuestionId);
								examQuestion.setQuestionDbId(dbId);
								examQuestion.setQuestionContent(bean.getQuestion());;
								examQuestion.setQuestionType(Constant.VALUE_ONE);
								examQuestion.setStatus(Byte.valueOf(Constant.VALUE_ONE));
								examQuestion.setCreateTime(TimeUtil.currentTimeMillis());
								examQuestion.setInputType(Byte.valueOf(Constant.VALUE_ZERO));
								questionMapper.insertSelective(examQuestion);
								
								//创建试卷关联关系
								HashMap<String,Object> map = new HashMap<String, Object>();
								map.put("id", UuidUtil.getUUID());
								map.put("question_id", examQuestionId);
								map.put("paper_id", paperId);
								map.put("priority", i);
								mapper.insertPaperQuestionRelation(map);
								
								i++; //问题题号自动生成
							}
						}
					}
				}
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	/**
	 * 新建题库
	 * @param dbId
	 * @param companyId
	 * @param positionId
	 * @return
	 */
	public boolean createDb(String dbId, String companyId, String positionId){
		try {
			PositionInfo info = positionMapper.selectByPrimaryKey(positionId);
			if(info != null){
				ExamQuestionDb db = new ExamQuestionDb();
				db.setId(dbId);
				db.setDbName("题库_" + new Random().nextInt(999999999));
				db.setCreateTime(TimeUtil.currentTimeMillis());
				db.setCompanyId(companyId);
				db.setParentId(info.getJobTypeCode());
				dbMapper.insertSelective(db);
				return true;
			}else{
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 创建试卷
	 * @param dbId
	 * @param companyId
	 * @param positionId
	 * @return
	 */
	public boolean createPaper(String paperId, String companyId, String dbId){
		try {
			ExamPaper paper = new ExamPaper();
			paper.setId(paperId);
			paper.setPaperName("试卷_" + new Random().nextInt(999999999));
			paper.setCompanyId(companyId);
			paper.setQuestionDbId(dbId);
			paper.setStatus(Byte.valueOf(Constant.VALUE_ONE));
			paper.setCreateTime(TimeUtil.currentTimeMillis());
			paper.setPaperType(Byte.valueOf(Constant.VALUE_ZERO));
			paper.setPaperMinutes(60);//设置笔试时间为60分钟
			int i = paperMapper.insertSelective(paper);
			if(i == 1){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 更新卷子
	 */
	@Override
	public int updateExamPaperQuestions(ExamListBean examList, String paperId, String editFlag, String dbId) {
		try {
			//editFlag == 0 未发出去的卷子，可以增删改
			if(editFlag.equals(Constant.VALUE_ZERO)){
				//先把该卷子的试题全部删掉
				HashMap<String,Object> map = new HashMap<String, Object>();
				map.put("paperId", paperId);
				//删除所有的关联
				mapper.deletePaperQuestionRelation(map);
				//删除所有的题目
				questionMapper.deletePaperQuestion(map);
				
				//List<Map<String,Object>> list = questionMapper.questionList(map);
				//System.out.println(list);
				/*for (int i = 0; i < list.size(); i++) {
					String questionId = String.valueOf(list.get(i).get("id"));
					map.clear();
					map.put("questionId",questionId);
					map.put("paperId", paperId);
					//删除该份试卷所有的关联问题
					mapper.deletePaperQuestionRelation(map);
					//把所有的问题删掉
					questionMapper.deleteByPrimaryKey(questionId);
					//examLists.remove(i);
				}*/
				//重新全部保存一次
				int i = 1;
				for (ExamBean bean : examList.getExamList()) {
					//创建问题
					if(StringUtil.isNotEmpty(bean.getQuestion())){
						String examQuestionId = UuidUtil.getUUID();
						ExamQuestion examQuestion = new ExamQuestion();
						examQuestion.setId(examQuestionId);
						examQuestion.setQuestionDbId(dbId);
						examQuestion.setQuestionContent(bean.getQuestion());;
						examQuestion.setQuestionType(Constant.VALUE_ONE);
						examQuestion.setStatus(Byte.valueOf(Constant.VALUE_ONE));
						examQuestion.setCreateTime(TimeUtil.currentTimeMillis());
						examQuestion.setInputType(Byte.valueOf(Constant.VALUE_ZERO));
						questionMapper.insertSelective(examQuestion);
						
						//创建试卷关联关系
						map.clear();
						map.put("id", UuidUtil.getUUID());
						map.put("question_id", examQuestionId);
						map.put("paper_id", paperId);
						map.put("priority", i);
						int ik = mapper.insertPaperQuestionRelation(map);
						
						i++; //问题题号自动生成
					}
				}
				
			}else{//已经发出去的卷子，只能修改题目名称
				for (ExamBean bean : examList.getExamList()) {
					ExamQuestion examQuestion = new ExamQuestion();
					examQuestion = questionMapper.selectByPrimaryKey(bean.getId());
					examQuestion.setQuestionContent(bean.getQuestion());;
					questionMapper.updateByPrimaryKeySelective(examQuestion);
				}
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Map<String, Object>> questionList(Map<String, Object> map) {
		try {
			return questionMapper.questionList(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int deletePaperQuestion(Map<String, Object> map) {
		try {
			questionMapper.deletePaperQuestion(map);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
