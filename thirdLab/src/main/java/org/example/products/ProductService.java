package org.example.products;

import org.example.filter.Filter;

import java.util.List;

public class ProductService {

    public static int countByFilter(ProductBatch batch, Filter filter) {
        if (batch == null || filter == null) {
            throw new IllegalArgumentException("Партия или фильтр не существуют");
        }
        int count = 0;
        for (IPackagedProduct item: batch.getProducts()) {
            if (filter.apply(item.getName())) {
                count++;
            }
        }
        return count;
    }

    public static int countByFilterDeep(ProductBatch batch, Filter filter) {
        if (batch == null || filter == null) {
            throw new NullPointerException("Партия или фильтр не существуют");
        }
        int count = 0;
        for (IPackagedProduct item: batch.getProducts()) {
            count += countByFilterDeepHelp(item, filter);
        }
        return count;
    }

    private static int countByFilterDeepHelp(IPackagedProduct product, Filter filter) {
        int count = 0;

        if (product instanceof PackagedProductSet packedProductSet) {
            for (IPackagedProduct packedItem : packedProductSet.getPackedItems()) {
                count += countByFilterDeepHelp(packedItem, filter);
            }
        }

        if (filter.apply(product.getName())) {
            count++;
        }

        return count;
    }

    public static boolean checkAllWeighted(ProductBatch batch) {
        if (batch == null) {
            throw new NullPointerException("Партия не существует");
        }
        return checkAllWeightedRecur(batch.getProducts());
    }

    private static boolean checkAllWeightedRecur(List<IPackagedProduct> products) {
        for (IPackagedProduct item : products) {
            if (!(item instanceof PackagedWeightProduct)) {
                if (item instanceof PackagedProductSet set) {
                    if (!checkAllWeightedRecur(set.getPackedItems())) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
