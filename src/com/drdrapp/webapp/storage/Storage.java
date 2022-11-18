package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.model.Resume;

public interface Storage {

    void save(Resume r);

    void deleteByUuid(String uuid);

    Resume getByUuid(String uuid);

    void update(Resume r);

    int size();

    Resume[] getAll();

    void clear();

}