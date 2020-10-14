package com.school.exam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.school.util.ResponseUtil;
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
@Api(tags = { "1. 단지 Controller" })
@RequestMapping("/exam")
@Slf4j
public class ExamController {
	private final ExamService examService;
	
	@ApiOperation(value = "단지 데이터 조회 요청"
				, notes = "단지 데이터 정보를 조회한다.")
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	@ApiImplicitParams({
        @ApiImplicitParam(name = "시작위도지점", value = "위도 시작 지점", required = true, paramType="query", dataType = "Double", defaultValue = "37.5329128"),
        @ApiImplicitParam(name = "종료위도지점", value = "위도 종료 지점", required = true, paramType="query", dataType = "Double", defaultValue = "37.5630591"),
        @ApiImplicitParam(name = "시작경도지점", value = "경도 시작 지점", required = true, paramType="query", dataType = "Double", defaultValue = "126.877923"),
        @ApiImplicitParam(name = "종료경도지점", value = "경조 종료 지점", required = true, paramType="query", dataType = "Double", defaultValue = "126.943798"),
        @ApiImplicitParam(name = "세대수표시", value = "총 세대수 표시 여부", required = false, paramType="query", dataType = "Boolean"),
	})
	public Map getComplexData(Double 시작위도지점, Double 종료위도지점, Double 시작경도지점, Double 종료경도지점, Boolean 세대수표시){
		Map response;
		
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("시작위도지점", 시작위도지점);
		params.put("종료위도지점", 종료위도지점);
		params.put("시작경도지점", 시작경도지점);
		params.put("종료경도지점", 종료경도지점);
		params.put("showGnrtnCnt", 세대수표시);
		
		try {
			List<Map<String, Object>> data = examService.getExamData(params);

			response = ResponseUtil.getSuccessResponse(data);
			
		} catch(BadSqlGrammarException exception) {
			response = ResponseUtil.getSqlErrorResponse();
		}
		
		return response;
	}
	
}
