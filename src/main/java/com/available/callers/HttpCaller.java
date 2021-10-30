package com.available.callers;

import com.available.exceptions.CommunationFailedException;

/**
 * This Class make the HTTP Calls to an API
 */
public interface HttpCaller {

	/**
	 * This method used to perform the API call, accept and returns JSON
	 * as String value
	 * 
	 * @param jsonString
	 * @return String
	 * @throws CommunationFailedException 
	 */
	String performHttpRequest(String jsonString, String url) throws CommunationFailedException;
}
