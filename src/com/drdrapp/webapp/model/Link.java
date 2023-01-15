package com.drdrapp.webapp.model;

import java.io.Serializable;
import java.util.Objects;

public class Link implements Serializable {
    private final String title;
    private final String url;

    public Link(String linkTitle, String linkUrl) {
        Objects.requireNonNull(linkTitle);
        Objects.requireNonNull(linkUrl);
        this.title = linkTitle;
        this.url = linkUrl;
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
        return title + " {" + url + "}";
    }
}