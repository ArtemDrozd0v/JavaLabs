package org.example.serialize;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Flat implements Serializable {
    private int number;
    private double area;
    private List<Person> owners;

    public Flat () {}

    public Flat(int number, double area, List<Person> owners) {
        this.number = number;
        this.area = area;
        this.owners = owners;
    }

    public int getNumber() {
        return number;
    }

    public double getArea() {
        return area;
    }

    public List<Person> getOwners() {
        return owners;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return number == flat.number && Double.compare(area, flat.area) == 0 && Objects.equals(owners, flat.owners);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, area, owners);
    }
}
