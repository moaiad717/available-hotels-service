package com.example.availablehotelsservice.mock;

import com.available.exceptions.BusinessException;
import com.available.exceptions.CommunationFailedException;
import com.available.rest.model.AvailableHotelRequest;
import com.available.rest.model.AvailableHotelsResponse;

import java.util.ArrayList;
import java.util.List;

public class MockAvailableServiceImpl {

	public List<AvailableHotelsResponse> getAvailableHotels(AvailableHotelRequest request) throws CommunationFailedException, BusinessException {
		
		List<AvailableHotelsResponse> availableHotelsResponses = new ArrayList<>();
		String [] amenities = {"snacks","drinks"};	
		
		AvailableHotelsResponse availableHotelsResponse = new AvailableHotelsResponse();
		availableHotelsResponse.setRate(4);
		availableHotelsResponse.setAmenities(amenities);
		availableHotelsResponse.setFare("150");
		availableHotelsResponse.setHotelname("Sheraton");
		availableHotelsResponse.setProvider("BestHotel");
		
		AvailableHotelsResponse availableHotelsResponse2 = new AvailableHotelsResponse();
		availableHotelsResponse2.setRate(5);			
		availableHotelsResponse2.setAmenities(amenities);
		availableHotelsResponse2.setFare("190");
		availableHotelsResponse2.setHotelname("Mariot");
		availableHotelsResponse2.setProvider("CrazyHotel");
		
		availableHotelsResponses.add(availableHotelsResponse);
		availableHotelsResponses.add(availableHotelsResponse2);
		
		return availableHotelsResponses;
	}
	
}
