package ru.javasch.metro.utils;

public class URLs {
    /**Admin Controller URLs */
    public static final String REDIRECT_DASHTRAIN = "redirect:/dashtrain";
    public static final String CREATE_TRAIN = "/createtrain";
    public static final String DELETE_TRAIN_BY_ID = "/deleteTrain/{id}";
    public static final String CLOSE_STATION_BY_NAME = "/closeStation/{stationName}";
    public static final String OPEN_STATION_BY_NAME = "/openStation/{stationName}";
    public static final String DASHTRAIN = "/dashtrain";
    public static final String DASHTRAIN_COUNT = "/dashtrain/{count}";
    public static final String DASHSTATION = "/dashstation";
    public static final String DASHSTATION_COUNT = "/dashstation/{stcount}";
    public static final String PASSENGERS_BY_TRAIN_ID = "/passengers/{id}";
    public static final String REDIRECT_DASHSTATION = "redirect:/dashstation";


    /**Board Controller URLs */
    public static final String GET_SCHEDULE_FOR_BOARD = "/boardschedule/schedulestoday";
    public static final String GET_ALL_STATIONS_FOR_BOARD = "/boardschedule/allstations";

    /**Ticket Controller URLs */
    public static final String TICKETS = "/tickets";
    public static final String TICKETS_FAIL = "/ticketsFail";
    public static final String TICKET_BOOKING = "/registerTickets/{count}";
    public static final String MY_TICKETS = "/myTickets";
    public static final String REDIRECT_TICKET_FAIL = "redirect:/ticketsFail";
    public static final String REDIRECT_MY_TICKETS = "redirect:/myTickets";

    /**Schedule Controller URLs */
    public static final String SCHEDULE = "/schedule";

    /**User Controller URLs */
    public static final String REGISTRATION = "/registration";
    public static final String LOGIN = "/login";
    public static final String EMPTY_URL = "/";
    public static final String HOME = "/home";
    public static final String LOGOUT = "/logout";
}
