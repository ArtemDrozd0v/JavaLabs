import org.example.FinanceReport;
import org.example.Payment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FinanceReportTest {

    @Test
    void testFinanceReport() {
        Payment[] payments = {
                new Payment("Иван Морозов Александрович", 20, 10, 2024, 100000),
                new Payment("Николай Дуб Антонович", 1, 5, 2023, 323123),
                new Payment("Евгений Комаров Алексеевич", 13, 8, 2022, 999999),
        };

        FinanceReport report = new FinanceReport(payments,
                "Алексей Математика Игоревич", 21, 10, 2025);

        assertEquals(3, report.getPaymentCount());
        assertEquals(payments[0], report.getPayment(0));
        assertEquals(payments[1], report.getPayment(1));
        assertThrows(IndexOutOfBoundsException.class, () -> report.getPayment(-1));

        Payment p = new Payment("###", 1, 2, 2001, 1);
        report.setPayment(1, p);
        assertEquals(p, report.getPayment(1));
    }

    @Test
    void testToString() {
        Payment[] payments = {
                new Payment("Петр Петров Петрович", 1, 1, 2025, 77700),
                new Payment("Иван Иванов Иванович", 10, 5, 2024, 10000)
        };

        FinanceReport report = new FinanceReport(payments, "Андрей Смирнов",
                31, 12, 2024);

        String result = report.toString();

        assertTrue(result.contains("Автор: Андрей Смирнов"));
        assertTrue(result.contains("дата: 31.12.2024"));
        assertTrue(result.contains("Плательщик: Петр Петров Петрович"));
        assertTrue(result.contains("Плательщик: Иван Иванов Иванович"));
        assertTrue(result.contains("777 руб. 00 коп."));
        assertTrue(result.contains("100 руб. 00 коп."));
        assertTrue(result.startsWith("["));
        assertTrue(result.endsWith("]]"));
    }

    @Test
    void testFinanceReportCopy() {
        Payment[] payments = {
                new Payment("Петр Петров Петрович", 1, 1, 2025, 77700),
                new Payment("Иван Иванов Иванович", 10, 5, 2024, 10000)
        };

        FinanceReport original = new FinanceReport(payments, "Андрей Смирнов",
                31, 12, 2024);
        FinanceReport copy = new FinanceReport(original);

        assertEquals(original.getPaymentCount(), copy.getPaymentCount());
        assertEquals(original.getReporterName(), copy.getReporterName());

        Payment oP = original.getPayment(0);
        oP.setSum(0);

        assertNotEquals(original.getPayment(0).getSum(), copy.getPayment(0).getSum());
        assertEquals(77700, copy.getPayment(0).getSum());

        assertThrows(IllegalArgumentException.class, () -> new FinanceReport(null));
    }

}
