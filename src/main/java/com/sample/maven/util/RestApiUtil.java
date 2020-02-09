package com.sample.maven.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.simple.JSONObject;

public class RestApiUtil {
	public static JSONObject restApiCall(String urlStr, String paramStr, String method, HashMap<String, Object> headerInfoMap) throws Exception{
		
		JSONObject returnJsonObj = new JSONObject();
		URL url = new URL(urlStr);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod(method);
		con.setRequestProperty("Accept-Charset", "UTF-8");
		headerInfoMap.put("Accept", "*/*");
		if(method.equals("POST") || method.equals("PUT")) {
			headerInfoMap.put("Content-Type", "application/json");
		}
		
		for(Entry<String, Object> entry : headerInfoMap.entrySet()) {
			con.setRequestProperty(entry.getKey(), entry.getValue().toString());
		}
		
		if(method.equals("POST") || method.equals("PUT")) {
			byte[] outputBytes = paramStr.getBytes("UTF-8");
			OutputStream os = con.getOutputStream();
			os.write(outputBytes);
			os.flush();
		}
		
		
		int responseCode = con.getResponseCode();
		BufferedReader br;
		if(responseCode==200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        } else {  // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
        }
        String inputLine;
        StringBuffer responseStr = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
        	responseStr.append(inputLine);
        }
        br.close();
        returnJsonObj.put("responseStr", responseStr.toString());
        returnJsonObj.put("responseCode", responseCode);
        
		return returnJsonObj;
		
	}	
}
