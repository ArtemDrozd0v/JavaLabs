import org.example.Human;
import org.example.LambdaDemo;
import org.example.LambdaRunner;
import org.example.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LambdaDemoTest {
    private final Human human1 = new Human("Drozdov", "Artem", "Alexandrovich", 19, Human.Gender.MALE);
    private final Human human2 = new Human("Voloshin", "Andrey", "Romanovich", 66, Human.Gender.MALE);
    private final Human human3 = new Human("Leonova", "Ksenia", "Mironovna", 42, Human.Gender.FEMALE);
    private final Student student = new Student("Voloshin", "Maxim", "Mikhailovich", 17,
            Human.Gender.MALE, "OmSU", "IT", "PMI");

    @Test
    void testStringLength() {
        assertEquals(5, LambdaRunner.run(LambdaDemo.stringLength, "Human"));
        assertEquals(0, LambdaRunner.run(LambdaDemo.stringLength, ""));
    }

    @Test
    void testFirstChar() {
        assertEquals('H', LambdaRunner.run(LambdaDemo.firstChar, "Human"));
        assertNull(LambdaRunner.run(LambdaDemo.firstChar, ""));
    }

    @Test
    void testCheckSpaces() {
        assertTrue(LambdaRunner.run(LambdaDemo.checkSpaces, "123"));
        assertFalse(LambdaRunner.run(LambdaDemo.checkSpaces, "123 45 45"));
    }

    @Test
    void testCountWords() {
        assertEquals(0, LambdaRunner.run(LambdaDemo.countWords, ""));
        assertEquals(1, LambdaRunner.run(LambdaDemo.countWords, "123"));
        assertEquals(2, LambdaRunner.run(LambdaDemo.countWords, "123, 123"));
    }

    @Test
    void testGetHumanAge() {
        assertEquals(19, LambdaRunner.run(LambdaDemo.getHumanAge, human1));
        assertEquals(17, LambdaRunner.run(LambdaDemo.getHumanAge, student));
    }

    @Test
    void testSameSurnames() {
        assertTrue(LambdaRunner.run(LambdaDemo.sameSurnames, human1, human1));
        assertTrue(LambdaRunner.run(LambdaDemo.sameSurnames, human2, student));
        assertFalse(LambdaRunner.run(LambdaDemo.sameSurnames, human2, human3));
    }

    @Test
    void testGetFullName() {
        String expected = "Drozdov Artem Alexandrovich";
        assertEquals(expected, LambdaRunner.run(LambdaDemo.getFullName, human1));
    }

    @Test
    void testMakeOlder() {
        Human older = LambdaRunner.run(LambdaDemo.makeOlder, human1);
        assertEquals(human1.getAge() + 1, older.getAge());
    }

    @Test
    void testAgeCheck() {
        assertTrue(LambdaRunner.run(LambdaDemo.ageCheck, human1, human2, human3, 100));
        assertFalse(LambdaRunner.run(LambdaDemo.ageCheck, human1, student, human3, 10));
    }
}
