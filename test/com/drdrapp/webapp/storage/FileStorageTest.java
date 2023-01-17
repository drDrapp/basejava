package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.storage.serializers.ObjectSerializer;

import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;

class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() throws NotDirectoryException, FileNotFoundException {
        super(new FileStorage(STORAGE_DIR, new ObjectSerializer()));
    }

}