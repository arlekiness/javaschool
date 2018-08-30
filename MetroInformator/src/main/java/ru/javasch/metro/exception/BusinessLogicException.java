package ru.javasch.metro.exception;

public class BusinessLogicException extends Exception {

    private String error;

    public BusinessLogicException(String error){
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
