package com.drdrapp.webapp.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private String uuid;
    private String fullName;
    private final Map<ContactType, String> contacts = new HashMap<>();
    private final Map<SectionType, AbstractSection> sections = new HashMap<>();

    public Resume() {
    }

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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void addContact(ContactType contactType, String val) {
        contacts.put(contactType, val);
    }

    public String getContact(ContactType contactType) {
        return contacts.get(contactType);
    }

    public void addSection(SectionType sectionType, AbstractSection abstractSection) {
        sections.put(sectionType, abstractSection);
    }

    public AbstractSection getSection(SectionType sectionType) {
        return sections.get(sectionType);
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public int compareTo(Resume r) {
        int fullNameComparisonRes = fullName.compareTo(r.getFullName());
        return (fullNameComparisonRes == 0) ? uuid.compareTo(r.getUuid()) : fullNameComparisonRes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, sections);
    }

    @Override
    public String toString() {
        return fullName + " {" + uuid + "}";
    }

}