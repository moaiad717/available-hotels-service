package com.available.holder;

import com.available.enums.HotelProviders;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/***
 * 
 * Class holder to get the providers URLs from resource file
 *
 */
public class ApplicationUrlsHolder {
	
	private ResourceBundle applicationUrlsBundle;
	
	public ApplicationUrlsHolder() {
		applicationUrlsBundle = ResourceBundle.getBundle("application_urls");
	}

	/***
	 * This method get the providers service names (as registered in Eureka) from source folder
	 * @return Map contains each provider and his service link
	 */
	public Map<HotelProviders, String> getHotelProvidersUrls() {
		String crazyHotelUrl = applicationUrlsBundle.getString("hotel.reservations.url.crazy.hotel");
		String bestHotelUrl = applicationUrlsBundle.getString("hotel.reservations.url.best.hotel");
		Map<HotelProviders, String> providerUrls = new HashMap<>();
		
		providerUrls.put(HotelProviders.CRAZY_HOTEL, crazyHotelUrl);
		providerUrls.put(HotelProviders.BEST_HOTEL, bestHotelUrl);
		return providerUrls;
	}

}