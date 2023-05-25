package com.driver;

public class MovieNameInvalidException extends  RuntimeException{
    public MovieNameInvalidException(String msg) {
        super(msg);
    }
}
