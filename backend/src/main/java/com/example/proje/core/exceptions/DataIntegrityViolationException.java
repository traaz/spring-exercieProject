package com.example.proje.core.exceptions;

public class DataIntegrityViolationException extends org.springframework.dao.DataIntegrityViolationException {

    public DataIntegrityViolationException(String message){
        super(message);
    }
}
