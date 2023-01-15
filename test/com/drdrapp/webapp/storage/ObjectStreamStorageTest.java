package com.drdrapp.webapp.storage;

import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;

class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() throws NotDirectoryException, FileNotFoundException {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }

}