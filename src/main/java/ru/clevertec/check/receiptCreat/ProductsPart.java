package main.java.ru.clevertec.check.receiptCreat;

import main.java.ru.clevertec.check.CheckItem;
import main.java.ru.clevertec.check.Product;
import main.java.ru.clevertec.check.ReceiptReaderAndWriter;
import java.util.ArrayList;
import java.util.List;

public class ProductsPart {
    private static final String BAD_REQUEST = "BAD REQUEST";
    private final List<Product> products;
    private final List<CheckItem> checkItems;

    public ProductsPart(List<Product> products, List<CheckItem> checkItems) {
        this.products = products;
        this.checkItems = checkItems;
    }

    public List<Product> getProductsInReceipt() {
        List<Product> productsInReceipt = new ArrayList<>();

        for (CheckItem checkItem : checkItems) {

            boolean isCorrectCheckItem = false;

            for (Product product : products) {

                if (checkItem.getId() == product.getId()) {
                    isCorrectCheckItem = true;

                    if (checkItem.getQuantity() <= product.getQuantity()) {
                        productsInReceipt.add(new Product(product.getId(), product.getName(), product.getPrice(), checkItem.getQuantity(), product.isWholesale()));
                    } else {
                        ReceiptReaderAndWriter.writeCheck(BAD_REQUEST);
                        throw new IllegalArgumentException(BAD_REQUEST);
                    }
                }
            }

            if (!isCorrectCheckItem) {
                ReceiptReaderAndWriter.writeCheck(BAD_REQUEST);
                throw new IllegalArgumentException(BAD_REQUEST);
            }
        }
        return productsInReceipt;
    }
}

