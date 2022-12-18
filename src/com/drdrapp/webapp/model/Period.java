package com.drdrapp.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period implements Comparable<Period> {
    private final String position;
    private final String description;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;

    public Period(String position, String description, LocalDate dateFrom, LocalDate dateTo) {
        Objects.requireNonNull(position, "Position must not be null");
        Objects.requireNonNull(dateFrom, "dateFrom must not be null");
        this.position = position;
        this.description = description;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Period(String position, LocalDate dateFrom, LocalDate dateTo) {
        this(position, null, dateFrom, dateTo);
    }

    public String getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Period period)) return false;
        return Objects.equals(position, period.position) &&
                Objects.equals(description, period.description) &&
                Objects.equals(dateFrom, period.dateFrom) &&
                Objects.equals(dateTo, period.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, description, dateFrom, dateTo);
    }

    @Override
    public String toString() {
        return "position: " + position + " description:  " + description + " dateFrom: " + dateFrom + " dateTo: " + dateTo;
    }

    @Override
    public int compareTo(Period period) {
        return period.dateFrom.compareTo(dateFrom);
    }
}