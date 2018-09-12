package ru.javasch.metro.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Utils {

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
        return (time2 - time1) / 1000 / 60 ;
    }

    public static String getHelloContext() throws IOException {
        File file = ResourceUtils.getFile("classpath:messages/templateForEmailWelcomeMessage.html");
        return new String(Files.readAllBytes(file.toPath()));
    }

    public static String getInvalidContext() throws IOException {
        File file = ResourceUtils.getFile("classpath:messages/templateForEmailInvalidateMessage.html");
        return new String(Files.readAllBytes(file.toPath()));
    }
}
