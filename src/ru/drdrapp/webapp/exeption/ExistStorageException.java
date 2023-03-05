package ru.drdrapp.webapp.exeption;

public class ExistStorageException extends StorageException{
    public ExistStorageException(String uuid) {
        super("Resume already exist.", uuid);
    }

    public ExistStorageException(Exception e) {
        super(e);
    }
}