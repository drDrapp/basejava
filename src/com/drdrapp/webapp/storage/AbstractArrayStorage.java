package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.exeption.ExistStorageException;
import com.drdrapp.webapp.exeption.NotExistStorageException;
import com.drdrapp.webapp.exeption.StorageException;
import com.drdrapp.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int MAX_COUNT_RESUME = 10000;
    protected final Resume[] storage = new Resume[MAX_COUNT_RESUME];
    protected int countResume = 0;

    public void save(Resume r) {
        if (MAX_COUNT_RESUME == countResume) {
            throw new StorageException("Error: storage is full.", r.getUuid());
        }
        int index = getIndexByUuid(r.getUuid());
        if ( index>= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveByIndex(r, index);
            countResume++;
        }
    }

    public void delete(String uuid) {
        int index = getIndexByUuid(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteByIndex(index);
            storage[countResume - 1] = null;
            countResume--;
        }
    }

    public Resume get(String uuid) {
        int index = getIndexByUuid(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return storage[index];
        }
    }

    public void update(Resume r) {
        int index = getIndexByUuid(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[index] = r;
        }
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