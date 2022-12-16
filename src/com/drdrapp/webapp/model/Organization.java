package com.drdrapp.webapp.model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization implements Comparable<Organization>{
    private final Link title;
    private final String jobTitle;
    private final String description;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;

    public Organization(String organizationTitle, String organizationUrl, String jobTitle, String description, LocalDate dateFrom, LocalDate dateTo) {
        Objects.requireNonNull(organizationTitle, "Title must not be null");
        Objects.requireNonNull(dateFrom, "DateFrom must not be null");
        Objects.requireNonNull(dateTo, "DateTo must not be null");
        this.title = new Link(organizationTitle, organizationUrl);
        this.jobTitle = jobTitle;
        this.description = description;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Link getTitle(){
        return title;
    };

    public String jobTitle(){
        return jobTitle;
    };

    public String getJobTitle(){
        return jobTitle;
    };

    public LocalDate getDateFrom(){
        return dateFrom;
    };

    public LocalDate getDateTo(){
        return dateTo;
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization tmpOrg)) return false;
        if (!title.equals(tmpOrg.title)) return false;
        if (!dateFrom.equals(tmpOrg.dateFrom)) return false;
        if (!dateTo.equals(tmpOrg.dateTo)) return false;
        if (!(Objects.equals(jobTitle, tmpOrg.jobTitle))) return false;
        return Objects.equals(description, tmpOrg.description);
    }
    @Override
    public int hashCode() {
        return Objects.hash(title, jobTitle, description,dateFrom, dateTo);
    }

    @Override
    public String toString() {

        List<String> items = new ArrayList<String>();
        items.add(title.getTitle());
        items.add(String.format("с %s", YearMonth.from(dateFrom)) + String.format(" по %s", YearMonth.from(dateTo)));
        if (!title.getUrl().isEmpty()){
            items.add(title.getUrl());
        }
        if (!jobTitle.isEmpty()){
            items.add(jobTitle);
        }
        if (!description.isEmpty()){
            items.add(description);
        }
        return String.join("\n", items);
    }

    @Override
    public int compareTo(Organization o) {
        Objects.requireNonNull(o);
        Objects.requireNonNull(o.dateFrom);
        Objects.requireNonNull(o.dateTo);
        return o.getDateFrom().compareTo(getDateFrom());
    }
}