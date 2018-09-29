package ru.javasch.metro.exception;

public enum ErrorCodeEnum {

    USER_EXIST(1, "User already exist"),
    EMPTY_FIELDS(2, "One or two fields are empty"),
    BEGIN_STATION_CLOSED(3, "Begin station is closed"),
    END_STATION_CLOSED(4, "End station is closed"),
    ATS_ARE_CLOSED(5, "All Transition Stations are Closed. Can't find the way"),
    STATION_CLOSED(6, "Station closed for some reason"),
    NO_MORE_TICKETS(7, "All tickets was already booked. Please try new search"),
    TRAIN_EXIST(8, "Such train already exist"),
    NO_TRAIN_ON_DATE(9, "No trains on that date"),
    INCORRECT_PASSWORD(10, "Password must contain not less 6 numbers or letters"),
    INCORRECT_EMAIL(11, "Invalid email"),
    TO_LATE_FOR_TRAIN(12, "To late for train"),
    EMPTY_FIELDS_TRAIN_FORM(13, "One or two fields are empty in train form are empty"),
    INCORRECT_DATE_SCHEDULE(14, "Your date is in past"),
    INCORRECT_DATE_TICKETS(15, "You trying to get tickets in past"),
    TRAIN_IN_PAST(16, "You trying to add train in past"),
    DONT_KNOW_STATION (17, "No such station in that form");

    private int code;
    private String reason;

    ErrorCodeEnum(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public String getMessage() {
        return String.format("[CODE]:[" + code + "], [MESSAGE]: " + reason);
    }

    public int getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }
}
