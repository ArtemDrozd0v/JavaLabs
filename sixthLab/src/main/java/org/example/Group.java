package org.example;

public class Group {
    private int id;
    private int[] data;

    public Group() {
        id = 0;
        data = null;
    }

    public Group(int id, int... data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public int[] getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public int getLength() {
        return data.length;
    }
}
