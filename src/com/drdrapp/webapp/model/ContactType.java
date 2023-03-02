package com.drdrapp.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    PHONE_MOBILE("Моб.телефон"),
    PHONE_HOME("Дом.телефон"),
    SKYPE("Skype") {
        @Override
        public String toHtml_Title(String val) {
            return "<a href= \"skype:" + val + "\">" + val + "</a>";
        }
    },
    EMAIL("Почта") {
        @Override
        protected String toHtml_Title(String val) {
            return "<a href= \"mailto:" + val + "\">" + val + "</a>";
        }
    },
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOME_PAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    protected String toHtml_Title(String val) {
        return title + ":" + val;
    }

    public String toHtml(String val) {
        return (val == null) ? "" : toHtml_Title(val);
    }

}