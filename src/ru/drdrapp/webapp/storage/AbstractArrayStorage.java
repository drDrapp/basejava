package ru.drdrapp.webapp.storage;

import ru.drdrapp.webapp.exeption.StorageException;
import ru.drdrapp.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int MAX_COUNT_RESUME = 10000;
    protected final Resume[] storage = new Resume[MAX_COUNT_RESUME];
    protected int countResume = 0;

    @Override
    protected Integer getSearchKey(String uuid) {
        return getIndexByUuid(uuid);
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected void doSave(Resume r, Integer searchKey) {
        if (MAX_COUNT_RESUME == countResume) {
            throw new StorageException("Error: storage is full.", r.getUuid());
        }
        saveByIndex(r, searchKey);
        countResume++;
    }

    @Override
    protected void doDelete(Integer searchKey) {
        deleteByIndex(searchKey);
        storage[countResume - 1] = null;
        countResume--;
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    protected void doUpdate(Resume r, Integer searchKey) {
        storage[searchKey] = r;
    }

    public int size() {
        return countResume;
    }

    @Override
    protected List<Resume> doGetAll() {
        return Arrays.asList(Arrays.copyOf(storage, countResume));
    }

    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    protected abstract int getIndexByUuid(String uuid);

    protected abstract void saveByIndex(Resume r, int index);

    protected abstract void deleteByIndex(int index);

}