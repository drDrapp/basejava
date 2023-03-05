package ru.drdrapp.webapp.storage;

import ru.drdrapp.webapp.storage.serializers.ObjectSerializer;

import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;

class ObejectFileStorageTest extends AbstractStorageTest {

    public ObejectFileStorageTest() throws NotDirectoryException, FileNotFoundException {
        super(new FileStorage(STORAGE_DIR, new ObjectSerializer()));
    }

}