package com.available.rest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BestHotelResponse implements Serializable {

	private static final long serialVersionUID = 2L;

	private List<BestHotels> hotels = new ArrayList<>();

	public List<BestHotels> getHotels() {
		return hotels;
	}
	
}
