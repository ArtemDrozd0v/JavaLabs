import org.example.StringProcessor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringProcessorTest {

    @Test
    void testCopyString() {
        assertEquals("ababab", StringProcessor.copyString("ab", 3));
        assertEquals("hihihihi", StringProcessor.copyString("hi", 4));

        assertEquals("", StringProcessor.copyString("hello", 0));
        assertEquals("", StringProcessor.copyString("", 0));
        assertEquals("", StringProcessor.copyString(null, 0));

        assertThrows(IllegalArgumentException.class, () -> StringProcessor.copyString("hello", -1));

        assertEquals("", StringProcessor.copyString("", 5));
    }


    @Test
    void testCountStr() {
        assertEquals(2, StringProcessor.countStr("hello world hello", "hello"));
        assertEquals(3, StringProcessor.countStr("ababab", "ab"));
        assertEquals(0, StringProcessor.countStr("hello", "world"));

        assertEquals(2, StringProcessor.countStr("aaaa", "aa"));

        assertThrows(IllegalArgumentException.class, () -> StringProcessor.countStr("hello", ""));
        assertThrows(IllegalArgumentException.class, () -> StringProcessor.countStr("hello", null));

        assertEquals(0, StringProcessor.countStr("", "test"));
    }


    @Test
    void testReplaceNumbers() {
        assertEquals("одиндватри", StringProcessor.replaceNumbers("123"));
        assertEquals("один два три", StringProcessor.replaceNumbers("1 2 3"));

        assertEquals("hello world", StringProcessor.replaceNumbers("hello world"));

        assertEquals("одинОДИНдваДВАтри", StringProcessor.replaceNumbers("1ОДИН2ДВА3"));

        assertEquals("", StringProcessor.replaceNumbers(""));

    }

    @Test
    void testRemoveChar() {
        StringBuilder s1 = new StringBuilder("123456");
        StringProcessor.removeChars(s1);
        assertEquals("135", s1.toString());

        StringBuilder s2 = new StringBuilder("a");
        StringProcessor.removeChars(s2);
        assertEquals("a", s2.toString());

        StringBuilder s3 = new StringBuilder("");
        StringProcessor.removeChars(s3);
        assertEquals("", s3.toString());

        StringBuilder s4 = new StringBuilder("ab");
        StringProcessor.removeChars(s4);
        assertEquals("a", s4.toString());
    }
}