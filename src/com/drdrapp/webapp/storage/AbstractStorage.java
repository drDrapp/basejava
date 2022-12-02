package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.exeption.ExistStorageException;
import com.drdrapp.webapp.exeption.NotExistStorageException;
import com.drdrapp.webapp.model.Resume;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void saveBySearchKey(Resume r, Object searchKey);

    protected abstract void deleteBySearchKey(Object searchKey);

    protected abstract Resume getBySearchKey(Object searchKey);

    protected abstract void updateBySearchKey(Resume r, Object searchKey);

    public void save(Resume r) {
        Object searchKey = tryGetSearchKey(r.getUuid(), TRUE);
        saveBySearchKey(r, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = tryGetSearchKey(uuid, FALSE);
        deleteBySearchKey(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = tryGetSearchKey(uuid, FALSE);
        return getBySearchKey(searchKey);
    }

    public void update(Resume r) {
        Object searchKey = tryGetSearchKey(r.getUuid(), FALSE);
        updateBySearchKey(r, searchKey);
    }

    private Object tryGetSearchKey(String uuid, boolean exceptionIfExist) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            if (exceptionIfExist) {
                throw new ExistStorageException(uuid);
            } else {
                return searchKey;
            }
        } else {
            if (exceptionIfExist) {
                return searchKey;
            } else {
                throw new NotExistStorageException(uuid);
            }
        }
    }
}