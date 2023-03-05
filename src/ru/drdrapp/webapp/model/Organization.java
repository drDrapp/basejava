package ru.drdrapp.webapp.model;

import ru.drdrapp.webapp.util.XmlLocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Comparable<Organization>, Serializable {
    private Link title;
    private List<Period> periods = new ArrayList<>();

    public Organization() {
    }

    public Organization(String organizationTitle, String organizationUrl, Period... periods) {
        Objects.requireNonNull(organizationTitle, "Title must not be null");
        Objects.requireNonNull(periods, "Periods must not be null");
        this.title = new Link(organizationTitle, organizationUrl);
        this.periods.addAll(List.of(periods));
    }

    public Organization(String organizationTitle, String organizationUrl, List<Period> periods) {
        this.title = new Link(organizationTitle, organizationUrl);
        this.periods = periods;
    }

    public Link getTitle() {
        return title;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return title.equals(that.title) && periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, periods);
    }

    @Override
    public String toString() {
        List<String> items = new ArrayList<>();
        items.add(title.getTitle());
        if (!title.getUrl().isEmpty()) {
            items.add(title.getUrl());
        }
        for (Period period : periods
        ) {
            items.add(period.getPosition());
            if (period.getDescription() != null) {
                items.add(period.getDescription());
            }
            if (period.getDateTo() == null) {
                items.add(String.format("с %s", YearMonth.from(period.getDateFrom())) + " по н.в.");
            } else {
                items.add(String.format("с %s", YearMonth.from(period.getDateFrom())) + String.format(" по %s", YearMonth.from(period.getDateTo())));
            }
        }
        return String.join("\n", items);
    }

    @Override
    public int compareTo(Organization o) {
        Objects.requireNonNull(o);
        return o.title.getTitle().compareTo(title.getTitle());
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Comparable<Period>, Serializable{
        private String position;
        private String description;
        @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
        private LocalDate dateFrom;
        @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
        private LocalDate dateTo;

        public Period() {
        }

        public Period(String position, String description, LocalDate dateFrom, LocalDate dateTo) {
            Objects.requireNonNull(position, "Position must not be null");
            Objects.requireNonNull(dateFrom, "DateFrom must not be null");
            this.position = position;
            this.description = description == null ? "" : description;
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
            if (o == null || getClass() != o.getClass()) return false;
            Period period = (Period) o;
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
}