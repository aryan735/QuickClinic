package com.quickclinic.userauth.exception;

public class UserAuthException extends RuntimeException{
    public UserAuthException(String error){
        super(error);
    }
}
