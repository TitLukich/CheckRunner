package main.java.ru.clevertec.check.receiptCreat;

import main.java.ru.clevertec.check.DiscountCard;
import java.util.List;

public class DiscountCardPart {

    private final List<DiscountCard> discountCards;
    private final String discountCardNumber;
    private int discountPercentage;

    public DiscountCardPart(List<DiscountCard> discountCards, String discountCardNumber) {
        this.discountCards = discountCards;
        this.discountCardNumber = discountCardNumber;

    }

    private boolean isValidDiscountCard() {
        if (discountCardNumber == null) {
            return false;
        }
        for (DiscountCard discountCard : discountCards) {
            if (discountCardNumber.equals(discountCard.getNumber())) {
                discountPercentage = discountCard.getDiscountPercentage();
                return true;
            }
        }
        return false;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public String getDiscountCardForReceipt() {
        if (isValidDiscountCard()) {
            return discountCardNumber + ";" + discountPercentage + "%\n\n";
        }
        return "";
    }
}
