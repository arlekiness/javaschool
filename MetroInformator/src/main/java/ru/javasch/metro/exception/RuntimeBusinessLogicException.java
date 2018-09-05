package ru.javasch.metro.exception;

public class RuntimeBusinessLogicException extends RuntimeException{
    private String error;

    public RuntimeBusinessLogicException(String error){
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
