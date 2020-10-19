package com.school.exam.controller;

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

import com.school.util.ResponseUtil;
import com.school.util.StringUtil;
import com.school.exam.service.ExamService;

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
@Api(tags = { "1. 시험 성적 Controller" })
@RequestMapping("/exam")
@Slf4j
public class ExamController {
	private final ExamService examService;
	
	@ApiOperation(value = "1.1 시험 성적 정보 조회"
			, notes = "시험 성적 데이터 정보를 조회한다. 								\r\n"
		      +"*** 입력부 ***  													\r\n"
		      +" - 사용자 정보 미포함 조회시 : 해당 시험 정보 조회  						\r\n"
		      +" - 사용자 정보 포함 조회시 : 해당 시험 사용자 정보 조회  					\n\n"
		      +"``` 															\r\n"
		      +"{ 																\r\n"
		      +"   \"test_no\": \"1\"      			-- 필수 , Integer 			\r\n"
		      +"   \"user_no\": \"\"      			-- 선택 , Integer 			\r\n"
		      +"   \"page_no\": \"1\"      			-- 선택 , Integer 			\r\n"
		      +"   \"page_list_cnt\": \"10\"      	-- 선택 , Integer 			\r\n"
		      +" } 																\r\n"
		      +"``` 															\r\n"
		      +"*** 출력부 ***  													\r\n"
		      +" - 해당 시험 성적 정보 조회  											\n\n"
		      +"``` 															\r\n"
		      +"{																\r\n" 
		      + "\"data\" : {													\r\n" 
		      + "      \"dataList\" : [ 										\r\n"
		      + "       {														\r\n" 
		      + "       \"subject_code\" : \"MAT\" , 							\r\n" 
		      + "       \"score\" : \"26\" , 									\r\n" 
		      + "       \"ROWNO\" : \"4\" , 									\r\n" 
		      + "       \"subject_name\" : \"수학\" , 							\r\n" 
		      + "       \"user_nm\" : \"USER5\" , 								\r\n" 
		      + "       \"TEST_NO\" : \"1\" ,									\r\n" 
		      + "       \"user_no\" : \"5\"  									\r\n" 
		      + "    } 															\r\n"
		      + "    ]\n , 														\r\n"
		      + " \"page_list_cnt \" : 4										\r\n "
		      +"}, 																\r\n"
		      +" \"resultCode\" : 11000 										\r\n"
		      +" } 																\r\n"
		      +"```																\r\n"
			)
	@RequestMapping(value = "/getTestScore", method = RequestMethod.GET)
	@ApiImplicitParams({
        @ApiImplicitParam(name = "test_no", value = "시험 번호", defaultValue = "1", required = true, paramType="query", dataType = "integer"),
        @ApiImplicitParam(name = "user_no", value = "사용자 번호", defaultValue = "", required = false, paramType="query", dataType = "integer"),
        @ApiImplicitParam(name = "page_no", value = "페이지 번호", defaultValue = "1", required = false, paramType="query", dataType = "integer"),
        @ApiImplicitParam(name = "page_list_cnt", value = "10", defaultValue = "10", required = false, paramType="query", dataType = "integer")
	})
	public Map selectTestScoreList(Integer test_no, Integer user_no, Integer page_no, Integer page_list_cnt){
		Map response;
		
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("test_no", test_no);
		params.put("user_no", StringUtil.isEmpty(user_no + "")?null:user_no);
		params.put("page_no", (page_no == null)?0:((page_no -1) * page_list_cnt));
		params.put("page_list_cnt", (page_list_cnt == null)?10:page_list_cnt);
		
		try {
			List<Map<String, Object>> dataList = examService.getTestScoreList(params);
			int listCnt = examService.selectTestScoreCnt(params);
			
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("dataList",dataList);
			data.put("page_list_cnt",listCnt);

			response = ResponseUtil.getSuccessResponse(data);
			
		} catch(BadSqlGrammarException exception) {
			response = ResponseUtil.getSqlErrorResponse();
		}
		
		return response;
	}
	
	
	@ApiOperation(value = "1.2 시험 정보 등록", notes = "시험 정보 등록	\r\n"
		      +"*** 입력부 ***  										\r\n"
		      +" - 4개의 과목 점수 등록 한다.  							\n\n"
		      +"``` 												\r\n"
		      +"{ 													\r\n"
		      +"   \"test_no\": \"1\"  ,  							\r\n"
		      +"   \"user_no\": \"99\" ,  							\r\n"
		      +"   \"subject\": [{\"subject_code\" : \"MAT\" ,      \r\n"
		      +"                  \"score\" : \"80\"   				\r\n"
		      +"                  } ,    							\r\n"
		      +"                  {\"subject_code\" : \"ENG\" , 	\r\n"
		      +"                  \"score\" : \"90\" 				\r\n"
		      +"                  } , 								\r\n"
		      +"                  {\"subject_code\" : \"KOR\" , 	\r\n"
		      +"                  \"score\" : \"70\" 				\r\n"
		      +"                  } , 								\r\n"
		      +"                  {\"subject_code\" : \"ART\" , 	\r\n"
		      +"                  \"score\" : \"60\" 				\r\n"
		      +"                  } 								\r\n"
		      +"                  ] 								\r\n"
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
	@RequestMapping(value = "/insertTestScore", method = RequestMethod.POST)
	@ApiImplicitParams({
        @ApiImplicitParam(name = "insertTestScoreParam" , value = "JsonRequestBody")
	})
	public Map insertTestScore(@RequestBody Map<String, Object> insertTestScoreParam) {
		Map response;
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		List<Map<String,Object>> subject = (List) insertTestScoreParam.get("subject");
		String test_no = insertTestScoreParam.get("test_no").toString();
		String user_no = insertTestScoreParam.get("user_no").toString();
		
		params.put("test_no", StringUtil.isEmpty(test_no)?null:test_no); // 시험 번호
		params.put("user_no", StringUtil.isEmpty(user_no + "")?null:user_no); // 사용자 
		params.put("empl_no", "1"); // 등록자
		
		String[][] subjectList = new String[subject.size()][2]; // 배열크기 설정 중요함
		
		for(int i = 0; i<subject.size(); i++) {
			log.debug(i + " : " + subject.get(i).get("subject_code").toString());
			log.debug(i +" : " + subject.get(i).get("score").toString());
			subjectList[i][0] = subject.get(i).get("subject_code").toString();
			subjectList[i][1] = subject.get(i).get("score").toString();
			
		}
		
		try {
			if(subjectList != null && subjectList.length > 0) {
				for(int i=0; i<subjectList.length ; i++) {
					if(subjectList[i] != null) {
						params.put("subject_code", subjectList[i][0]);
						params.put("score", subjectList[i][1]);
						examService.insertTestScore(params);
					}
				}
			}
			Map<String, Object> data = new HashMap<String, Object>();
			response = ResponseUtil.getSuccessResponse(data);
		} catch(BadSqlGrammarException exception) {
			log.error("error : {} ", exception.toString());
			response = ResponseUtil.getSqlErrorResponse();
		}
		
		return response;
	}

	@ApiOperation(value = "1.3 시험 정보 수정", notes = "시험 정보 수정	\r\n"
		      +"*** 입력부 ***  										\r\n"
		      +" - 4개의 과목 점수 수정 한다.  							\n\n"
		      +"``` 												\r\n"
		      +"{ 													\r\n"
		      +"   \"test_no\": \"1\"  ,  							\r\n"
		      +"   \"user_no\": \"99\" ,  							\r\n"
		      +"   \"subject\": [{\"subject_code\" : \"MAT\" ,      \r\n"
		      +"                  \"score\" : \"10\"   				\r\n"
		      +"                  } ,    							\r\n"
		      +"                  {\"subject_code\" : \"ENG\" , 	\r\n"
		      +"                  \"score\" : \"20\" 				\r\n"
		      +"                  } , 								\r\n"
		      +"                  {\"subject_code\" : \"KOR\" , 	\r\n"
		      +"                  \"score\" : \"30\" 				\r\n"
		      +"                  } , 								\r\n"
		      +"                  {\"subject_code\" : \"ART\" , 	\r\n"
		      +"                  \"score\" : \"40\" 				\r\n"
		      +"                  } 								\r\n"
		      +"                  ] 								\r\n"
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
	@RequestMapping(value = "/updateTestScore", method = RequestMethod.POST)
	@ApiImplicitParams({
      @ApiImplicitParam(name = "updateTestScoreParam" , value = "JsonRequestBody")
	})
	public Map updateTestScore(@RequestBody Map<String, Object> updateTestScoreParam) {
		Map response;
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		List<Map<String,Object>> subject = (List) updateTestScoreParam.get("subject");
		String test_no = updateTestScoreParam.get("test_no").toString();
		String user_no = updateTestScoreParam.get("user_no").toString();
		
		params.put("test_no", StringUtil.isEmpty(test_no)?null:test_no); // 시험 번호
		params.put("user_no", StringUtil.isEmpty(user_no + "")?null:user_no); // 사용자 
		params.put("empl_no", "1"); // 등록자
		
		String[][] subjectList = new String[subject.size()][2]; // 배열크기 설정 중요함
		
		for(int i = 0; i<subject.size(); i++) {
			log.debug(i + " : " + subject.get(i).get("subject_code").toString());
			log.debug(i +" : " + subject.get(i).get("score").toString());
			subjectList[i][0] = subject.get(i).get("subject_code").toString();
			subjectList[i][1] = subject.get(i).get("score").toString();
			
		}
		
		try {
			if(subjectList != null && subjectList.length > 0) {
				for(int i=0; i<subjectList.length ; i++) {
					if(subjectList[i] != null) {
						params.put("subject_code", subjectList[i][0]);
						params.put("score", subjectList[i][1]);
						examService.updateTestScore(params);
					}
				}
			}
			Map<String, Object> data = new HashMap<String, Object>();
			response = ResponseUtil.getSuccessResponse(data);
		} catch(BadSqlGrammarException exception) {
			log.error("error : {} ", exception.toString());
			response = ResponseUtil.getSqlErrorResponse();
		}
		
		return response;
	}
	
	
	@ApiOperation(value = "1.4 시험 정보 삭제", notes = "시험 정보 삭제	\r\n"
		      +"*** 입력부 ***  										\r\n"
		      +" - 4개의 과목 점수 삭제 한다.  							\n\n"
		      +"``` 												\r\n"
		      +"{ 													\r\n"
		      +"   \"test_no\": \"1\"  ,  							\r\n"
		      +"   \"user_no\": \"99\"   							\r\n"
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
	@RequestMapping(value = "/deleteTestScore", method = RequestMethod.POST)
	@ApiImplicitParams({
    @ApiImplicitParam(name = "deleteTestScoreParam" , value = "JsonRequestBody")
	})
	public Map deleteTestScore(@RequestBody Map<String, Object> deleteTestScoreParam) {

		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		int resultCnt = 0;
		
		String test_no = deleteTestScoreParam.get("test_no").toString();
		String user_no = deleteTestScoreParam.get("user_no").toString();
		
		if(test_no == null || user_no == null) {
			return ResponseUtil.getParameterErrorResponse();
		}
		
		params.put("test_no", test_no);
		params.put("user_no", user_no);
		
		try {
			
			
			resultCnt = examService.deleteTestScore(params);
			
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
