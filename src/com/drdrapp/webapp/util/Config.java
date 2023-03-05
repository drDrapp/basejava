package com.drdrapp.webapp.util;

import com.drdrapp.webapp.storage.SqlStorage;
import com.drdrapp.webapp.storage.Storage;
import com.drdrapp.webapp.web.ResumeServlet;

import java.io.*;
import java.util.Properties;

public class Config {
    private static final File PROPERTIES_DIR = new File("config/resumes.properties");
    private static final Config INSTANCE = new Config();
    private final File storageDir;
    private final Storage storage;

    private Config() {
        try (InputStream is = getRunContextInputStream()) {
            Properties properties = new Properties();
            properties.load(is);
            storageDir = new File(properties.getProperty("storage.dir"));
            storage = new SqlStorage(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPERTIES_DIR.getAbsolutePath());
        }
    }

    public static Config getInstance() {
        return INSTANCE;
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Storage getStorage() {
        return storage;
    }

    private InputStream getRunContextInputStream() throws FileNotFoundException {
        return (ResumeServlet.getContext() == null) ?
                new FileInputStream(PROPERTIES_DIR) :
                ResumeServlet.getContext().getResourceAsStream("/" + PROPERTIES_DIR);
    }
}