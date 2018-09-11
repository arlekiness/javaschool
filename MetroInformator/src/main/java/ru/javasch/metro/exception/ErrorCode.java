package ru.javasch.metro.exception;

public enum ErrorCode {
    USER_ALREADY_EXIST(1, "User already was registered. Try another e-mail or sign in."),
    NULL_ELEMENTS(4, "Wrong parameters, not found.");

    private int code;
    private String reason;

    private ErrorCode(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public String getMessage() {
        return String.format("[CODE]:[" + code + "], [MESSAGE]: " + reason);
    }
}
