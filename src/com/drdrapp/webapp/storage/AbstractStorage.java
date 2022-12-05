package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.exeption.ExistStorageException;
import com.drdrapp.webapp.exeption.NotExistStorageException;
import com.drdrapp.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    public void save(Resume r) {
        Object searchKey = getNotExistSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistSearchKey(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getExistSearchKey(uuid);
        return doGet(searchKey);
    }

    public void update(Resume r) {
        Object searchKey = getExistSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    private Object getExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) throw new NotExistStorageException(uuid);
        return searchKey;
    }

    private Object getNotExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) throw new ExistStorageException(uuid);
        return searchKey;
    }

}