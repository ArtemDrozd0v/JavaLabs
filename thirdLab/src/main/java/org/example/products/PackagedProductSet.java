package org.example.products;

import java.util.List;
import java.util.Objects;

public class PackagedProductSet implements IPackagedProduct {
    private final String name;
    private final org.example.products.Package pack;
    private final List<IPackagedProduct> packedItems;

    public PackagedProductSet(String name, org.example.products.Package pack, List<IPackagedProduct> packedItems) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Отсутствует название упаковки");
        }
        if (pack == null) {
            throw new IllegalArgumentException("Отсутствует упаковка");
        }
        if (packedItems == null || packedItems.isEmpty()) {
            throw new IllegalArgumentException("Список товаров не может быть пустым");
        }
        this.name = name;
        this.pack = pack;
        this.packedItems = packedItems;
    }

    public String getName() {
        return name;
    }

    public Package getPack() {
        return pack;
    }

    public List<IPackagedProduct> getPackedItems() {
        return packedItems;
    }

    @Override
    public double getNetMass() {
        double total = 0;
        for (IPackagedProduct item : packedItems) {
            total += item.getBruttoMass();
        }
        return total;
    }

    @Override
    public double getBruttoMass() {
        return getNetMass() + pack.getWeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackagedProductSet that = (PackagedProductSet) o;
        return Objects.equals(name, that.name) && Objects.equals(pack, that.pack) && Objects.equals(packedItems, that.packedItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pack, packedItems);
    }

    @Override
    public String toString() {
        return "PackedProductSet{" +
                "name='" + name + '\'' +
                ", pack=" + pack +
                ", packedItems=" + packedItems +
                '}';
    }
}
