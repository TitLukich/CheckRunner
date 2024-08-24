package main.java.ru.clevertec.check.CSVReader;

import main.java.ru.clevertec.check.DiscountCard;
import java.util.ArrayList;
import java.util.List;


public class ReaderDiscountCard extends AbstractCSVReader<DiscountCard>{

    @Override
    public List<DiscountCard> createFileObjectList(List<String[]> list) {

        List<DiscountCard> discountCardList = new ArrayList<>();

        for (String[] row : list) {
            discountCardList.add(new DiscountCard(Integer.parseInt(row[0]), row[1], Integer.parseInt(row[2])));
        }

        return discountCardList;
    }
}
