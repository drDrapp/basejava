package com.drdrapp.webapp;

import com.drdrapp.webapp.model.*;
import com.drdrapp.webapp.storage.*;

public class MainTestStorage {
    // Типы тестовых хранилищ
    //static final ArrayStorage STORAGE = new ArrayStorage();
    //static final SortedArrayStorage STORAGE = new SortedArrayStorage();
    //static final ListStorage STORAGE = new ListStorage();
    static final MapStorage STORAGE = new MapStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid3");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid1");
        Resume r4 = new Resume("uuid5");
        Resume r5 = new Resume("uuid4");

        STORAGE.save(r1);
        STORAGE.save(r2);
        STORAGE.save(r3);
        STORAGE.save(r4);
        STORAGE.save(r5);

        System.out.println("Get r1: " + STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + STORAGE.size());
        //System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        STORAGE.update(r3);
        
        printAll();
        STORAGE.delete(r1.getUuid());
        printAll();
        STORAGE.clear();
        printAll();

        System.out.println("Size: " + STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}