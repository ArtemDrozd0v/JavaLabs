import org.example.Payment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    void testPayment() {
        Payment payment = new Payment("Иванов Иван Иванович", 20, 1, 2006, 100005);
        String expected = "Плательщик: Иванов Иван Иванович, дата: 20.01.2006 сумма: 1000 руб. 05 коп.";
        assertEquals(expected, payment.toString());

        Payment payment2 = new Payment("Воронин Семён Павлович", 29, 2, 2024, 324723);
        String expected2 = "Плательщик: Воронин Семён Павлович, дата: 29.02.2024 сумма: 3247 руб. 23 коп.";
        assertEquals(expected2, payment2.toString());

        assertThrows(IllegalArgumentException.class,
                () -> new Payment("1", 31, 11, 2025, 100));

        assertThrows(IllegalArgumentException.class,
                () -> new Payment("1", 31, 15, 2025, 100));

        assertThrows(IllegalArgumentException.class,
                () -> new Payment("1", 31, 12, 2025, -100000));
    }
}
