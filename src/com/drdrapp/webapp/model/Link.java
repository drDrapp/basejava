package com.drdrapp.webapp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Link implements Serializable {
    private String title;
    private String url;

    public Link() {
    }

    public Link(String linkTitle, String linkUrl) {
        Objects.requireNonNull(linkTitle);
        this.title = linkTitle;
        this.url = linkUrl == null ? "" : linkUrl;
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
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return title.equals(link.title) && Objects.equals(url, link.url);
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