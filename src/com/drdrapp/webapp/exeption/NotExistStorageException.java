package com.drdrapp.webapp.exeption;

public class NoExistStorageException extends StorageException{
    public NoExistStorageException(String uuid) {
        super("Resume " + uuid + "not exist.", uuid);
    }
}