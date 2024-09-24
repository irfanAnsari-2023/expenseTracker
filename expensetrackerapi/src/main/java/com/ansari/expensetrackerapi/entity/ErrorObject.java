package com.ansari.expensetrackerapi.entity;

import lombok.Data;

@Data
public class ErrorObject {

	private Integer statusCode;
	
	private String msg;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ErrorObject() {
		super();
		this.statusCode = statusCode;
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ErrorObject [statusCode=" + statusCode + ", msg=" + msg + "]";
	}
	
	
}
