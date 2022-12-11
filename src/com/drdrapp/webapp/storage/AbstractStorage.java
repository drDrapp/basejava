package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.exeption.ExistStorageException;
import com.drdrapp.webapp.exeption.NotExistStorageException;
import com.drdrapp.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    public static final Comparator<Resume> COMPARE_BY_NAME = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract List<Resume> doGetAll();

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

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> tmpResumeList = doGetAll();
        tmpResumeList.sort(COMPARE_BY_NAME);
        return tmpResumeList;
    }

}