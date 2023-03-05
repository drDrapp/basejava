package ru.drdrapp.webapp.testdata;

import java.io.File;
import java.nio.file.Path;

public class TestFiles {
    public static void main(String[] args) {
        getFiles(new File("."), "");
    }

    private static void getFiles(File path, String offset) {
        if (path.isDirectory()) {
            System.out.println(offset + "DIR: " + path);
        } else {
            System.out.println(offset + "     " + Path.of(path.getPath()).getFileName().toString());
            return;
        }
        File[] files = path.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            getFiles(file, offset + "  ");
        }
    }
}