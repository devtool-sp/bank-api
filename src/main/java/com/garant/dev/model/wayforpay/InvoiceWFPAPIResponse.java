package com.garant.dev.model.wayforpay;

import java.io.Serializable;

public class InvoiceWFPAPIResponse extends WFPAPIResopnse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String invoiceUrl;
	private String qrCode;

	public InvoiceWFPAPIResponse() {

	}

	public String getInvoiceUrl() {
		return invoiceUrl;
	}

	public void setInvoiceUrl(String invoiceUrl) {
		this.invoiceUrl = invoiceUrl;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

}
