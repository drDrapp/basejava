package com.drdrapp.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private List<String> items = new ArrayList<>();

    public ListSection() {
    }

    public ListSection(List<String> items) {
        this.items = items;
    }

    public void addItem(String listItem) {
        items.add(listItem);
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListSection tmpSectionList)) return false;
        return items.equals(tmpSectionList.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        return String.join("\n", items);
    }
}