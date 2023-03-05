package ru.drdrapp.webapp.util;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;

public class XmlLocalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String s){
        return LocalDate.parse(s);
    }

    @Override
    public String marshal(LocalDate localDate){
        return localDate.toString();
    }
}