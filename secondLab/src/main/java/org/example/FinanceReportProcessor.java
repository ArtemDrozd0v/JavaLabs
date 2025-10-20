package org.example;

import java.util.ArrayList;
import java.util.List;

public class FinanceReportProcessor {
    public static FinanceReport getPaymentsByLastName(FinanceReport report, char startChar) {
        if (report == null) {
            throw new IllegalArgumentException("Отчет не может быть null");
        }

        List<Payment> filtered = new ArrayList<>();

        for (int i = 0; i < report.getPaymentCount(); i++) {
            Payment payment = report.getPayment(i);
            if (payment != null && startsWithChar(payment.getName(), startChar)) {
                filtered.add(new Payment(
                        payment.getName(), payment.getDay(), payment.getMonth(),
                        payment.getYear(), payment.getSum()
                ));
            }
        }

        return new FinanceReport(
                filtered.toArray(new Payment[0]) ,
                report.getReporterName(),
                report.getDay(),
                report.getMonth(),
                report.getYear()
        );
    }

    private static boolean startsWithChar(String name, char startChar) {
        if (name == null || name.isEmpty()) {
            return false;
        }

        String[] parts = name.split(" ");
        if (parts.length == 0) {
            return false;
        }

        String lastName = parts[0];
        return !lastName.isEmpty() &&
                Character.toLowerCase(lastName.charAt(0)) == Character.toLowerCase(startChar);
    }

    public static FinanceReport getMaxSum(FinanceReport report, int maxSum) {
        if (report == null) {
            throw new IllegalArgumentException("Отчет не может быть null");
        }
        if (maxSum < 0) {
            throw new IllegalArgumentException("Максимальная сумма не может быть отрицательной");
        }

        List<Payment> resultPayments = new ArrayList<>();

        for (int i = 0; i < report.getPaymentCount(); i++) {
            Payment payment = report.getPayment(i);
            if (payment != null && payment.getSum() < maxSum) {
                resultPayments.add(new Payment(
                        payment.getName(),
                        payment.getDay(),
                        payment.getMonth(),
                        payment.getYear(),
                        payment.getSum()
                ));
            }
        }

        return new FinanceReport(
                resultPayments.toArray(new Payment[0]),
                report.getReporterName(),
                report.getDay(),
                report.getMonth(),
                report.getYear()
        );
    }

    public static int getSumByDate(FinanceReport report, String dateString) {
        if (report == null) {
            throw new IllegalArgumentException("Отчет не может быть null");
        }
        if (dateString == null) {
            throw new IllegalArgumentException("Дата не может быть null");
        }

        String[] dateParts = dateString.split("\\.");
        if (dateParts.length != 3) {
            throw new IllegalArgumentException("Неверный формат даты");
        }

        try {
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);

            int total = 0;
            for (int i = 0; i < report.getPaymentCount(); i++) {
                Payment payment = report.getPayment(i);
                if (payment != null &&
                        payment.getDay() == day &&
                        payment.getMonth() == month &&
                        payment.getYear() == year) {
                    total += payment.getSum();
                }
            }

            return total;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат чисел в дате");
        }
    }

    public static List<String> getMonthsWithoutPayments(FinanceReport report, int year) {
        if (report == null) {
            throw new IllegalArgumentException("Отчет не может быть null");
        }

        boolean[] monthsWithPayments = new boolean[12];

        for (int i = 0; i < report.getPaymentCount(); i++) {
            Payment payment = report.getPayment(i);
            if (payment != null && payment.getYear() == year) {
                int month = payment.getMonth();
                if (month >= 1 && month <= 12) {
                    monthsWithPayments[month - 1] = true;
                }
            }
        }

        List<String> monthsWithoutPayments = new ArrayList<>();
        String[] monthNames = {
                "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
        };

        for (int i = 0; i < 12; i++) {
            if (!monthsWithPayments[i]) {
                monthsWithoutPayments.add(monthNames[i]);
            }
        }

        return monthsWithoutPayments;
    }
}
