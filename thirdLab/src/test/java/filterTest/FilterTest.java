package filterTest;

import org.example.filter.BeginStringFilter;
import org.example.filter.ContainsStringFilter;
import org.example.filter.EndStringFilter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FiltersTest {

    @Test
    void testBeginStringFilter() {
        BeginStringFilter filter = new BeginStringFilter("Мама");

        assertTrue(filter.apply("Мама мыла раму"));
        assertFalse(filter.apply("папа мыла раму"));
        assertFalse(filter.apply("мама мыла раму"));
        assertTrue(filter.apply("Мама"));

        assertEquals("Мама", filter.getPattern());


        BeginStringFilter filter1 = new BeginStringFilter("test");

        assertFalse(filter1.apply(null));
        assertFalse(filter1.apply(""));
        assertFalse(filter1.apply("tes"));
        assertTrue(filter1.apply("test"));
        assertTrue(filter1.apply("testing"));
    }

    @Test
    void testBeginStringFilterEHT() {
        BeginStringFilter filter1 = new BeginStringFilter("hello");
        BeginStringFilter filter2 = new BeginStringFilter("world");
        BeginStringFilter filter3 = new BeginStringFilter("hello");

        assertNotEquals(filter1, filter2);
        assertEquals(filter1, filter3);
        assertNotEquals(null, filter1);

        assertEquals(filter1.hashCode(), filter3.hashCode());

        String toString = filter1.toString();
        assertTrue(toString.contains("BeginStringFilter"));
        assertTrue(toString.contains("hello"));
    }


    @Test
    void testContainsStringFilter() {
        ContainsStringFilter filter = new ContainsStringFilter("мыла");

        assertTrue(filter.apply("Мама мыла раму"));
        assertTrue(filter.apply("папа мыла раму"));
        assertFalse(filter.apply("Мама готовила ужин"));
        assertTrue(filter.apply("мыла"));

        assertEquals("мыла", filter.getPattern());


        ContainsStringFilter filter1 = new ContainsStringFilter("test");

        assertFalse(filter1.apply(null));
        assertFalse(filter1.apply(""));
        assertFalse(filter1.apply("tes"));
        assertTrue(filter1.apply("test"));
        assertTrue(filter1.apply("this is a test"));
        assertTrue(filter1.apply("testing"));
    }

    @Test
    void testContainsStringFilterEHT() {
        ContainsStringFilter filter1 = new ContainsStringFilter("hello");
        ContainsStringFilter filter2 = new ContainsStringFilter("world");
        ContainsStringFilter filter3 = new ContainsStringFilter("hello");

        assertNotEquals(filter1, filter2);
        assertEquals(filter1, filter3);
        assertNotEquals(null, filter1);

        assertEquals(filter1.hashCode(), filter3.hashCode());

        String toString = filter1.toString();
        assertTrue(toString.contains("ContainsStringFilter"));
        assertTrue(toString.contains("hello"));
    }

    @Test
    void testEndStringFilter() {
        EndStringFilter filter = new EndStringFilter("раму");

        assertTrue(filter.apply("Мама мыла раму"));
        assertFalse(filter.apply("Мама мыла окно"));
        assertFalse(filter.apply("раму мыла Мама"));
        assertTrue(filter.apply("раму"));

        assertEquals("раму", filter.getPattern());


        EndStringFilter filter1 = new EndStringFilter("test");

        assertFalse(filter1.apply(null));
        assertFalse(filter1.apply(""));
        assertFalse(filter1.apply("est"));
        assertTrue(filter1.apply("test"));
        assertTrue(filter1.apply("this is a test"));
    }

    @Test
    void testEndStringFilterEHT() {
        EndStringFilter filter1 = new EndStringFilter("hello");
        EndStringFilter filter2 = new EndStringFilter("world");
        EndStringFilter filter3 = new EndStringFilter("hello");

        assertNotEquals(filter1, filter2);
        assertEquals(filter1, filter3);
        assertNotEquals(null, filter1);

        assertEquals(filter1.hashCode(), filter3.hashCode());

        String toString = filter1.toString();
        assertTrue(toString.contains("EndStringFilter"));
        assertTrue(toString.contains("hello"));
    }
}