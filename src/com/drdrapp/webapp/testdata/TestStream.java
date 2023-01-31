package com.drdrapp.webapp.testdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestStream {

    public static void main(String[] args) {
        int[] values = fillTestData(12);
        System.out.println("Input data: " + Arrays.stream(values).boxed().toList());
        System.out.println("Task 1 result: " + minValue(values));
        System.out.println("Task 2 result: " + oddOrEven(Arrays.stream(values).boxed().toList()));
    }

    private static int[] fillTestData(int i) {
        int[] result = new int[i];
        int j = 0;
        while (j < i) {
            result[j] = (int) (Math.random() * 9) + 1;
            j++;
            if ((j < i) & (Math.random() > 0.6)) {
                result[j] = result[j - 1];
                j++;
            }
        }
        return result;
    }

    public static int minValue(int[] values) {
        return Arrays.stream(values).sorted().distinct().reduce(0, (left, right) -> left * 10 + right);
    }

    public static List<Integer> oddOrEven(List<Integer> values) {
        List<Integer> listOfOdd = new ArrayList<>();
        List<Integer> listOfEven = new ArrayList<>();
        long sum = values.stream().peek((i) -> {
            if (i % 2 == 0) listOfEven.add(i);
            else listOfOdd.add(i);
        }).mapToInt(i -> i).sum();
        return (sum % 2 == 0) ? listOfEven : listOfOdd;
    }

}