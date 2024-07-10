package main.java.ru.clevertec.check;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;


public class Check {
   private ArrayList<Product> products;
   private ArrayList<DiscountCard> discountCards;
   private String discountCardNumber;
   private ArrayList<CheckItem> checkItems;
   private double discountPercentage;
   private double debitCardValue;

   public Check(ArrayList<Product> products, ArrayList<DiscountCard> discountCards, String discountCardNumber, ArrayList<CheckItem> checkItems, double debitCardValue) {
      this.products = products;
      this.discountCards = discountCards;
      this.discountCardNumber = discountCardNumber;
      this.checkItems = checkItems;
      this.debitCardValue = debitCardValue;
      this.discountPercentage = 0.0;
   }

   private ArrayList<Product> getProductsInCheck() {

      ArrayList<Product> productsInCheck = new ArrayList<>();

      try {
         for (CheckItem checkItem : checkItems) {

            boolean isCorrectCheckItem = false;

            for (Product product : products) {

               if (checkItem.getId() == product.getId()) {
                  isCorrectCheckItem = true;

                  if (checkItem.getQuantity() <= product.getQuantity()) {
                     productsInCheck.add(new Product(product.getId(), product.getName(), product.getPrice(), checkItem.getQuantity(), product.isWholesale()));
                  } else {
                     throw new IllegalArgumentException();
                  }
               }
            }
            if (!isCorrectCheckItem) {
               throw new IllegalArgumentException();
            }
         }
      } catch (IllegalArgumentException e) {
         String error = "BAD REQUEST";
         writeCheck(error);
         printCheck(error);
         System.exit(1);
      }
      return productsInCheck;
   }

   private boolean isValidDiscountCard() {
      if (discountCardNumber == null) {
         return false;
      }

      for (DiscountCard discountCard : discountCards) {
         if (discountCardNumber.equals(discountCard.getNumber())) {
            discountPercentage = discountCard.getDiscountPercentage();
            return true;
         }

      }

      return false;
   }

   public String checkCreate() {

      ArrayList<Product> productsInCheck = getProductsInCheck();
      double discount = 0;
      double discountSum = 0;
      double totalPrice = 0;
      StringBuilder check = new StringBuilder();


      LocalDate currentDate = LocalDate.now();
      LocalTime currentTime = LocalTime.now();
      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
      String formattedDate = currentDate.format(dateFormatter);
      String formattedTime = currentTime.format(timeFormatter);

      check.append("Date;Time\n").append(formattedDate).append(";").append(formattedTime).append("\n")
              .append("\n").append("QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL\n");

      for (Product product : productsInCheck) {

         if (product.getQuantity() > 4 && product.isWholesale()) {
            discount = product.getPrice() * product.getQuantity() / 10;

         } else if (isValidDiscountCard()) {

            discount = product.getTotalPrice() * discountPercentage / 100;
         } else {
            discount = 0;
         }

         check.append(product.getQuantity()).append(";")
                 .append(product.getName()).append(";")
                 .append(getStringFormat(product.getPrice())).append("$;")
                 .append(getStringFormat(discount)).append("$;")
                 .append(getStringFormat(product.getTotalPrice())).append("$\n");

         discountSum += discount;
         totalPrice += product.getTotalPrice();
      }

      if (isValidDiscountCard()) {
         check.append("\n")
                 .append("DISCOUNT CARD;DISCOUNT PERCENTAGE\n")
                 .append(discountCardNumber).append(";")
                 .append(discountPercentage).append("%");
      }

      double totalWithDiscount = Math.round((Math.round(totalPrice * 100.0) / 100.0 - Math.round(discountSum * 100.0) / 100.0) * 100.0) / 100.0;

      if (totalWithDiscount > debitCardValue) {
         String error = "NOT ENOUGH MONEY";
         writeCheck(error);
         printCheck(error);
         System.exit(1);

      }

      check.append("\n").append("TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT \n")
              .append(getStringFormat(totalPrice)).append("$;")
              .append(getStringFormat(discountSum)).append("$;")
              .append(getStringFormat(totalWithDiscount)).append("$");

      return check.toString();
   }

   public static void writeCheck(String string) {

      try (FileWriter fileWriter = new FileWriter("result.csv")) {

         fileWriter.write(string);
      } catch (IOException e) {
         printCheck("INTERNAL SERVER ERROR");
         System.exit(1);
      }
   }

   public static void printCheck(String string) {
      System.out.println(string);
   }

   private String getStringFormat(Double d) {
      return String.format("%.2f", d);
   }
}
