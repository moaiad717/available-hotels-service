package com.available.rest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CrazyHotelResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<CrazyHotels> hotels = new ArrayList<>();

	public List<CrazyHotels> getHotels() {
		return hotels;
	}


}
