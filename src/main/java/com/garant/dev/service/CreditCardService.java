package com.garant.dev.service;

import com.garant.dev.model.wayforpay.InvoiceWFPAPIResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface CreditCardService {

	public InvoiceWFPAPIResponse getJSONFromApi(String infoData) throws UnirestException;
	
}
