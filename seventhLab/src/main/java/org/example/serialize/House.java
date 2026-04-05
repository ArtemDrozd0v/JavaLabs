package org.example.serialize;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class House implements Serializable {
    private String cadastreNumber;
    private String address;
    private Person houseManager;
    private List<Flat> flats;

    public House () {}
    public House(String cadastreNumber, String address, Person houseManager, List<Flat> flats) {
        this.cadastreNumber = cadastreNumber;
        this.address = address;
        this.houseManager = houseManager;
        this.flats = flats;
    }

    public String getCadastreNumber() {
        return cadastreNumber;
    }

    public String getAddress() {
        return address;
    }

    public Person getHouseManager() {
        return houseManager;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(cadastreNumber, house.cadastreNumber) && Objects.equals(address, house.address) && Objects.equals(houseManager, house.houseManager) && Objects.equals(flats, house.flats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cadastreNumber, address, houseManager, flats);
    }
}
