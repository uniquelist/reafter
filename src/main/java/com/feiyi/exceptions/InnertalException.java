package com.feiyi.exceptions;

public class InnertalException extends Exception {

    private String message;

    public InnertalException(){
        super();
    }
    public InnertalException(String message){
        super();
        this.message = message;
    }
}
