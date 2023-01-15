package com.drdrapp.webapp.storage;

import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;

class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() throws NotDirectoryException, FileNotFoundException {
        super(new PathStorage(STORAGE_DIR.toPath(), new ObjectStreamStorage()));
    }

}