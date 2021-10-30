package com.available.service.impl;

import com.available.enums.HotelProviders;
import com.available.holder.ApplicationUrlsHolder;
import com.available.exceptions.BusinessException;
import com.available.exceptions.CommunationFailedException;
import com.available.exceptions.ErrorObj;
import com.available.callers.HttpCaller;
import com.available.rest.model.AvailableHotelRequest;
import com.available.rest.model.AvailableHotelsResponse;
import com.available.rest.model.adapter.ProvidersHotelResponseMapper;
import com.available.service.AvailableHotelsService;
import com.available.utils.ApplicationConstants;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * An implementation of {@link AvailableHotelsService}, it follows
 * <b>Aggregator</b> pattern.
 * <p>
 * It will call all available <b>Providers</b>, aggregate their results to an
 * list of available hotels.
 * <p>
 * <b>Aggregator</b> is a simple service that invokes multiple services to
 * achieve the functionality required by the application.
 * @see {@link http://blog.arungupta.me/microservice-design-patterns/}
 */
@Service("availableHotelsService")
public class AvailableHotelsAggregatorService implements AvailableHotelsService {

	final static Logger logger = Logger.getLogger(AvailableHotelsAggregatorService.class);

	HttpCaller httpCaller;

	@Autowired
	public AvailableHotelsAggregatorService(HttpCaller httpCaller) {
		super();
		this.httpCaller = httpCaller;
	}

	/***
	 * <p>
	 * Returns a list of the available hotels according to the user search data
	 * 
	 * @param <b>AvailableHotelRequest</b>
	 *            contains the user search details
	 * @return A list of the available hotel according to the user search
	 * @throws <b>CommunationFailedException</b>
	 *             in case something wrong happen during service call
	 * @throws <b>BusinessException</b>
	 *             in case runtime error occur
	 */
	@Override
	public List<AvailableHotelsResponse> getAvailableHotels(AvailableHotelRequest request)
			throws CommunationFailedException, BusinessException {

		List<AvailableHotelsResponse> hotelResponses = new ArrayList<>();
		if (request == null) {
			throw new IllegalArgumentException("Invalid request data");
		}
		try {

			Gson gson = new Gson();
			String jsonRequestString = gson.toJson(request);

			logger.info("AvailableHotelRequest Json String  " + jsonRequestString);

			ApplicationUrlsHolder applicationUrlsHolder = new ApplicationUrlsHolder();
			Map<HotelProviders, String> providersUrls = applicationUrlsHolder.getHotelProvidersUrls();

			for (Entry<HotelProviders, String> entry : providersUrls.entrySet()) {
				HotelProviders hotelProvider = entry.getKey();
				String providerUrl = entry.getValue();
				try {
					String jsonResponse = httpCaller.performHttpRequest(jsonRequestString, providerUrl);
					hotelResponses.addAll(ProvidersHotelResponseMapper.mappingHotelResponse(hotelProvider, jsonResponse));
				}
				catch (Exception e){
				}
			}
			Collections.sort(hotelResponses);
		} catch (Exception e) {

			ErrorObj errorObj = new ErrorObj();
			errorObj.setErrorCode(ApplicationConstants.UNKNOWN_EXCEPTION);
			errorObj.setErrorMessage("Sorry, Service currently unavailable");
		}

		return hotelResponses;
	}

	public static String formatDate(Date date) {

		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String dateString = dateFormat.format(date);
		return dateString;

	}

	// private String getJsonValue(AvailableHotelRequest request){
	//
	// LocalDate fromDate = LocalDate.now();
	// LocalDate toDate = LocalDate.now();
	//
	// String b = fromDate.format(DateTimeFormatter.ISO_DATE);
	//
	// return "";
	// }

}
