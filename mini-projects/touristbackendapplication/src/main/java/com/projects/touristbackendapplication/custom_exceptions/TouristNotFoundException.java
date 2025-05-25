package com.projects.touristbackendapplication.custom_exceptions;

public class TouristNotFoundException extends RuntimeException {

    public TouristNotFoundException(String msg) {
        super(msg);
    }

}
