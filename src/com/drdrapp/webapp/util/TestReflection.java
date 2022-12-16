package com.drdrapp.webapp.util;

import com.drdrapp.webapp.model.Resume;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume testResume = new Resume("Иванов Иван Иванович");
        Class<? extends Resume> testClass = testResume.getClass();
        Field testField = testClass.getDeclaredFields()[0];
        System.out.println("Field name: " + testField.getName());
        testField.setAccessible(true);
        System.out.println("Field value: " + testField.get(testResume));
        testField.set(testResume, "new_uuid");
        System.out.println("New value: " + testResume);
        Method testMethod = testClass.getMethod("toString", (Class<?>) null);
        System.out.println("Execute method toString: " + testMethod.invoke(testResume));
        Annotation[] annotations = testMethod.getDeclaredAnnotations();
        System.out.println(Arrays.toString(annotations));
    }
}