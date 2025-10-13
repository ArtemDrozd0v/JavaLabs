import org.example.Payment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    void testPayment() {
        Payment payment = new Payment("Иванов Иван Иванович", 20, 1, 2006, 100005);
        String expected = "Плательщик: Иванов Иван Иванович, дата: 20.01.2006 сумма: 1000 руб. 05 коп.";
        assertEquals(expected, payment.toString());

        Payment payment2 = new Payment("Воронин Семён Павлович", 7, 6, 2025, 324723);
        String expected2 = "Плательщик: Воронин Семён Павлович, дата: 07.06.2025 сумма: 3247 руб. 23 коп.";
        assertEquals(expected2, payment2.toString());
    }
}
