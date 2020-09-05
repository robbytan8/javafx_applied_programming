package com.robby.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DemoObservableList {

    public static void main(String[] args) {
        ObservableList<String> list = FXCollections.observableArrayList("one", "two");
        System.out.println("After creating list: " + list);
        list.add("three");
        list.addAll("four", "five");
        System.out.println("After adding element: " + list);
        list.remove(4);
        list.remove(1, 3);
        System.out.println("After removing element: " + list);
        ObservableList<String> list2 = FXCollections.observableArrayList();
        list2.addAll("eleven", "twelve");
        System.out.println("Second list: " + list2);
        ObservableList<String> list3 = FXCollections.concat(list, list2);
        System.out.println("After join first and second list: " + list3);
    }
}
