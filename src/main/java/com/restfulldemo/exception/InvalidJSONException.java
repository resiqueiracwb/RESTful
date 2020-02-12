package com.restfulldemo.exception;

public class InvalidJSONException extends Exception {

	private static final long serialVersionUID = -4175972843472305288L;
	
	public InvalidJSONException(String msg){
		super(msg);
	}
	
	public InvalidJSONException(String msg, Throwable cause){
		super(msg, cause);
	}
}
