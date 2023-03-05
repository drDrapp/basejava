package ru.drdrapp.webapp.storage;

import ru.drdrapp.webapp.storage.serializers.XmlSerializer;

import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;

class XmlPathStorageTest extends AbstractStorageTest{

    public XmlPathStorageTest() throws NotDirectoryException, FileNotFoundException {
        super(new PathStorage(STORAGE_DIR.toPath(), new XmlSerializer()));
    }

}