package org.example;

import java.util.Objects;

public class Payment {
    private String name;
    private int day;
    private int month;
    private int year;
    private int sum;

    public Payment(String name, int day, int month, int year, int sum) {
        correctDate(day, month, year);

        if (sum < 0){
            throw new IllegalArgumentException("Отрицательная сумма");
        }

        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.sum = sum;
    }

    private void correctDate(int day, int month, int year) {
        if (month <= 0 || month > 12){
            throw new IllegalArgumentException("Ошибка ввода месяца");
        }

        int mxDay = maxDayThisMount(month, year);
        if (day <= 0 || day > mxDay){
            throw new IllegalArgumentException("Ошибка ввода дня");
        }
    }

    private int maxDayThisMount(int month, int year){
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                if (thisIsLeapYear(year)){
                    return 29;
                } else{
                    return 28;
                }
            default:
                throw new IllegalArgumentException("Недопустимый месяц");
        }
    }

    private boolean thisIsLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
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
        correctDate(day, this.month, this.year);
        this.day = day;
    }

    public void setMonth(int month) {
        correctDate(this.day, month, this.year);
        this.month = month;
    }

    public void setYear(int year) {
        correctDate(this.day, this.month, year);
        this.year = year;
    }

    public void setSum(int sum) {
        if (sum < 0) {
            throw new IllegalArgumentException("Отрицательная сумма");
        }
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
