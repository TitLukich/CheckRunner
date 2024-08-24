package main.java.ru.clevertec.check;

public class Product {
    private final int id;
    private final String name;
    private final double price;
    private final int quantity;
    private final boolean isWholesale;

    public Product(int id, String name, double price, int quantity, boolean wholesale) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isWholesale = wholesale;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isWholesale() {
        return isWholesale;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

}
