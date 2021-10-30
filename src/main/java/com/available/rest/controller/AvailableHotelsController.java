package com.available.rest.controller;

import com.available.exceptions.BusinessException;
import com.available.exceptions.CommunationFailedException;
import com.available.rest.model.AvailableHotelRequest;
import com.available.rest.model.AvailableHotelsResponse;
import com.available.service.AvailableHotelsService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/***
 * A class to call an APIs to get the available hotels 
 *
 */
@RestController
@RequestMapping("/availablehotels")
@JsonIgnoreProperties
public class AvailableHotelsController {

	final static Logger logger = Logger.getLogger(AvailableHotelsController.class);
	
	@Autowired
	AvailableHotelsService availableHotelsService;

	
	@PostMapping("/getavailable")
	public List<AvailableHotelsResponse> getAvailableHotels(@RequestBody AvailableHotelRequest request) {
		
		List<AvailableHotelsResponse> availableHotelsResponses = null;
		try {
			logger.info("AvailableHotelRequest  " + request.toString());			
			availableHotelsResponses = availableHotelsService.getAvailableHotels(request);			
			logger.info("AvailableHotelsResponses  " + availableHotelsResponses.toString());

		} catch (CommunationFailedException e) {
			logger.error("getAvailableHotels   CommunationFailedException" + Arrays.toString(e.getStackTrace()));
		}catch(BusinessException e){
			logger.error("getAvailableHotels   BusinessException" + Arrays.toString(e.getStackTrace()));
		}
		return availableHotelsResponses;
	}
}
