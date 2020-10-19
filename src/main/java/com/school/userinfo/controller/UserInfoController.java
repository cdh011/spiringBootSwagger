package com.school.userinfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.school.util.DateUtil;
import com.school.util.ResponseUtil;
import com.school.util.StringUtil;
import com.school.userinfo.service.UserInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RefreshScope
@RestController
@RequiredArgsConstructor
@Validated
@Api(tags = { "2. 사용자 정보 Controller" })
@RequestMapping("/userinfo")
@Slf4j
public class UserInfoController {
	private final UserInfoService userInfoService;
	
	@ApiOperation(value = "2.1 사용자 정보 조회"
			, notes = "사용자 데이터 정보를 조회한다. 									\r\n"
		      +"*** 입력부 ***  													\r\n"
		      +" - 사용자 정보를 이름,ID,번호 등으로 조회 한다.	  						\r\n"
		      +"``` 															\r\n"
		      +"{ 																\r\n"
		      +"   \"user_no\": \"1\"      			-- 선택 , Integer 			\r\n"
		      +"   \"user_id\": \"\"      			-- 선택 , String 				\r\n"
		      +"   \"user_nm\": \"\"      			-- 선택 , String 				\r\n"
		      +" } 																\r\n"
		      +"``` 															\r\n"
		      +"*** 출력부 ***  													\r\n"
		      +" - 해당 시험 성적 정보 조회  											\n\n"
		      +"``` 															\r\n"
		      +"{																\r\n" 
		      + "\"data\" : {													\r\n" 
		      + "      \"dataList\" : [ 										\r\n"
		      + "       {														\r\n" 
		      + "       \"user_no\" : \"1\" , 									\r\n" 
		      + "       \"user_id\" : \"USER1\" , 								\r\n" 
		      + "       \"user_nm\" : \"USER1\" , 								\r\n" 
		      + "       \"phone_no\" : \"01012341234\" , 						\r\n" 
		      + "       \"email\" : \"test@naver.com\"  						\r\n" 
		      + "    } 															\r\n"
		      + "    ]\n , 														\r\n"
		      + " \"page_list_cnt \" : 4										\r\n "
		      +"}, 																\r\n"
		      +" \"resultCode\" : 11000 										\r\n"
		      +" } 																\r\n"
		      +"```																\r\n"
			)
	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	@ApiImplicitParams({
        @ApiImplicitParam(name = "user_no", value = "사용자 번호", defaultValue = "1", required = false, paramType="query", dataType = "integer"),
        @ApiImplicitParam(name = "user_id", value = "사용자 번호", defaultValue = "", required = false, paramType="query", dataType = "String"),
        @ApiImplicitParam(name = "user_nm", value = "사용자 이름", defaultValue = "", required = false, paramType="query", dataType = "String")
	})
	public Map selectUserInfoList(Integer user_no, String user_id, String user_nm){
		Map response;
		
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("user_no", StringUtil.isEmpty(user_no + "")?null:user_no);
		params.put("user_id", StringUtil.isEmpty(user_id + "")?null:user_id);
		params.put("user_nm", StringUtil.isEmpty(user_nm + "")?null:user_nm);
		
		
		try {
			List<Map<String, Object>> dataList = userInfoService.getUserInfoList(params);
			int listCnt = userInfoService.selectUserInfoListCnt(params);
			
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("dataList",dataList);
			data.put("page_list_cnt",listCnt);

			response = ResponseUtil.getSuccessResponse(data);
			
		} catch(BadSqlGrammarException exception) {
			response = ResponseUtil.getSqlErrorResponse();
		}
		
		return response;
	}
	
	
	@ApiOperation(value = "2.2 사용자 정보 등록", notes = "사용자 정보 등록	\r\n"
		      +"*** 입력부 ***  										\r\n"
		      +" - 사용자 정보를 등록 한다.  								\n\n"
		      +"``` 												\r\n"
		      +"{ 													\r\n"
		      +"   \"user_id\": \"USER99\"  ,  						\r\n"
		      +"   \"user_nm\": \"USER99\" ,  						\r\n"
		      +"   \"birth_date\": \"20001011\"  ,  				\r\n"
		      +"   \"gender\": \"M\" ,  							\r\n"
		      +"   \"phone_no\": \"01012341234\"  ,  				\r\n"
		      +"   \"email\": \"test@naver.com\" ,  				\r\n"
		      +"   \"pwd\": \"1234\"    							\r\n"
		      +" } 													\r\n"
		      +"``` 												\r\n"
		      +"*** 출력부 ***  										\r\n"
		      +" - '0' : 데이터 없음, '1' : 데이터 존재  					\n\n"
		      +"``` 												\r\n"
		      +"{													\r\n" 
		      + "\"data\" : {										\r\n" 
		      +"            }, 										\r\n"
		      +"   \"resultCode\" : \"33000\" 						\r\n"
		      +" } 													\r\n"
		      +"``` 												\r\n"
		      )
	@RequestMapping(value = "/insertUserInfo", method = RequestMethod.POST)
	@ApiImplicitParams({
        @ApiImplicitParam(name = "insertUserInfoParam" , value = "JsonRequestBody")
	})
	public Map insertUserInfo(@RequestBody Map<String, Object> insertUserInfoParam) {
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		int resultCnt = 0;
		
		String user_id = insertUserInfoParam.get("user_id").toString();
		String user_nm = insertUserInfoParam.get("user_nm").toString();
		String birth_date = insertUserInfoParam.get("birth_date").toString().replaceAll("/","").replaceAll("-","");
		String gender = insertUserInfoParam.get("gender").toString();
		String phone_no = insertUserInfoParam.get("phone_no").toString();
		String email = insertUserInfoParam.get("email").toString();
		String pwd = insertUserInfoParam.get("pwd").toString();
		
		// 필수 값 체크 
		if(user_id == null || user_nm == null) {
			return ResponseUtil.getParameterErrorResponse(); // 파라미터 값 에러 반환 
		}
		
		// 생일 Type이 맞지 않는 경우 반환
		if(DateUtil.checkDate(birth_date) == false || birth_date.length() != 8) {
			return ResponseUtil.getParameterErrorResponse();  // 파라미터 값 에러 반환 
		}
		
		params.put("user_id", user_id);
		params.put("user_nm", user_nm);
		params.put("birth_date", birth_date);
		params.put("gender", gender);
		params.put("phone_no", phone_no);
		params.put("email", email);
		params.put("pwd", pwd);
		params.put("empl_no", "1");
		
		try {
			
			
			resultCnt = userInfoService.insertUserInfo(params);
			
			log.debug("resultCnt : " + resultCnt);
			
			if(resultCnt > 0) {
				response = ResponseUtil.getSuccessResponse(resultCnt);
			}else {
				response = ResponseUtil.getSqlErrorResponse();
			}
			
		} catch(BadSqlGrammarException exception) {
			log.error("error : {} ", exception.toString());
			response = ResponseUtil.getSqlErrorResponse();
		}
		
		return response;
	}

	@ApiOperation(value = "2.3 사용자 정보 수정", notes = "사용자 정보 수정	\r\n"
			 +"*** 입력부 ***  										\r\n"
		      +" - 사용자 정보를 수정 한다.  								\n\n"
		      +"``` 												\r\n"
		      +"{ 													\r\n"
		      +"   \"user_no\": \"11\"  ,  							\r\n"
		      +"   \"user_id\": \"USER99\"  ,  						\r\n"
		      +"   \"user_nm\": \"USER99\" ,  						\r\n"
		      +"   \"birth_date\": \"20001011\"  ,  				\r\n"
		      +"   \"gender\": \"W\" ,  							\r\n"
		      +"   \"phone_no\": \"01043214321\"  ,  				\r\n"
		      +"   \"email\": \"test1234@naver.com\" ,  				\r\n"
		      +"   \"pwd\": \"4321\"    							\r\n"
		      +" } 													\r\n"
		      +"``` 												\r\n"
		      +"*** 출력부 ***  										\r\n"
		      +" - '0' : 데이터 없음, '1' : 데이터 존재  					\n\n"
		      +"``` 												\r\n"
		      +"{													\r\n" 
		      + "\"data\" : {										\r\n" 
		      +"            }, 										\r\n"
		      +"   \"resultCode\" : \"33000\" 						\r\n"
		      +" } 													\r\n"
		      +"``` 												\r\n"
		      )
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	@ApiImplicitParams({
      @ApiImplicitParam(name = "updateUserInfoParam" , value = "JsonRequestBody")
	})
	public Map updateUserInfo(@RequestBody Map<String, Object> updateUserInfoParam) {
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		int resultCnt = 0;
		
		String user_no = updateUserInfoParam.get("user_no").toString();
		String user_id = updateUserInfoParam.get("user_id").toString();
		String user_nm = updateUserInfoParam.get("user_nm").toString();
		String birth_date = updateUserInfoParam.get("birth_date").toString().replaceAll("/","").replaceAll("-","");
		String gender = updateUserInfoParam.get("gender").toString();
		String phone_no = updateUserInfoParam.get("phone_no").toString();
		String email = updateUserInfoParam.get("email").toString();
		String pwd = updateUserInfoParam.get("pwd").toString();
		
		// 필수 값 체크 
		if(user_no == null || user_id == null || user_nm == null) {
			return ResponseUtil.getParameterErrorResponse(); // 파라미터 값 에러 반환 
		}
		
		// 생일 Type이 맞지 않는 경우 반환
		if(DateUtil.checkDate(birth_date) == false || birth_date.length() != 8) {
			return ResponseUtil.getParameterErrorResponse();  // 파라미터 값 에러 반환 
		}
		
		params.put("user_no", user_no);
		params.put("user_id", user_id);
		params.put("user_nm", user_nm);
		params.put("birth_date", birth_date);
		params.put("gender", gender);
		params.put("phone_no", phone_no);
		params.put("email", email);
		params.put("pwd", pwd);
		params.put("empl_no", "1");
		
		try {
			
			
			resultCnt = userInfoService.updateUserInfo(params);
			
			log.debug("resultCnt : " + resultCnt);
			
			if(resultCnt > 0) {
				response = ResponseUtil.getSuccessResponse(resultCnt);
			}else {
				response = ResponseUtil.getSqlErrorResponse();
			}
			
		} catch(BadSqlGrammarException exception) {
			log.error("error : {} ", exception.toString());
			response = ResponseUtil.getSqlErrorResponse();
		}
		
		return response;
	}
	
	
	@ApiOperation(value = "2.4 사용자 정보 삭제", notes = "사용자 정보 삭제	\r\n"
		      +"*** 입력부 ***  										\r\n"
		      +" - 사용자 정보를 삭제 한다.  							\n\n"
		      +"``` 												\r\n"
		      +"{ 													\r\n"
		      +"   \"user_no\": \"11\"   							\r\n"
		      +" } 													\r\n"
		      +"``` 												\r\n"
		      +"*** 출력부 ***  										\r\n"
		      +" - '0' : 데이터 없음, '1' : 데이터 존재  					\n\n"
		      +"``` 												\r\n"
		      +"{													\r\n" 
		      + "\"data\" : {										\r\n" 
		      +"            }, 										\r\n"
		      +"   \"resultCode\" : \"33000\" 						\r\n"
		      +" } 													\r\n"
		      +"``` 												\r\n"
		      )
	@RequestMapping(value = "/deleteUserInfo", method = RequestMethod.POST)
	@ApiImplicitParams({
    @ApiImplicitParam(name = "deleteUserInfoParam" , value = "JsonRequestBody")
	})
	public Map deleteUserInfo(@RequestBody Map<String, Object> deleteUserInfoParam) {

		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		int resultCnt = 0;
		
		String user_no = deleteUserInfoParam.get("user_no").toString();
		
		if(user_no == null) {
			return ResponseUtil.getParameterErrorResponse();
		}
		
		params.put("user_no", user_no);
		
		try {
			
			
			resultCnt = userInfoService.deleteUserInfo(params);
			
			log.debug("resultCnt : " + resultCnt);
			
			if(resultCnt > 0) {
				response = ResponseUtil.getSuccessResponse(resultCnt);
			}else {
				response = ResponseUtil.getSqlErrorResponse();
			}
			
		} catch(BadSqlGrammarException exception) {
			log.error("error : {} ", exception.toString());
			response = ResponseUtil.getSqlErrorResponse();
		}
		
		return response;
	}
	
}
