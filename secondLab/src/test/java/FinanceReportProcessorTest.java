import org.example.FinanceReport;
import org.example.FinanceReportProcessor;
import org.example.Payment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FinanceReportProcessorTest {

    @Test
    void testGetPaymentsByLastName() {
        Payment[] payments = {
                new Payment("Синус Петр", 1, 2, 2025, 10000),
                new Payment("Соснова Алла", 10, 10, 2025, 42342),
                new Payment("Дубов Константин", 4, 5, 2025, 5000000),
                new Payment("Косинус Михаил", 12, 9, 2024, 10)
        };

        FinanceReport report = new FinanceReport(payments, "Жиганов Анатолий",
                10, 12, 2026);

        FinanceReport filtered = FinanceReportProcessor.getPaymentsByLastName(report, 'С');

        assertEquals(2, filtered.getPaymentCount());
        assertEquals("Синус Петр", filtered.getPayment(0).getName());
        assertEquals("Соснова Алла", filtered.getPayment(1).getName());

        FinanceReport filtered2 = FinanceReportProcessor.getPaymentsByLastName(report, 'д');
        assertEquals(1, filtered2.getPaymentCount());

        FinanceReport filtered3 = FinanceReportProcessor.getPaymentsByLastName(report, 'Ч');
        assertEquals(0, filtered3.getPaymentCount());

        assertThrows(IllegalArgumentException.class,
                () -> FinanceReportProcessor.getPaymentsByLastName(null, 'С'));

    }

    @Test
    void testGetPaymentsByMaxSum() {
        Payment[] payments = {
                new Payment("Синус Петр", 1, 2, 2025, 1000000),
                new Payment("Соснова Алла", 10, 10, 2025, 500000),
                new Payment("Дубов Константин", 4, 5, 2025, 2000),
                new Payment("Косинус Михаил", 12, 9, 2024, 10)
        };

        FinanceReport report = new FinanceReport(payments, "Жиганов Анатолий",
                10, 12, 2026);


        FinanceReport filtered = FinanceReportProcessor.getMaxSum(report, 100000);

        assertEquals(2, filtered.getPaymentCount());
        assertEquals("Дубов Константин", filtered.getPayment(0).getName());
        assertEquals("Косинус Михаил", filtered.getPayment(1).getName());

        FinanceReport filtered2 = FinanceReportProcessor.getMaxSum(report, 0);
        assertEquals(0, filtered2.getPaymentCount());

        assertThrows(IllegalArgumentException.class,
                () -> FinanceReportProcessor.getMaxSum(null, 1));

        assertThrows(IllegalArgumentException.class,
                () -> FinanceReportProcessor.getMaxSum(report, -100));
    }

    @Test
    void testGetSumByDate() {
        Payment[] payments = {
                new Payment("Синус Петр", 1, 2, 2025, 1000000),
                new Payment("Соснова Алла", 10, 10, 2025, 500000),
                new Payment("Дубов Константин", 4, 5, 2025, 2000),
                new Payment("Косинус Михаил", 4, 5, 2025, 10)
        };

        FinanceReport report = new FinanceReport(payments, "Жиганов Анатолий",
                10, 12, 2026);

        int total = FinanceReportProcessor.getSumByDate(report, "4.5.2025");
        assertEquals(2010, total);

        int total2 = FinanceReportProcessor.getSumByDate(report, "12.12.2030");
        assertEquals(0, total2);

        assertThrows(IllegalArgumentException.class,
                () -> FinanceReportProcessor.getSumByDate(report, "11 2 2011"));
        assertThrows(IllegalArgumentException.class,
                () -> FinanceReportProcessor.getSumByDate(report, "1a.2.2011"));

        assertThrows(IllegalArgumentException.class,
                () -> FinanceReportProcessor.getSumByDate(null, "1.1.2025"));
        assertThrows(IllegalArgumentException.class,
                () -> FinanceReportProcessor.getSumByDate(report, null));
    }

    @Test
    void testGetMonthsWithoutPayments() {
        Payment[] payments = {
                new Payment("Синус Петр", 1, 1, 2025, 1000000),
                new Payment("Синус Петр", 1, 2, 2025, 1000000),
                new Payment("Синус Петр", 1, 3, 2025, 1000000),
                new Payment("Синус Петр", 1, 4, 2025, 1000000),
                new Payment("Синус Петр", 1, 5, 2025, 1000000),
                new Payment("Синус Петр", 1, 6, 2025, 1000000),
        };

        FinanceReport report = new FinanceReport(payments, "Жиганов Анатолий",
                10, 12, 2026);

        List<String> monthsWithout = FinanceReportProcessor.getMonthsWithoutPayments(report, 2025);
        assertEquals(6, monthsWithout.size());

        assertTrue(monthsWithout.contains("Июль"));
        assertTrue(monthsWithout.contains("Август"));
        assertTrue(monthsWithout.contains("Сентябрь"));
        assertTrue(monthsWithout.contains("Октябрь"));
        assertTrue(monthsWithout.contains("Ноябрь"));
        assertTrue(monthsWithout.contains("Декабрь"));


        Payment[] payments2 = {
                new Payment("Синус Петр", 1, 1, 2025, 1000000),
                new Payment("Синус Петр", 1, 2, 2025, 1000000),
                new Payment("Синус Петр", 1, 3, 2025, 1000000),
                new Payment("Синус Петр", 1, 4, 2025, 1000000),
                new Payment("Синус Петр", 1, 5, 2025, 1000000),
                new Payment("Синус Петр", 1, 6, 2025, 1000000),
                new Payment("Синус Петр", 1, 7, 2025, 1000000),
                new Payment("Синус Петр", 1, 8, 2025, 1000000),
                new Payment("Синус Петр", 1, 9, 2025, 1000000),
                new Payment("Синус Петр", 1, 10, 2025, 1000000),
                new Payment("Синус Петр", 1, 11, 2025, 1000000),
                new Payment("Синус Петр", 1, 12, 2025, 1000000),
        };

        FinanceReport report2 = new FinanceReport(payments2, "Жиганов Анатолий",
                10, 12, 2026);

        List<String> monthsWithout2 = FinanceReportProcessor.getMonthsWithoutPayments(report2, 2025);
        assertEquals(0, monthsWithout2.size());

        assertThrows(IllegalArgumentException.class,
                () -> FinanceReportProcessor.getMonthsWithoutPayments(null, 2025));

    }

}
