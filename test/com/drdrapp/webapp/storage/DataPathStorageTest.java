package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.storage.serializers.DataSerializer;

import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;

class DataPathStorageTest extends AbstractStorageTest{

    public DataPathStorageTest() throws NotDirectoryException, FileNotFoundException {
        super(new PathStorage(STORAGE_DIR.toPath(), new DataSerializer()));
    }

}