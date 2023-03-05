package ru.drdrapp.webapp.storage;

import ru.drdrapp.webapp.util.Config;

class SqlStorageTest extends AbstractStorageTest{

    public SqlStorageTest() {
        super(Config.getInstance().getStorage());
    }

}