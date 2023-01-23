package com.drdrapp.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationsSection extends AbstractSection {
    private final List<Organization> organizations = new ArrayList<>();

    public OrganizationsSection() {
    }

    public void addItem(Organization item) {
        organizations.add(item);
    }

    public void addItems(Organization... itemsArr) {
        organizations.addAll(Arrays.asList(itemsArr));
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganizationsSection tmpSectionOrganizations)) return false;
        return Objects.equals(organizations, tmpSectionOrganizations.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }

    @Override
    public String toString() {
        var resultStr = new StringBuilder();
        for (var item : organizations) {
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