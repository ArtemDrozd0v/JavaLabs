import org.example.Data;
import org.example.DataDemo;
import org.example.Group;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class DataTest {
    @Test
    void testDataIterator() {
        Group g1 = new Group(1, 1, 2);
        Group g2 = new Group(2, 3, 4, 5);
        Group g3 = new Group(3);

        Data data = new Data("data", g1, g2, g3);

        List<Integer> result = DataDemo.getAll(data);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);

        assertEquals(expected, result);
    }

}
