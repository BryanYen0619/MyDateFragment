package com.example.bryanyen.mydatefragment.utils;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by bryan.yen on 2017/7/26.
 */

public class Utils {

    public static boolean isLegalDate(int currentYear, int currentMonth, int currentDay) {
        Calendar nowCalendar = Calendar.getInstance();

        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.set(currentYear, currentMonth, currentDay);

        Calendar futureCalendar = Calendar.getInstance();
        futureCalendar.add(Calendar.MONTH, 3);

        return currentCalendar.getTimeInMillis() >= nowCalendar.getTimeInMillis() && currentCalendar
                .getTimeInMillis() <= futureCalendar.getTimeInMillis();
    }

    public static boolean isInOpenDays(int currentYear, int currentMonth, int currentDay, int openDays) {
        if (openDays > 0) {
            Calendar nowCalendar = Calendar.getInstance();

            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.set(currentYear, currentMonth, currentDay);

            Calendar futureCalendar = Calendar.getInstance();
            futureCalendar.add(Calendar.DAY_OF_YEAR, openDays);

            return currentCalendar.getTimeInMillis() >= nowCalendar.getTimeInMillis() && currentCalendar
                    .getTimeInMillis() <= futureCalendar.getTimeInMillis();
        } else {
            return true;
        }
    }

    public static String convertDateToDateString(int year, int month, int day) {
        return year + "-" + String.format(Locale.TAIWAN, "%02d", month + 1) + "-" + String.format
                (Locale.TAIWAN, "%02d", day);
    }
}
