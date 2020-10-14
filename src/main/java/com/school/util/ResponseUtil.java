package com.school.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 응답코드 제어
 */
public class ResponseUtil {

	/**
	 * API 서버 식별자 - 관리자, 배치 제외
	 * Gateway : 10000
	 * 인증(land-auth) : 20000
	 * 매물(land-property) : 30000
	 * 시세(land-price) : 31000
	 * 부가서비스(land-extra) : 32000
	 * 단지(land-complex) : 33000
	 * 분양(land-lots) : 34000
	 * 중개업소(land-agent) : 35000
	 * Admin 시세(land-admin-price) : 50000
	 * Admin 매물(land-admin-property) : 51000
	 */
	static final int serverCode = 33000;
	
	/**
	 * 성공 응답
	 */
	public static Map getSuccessResponse(Object data) {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode);
		response.put("data", data);
		
		return response;
	}
	
	/**
	 * NO DATA 응답
	 */
	public static Map getNoDataResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 210);
		response.put("message", "No Data.");
		
		return response;
	}
	
	/**
	 * 파라미터 오류(null, validation 등)
	 */
	public static Map getParameterErrorResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 500);
		response.put("message", "Parameter validation error.");
		
		return response;
	}
	
	/**
	 * Try Catch 에러
	 */
	public static Map getErrorResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 500);
		response.put("message", "throw error.");
		
		return response;
	}
	
	/**
	 * SQL 실패
	 */
	public static Map getSqlErrorResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 500);
		response.put("message", "SQL error.");
		
		return response;
	}
	
	/**
	 * SNS 서버 네트워크 에러
	 */
	public static Map getSNSNetworkErrorResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 900);
		response.put("message", "SNS server network connection error.");
		
		return response;
	}
	
	/**
	 * Email 네트워크 에러
	 */
	public static Map getEmailNetworkErrorResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 901);
		response.put("message", "Email server network connection error.");
		
		return response;
	}
	
	/**
	 * NHN PushServer 네트워크 에러
	 */
	public static Map getNHNPushserverNetworkErrorResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 902);
		response.put("message", "NHN PushServer network connection error.");
		
		return response;
	}
	
	/**
	 * NHN ObjectServer 네트워크 에러
	 */
	public static Map getNHNObjectserverNetworkErrorResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 903);
		response.put("message", "NHN ObjectServer network connection error.");
		
		return response;
	}
	
	
	/**
	 * 네이버 네트워크 에러
	 */
	public static Map getNaverNetworkErrorResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 910);
		response.put("message", "Naver network connection error.");
		
		return response;
	}
	
	/**
	 * 카카오 네트워크 에러
	 */
	public static Map getKakaoNetworkErrorResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 911);
		response.put("message", "Kakao network connection error.");
		
		return response;
	}
	
	/**
	 * 페이스북 네트워크 에러
	 */
	public static Map getFacebookNetworkErrorResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 912);
		response.put("message", "Facebook network connection error.");
		
		return response;
	}
	
	/**
	 * 구글 네트워크 에러
	 */
	public static Map getGoogleNetworkErrorResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 913);
		response.put("message", "Facebook network connection error.");
		
		return response;
	}
	
	/**
	 * KMS 네트워크 에러
	 */
	public static Map getKmsNetworkErrorResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 930);
		response.put("message", "KMS network connection error.");
		
		return response;
	}
	
	/**
	 * MK 네트워크 에러
	 */
	public static Map getMkNetworkErrorResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 931);
		response.put("message", "MK network connection error.");
		
		return response;
	}
	
	/**
	 * 부동산114 네트워크 에러
	 */
	public static Map getR114NetworkErrorResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 932);
		response.put("message", "R114 network connection error.");
		
		return response;
	}
	
	/**
	 * 공인중개사협회 네트워크 에러
	 */
	public static Map getKarNetworkErrorResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 933);
		response.put("message", "KAR network connection error.");
		
		return response;
	}
	
	/**
	 * 기타 오류 응답
	 */
	public static Map getSystemErrorResponse() {
		Map<String, Object> response = new HashMap<String, Object>();
		
		response.put("resultCode", serverCode + 999);
		response.put("message", "System error.");
		
		return response;
	}
	
}
