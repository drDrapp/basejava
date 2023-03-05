package ru.drdrapp.webapp.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static final LocalDate DATE_OFF = LocalDate.of(9999, 12, 31);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    public static String periodToString(LocalDate date1, LocalDate date2) {
        var d1 = localDateToString(date1);
        var d2 = localDateToString(date2);
        return (!isEmpty(d1) && !isEmpty(d2)) ? d1 + " - " + d2 : d1 + d2;
    }

    public static String localDateToString(LocalDate date) {
        return date.equals(DATE_OFF) ? "" : date.format(DATE_FORMATTER);
    }

    public static LocalDate stringToLocalDate(String date) {
        if (date.matches("^\\d{1,2}/\\d{4}$")) {
            YearMonth yearMonth = YearMonth.parse(date, DATE_FORMATTER);
            return LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), 1);
        } else return DATE_OFF;
    }

    public static boolean isEmpty(String string) {
        return string == null || string.trim().length() == 0;
    }

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

}