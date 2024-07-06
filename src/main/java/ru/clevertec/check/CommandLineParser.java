package main.java.ru.clevertec.check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CommandLineParser {
    private ArrayList<CheckItem> checkItems;
    private String discountCardNumber;
    private double debitCardValue;


    public CommandLineParser(String[] args) {

        checkItems = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        try {
            for (String arg : args) {

                if (arg.startsWith("discountCard")) {
                    String[] parts = arg.split("=");
                    discountCardNumber = parts[1];
                } else if (arg.startsWith("balanceDebitCard")) {
                    String[] parts = arg.split("=");
                    debitCardValue = Double.parseDouble(parts[1]);

                } else if (startsWithNumber(arg)) {
                    String[] parts = arg.split("-");
                    map.merge(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer::sum);
                } else {
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            String error = "BAD REQUEST";
            Check.writeCheck(error);
            Check.printCheck(error);
            System.exit(1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            checkItems.add(new CheckItem(entry.getKey(), entry.getValue()));
        }
    }

    public ArrayList<CheckItem> getCheckItems() {
        return checkItems;
    }

    public String getDiscountCardNumber() {
        return discountCardNumber;
    }

    public double getDebitCardValue() {

        return debitCardValue;
    }

    private static boolean startsWithNumber(String str) {
        return Pattern.matches("^\\d.*", str);
    }
}
