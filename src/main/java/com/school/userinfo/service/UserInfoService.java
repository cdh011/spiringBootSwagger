package com.school.userinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.userinfo.mapper.UserInfoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserInfoService {
	private final UserInfoMapper userInfoMapper;
	
	public List<Map<String, Object>> getUserInfoList(Map<String, Object> params) {
		log.debug("getUser called");
		List<Map<String, Object>> data = userInfoMapper.selectUserInfoList(params);
		return data;
	}

	public int selectUserInfoListCnt(Map<String, Object> params) {
		log.debug("getUser called");
		int data = userInfoMapper.selectUserInfoListCnt(params);
		return data;
	}
	
	public int insertUserInfo(Map<String, Object> params) {
		int resultValue = userInfoMapper.insertUserInfo(params);
		return resultValue;
	}
	
	public int updateUserInfo(Map<String, Object> params) {
		int resultValue = userInfoMapper.updateUserInfo(params);
		return resultValue;
	}
	
	public int deleteUserInfo(Map<String, Object> params) {
		int resultValue = userInfoMapper.deleteUserInfo(params);
		return resultValue;
	}
	
}
