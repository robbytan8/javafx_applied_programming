package com.robby.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

public class DemoObservableSet {

    public static void main(String[] args) {
        ObservableSet<String> set1 = FXCollections.observableSet("one", "two");
        System.out.println("Creating set: " + set1);
        set1.add("three");
        set1.add("four");
        set1.add("two");
        System.out.println("After adding: " + set1);
        set1.remove("four");
        System.out.println("After removing: " + set1);
        ObservableSet<Integer> set2 = FXCollections.observableSet();
        set2.add(25);
        set2.add(100);
        set2.add(25);
        System.out.println("Set 2 after adding: " + set2);
    }
}
