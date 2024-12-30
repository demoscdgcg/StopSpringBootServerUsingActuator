package com.example.card.exception;

public class RescourceNotFoundException extends RuntimeException{

    public RescourceNotFoundException(String message,String feildName,String feildValue){
        super(String.format("%s there is some problem %s:%s",message,feildName,feildValue));
    }
}
