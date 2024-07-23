package main.java.ru.clevertec.check;

public class DiscountCard {

    private final int id;
    private final String number;
    private final int discountPercentage;

    public DiscountCard(int id,  String number, int discountPercentage) {
        this.id = id;
        this.number = number;
        this.discountPercentage = discountPercentage;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

}
