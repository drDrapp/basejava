package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.util.Config;
import com.drdrapp.webapp.exeption.ExistStorageException;
import com.drdrapp.webapp.exeption.NotExistStorageException;
import com.drdrapp.webapp.model.Resume;
import com.drdrapp.webapp.testdata.TestResumeData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.drdrapp.webapp.testdata.TestResumeData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class AbstractStorageTest {
    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();
    private static final String DUMMY = "dummy";
    private static final String NAME_1 = "Дульсинея Тобосская";
    private static final String NAME_2 = "Евлампий Агапов";
    private static final String NAME_3 = "Эдуард Суровый";
    private static final String NAME_4 = "Виниамин Дорохов";
    protected static final Resume RESUME_1 = TestResumeData.resumeCreate(UUID_1, NAME_1);
    protected static final Resume RESUME_2 = TestResumeData.resumeCreate(UUID_2, NAME_2);
    protected static final Resume RESUME_3 = TestResumeData.resumeCreate(UUID_3, NAME_3);
    protected static final Resume RESUME_4 = TestResumeData.resumeCreate(UUID_4, NAME_4);
    protected static final File STORAGE_DIR = Config.getInstance().getStorageDir();

    protected final Storage storage;
    protected int initialSize;

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void prepareBeforeTest() {
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
        initialSize = 3;
    }

    @AfterEach
    void clearDir() {
        storage.clear();
    }

    @Test
    void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test
    void saveExist() {
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
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
        storage.save(RESUME_4);
        Resume resumeForUpdate = new Resume(RESUME_4.getUuid(), NAME_4);
        resumeFillContacts(resumeForUpdate);
        resumeFillSectionText(resumeForUpdate);
        resumeFillSectionList(resumeForUpdate);
        resumeFillSectionOrganization(resumeForUpdate);
        storage.update(resumeForUpdate);
        assertEquals(resumeForUpdate, storage.get(UUID_4));
    }

    @Test
    void updateNotExist() {
        Resume newResume = new Resume(UUID_4, NAME_4);
        assertThrows(NotExistStorageException.class, () -> storage.update(newResume));
    }

    @Test
    void size() {
        assertSize(initialSize);
    }

    @Test
    void getAllSorted() {
        Resume[] arrayForTest = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        Arrays.sort(arrayForTest);
        List<Resume> listForTest = Arrays.asList(arrayForTest);
        assertEquals(listForTest, storage.getAllSorted());
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