package ru.drdrapp.webapp.storage;

import ru.drdrapp.webapp.storage.serializers.ObjectSerializer;

import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;

class ObjectPathStorageTest extends AbstractStorageTest {

    public ObjectPathStorageTest() throws NotDirectoryException, FileNotFoundException {
        super(new PathStorage(STORAGE_DIR.toPath(), new ObjectSerializer()));
    }

}