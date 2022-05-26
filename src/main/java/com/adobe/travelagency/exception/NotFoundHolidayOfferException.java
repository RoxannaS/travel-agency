package com.adobe.travelagency.exception;

public class NotFoundHolidayOfferException extends RuntimeException {

    public NotFoundHolidayOfferException() {
        super("Holiday offer not found!");
    }

}
