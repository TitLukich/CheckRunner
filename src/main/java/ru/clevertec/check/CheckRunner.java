package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.CSVReader.ReaderDiscountCard;
import main.java.ru.clevertec.check.CSVReader.ReaderProducts;
import main.java.ru.clevertec.check.receiptCreat.*;
import java.util.List;

public class CheckRunner {

    public static void main(String[] args) {
        CommandLineParser commandLineParser = new CommandLineParser(args);

        ReaderProducts readerProducts = new ReaderProducts();
        ReaderDiscountCard discountCard = new ReaderDiscountCard();

        List<Product> products = readerProducts
                .getFileObjectList("D:\\Java\\Проекты\\CheckRunner\\src\\main\\resources\\products.csv");
        List<DiscountCard> discountCards = discountCard.
                getFileObjectList("D:\\Java\\Проекты\\CheckRunner\\src\\main\\resources\\discountCards.csv");


        DateTimePart dateTimePart = new DateTimePart();
        ProductsPart productsPart = new ProductsPart(products, commandLineParser.getCheckItems());
        DiscountCardPart discountCardPart = new DiscountCardPart(discountCards, commandLineParser.getDiscountCardNumber());
        CalculatedPart calculatedPart = new CalculatedPart(productsPart, discountCardPart, commandLineParser.getDebitCardValue());

        ReceiptCreator receiptCreator = new ReceiptCreator(dateTimePart, calculatedPart);
        String result = receiptCreator.createReceipt();
        ReceiptReaderAndWriter.printCheck(result);
        ReceiptReaderAndWriter.writeCheck(result);


    }
}

