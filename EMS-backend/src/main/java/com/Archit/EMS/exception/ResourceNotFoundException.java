package com.Archit.EMS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    //whenever an employee with given Id does not exist in database we throw this custom exception
    //srpingboot will catch the exception and it will send the error message with the Http status when the employee with the gievn Id does not exist in the database
    public ResourceNotFoundException(String message){
        super(message);
    }

}
