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
	
	public List<Map<String, Object>> getTestScoreList(Map<String, Object> params) {
		log.debug("getExam called");
		List<Map<String, Object>> data = examMapper.selectTestScoreList(params);
		return data;
	}

	public int selectTestScoreCnt(Map<String, Object> params) {
		log.debug("getExam called");
		int data = examMapper.selectTestScoreCnt(params);
		return data;
	}
	
	public int insertTestScore(Map<String, Object> params) {
		int resultValue = examMapper.insertTestScore(params);
		return resultValue;
	}
	
	public int updateTestScore(Map<String, Object> params) {
		int resultValue = examMapper.updateTestScore(params);
		return resultValue;
	}
	
	public int deleteTestScore(Map<String, Object> params) {
		int resultValue = examMapper.deleteTestScore(params);
		return resultValue;
	}
	
}
