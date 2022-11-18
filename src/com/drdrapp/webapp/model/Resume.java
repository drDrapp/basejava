package com.drdrapp.webapp.model;

public class Resume implements Comparable<Resume> {
    private String uuid;

    public void setUuid(String newUuid) {
        uuid = newUuid;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public int compareTo(Resume r) {
        return uuid.compareTo(r.uuid);
    }
}