package ru.javasch.metro.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;


public class Utils {

    private static final int MILLIS_IN_SECONDS = 1000;
    private static final int SECONDS_IN_MINUTES = 60;


    public static Date parseToDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        return format.parse(date);
    }

    public static Date parseToDateTime(String date, String time) throws ParseException {
        StringBuilder str = new StringBuilder(date);
        str.append(" ").append(time);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return format.parse(str.toString());
    }

    public static Long twoDateSubstraction(Date dateOne, Date dateTwo) {
        Calendar calOne = Calendar.getInstance();
        Calendar calTwo = Calendar.getInstance();
        calOne.setTime(dateOne);
        calTwo.setTime(dateTwo);
        Long time1 = calOne.getTimeInMillis();
        Long time2 = calTwo.getTimeInMillis();
        return (time2 - time1) / MILLIS_IN_SECONDS / SECONDS_IN_MINUTES;
    }

    public static String getHelloContext() throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("message");
        String message = bundle.getString("welcome_message");
        return message;
    }

    public static String getInvalidContext() throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("message");
        String message = bundle.getString("invalid_message");
        return message;
    }

    public static String encodePassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
