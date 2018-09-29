package ru.javasch.metro.exception;

public class ErrorCode {
    public static final String USER_EXIST = "User already exist";
    public static final String EMPTY_FIELDS = "One or two fields are empty";
    public static final String BEGIN_STATION_CLOSED = "Begin station is closed";
    public static final String END_STATION_CLOSED = "End station is closed";
    public static final String ATS_ARE_CLOSED = "All Transition Stations are Closed. Can't find the way";
    public static final String STATION_CLOSED = "Station closed for some reason";
    public static final String NO_MORE_TICKETS = "All tickets was already booked. Please try new search";
    public static final String TRAIN_EXIST = "Such train already exist";
    public static final String NO_TRAIN_ON_DATE = "No trains on that date";
    public static final String INCORRECT_PASSWORD = "Password must contain not less 6 numbers or letters";
    public static final String INCORRECT_EMAIL = "Invalid email";
    public static final String TO_LATE_FOR_TRAIN = "To late for train";
    public static final String EMPTY_FIELDS_TRAIN_FORM = "One or two fields are empty in train form are empty";
    public static final String INCORRECT_DATE_SCHEDULE = "Your date is in past";
    public static final String INCORRECT_DATE_TICKETS = "You trying to get tickets in past";
    public static final String TRAIN_IN_PAST = "You trying to add train in past";
    public static final String DONT_KNOW_STATION = "No such station in that form";

}
