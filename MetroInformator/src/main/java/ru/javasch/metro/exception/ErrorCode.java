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
    public static final String UNCORRECT_PASSWORD = "Password must contain not less 6 numbers or letters";
    public static final String UNCORRECT_EMAIL = "Invalid email";
}
