package com.school.exam.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.exam.mapper.ExamMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ExamService {
	private final ExamMapper examMapper;
	
	public List<Map<String, Object>> getExamData(Map<String, Object> params) {
		log.debug("getExam called");
		
		List<Map<String, Object>> data = examMapper.selectExamData(params);
		
		return data;
	}

}
