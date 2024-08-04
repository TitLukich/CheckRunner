1. Находясь в папке CheckRunner, через командную строку создаем каталог out командой mkdir out. 
2. Компилируем программу через командную строку: javac -d out -sourcepath src src/main/java/ru/clevertec/check/*.java src/main/java/ru/clevertec/check/CSVReader/*.java src/main/java/ru/clevertec/check/receiptCreat/*.java
3. Переходим в папку out командой: cd out
4. Запускаем программу командой: java main.java.ru.clevertec.check.CheckRunner id-quantity discountCard=xxxx balanceDebitCard=xxxx