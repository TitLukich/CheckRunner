package main.java.ru.clevertec.check;

public class CheckItem {
    private int id;
    private int quantity;

    public CheckItem(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;

    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
}
