package main.java.ru.clevertec.check;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private boolean isWholesale;

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


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", isWholesale=" + isWholesale +
                '}';
    }
}
