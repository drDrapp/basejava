package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage{
    protected List<Resume> storage = new ArrayList<>();
    @Override
    protected Integer getSearchKey(String uuid) {
        for (var listIterator = storage.listIterator(); listIterator.hasNext(); ) {
            Resume r = listIterator.next();
            if (r.getUuid().equals(uuid)) return listIterator.previousIndex();
        }
        return -1;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (int) searchKey >= 0;
    }

    @Override
    protected void saveBySearchKey(Resume r, Object searchKey) {
        storage.add(r);
    }

    @Override
    protected void deleteBySearchKey(Object searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    protected Resume getBySearchKey(Object searchKey) {
        return storage.get((int) searchKey);
    }

    @Override
    protected void updateBySearchKey(Resume r, Object searchKey) {
        storage.set((int) searchKey, r);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public void clear() {
        storage.clear();
    }
}