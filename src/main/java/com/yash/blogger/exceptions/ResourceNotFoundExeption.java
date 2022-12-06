package com.yash.blogger.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundExeption extends RuntimeException{
	
	String resourceName;
	String fieldValue;
	long fieldName;
	
	
	public ResourceNotFoundExeption(String resourceName, String fieldValue, long fieldName) {
		super(String.format("%s not found with %s:%s", resourceName,fieldValue,fieldName));
		this.resourceName = resourceName;
		this.fieldValue = fieldValue;
		this.fieldName = fieldName;
	}

	
}
