package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{
    @Override
    protected int getIndexByUuid(String uuid) {
        var searchResume = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, countResume, searchResume);
    }

    @Override
    protected void saveByIndex(Resume r, int index) {
        int insertionIndex = -index - 1;
        System.arraycopy(storage, insertionIndex, storage, insertionIndex + 1, countResume - insertionIndex);
        storage[insertionIndex] = r;
    }

    @Override
    protected void deleteByIndex(int index) {
        System.arraycopy(storage,index+1, storage, index, countResume - index - 1);
    }
}
