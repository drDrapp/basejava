package com.drdrapp.webapp.model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization implements Comparable<Organization>{
    private final Link title;
    private final List<Period> periods = new ArrayList<>();

    public Organization(String organizationTitle, String organizationUrl, Period... periods) {
        Objects.requireNonNull(organizationTitle, "Title must not be null");
        Objects.requireNonNull(periods, "Periods must not be null");
        this.title = new Link(organizationTitle, organizationUrl);
        this.periods.addAll(List.of(periods));
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
        if (!(o instanceof Organization tmpOrg)) return false;
        return title.equals(tmpOrg.title) && periods.equals(tmpOrg.periods);
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
}