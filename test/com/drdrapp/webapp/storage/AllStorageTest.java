package com.drdrapp.webapp.storage;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapStorageTest.class,
        MapResumeStorageTest.class,
        ObejectFileStorageTest.class,
        ObjectPathStorageTest.class,
        XmlPathStorageTest.class})
public final class AllStorageTest {
}