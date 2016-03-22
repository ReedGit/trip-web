package com.bysj.exception;

public class PredictableException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public PredictableException(String message){
        super(message);
    }

}
