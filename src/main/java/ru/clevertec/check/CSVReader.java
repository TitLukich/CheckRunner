package main.java.ru.clevertec.check;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class CSVReader {

    public static ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/products.csv"))) {

            br.readLine();

            while (br.ready()) {
                String line = br.readLine();
                String[] product = line.split(";");
                products.add(new Product(Integer.parseInt(product[0]), product[1], Double.parseDouble(product[2]), Integer.parseInt(product[3]), Boolean.parseBoolean(product[4])));
            }

        } catch (IOException e) {
            Check.printCheck("INTERNAL SERVER ERROR");
            System.exit(1);
        }

        return products;
    }

    public static ArrayList<DiscountCard> getDiscountCards() {
        ArrayList<DiscountCard> discountCards = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./src/main/resources/discountCards.csv"))) {

            br.readLine();

            while (br.ready()) {
                String line = br.readLine();
                String[] discountCard = line.split(";");
                discountCards.add(new DiscountCard(Integer.parseInt(discountCard[0]), discountCard[1], Integer.parseInt(discountCard[2])));
            }

        } catch (IOException e) {
            Check.printCheck("INTERNAL SERVER ERROR");
            System.exit(1);
        }

        return discountCards;
    }

}
