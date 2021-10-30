package com.example.availablehotelsservice;

import com.available.callers.HttpCaller;
import com.available.exceptions.BusinessException;
import com.available.exceptions.CommunationFailedException;
import com.available.rest.model.AvailableHotelRequest;
import com.available.rest.model.AvailableHotelsResponse;
import com.available.service.impl.AvailableHotelsAggregatorService;
import com.google.gson.Gson;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AvailableHotelsAggregatorServiceTest  extends TestCase{
	
	@Mock
	private HttpCaller httpCaller;
	
	@InjectMocks
	private AvailableHotelsAggregatorService aggregatorService;
	
	private List<AvailableHotelsResponse> expectedList = new ArrayList<AvailableHotelsResponse>();
	
	private AvailableHotelRequest dummyRequest = new AvailableHotelRequest();
	
	private String dummySuccessCrazyHotelsResponse = "";
	
	private String dummySuccessBestHotelsResponse = "";


	@Before
	public void Setup() {
		
		// [{"hotel":"Shirator","hotelRate":5,"hotelFare":"180","roomAmenities":"Snacks,Drinks"},{"hotel":"Mars","hotelRate":5,"hotelFare":"210","roomAmenities":"Snacks,Drinks,extra bed"}]
		dummySuccessCrazyHotelsResponse = "[{\"hotelName\":\"Shiraton\", \"rate\":\"****\", \"price\":\"50\", \"discount\":\"5\", \"amenities\":[ \"Dryer\", \"Towels\"]}]";
		
		// { "hotel":"Mariot", "hotelRate":"4", "hotelFare":"22", "roomAmenities":"Dryer,Towels" }
		dummySuccessBestHotelsResponse = "[{\"hotel\":\"Mariot\",\"hotelRate\":5,\"hotelFare\":\"180\",\"roomAmenities\":\"Snacks,Drinks\"},{\"hotel\":\"Mars\",\"hotelRate\":5,\"hotelFare\":\"210\",\"roomAmenities\":\"Snacks,Drinks,extra bed\"}]";

		
		AvailableHotelsResponse bestHotelExpectedResponse1 = new AvailableHotelsResponse();
		bestHotelExpectedResponse1.setProvider("BestHotel");
		bestHotelExpectedResponse1.setHotelname("Mariot");
		bestHotelExpectedResponse1.setFare("180");
		bestHotelExpectedResponse1.setAmenities(new String[] {"Snacks", "Drinks"});
		bestHotelExpectedResponse1.setRate(5);
		
 		AvailableHotelsResponse bestHotelExpectedResponse2 = new AvailableHotelsResponse();
 		bestHotelExpectedResponse2.setProvider("BestHotel");
 		bestHotelExpectedResponse2.setHotelname("Sheraton");
 		bestHotelExpectedResponse2.setFare("210");
 		bestHotelExpectedResponse2.setAmenities(new String[] {"Snacks", "Drinks", "extra bed"});
 		bestHotelExpectedResponse2.setRate(5);

 		AvailableHotelsResponse crazyHotelExpectedResponse = new AvailableHotelsResponse();
		crazyHotelExpectedResponse.setProvider("CrazyHotel");
		crazyHotelExpectedResponse.setHotelname("Sheraton");
		crazyHotelExpectedResponse.setFare("50");
		crazyHotelExpectedResponse.setAmenities(new String[] {"Dryer", "Towels"});
		crazyHotelExpectedResponse.setRate(4);
		
		expectedList.add(bestHotelExpectedResponse1);
		expectedList.add(bestHotelExpectedResponse2);
		expectedList.add(crazyHotelExpectedResponse);
		
		dummyRequest.setCity("AAA");
		dummyRequest.setFromDate("11-10-2018");
		dummyRequest.setToDate("11-10-2018");
		dummyRequest.setNumberOfAdults(5);
	}
	
	@Test
	public void TestGetAvailableHotels() throws BusinessException, CommunationFailedException {
		
		try {
			String crazyHotelProviderUrl = "http://CRAZY-HOTELS-SERVICE/crazy/CrazyHotels";
			String bestHotelProviderUrl = "http://BEST-HOTELS-SERVICE/best/BestHotels";
			
			Gson gson = new Gson();
			String jsonRequestString = gson.toJson(dummyRequest);
			
			Mockito.when(httpCaller.performHttpRequest(jsonRequestString, crazyHotelProviderUrl)).thenReturn(dummySuccessCrazyHotelsResponse);
			Mockito.when(httpCaller.performHttpRequest(jsonRequestString, bestHotelProviderUrl)).thenReturn(dummySuccessBestHotelsResponse);
			
			List<AvailableHotelsResponse> testResultList = aggregatorService.getAvailableHotels(dummyRequest);			
			assertEquals(expectedList, testResultList);
			
		} catch (Exception e) {
			Assert.fail("Exception " + e);
		}
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenAvailableHotelsResponseIsNull_thenIllegalArgumentExceptionThrown() {
		
		try {
			Mockito.when(aggregatorService.getAvailableHotels(null)).thenThrow(IllegalArgumentException.class);
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (CommunationFailedException e) {
			e.printStackTrace();
		}
	       
	}

}
