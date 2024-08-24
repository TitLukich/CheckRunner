package main.java.ru.clevertec.check.receiptCreat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimePart {
    private final LocalDate currentDate;
    private final LocalTime currentTime;

    public DateTimePart() {
        this.currentDate = LocalDate.now();
        this.currentTime = LocalTime.now();
    }

    public String getDateForReceipt(){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(dateFormatter);
    }

    public String getTimeForReceipt(){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(timeFormatter);
    }

}
