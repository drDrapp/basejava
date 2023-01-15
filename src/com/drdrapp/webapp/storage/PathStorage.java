package com.drdrapp.webapp.storage;

import com.drdrapp.webapp.exeption.StorageException;
import com.drdrapp.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    private final Path directory;
    private final StreamSerializer streamSerializer;

    public PathStorage(Path directory, StreamSerializer streamSerializer) throws NotDirectoryException, FileNotFoundException {
        this.streamSerializer = streamSerializer;
        Objects.requireNonNull(directory);
        if (!Files.exists(directory)) {
            throw new FileNotFoundException(directory.toAbsolutePath().toString());
        }
        if (!Files.isDirectory(directory)) {
            throw new NotDirectoryException(directory.toAbsolutePath().toString());
        }
        if (!Files.isReadable(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(directory.toAbsolutePath().toString());
        }
        this.directory = directory;
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path searchKey) {
        return Files.exists(searchKey);
    }

    @Override
    protected void doSave(Resume r, Path searchKey) {
        try {
            Files.createFile(searchKey);
            streamSerializer.doWrite(r, new BufferedOutputStream(new FileOutputStream(searchKey.toFile())));
        } catch (IOException e) {
            throw new StorageException("File '" + searchKey.getFileName() + "' could not be created", r.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(Path searchKey) {
        try {
            Files.delete(searchKey);
        } catch (IOException e) {
            throw new StorageException("File '" + searchKey.getFileName() + "' not deleted", null, e);
        }
    }

    @Override
    protected Resume doGet(Path searchKey) {
        try {
            return streamSerializer.doRead(new BufferedInputStream(new FileInputStream(searchKey.toFile())));
        } catch (IOException e) {
            throw new StorageException("File '" + searchKey.getFileName() + "' could not be read", null, e);
        }
    }

    @Override
    protected void doUpdate(Resume r, Path searchKey) {
        try {
            streamSerializer.doWrite(r, new BufferedOutputStream(new FileOutputStream(searchKey.toFile())));
        } catch (IOException e) {
            throw new StorageException("File '" + searchKey.getFileName() + "' could not be changed", r.getUuid(), e);
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        return getFilesList().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    @Override
    public void clear() {
        getFilesList().forEach(this::doDelete);
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", null, e);
        }
    }

}