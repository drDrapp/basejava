package com.drdrapp.webapp.model;

public class Resume {
    private String uuid;

    @Override
    public String toString() {
        return uuid;
    }

    public void setUuid(String newUuid) {
        uuid = newUuid;
    }

    public String getUuid() {
        return uuid;
    }
}