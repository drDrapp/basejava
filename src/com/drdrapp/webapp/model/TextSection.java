package com.drdrapp.webapp.model;

import java.util.Objects;

public class TextSection extends AbstractSection {
    private final String text;

    public TextSection(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TextSection tmpSectionText)) return false;
        return text.equals(tmpSectionText.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return text;
    }
}