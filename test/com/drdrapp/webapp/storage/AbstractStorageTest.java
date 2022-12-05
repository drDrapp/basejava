package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.exeption.ExistStorageException;
import com.drdrapp.webapp.exeption.NotExistStorageException;
import com.drdrapp.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

abstract class AbstractStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String DUMMY = "dummy";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);

    protected final AbstractStorage storage;

    protected int initialSize;

    public AbstractStorageTest(AbstractStorage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void prepareBeforeTest() {
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        initialSize = 3;
    }

    @Test
    void save() {
        Resume newResume = new Resume(UUID_4);
        storage.save(newResume);
        assertGet(newResume);
        assertSize(4);
    }

    @Test
    void saveExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));
    }

    @Test
    void delete() {
        storage.delete(UUID_2);
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_2));
    }

    @Test
    void deleteNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.delete(DUMMY));
    }

    @Test
    void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void getNotExist() {
        assertThrows(NotExistStorageException.class, () -> storage.get(DUMMY));
    }

    @Test
    void update() {
        Resume newResume = new Resume(UUID_2);
        storage.update(newResume);
        assertSame(storage.get(newResume.getUuid()), newResume);
    }

    @Test
    void updateNotExist() {
        Resume newResume = new Resume();
        assertThrows(NotExistStorageException.class, () -> storage.update(newResume));
    }

    @Test
    void size() {
        assertSize(initialSize);
    }

    @Test
    void getAll() {
        assertArrayEquals(new Resume[]{RESUME_1, RESUME_2, RESUME_3}, storage.getAll());
    }

    @Test
    void clear() {
        storage.clear();
        assertSize(0);
    }

    void assertGet(Resume r) {
        assertEquals(storage.get(r.getUuid()), r);
    }

    void assertSize(int testSize) {
        assertEquals(testSize, storage.size());
    }

}