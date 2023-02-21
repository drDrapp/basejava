package com.drdrapp.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    PHONE_MOBILE("Моб.телефон"),
    PHONE_HOME("Дом.телефон"),
    SKYPE("Skype"),
    EMAIL("Почта"),
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
}