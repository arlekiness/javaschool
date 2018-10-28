package ru.javasch.metro.utils;

import lombok.extern.log4j.Log4j;
import ru.javasch.metro.dto.ScheduleDTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Log4j
public class Utils {
    public static final String URL_SCHEDULES = "http://localhost:8000/boardschedule/schedulestoday";
    public static final String URL_STATIONS = "http://localhost:8000/boardschedule/allstations";

    public static String parseToTime(String date) {
        Date dateToday = parseToDateTime(date);
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        return localDateFormat.format(dateToday);
    }

    public static Date getTodayDateTime() throws ParseException {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        return parseToDate(df.format(date));
    }

    public static Date parseToDateTime(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            log.error("ParseException", e);
        }
        return new Date();
    }

    public static Date parseToDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(date);
    }

    public static boolean checkScheduleForToday(ScheduleDTO schedule) {
        try {
//            log.info("schedule " + schedule.getStationDepartureName() + " - " + schedule.getStationArrivalName() + " " + schedule.getDateDeparture() + " " + schedule.getDateArrival());
            Date dateArrival = parseToDate(schedule.getDateArrival());
            Date dateToday = getTodayDateTime();
            log.info("arrival" + dateArrival + " today" + dateToday + " status " + dateArrival.equals(dateToday));
            return dateArrival.equals(dateToday);
        } catch (ParseException e) {
            log.error("ParseException", e);
        }
        return false;
    }
}
