package ru.drdrapp.webapp.storage;

import ru.drdrapp.webapp.exeption.StorageException;
import ru.drdrapp.webapp.model.Resume;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    public AbstractArrayStorageTest(AbstractStorage storage) {
        super(storage);
    }

    @Test
    void checkOverflow() {
        try {
            storage.clear();
            for (int i = 0; i < AbstractArrayStorage.MAX_COUNT_RESUME; i++) {
                storage.save(new Resume("Василий Тестовый"));
            }
        } catch (Exception e) {
            fail("Storage overflow.");
        }
        assertThrows(StorageException.class, () -> storage.save(new Resume("Василий Тестовый")));
    }

}