package com.drdrapp.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SectionOrganizations extends Section {
    private final List<Organization> items = new ArrayList<>();

    public void addItem(Organization item) {
        items.add(item);
    }

    public void addItems(Organization... itemsArr) {
        items.addAll(Arrays.asList(itemsArr));
    }

    public List<Organization> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SectionOrganizations tmpSectionOrganizations)) return false;
        return Objects.equals(items, tmpSectionOrganizations.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        var resultStr = new StringBuilder();
        for (var item : items) {
            String itemStr = item.toString();
            if (!itemStr.isEmpty()) {
                if (!resultStr.isEmpty()) {
                    resultStr.append("\n");
                }
                resultStr.append(itemStr);
            }
        }
        return resultStr.toString();
    }

}