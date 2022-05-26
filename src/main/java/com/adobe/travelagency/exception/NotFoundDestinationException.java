package com.adobe.travelagency.exception;

public class NotFoundDestinationException extends RuntimeException {

    public NotFoundDestinationException() {
        super("Destination not found!");
    }

}
