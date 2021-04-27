package com.hhy.wj.result;

public class Result {
	// 响应码
	private int code;
	
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
	
	private String message;
	private Object result;
	
	public Result(int code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.result = data;
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
}
