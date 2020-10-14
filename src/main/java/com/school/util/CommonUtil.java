package com.school.util;

import java.util.Random;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**'
 * 데이터파싱, 통신, 랜덤넘버 생성 제어
 */
public class CommonUtil {

	public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static RestTemplate restTemplate;

	static {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(5000); // 읽기시간초과, ms
		factory.setConnectTimeout(3000); // 연결시간초과, ms
		
		HttpClient httpClient = HttpClientBuilder.create()
								.setMaxConnTotal(100) // connection pool 적용 
								.setMaxConnPerRoute(5) // connection pool 적용
								.build();
		
		factory.setHttpClient(httpClient); // 동기실행에 사용될 HttpClient 세팅 RestTemplate
		
		restTemplate = new RestTemplate(factory);

	}
	
	private final char[] characterTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
            'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

	private final char[] passwordTable =  { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
            'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&', '*',
            '(', ')', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
	
	public String GenerateCertNumber(int certLength) throws Exception{
		Random random = new Random(System.currentTimeMillis());
        int range = (int)Math.pow(10,certLength);
        int trim = (int)Math.pow(10, certLength-1);
        int result = random.nextInt(range)+trim;
         
        if(result>range){
            result = result - trim;
        }
        
        return String.valueOf(result);
	}
	
	public String GenerateCertCharacter(int certLength) throws Exception{
		
		Random random = new Random(System.currentTimeMillis());
        int tablelength = characterTable.length;
        StringBuffer buf = new StringBuffer();
        
        for(int i = 0; i < certLength; i++) {
        	int num = random.nextInt(tablelength);
        	if(num < 0) return "";
            buf.append(characterTable[num]);
        }
        
        return buf.toString();
	}
	
	public String GenerateCertPassword(int certLength) throws Exception{
		Random random = new Random(System.currentTimeMillis());
        int tablelength = passwordTable.length;
        StringBuffer buf = new StringBuffer();
        
        for(int i = 0; i < certLength; i++) {
        	int num = random.nextInt(tablelength);
        	if(num < 0) return "";
            buf.append(passwordTable[num]);
        }
        
        return buf.toString();
	}

}
