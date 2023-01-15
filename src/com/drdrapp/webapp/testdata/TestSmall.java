package com.drdrapp.webapp.testdata;

import com.drdrapp.webapp.model.Resume;
import com.drdrapp.webapp.storage.MapResumeStorage;

public class TestSmall {
    // Типы тестовых хранилищ
    //static final ArrayStorage STORAGE = new ArrayStorage();
    //static final SortedArrayStorage STORAGE = new SortedArrayStorage();
    //static final ListStorage STORAGE = new ListStorage();
    //static final MapStorage STORAGE = new MapStorage();
    static final MapResumeStorage STORAGE = new MapResumeStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("UUID3_1", "Василий Пупкин");
        Resume r2 = new Resume("UUID2_2", "Сидор Заболотный");
        Resume r3 = new Resume("UUID1_3", "Виниамин Кирпич");
        Resume r4 = new Resume("UUID4_4", "Федор Конюхов");
        Resume r5 = new Resume("UUID5_5", "Иван Иванов");

        STORAGE.save(r1);
        STORAGE.save(r2);
        STORAGE.save(r3);
        STORAGE.save(r4);
        STORAGE.save(r5);

        System.out.println("= Get r1: " + STORAGE.get(r1.getUuid()));
        System.out.println("= Size: " + STORAGE.size());
        System.out.println("= Update: " + r3);
        STORAGE.update(r3);

        printAll();
        System.out.println("= Delete: " + r1);
        STORAGE.delete(r1.getUuid());
        printAll();

        System.out.println("= Delete all ");
        STORAGE.clear();
        printAll();

        System.out.println("= Size: " + STORAGE.size());
    }

    static void printAll() {
        System.out.println("\n= Get All");
        for (Resume r : STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}