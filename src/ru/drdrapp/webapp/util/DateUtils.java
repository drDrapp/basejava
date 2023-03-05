package ru.drdrapp.webapp.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.MONTHS;

public class DateUtils {
    private static final LocalDate DATE_OFF = LocalDate.of(9999, 12, 31);
    private static final String DATE_NOW = "Cейчас";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    public static String periodToString(LocalDate date1, LocalDate date2) {
        var d1 = localDateToString(date1);
        var d2 = localDateToString(date2);
        if (d1.equals(DATE_NOW) || (d1.equals(DATE_NOW) && d2.equals(DATE_NOW))) {
            return DATE_NOW;
        }
        return (!isEmpty(d1) && !isEmpty(d2)) ? d1 + " - " + d2 : d1 + d2;
    }

    public static String localDateToString(LocalDate date) {
        return date.equals(DATE_OFF) ? "" : ( checkNow(date) ? DATE_NOW : date.format(DATE_FORMATTER));
    }

    public static LocalDate stringToLocalDate(String date) {
        if (DATE_NOW.equals(date)){
            return LocalDate.now();
        }
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

    public static boolean checkNow(LocalDate date){
        return MONTHS.between(date, LocalDate.now()) == 0;
    }

    public static String localDateForEdit(LocalDate date) {
        return date.equals(DATE_OFF) ? "" :
                ( checkNow(date) ?
                        LocalDate.now().format(DATE_FORMATTER) :
                        date.format(DATE_FORMATTER));
    }

}