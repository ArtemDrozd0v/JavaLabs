package org.example;

import java.util.Iterator;

public class Data implements Iterable<Integer> {
    private String name;
    public Group[] groups;

    public Data() {
        name = "";
        groups = null;
    }

    public Data(String str, Group... group) {
        this.name = str;
        this.groups = group;
    }

    public String getName() {
        return name;
    }

    public Group[] getGroups() {
        return groups;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroups(Group[] groups) {
        this.groups = groups;
    }

    public int getLength() {
        return groups.length;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new DataIterator(getGroups());
    }

}
