package main.java.ru.clevertec.check;

import java.io.FileWriter;
import java.io.IOException;

public class ReceiptReaderAndWriter {

    public static void writeCheck(String string) {

        try (FileWriter fileWriter = new FileWriter("result.csv")) {

            fileWriter.write(string);

        } catch (IOException e) {
            printCheck("INTERNAL SERVER ERROR");
        }
    }

    public static void printCheck(String string) {
        System.out.println(string);
    }
}
