package com.restful.exception;

import com.restful.model.APIError;

public class TransactionNotFoundException extends Exception {

	private static final long serialVersionUID = -2586209354700102349L;

	public TransactionNotFoundException() {
		super();
	}

	public TransactionNotFoundException(String msg) {
		super(msg);
	}

	public TransactionNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public TransactionNotFoundException(APIError error) {
		super(error.getMessage());
	}

}