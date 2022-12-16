package com.drdrapp.webapp.model;

import java.util.Objects;

public class Link {
    private final String title;
    private final String url;

    public Link(String title, String url) {
        Objects.requireNonNull(title);
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link tmpLink)) return false;
        return Objects.equals(title, tmpLink.title) && Objects.equals(url, tmpLink.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url);
    }

    @Override
    public String toString() {
        if (url == null || url == "") {
            return title;
        } else {
            return title + " <" + url + ">";
        }

    }
}