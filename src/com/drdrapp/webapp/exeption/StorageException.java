package com.drdrapp.webapp.exeption;

public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, String uuid) {
        super(message + " {UUID: " + uuid + "}");
    }

    public StorageException(String message, String uuid, Exception e) {
        super(message + " {UUID: " + uuid + "}", e);
    }
}