package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.model.Resume;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MapStorageTest extends AbstractStorageTest{

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Test
    @Override
    void getAll() {
        Resume[] templateArray = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        Resume[] currentArray = storage.getAll();
        Arrays.sort(templateArray);
        Arrays.sort(currentArray);
        assertArrayEquals(templateArray, currentArray);
    }
}