package com.drdrapp.webapp.exeption;

public class ExistStorageException extends StorageException{
    public ExistStorageException(String uuid) {
        super("Resume " + uuid + "already exist.", uuid);
    }
}