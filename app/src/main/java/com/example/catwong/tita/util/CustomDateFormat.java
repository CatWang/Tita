package com.example.catwong.tita.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by CatWong on 9/23/17.
 */

public class CustomDateFormat {
    private static volatile CustomDateFormat sInstance;
    private final SimpleDateFormat mFormatter;
    private final SimpleDateFormat dateToHourStringFormatter;

    private CustomDateFormat() {
        mFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        dateToHourStringFormatter = new SimpleDateFormat("HH:mm");
    }

    public static CustomDateFormat getsInstance() {
        if (sInstance == null) {
            synchronized (CustomDateFormat.class) {
                if (sInstance == null)
                    sInstance = new CustomDateFormat();
            }
        }
        return sInstance;
    }

    public Date getDate(String s) {
        try
        {
            Date date = mFormatter.parse(s);

            System.out.println("date : "+mFormatter.format(date));
            return date;
        }
        catch (ParseException ex)
        {
            System.out.println("Exception "+ex);
            return null;
        }
    }

    public String getTimeFromeDate(Date date) {
        String time = dateToHourStringFormatter.format(date);
        return time;
    }

    public String getDateTimeStringFromDate(Date date) {
        return mFormatter.format(date);
    }

    public Date getTimeFromString(String s) {
        try
        {
            Date date = dateToHourStringFormatter.parse(s);

            System.out.println("date : "+mFormatter.format(date));
            return date;
        }
        catch (ParseException ex)
        {
            System.out.println("Exception "+ex);
            return null;
        }
    }
}
