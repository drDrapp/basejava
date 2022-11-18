package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{
    @Override
    protected int getIndexByUuid(String uuid) {
        var searchResume = new Resume();
        searchResume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, countResume, searchResume);
    }

    @Override
    protected void saveByIndex(Resume r, int index) {
        for (int i = countResume; i >= -index; i--) {
            storage[i] = storage[i - 1];
        }
        storage[-index - 1] = r;
    }

    @Override
    protected void deleteByIndex(int index) {
        for (int i = index; i < countResume - 1; i++) {
            storage[i] = storage[i + 1];
        }
    }
}
