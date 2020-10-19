package com.school.exam.mapper;

import java.util.List;
import java.util.Map;

public interface ExamMapper {
	public List<Map<String, Object>> selectTestScoreList(Map<String, Object> params);
	public int selectTestScoreCnt(Map<String, Object> params);
	public int insertTestScore(Map params);
	public int updateTestScore(Map params);
	public int deleteTestScore(Map params);
	
}
