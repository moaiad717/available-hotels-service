package com.available.service;

import com.available.exceptions.BusinessException;
import com.available.exceptions.CommunationFailedException;
import com.available.rest.model.AvailableHotelRequest;
import com.available.rest.model.AvailableHotelsResponse;

import java.util.List;

public interface AvailableHotelsService {

	public List<AvailableHotelsResponse> getAvailableHotels(AvailableHotelRequest request) throws CommunationFailedException, BusinessException ;
}
