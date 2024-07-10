package main.java.ru.clevertec.check;


public class CheckRunner {
    public static void main(String[] args) {
        CommandLineParser commandLineParser = new CommandLineParser(args);

        Check check = new Check(CSVReader.getProducts(), CSVReader.getDiscountCards(), commandLineParser.getDiscountCardNumber(), commandLineParser.getCheckItems(), commandLineParser.getDebitCardValue());

        String textCheck = check.checkCreate();

        Check.printCheck(textCheck);

        Check.writeCheck(textCheck);
    }
}

