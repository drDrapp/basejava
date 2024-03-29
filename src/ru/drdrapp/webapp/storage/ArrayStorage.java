package ru.drdrapp.webapp.storage;

import ru.drdrapp.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    protected int getIndexByUuid(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveByIndex(Resume r, int index) {
        storage[countResume] = r;
    }

    @Override
    protected void deleteByIndex(int index) {
        storage[index] = storage[countResume - 1];
    }
}