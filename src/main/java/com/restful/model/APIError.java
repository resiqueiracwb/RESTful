package com.restful.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class APIError {

	
	private HttpStatus status;
	private int statusCode;
    private String message;
    private List<String> errors;
 
    public APIError(HttpStatus status, int statusCode, String message, String error) {
        super();
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        errors = Arrays.asList(error);
    }
    
    public APIError(String message) {
        super();
        this.message = message;
    }
}
