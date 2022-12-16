package com.drdrapp.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SectionList extends Section {
    private List<String> listItems = new ArrayList<>();

    public SectionList(List<String> listItems) {
        this.listItems = listItems;
    }

    public SectionList() {
    }

    public void addItem(String listItem) {
        listItems.add(listItem);
    }

    public List<String> getItems() {
        return listItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SectionList tmpSectionList)) return false;
        return listItems.equals(tmpSectionList.listItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listItems);
    }

    @Override
    public String toString() {
        return String.join("\n", listItems);
    }
}