package org.example;

import java.util.Iterator;

public class DataIterator implements Iterator<Integer> {
    private Group[] groups;
    private int groupIndex = 0;
    private int elementIndex = -1;

    public DataIterator(Group[] groups) {
        this.groups = groups;
    }

    @Override
    public boolean hasNext() {
        while (groupIndex < groups.length) {
            if (elementIndex < groups[groupIndex].getLength() - 1) {
                return true;
            }
            groupIndex++;
            elementIndex = -1;
        }
        return false;
    }

    @Override
    public Integer next() {
        elementIndex++;
        return groups[groupIndex].getData()[elementIndex];
    }
}