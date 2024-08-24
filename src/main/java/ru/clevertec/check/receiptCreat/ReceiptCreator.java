package main.java.ru.clevertec.check.receiptCreat;

public class ReceiptCreator {

    DateTimePart dateTimePart;
    CalculatedPart calculatedPart;

    public ReceiptCreator(DateTimePart dateTimePart, CalculatedPart calculatedPart) {
        this.dateTimePart = dateTimePart;
        this.calculatedPart = calculatedPart;
    }

    public String createReceipt() {
        return "Date;Time\n" +
                dateTimePart.getDateForReceipt() +
                ";" +
                dateTimePart.getTimeForReceipt() +
                "\n\n" +
                "QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL\n" +
                calculatedPart.getProductForReceipt() +
                "DISCOUNT CARD;DISCOUNT PERCENTAGE\n" +
                calculatedPart.getDiscountCardPart().getDiscountCardForReceipt() +
                "TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT\n" +
                calculatedPart.getCalculatedForReceipt();
    }

}
