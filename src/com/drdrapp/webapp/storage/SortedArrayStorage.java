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
        System.arraycopy(storage,-index-1, storage, -index, countResume + index + 1);
        storage[-index - 1] = r;
    }

    @Override
    protected void deleteByIndex(int index) {
        System.arraycopy(storage,index+1, storage, index, countResume - index - 1);
    }
}
