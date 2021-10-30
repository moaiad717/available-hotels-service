package com.available.callers;

import com.available.exceptions.CommunationFailedException;
import com.available.exceptions.ErrorObj;
import com.available.utils.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/***
 * <p>
 * This Class make the HTTP Calles to an API
 *
 */
@Component
public class RestHttpCaller implements HttpCaller {


	/***
	 * This method to perform the HTTP call, accept and returns JSON
	 * as String value
	 * 
	 * 
	 * @param jsonString
	 * @return String
	 * @throws CommunationFailedException 
	 */

	@Autowired
	private RestTemplate restTemplate;

	public String performHttpRequest(String jsonString, String url) throws CommunationFailedException {
		
		if (url == null || url.trim().isEmpty()) {
			throw new IllegalArgumentException("The passed url are null or empty");
		}

		ResponseEntity<String> result = null;

		try {
			result =restTemplate.postForEntity(url, new HttpEntity(jsonString, buildHeaderJson()), String.class);

			if (result == null || !result.getStatusCode().equals(HttpStatus.OK) || result.getBody() == null) {
				ErrorObj errorObj = new ErrorObj();
				errorObj.setErrorCode(ApplicationConstants.COMMUNICATION_EXCEPTION);
				errorObj.setErrorMessage(ApplicationConstants.USER_GENERAL_EXCEPTION_MEG);
				
				throw new CommunationFailedException(errorObj);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			ErrorObj errorObj = new ErrorObj();
			errorObj.setErrorCode(ApplicationConstants.COMMUNICATION_EXCEPTION);
			errorObj.setErrorMessage(ApplicationConstants.USER_GENERAL_EXCEPTION_MEG);
			
			throw new CommunationFailedException(errorObj);
		}
		return result.getBody();
	}

	private MultiValueMap<String, Object> buildHeaderJson() {
		MultiValueMap<String, Object> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "application/json");
		return headers;
	}

}
