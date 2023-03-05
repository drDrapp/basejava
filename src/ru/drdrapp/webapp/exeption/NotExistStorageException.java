package ru.drdrapp.webapp.exeption;

public class NotExistStorageException extends StorageException{
    public NotExistStorageException(String uuid) {
        super("Resume not exist.", uuid);
    }
}