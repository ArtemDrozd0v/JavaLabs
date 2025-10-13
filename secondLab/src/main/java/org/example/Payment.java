package org.example;

import java.util.Objects;

public class Payment {
    private String name;
    private int day;
    private int month;
    private int year;
    private int sum;

    public Payment(String name, int day, int month, int year, int sum) {
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getSum() {
        return sum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;
        return day == payment.day &&
                month == payment.month &&
                year == payment.year &&
                sum == payment.sum &&
                Objects.equals(name, payment.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, day, month, year, sum);
    }

    @Override
    public String toString() {
        int rubles = sum / 100;
        int kopecks = sum % 100;
        return String.format("Плательщик: %s, дата: %02d.%02d.%04d сумма: %d руб. %02d коп.",
                name, day, month, year, rubles, kopecks);
    }
}
