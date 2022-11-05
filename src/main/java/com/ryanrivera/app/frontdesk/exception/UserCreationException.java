package com.ryanrivera.app.frontdesk.exception;

public class UserCreationException extends BaseAppException{
    public UserCreationException(String appCode, String message) {
        super(appCode, message);
    }
}
