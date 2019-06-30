package com.garant.dev.service;

import com.garant.dev.model.CreditCard;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("creditCardService")
public class CreditCardServiceImpl implements CreditCardService{

	private static final String PATH_TO_WAYFORPAY_API = "https://api.wayforpay.com/api";
    private static final String APP_ID = "";
    private static final String APP_ID_VALUE = "";

    public JSONObject getJSONFromApi(String infoData) {
        HttpRequest httpRequest = buildRequest(infoData);
        JSONObject obj = execute(httpRequest);
        return obj;
    }

    private JSONObject execute(HttpRequest httpRequest) {
        try {
            return httpRequest.asJson().getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
		return null;
    }
	
    private HttpRequest buildRequest(String infoData) {
        return Unirest.get(PATH_TO_WAYFORPAY_API)
                .queryString("merchantAccount","test_merch_n1")
                .queryString("merchantSecretKey", "flk3409refn54t54t*FNJRET");
    }
    
    public String buildInfoData(String card, String expMonth, String expYear, String cardCvv, String cardHolder) {
        StringBuilder build = new StringBuilder();
        return build.append(card).append(";").append(expMonth).append(";").append(expYear).append(";").append(cardCvv).append(";").append(cardHolder).append(";").toString();
    		 
    }
    
    private CreditCard convertObject(JSONObject object){
        if (object == null) {
            return new CreditCard();
        }
        CreditCard card = new CreditCard();
        return card;
    }
	
}
