package com.citcd.prueba.models.dto;

import java.util.List;

public class Response {
	
	private Boolean isOk;
	private String message;
	private Object result;
	private List<?> results;
	private String error;
	
	public Response(boolean isOk, String message, Object result) {
		this.isOk = isOk;
		this.message = message;
		this.result = result;
	}
	
	public Response() {
		
	}

	public Boolean getIsOk() {
		return isOk;
	}

	public void setIsOk(Boolean isOk) {
		this.isOk = isOk;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public List<?> getResults() {
		return results;
	}

	public void setResults(List<?> results) {
		this.results = results;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
	

}
