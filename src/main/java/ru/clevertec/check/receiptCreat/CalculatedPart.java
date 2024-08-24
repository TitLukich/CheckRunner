package main.java.ru.clevertec.check.receiptCreat;

import main.java.ru.clevertec.check.Product;
import main.java.ru.clevertec.check.ReceiptReaderAndWriter;
import java.util.List;

public class CalculatedPart {

    private final ProductsPart productsPart;
    private final DiscountCardPart discountCardPart;
    private final double debitCardValue;
    private double discountSum;
    private double totalPrice;
    private double totalWithDiscount;

    public CalculatedPart(ProductsPart productsPart, DiscountCardPart discountCardPart, double debitCardValue) {
        this.productsPart = productsPart;
        this.discountCardPart = discountCardPart;
        this.debitCardValue = debitCardValue;
    }

    public String getProductForReceipt() {

        discountSum = 0;
        totalPrice = 0;
        List<Product> productsInCheck = productsPart.getProductsInReceipt();
        StringBuilder productsForPrint = new StringBuilder();

        for (Product product : productsInCheck) {
            double discount = 0;
            if (product.getQuantity() > 4 && product.isWholesale()) {
                discount = product.getPrice() * product.getQuantity() / 10;

            } else if (!discountCardPart.getDiscountCardForReceipt().isEmpty()) {

                discount = product.getTotalPrice() * discountCardPart.getDiscountPercentage() / 100;
            }

            discountSum += getRoundDouble(discount);
            totalPrice += product.getTotalPrice();

            productsForPrint.append(product.getQuantity())
                    .append(";")
                    .append(product.getName())
                    .append(";")
                    .append(getStringFormat(product.getPrice()))
                    .append("$;")
                    .append(getStringFormat(discount))
                    .append("$;")
                    .append(getStringFormat(product.getTotalPrice()))
                    .append("$\n");
        }

        totalWithDiscount = getRoundDouble(getRoundDouble(totalPrice) - getRoundDouble(discountSum));

        if (totalWithDiscount > debitCardValue) {
            ReceiptReaderAndWriter.writeCheck("NOT ENOUGH MONEY");
            throw new IllegalArgumentException("NOT ENOUGH MONEY");
        }

        return productsForPrint.append("\n").toString();
    }

    public String getCalculatedForReceipt() {
        return getStringFormat(totalPrice) +
                "$;" +
                getStringFormat(discountSum) +
                "$;" +
                getStringFormat(totalWithDiscount) +
                "$";
    }

    public DiscountCardPart getDiscountCardPart() {
        return discountCardPart;
    }

    private double getRoundDouble(double d) {
        return Math.round(d * 100.0) / 100.0;
    }

    private String getStringFormat(Double d) {
        return String.format("%.2f", d);
    }
}