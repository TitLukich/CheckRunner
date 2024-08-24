package main.java.ru.clevertec.check.CSVReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCSVReader <T> {

    public List<T> getFileObjectList(String filePath) {

        List<String[]> parsingFile = parseFile(filePath);

        return createFileObjectList(parsingFile);
    }

    public abstract List<T> createFileObjectList(List<String[]> list);

    private List<String[]> parseFile(String filePath) {
        List<String[]> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            br.readLine();
            while (br.ready()) {
                String line = br.readLine();
                result.add(line.split(";"));
            }
        } catch (IOException e) {
            System.out.println("INTERNAL SERVER ERROR");
        }

        return result;
    }

}
