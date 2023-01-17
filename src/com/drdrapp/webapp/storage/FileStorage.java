package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.exeption.StorageException;
import com.drdrapp.webapp.model.Resume;
import com.drdrapp.webapp.storage.serializers.SerializationStrategy;

import java.io.*;
import java.nio.file.NotDirectoryException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private final SerializationStrategy serializationStrategy;

    public FileStorage(File directory, SerializationStrategy serializationStrategy) throws NotDirectoryException, FileNotFoundException {
        Objects.requireNonNull(directory);
        this.serializationStrategy = serializationStrategy;
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
            searchKey.createNewFile();
        } catch (IOException e) {
            throw new StorageException("File '" + searchKey.getAbsolutePath() + "' is not saved.", r.getUuid(), e);
        }
        doUpdate(r, searchKey);
    }

    @Override
    protected void doDelete(File searchKey) {
        if (!searchKey.delete()) {
            throw new StorageException("File '" + searchKey.getAbsolutePath() + "' is not deleted.");
        }
    }

    @Override
    protected Resume doGet(File searchKey) {
        try {
            return serializationStrategy.doRead( new BufferedInputStream(new FileInputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("File '" + searchKey.getAbsolutePath() + "' could not be read.", null, e);
        }
    }

    @Override
    protected void doUpdate(Resume r, File searchKey) {
        try {
            serializationStrategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("File '" + searchKey.getAbsolutePath() + "' is not updated.", r.getUuid(), e);
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        return stream(getFilesList()).map(this::doGet).collect(Collectors.toList());
    }

    @Override
    public int size() {
        return getFilesList().length;
    }

    @Override
    public void clear() {
        stream(getFilesList()).forEach(this::doDelete);
    }

    private File[] getFilesList(){
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory reading error");
        }
        return files;
    }

}