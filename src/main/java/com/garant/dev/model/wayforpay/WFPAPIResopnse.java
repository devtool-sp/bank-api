package com.garant.dev.model.wayforpay;

import java.io.Serializable;

public class WFPAPIResopnse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer reasonCode;
	private String reason;

	public WFPAPIResopnse() {

	}

	public Integer getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(Integer reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
