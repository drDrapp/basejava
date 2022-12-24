package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.exeption.StorageException;
import com.drdrapp.webapp.model.Resume;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private File directory;

    public AbstractFileStorage(File directory) throws NotDirectoryException, FileNotFoundException {
        Objects.requireNonNull(directory);
        if (!directory.exists()) {
            throw new FileNotFoundException("Directory is not exist: " + directory.getAbsolutePath());
        }
        if (!directory.isDirectory()) {
            throw new NotDirectoryException("Parameter is not directory: " + directory.getAbsolutePath());
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException("Access denied for: " + directory.getAbsolutePath());
        }
        this.directory = directory;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File searchKey) {
        return searchKey.exists();
    }

    @Override
    protected void doSave(Resume r, File searchKey) {
        try {
            if (searchKey.createNewFile()) {
                doWrite(r, searchKey);
            } else {
                throw new StorageException("File '" + Path.of(searchKey.getPath()).getFileName().toString() + "' is not saved.", r.getUuid());
            }
        } catch (IOException e) {
            throw new StorageException("File '" + Path.of(searchKey.getPath()).getFileName().toString() + "' is not saved.", r.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(File searchKey) {
        if (!searchKey.delete()) {
            throw new StorageException("File '" + searchKey + "' is not deleted.", Path.of(searchKey.getPath()).getFileName().toString());
        }
    }

    @Override
    protected Resume doGet(File searchKey) {
        try {
            return doRead(searchKey);
        } catch (IOException e) {
            throw new StorageException("The file " + searchKey.getPath() + " could not be read", "dummy", e);
        }
    }

    @Override
    protected void doUpdate(Resume r, File searchKey) {
        try {
            doWrite(r, searchKey);
        } catch (IOException e) {
            throw new StorageException("File '" + Path.of(searchKey.getPath()).getFileName().toString() + "' can`t be updated.", r.getUuid(), e);
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory reading error");
        }

        List<Resume> allResume = new ArrayList<>();
        for (File file : files
        ) {
           allResume.add(doGet(file));
        }
        return allResume;
    }

    @Override
    public int size() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory reading error");
        }
        return files.length;
    }

    @Override
    public void clear() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory reading error");
        }
        for (File file : files
        ) {
            doDelete(file);
        }
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file) throws IOException;

}
