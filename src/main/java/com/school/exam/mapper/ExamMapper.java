package com.school.exam.mapper;

import java.util.List;
import java.util.Map;

public interface ExamMapper {
	public List<Map<String, Object>> selectExamData(Map<String, Object> params);
	
}
