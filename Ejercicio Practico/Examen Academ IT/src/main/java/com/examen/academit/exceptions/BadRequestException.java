package com.examen.academit.exceptions;

public class BadRequestException extends Exception{
    //Constructores
    public BadRequestException() {
    }
    public BadRequestException(String message) {
            super(message);
    }
}

