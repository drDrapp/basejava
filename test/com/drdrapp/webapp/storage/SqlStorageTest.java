package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.util.Config;

class SqlStorageTest extends AbstractStorageTest{

    public SqlStorageTest() {
        super(Config.getInstance().getStorage());
    }

}