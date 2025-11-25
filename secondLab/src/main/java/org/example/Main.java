package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        Payment[] payments = {
                new Payment("Петр Петров Петрович", 1, 1, 2025, 77700),
                new Payment("Иван Иванов Иванович", 10, 5, 2024, 10000)
        };

        FinanceReport report = new FinanceReport(payments, "Андрей Смирнов",
                31, 12, 2024);

        String result = report.toString();

        System.out.println(result);
    }
}