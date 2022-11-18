package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int MAX_COUNT_RESUME = 10000;
    protected final Resume[] storage = new Resume[MAX_COUNT_RESUME];
    protected int countResume = 0;

    public void save(Resume r) {
        if (MAX_COUNT_RESUME == countResume) {
            System.out.println("Error: storage is full.");
            return;
        }
        int index = getIndexByUuid(r.getUuid());
        if ( index>= 0) {
            System.out.println("Error: resume '" + r.getUuid() + "' already exist.");
        } else {
            saveByIndex(r, index);
            countResume++;
            System.out.println("Resume '" + r.getUuid() + "' - successfully saved.");
        }
    }

    public void deleteByUuid(String uuid) {
        int index = getIndexByUuid(uuid);
        if (index < 0) {
            System.out.println("Error: resume '" + uuid + "' can`t delete, it not found.");
        } else {
            deleteByIndex(index);
            storage[countResume - 1] = null;
            countResume--;
            System.out.println("Resume '" + uuid + "' - successfully deleted.");
        }
    }

    public Resume getByUuid(String uuid) {
        int index = getIndexByUuid(uuid);
        if (index < 0) {
            System.out.println("Error: resume '" + uuid + "' not found.");
            return null;
        } else {
            return storage[index];
        }
    }

    public void update(Resume r) {
        int index = getIndexByUuid(r.getUuid());
        if (index < 0) {
            System.out.println("Error: resume '" + r.getUuid() + "' can`t update, it not found.");
        } else {
            storage[index] = r;
            System.out.println("Resume '" + r.getUuid() + "' - successfully updated.");
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