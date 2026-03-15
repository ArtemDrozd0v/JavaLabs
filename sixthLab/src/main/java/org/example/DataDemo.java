package org.example;

import java.util.ArrayList;
import java.util.List;

public class DataDemo {
    public static List<Integer> getAll(Data data){
        List<Integer> list = new ArrayList<>();
        for (int i : data){
            list.add(i);
        }
        return list;
    }
}
