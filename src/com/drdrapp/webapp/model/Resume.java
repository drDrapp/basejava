package com.drdrapp.webapp.model;

import com.drdrapp.webapp.util.TestAnnotation;

import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume> {

    private final String uuid;
    private final String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "UUID is empty");
        Objects.requireNonNull(fullName, "FullName is empty");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @TestAnnotation
    @Override
    public String toString() {
        return fullName + " {" + uuid + "}";
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
        if (!uuid.equals(resume.uuid)) {
            return false;
        }
        return Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public int compareTo(Resume r) {
        return uuid.compareTo(r.uuid);
    }
}