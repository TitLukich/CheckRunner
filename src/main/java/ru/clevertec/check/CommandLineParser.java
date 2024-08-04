package main.java.ru.clevertec.check;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CommandLineParser {

    private final List<CheckItem> checkItems;
    private String discountCardNumber;
    private double debitCardValue;

    public CommandLineParser(String[] args) {

        checkItems = new ArrayList<>();
        Map<Integer, Integer> idQuantityProducts = new HashMap<>();

//        todo: Неудачный алгоритм парсинга. Если захочешь - обсудим, как сделать лучше
        for (String arg : args) {

            if (arg.startsWith("discountCard")) {

                String[] parts = arg.split("=");
                discountCardNumber = parts[1];

            } else if (arg.startsWith("balanceDebitCard")) {

                String[] parts = arg.split("=");
                debitCardValue = Double.parseDouble(parts[1]);

            } else if (startsWithNumber(arg)) {

                String[] parts = arg.split("-");
                idQuantityProducts.merge(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer::sum);
            }
            else {

                String error = "BAD REQUEST";
                System.exit(1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : idQuantityProducts.entrySet()) {
            checkItems.add(new CheckItem(entry.getKey(), entry.getValue()));
        }
    }

    public List<CheckItem> getCheckItems() {
        return checkItems;
    }

    public String getDiscountCardNumber() {
        return discountCardNumber;
    }

    public double getDebitCardValue() {

        return debitCardValue;
    }

    private boolean startsWithNumber(String str) {
        return Pattern.matches("^\\d.*", str);
    }
}
