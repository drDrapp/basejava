package com.drdrapp.webapp.exeption;

public class StorageException extends RuntimeException{

    public StorageException(String message, String uuid) {
        super(message + " {UUID: " + uuid + "}");
    }

}