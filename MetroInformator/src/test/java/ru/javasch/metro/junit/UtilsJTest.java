package ru.javasch.metro.junit;

import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;
import ru.javasch.metro.utils.Utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

@Log4j
public class UtilsJTest {

    @Test
    public void checkSubstractionOne() throws ParseException {
        String oneDate = "11.10.2018";
        String oneTime =  "14:34";
        String twoDate = "11.10.2018";
        String twoTime =  "15:34";
        Date one = Utils.parseToDateTime(oneDate, oneTime);
        Date two = Utils.parseToDateTime(twoDate, twoTime);
        Assert.assertTrue(Utils.twoDateSubstraction(one, two) == 60L);
    }

    @Test
    public void checkHSMSZero() throws ParseException {
        String oneDate = "11.10.2018";
        String oneTime =  "14:34";
        String twoDate = "11.10.2018";
        String twoTime =  "12:00";
        Date one = Utils.parseToDateTime(oneDate, oneTime);
        Date two = Utils.parseToDateTime(twoDate, twoTime);
        Calendar oneCal = Calendar.getInstance();
        oneCal.setTime(one);
        Utils.setHMSMfieldsInZero(oneCal);
        one = oneCal.getTime();
        Assert.assertTrue(one.equals(two));
    }
}
