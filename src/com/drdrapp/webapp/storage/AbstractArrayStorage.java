package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.exeption.StorageException;
import com.drdrapp.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int MAX_COUNT_RESUME = 10000;
    protected final Resume[] storage = new Resume[MAX_COUNT_RESUME];
    protected int countResume = 0;

    @Override
    protected Integer getSearchKey(String uuid) {
        return getIndexByUuid(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        if (MAX_COUNT_RESUME == countResume) {
            throw new StorageException("Error: storage is full.", r.getUuid());
        }
        saveByIndex(r, (int) searchKey);
        countResume++;
    }

    @Override
    protected void doDelete(Object searchKey) {
        deleteByIndex((int) searchKey);
        storage[countResume - 1] = null;
        countResume--;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage[(int) searchKey] = r;
    }

    public int size() {
        return countResume;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, countResume);
    }

    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    protected abstract int getIndexByUuid(String uuid);

    protected abstract void saveByIndex(Resume r, int index);

    protected abstract void deleteByIndex(int index);

}