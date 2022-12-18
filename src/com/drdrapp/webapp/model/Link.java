package com.drdrapp.webapp.model;

import java.util.Objects;

public class Link {
    private final String linkTitle;
    private final String linkUrl;

    public Link(String linkTitle, String linkUrl) {
        Objects.requireNonNull(linkTitle);
        Objects.requireNonNull(linkUrl);
        this.linkTitle = linkTitle;
        this.linkUrl = linkUrl;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link tmpLink)) return false;
        return Objects.equals(linkTitle, tmpLink.linkTitle) && Objects.equals(linkUrl, tmpLink.linkUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkTitle, linkUrl);
    }

    @Override
    public String toString() {
        return linkTitle + " {" + linkUrl + "}";
    }
}