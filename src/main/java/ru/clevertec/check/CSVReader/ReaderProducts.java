package main.java.ru.clevertec.check.CSVReader;

import main.java.ru.clevertec.check.Product;
import java.util.ArrayList;
import java.util.List;

public class ReaderProducts extends AbstractCSVReader<Product>
{
    @Override
    public List<Product> createFileObjectList(List<String[]> list) {

        List<Product> products = new ArrayList<>();

        for (String[] row : list) {
            products.add(new Product(Integer.parseInt(row[0]), row[1], Double.parseDouble(row[2]), Integer.parseInt(row[3]), Boolean.parseBoolean(row[4])));
        }

        return products;
    }
}
