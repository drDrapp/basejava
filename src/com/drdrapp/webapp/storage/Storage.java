package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.model.Resume;

import java.util.List;

public interface Storage {

    void save(Resume r);

    void delete(String uuid);

    Resume get(String uuid);

    void update(Resume r);

    int size();

    List<Resume> getAllSorted();

    void clear();

}