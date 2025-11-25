package org.example;

public class FinanceReport {
    private Payment[] payments;
    private String reporterName;
    private int day;
    private int month;
    private int year;

    public FinanceReport(Payment[] payments, String reporterName, int day, int month, int year){
        this.payments = payments;
        this.reporterName = reporterName;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public FinanceReport(FinanceReport other) {
        if (other == null) {
            throw new IllegalArgumentException("Объект null");
        }

        this.reporterName = other.reporterName;
        this.day = other.day;
        this.month = other.month;
        this.year = other.year;

        this.payments = new Payment[other.payments.length];
        for (int i = 0; i < other.payments.length; i++) {
            if (other.payments[i] != null) {
                this.payments[i] = new Payment(
                        other.payments[i].getName(),
                        other.payments[i].getDay(),
                        other.payments[i].getMonth(),
                        other.payments[i].getYear(),
                        other.payments[i].getSum()
                );
            }
        }
    }

    public int getPaymentCount() {
        if (payments == null){
            return 0;
        }
        else{
            return payments.length;
        }
    }

    public Payment getPayment(int index) {
        if (payments == null || index < 0 || index >= payments.length) {
            throw new IndexOutOfBoundsException("Неверный индекс платежа");
        }
        return payments[index];
    }

    public void setPayment(int index, Payment payment) {
        if (payments == null || index < 0 || index >= payments.length) {
            throw new IndexOutOfBoundsException("Неверный индекс платежа");
        }
        payments[index] = payment;
    }

    public String getNormalDate() {
        return String.format("%02d.%02d.%04d", day, month, year);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[Автор: %s, дата: %s, Платежи: [\n", reporterName, getNormalDate()));
        if (payments != null) {
            for (Payment payment : payments) {
                sb.append("  ").append(payment.toString()).append("\n");
            }
        }
        sb.append("]]");
        return sb.toString();
    }

    public String getReporterName() {
        return reporterName;
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

}
