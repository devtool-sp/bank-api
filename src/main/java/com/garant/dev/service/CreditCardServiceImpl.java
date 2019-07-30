package com.garant.dev.service;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.garant.dev.model.CreditCard;
import com.garant.dev.model.wayforpay.InvoiceInput;
import com.garant.dev.model.wayforpay.InvoiceWFPAPIResponse;
import com.garant.dev.model.wayforpay.TransactionType;
import com.garant.dev.util.HMAC;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service("creditCardService")
public class CreditCardServiceImpl implements CreditCardService{

	private static final String PATH_TO_WAYFORPAY_API = "https://api.wayforpay.com/api";
	private static final String MERCHANT_DOMAIN_NAME = "www.garant.com";
    private static final String APP_ID = "";
    private static final String APP_ID_VALUE = "";

    public InvoiceWFPAPIResponse getJSONFromApi(String infoData) throws UnirestException {
    	
    	HttpResponse<JsonNode> apiCallResponse = buildRequest(infoData); 
		InvoiceWFPAPIResponse invoiceWFPAPIResponse = new Gson().fromJson(apiCallResponse.getBody().toString(),
				InvoiceWFPAPIResponse.class);
		return invoiceWFPAPIResponse;
    }

	
    private HttpResponse<JsonNode> buildRequest(String infoData) throws UnirestException {
    	
    	Long orderTime = new Date().getTime();
    	InvoiceInput invoiceInput = new InvoiceInput();
    	invoiceInput.setTransactionType(TransactionType.CREATE_INVOICE.transactionType());
    	invoiceInput.setMerchantAccount("test_merch_n1");
    	invoiceInput.setMerchantSecretKey("flk3409refn54t54t*FNJRET");
    	invoiceInput.setMerchantDomainName(MERCHANT_DOMAIN_NAME);
    	invoiceInput.setApiVersion(1);
    	invoiceInput.setServiceUrl("http://localhost:8080/garant/invoiceResponse");
    	invoiceInput.setOrderReference("garant_merch_" + orderTime);
    	invoiceInput.setAmount(12.15);
    	invoiceInput.setCurrency("UAH");
    	invoiceInput.setProductName(new String[] {"Transfer Money"});
    	invoiceInput.setProductPrice(new Double[] {12.5});
    	invoiceInput.setProductCount(new Integer[] {1});
    	invoiceInput.setOrderDate(orderTime);
    	
		String sigParams = invoiceInput.getMerchantAccount() + ";" + invoiceInput.getMerchantDomainName() + ";"
				+ invoiceInput.getOrderReference() + ";" + invoiceInput.getOrderDate() + ";" + +invoiceInput.getAmount()
				+ ";" + invoiceInput.getCurrency() + ";" + invoiceInput.getProductName()[0] + ";"
				+ invoiceInput.getProductCount()[0] + ";" + invoiceInput.getProductPrice()[0];
    			String merchantSignature = HMAC.hmacDigest(sigParams, invoiceInput.getMerchantSecretKey(), "HmacMD5");

    			invoiceInput.setMerchantSignature(merchantSignature);
    	
    	String jsonData = new Gson().toJson(invoiceInput);
        return Unirest.post(PATH_TO_WAYFORPAY_API)
        		.header("Content-Type", "application/json")
        		.body(jsonData).asJson();
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
