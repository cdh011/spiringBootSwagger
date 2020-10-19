package com.school.userinfo.mapper;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper {
	public List<Map<String, Object>> selectUserInfoList(Map<String, Object> params);
	public int selectUserInfoListCnt(Map<String, Object> params);
	public int insertUserInfo(Map params);
	public int updateUserInfo(Map params);
	public int deleteUserInfo(Map params);
	
}
