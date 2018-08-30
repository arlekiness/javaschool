package ru.javasch.metro.configuration.constants;

import org.joda.time.DateTime;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class Utils {

    public static String encodePassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static boolean checkTransfer(Date dateDeparture, Date dateArrival, int deltaMin, int deltaMax) {
        Long delta = (dateArrival.getTime() - dateDeparture.getTime()) / (1000 * 60);
        return (delta >= deltaMin && delta <= deltaMax) ? true : false;
    }

    public static Date getNextDay(String dateForNextDay) throws ParseException {
        Date date = parseToDate(dateForNextDay);
        Date newDate = new Date();
        newDate.setTime(date.getTime() + (long) 1000 * 24 * 60 * 60);
        return newDate;
    }

    public static Date getNextDay(Date date) {
        Date newDate = new Date();
        newDate.setTime(date.getTime() + (long) 1000 * 24 * 60 * 60);
        return newDate;
    }

    public static Date parseToDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(date);
    }

    public static Date parseToDateTime(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(date);
    }

    public static String getHelloContext() throws IOException {
        File file = ResourceUtils.getFile("classpath:messages/templateForEmailWelcomeMessage.html");
        return new String(Files.readAllBytes(file.toPath()));
    }

    public static String getTicketContext() throws IOException {
        File file = ResourceUtils.getFile("classpath:messages/templateForEmailTicketMessage.html");
        return new String(Files.readAllBytes(file.toPath()));
    }

    public static Date getTodayDateTime() throws ParseException {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        return parseToDateTime(df.format(date));
    }

    public static boolean checkCurrentDay(Date date) {
        DateTime dateTime = new DateTime(date.getTime());
        return dateTime.isBeforeNow();
    }

    public static boolean checkForCurrentDayForBookingTicket(Date date) {
        Date currentDay = new Date();
        return (!checkCurrentDay(date) && (date.getTime() - currentDay.getTime()) / (1000 * 60) > 10);
    }
}
