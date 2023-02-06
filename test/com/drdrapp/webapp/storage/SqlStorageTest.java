package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.Config;

import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;

class SqlStorageTest extends AbstractStorageTest{

    public SqlStorageTest() throws NotDirectoryException, FileNotFoundException {
        super(Config.getInstance().getStorage());
    }

}