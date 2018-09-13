package ru.javasch.metro.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;


public class Utils {

    private static final int MILLIS_IN_SECONDS = 1000;
    private static final int SECONDS_IN_MINUTES = 60;


    public static Date parseToDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(date);
    }

    public static Date parseToDateTime(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(date);
    }

    public static Long twoDateSubstraction (Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        Long time1 = cal1.getTimeInMillis();
        Long time2 = cal2.getTimeInMillis();
        return (time2 - time1) / MILLIS_IN_SECONDS / SECONDS_IN_MINUTES ;
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
