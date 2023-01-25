package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.storage.serializers.JsonSerializer;

import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;

class JsonPathStorageTest extends AbstractStorageTest{

    public JsonPathStorageTest() throws NotDirectoryException, FileNotFoundException {
        super(new PathStorage(STORAGE_DIR.toPath(), new JsonSerializer()));
    }

}