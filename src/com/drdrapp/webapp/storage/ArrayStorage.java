package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private static final int MAX_COUNT_RESUME = 10000;
    private int countResume = 0;
    private final Resume[] storage = new Resume[MAX_COUNT_RESUME];

    public void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    public void save(Resume r) {
        if (MAX_COUNT_RESUME == countResume) {
            System.out.println("Error: storage is full.");
            return;
        } else if (getIndex(r.getUuid()) >= 0) {
            System.out.println("Error: resume '" + r.getUuid() + "' already exist.");
        } else {
            storage[countResume] = r;
            countResume++;
            System.out.println("Resume '" + r.getUuid() + "' - successfully saved.");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Error: resume '" + uuid + "' not found.");
            return null;
        } else {
            return storage[index];
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Error: resume '" + r.getUuid() + "' can`t update, it not found.");
        } else {
            storage[index] = r;
            System.out.println("Resume '" + r.getUuid() + "' - successfully updated.");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Error: resume '" + uuid + "' can`t delete, it not found.");
        } else {
            storage[index] = storage[countResume - 1];
            storage[countResume - 1] = null;
            countResume--;
            System.out.println("Resume '" + uuid + "' - successfully deleted.");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, countResume);
    }

    public int size() {
        return countResume;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < countResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}