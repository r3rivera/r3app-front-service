package com.ryanrivera.app.frontdesk.exception;

public abstract class BaseAppException extends RuntimeException{

    private String appCode;
    public BaseAppException(String appCode, String message){
        super(message);
        this.appCode = appCode;

    }
    public String getAppCode(){
        return appCode;
    }
}
