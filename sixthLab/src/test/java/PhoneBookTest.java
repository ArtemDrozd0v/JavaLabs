import org.example.Human;
import org.example.PhoneBook;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class PhoneBookTest {

    @Test
    void PhoneBookTest() {
        PhoneBook pb = new PhoneBook();
        Human h1 = new Human("Sazonov", "Pavel", "Pavlovich", 20);
        Human h2 = new Human("Sazonov", "Lev", "Kirillovich", 18);
        Human h3 = new Human("Orlov", "Ivan", "", 42);

        pb.addPhone(h1, "111");
        pb.addPhone(h1, "222");
        pb.addPhone(h2, "333");
        pb.addPhone(h3, "555");

        assertEquals(2, pb.getPhones(h1).size());

        pb.removePhone(h1, "111");
        assertEquals(1, pb.getPhones(h1).size());
        assertFalse(pb.getPhones(h1).contains("111"));

        assertEquals(h2, pb.findHumanByPhone("333"));

        Map<Human, List<String>> found = pb.findBySurnameStr("Saz");

        Map<Human, List<String>> map = new HashMap<>();
        map.put(h1, Arrays.asList("222"));
        map.put(h2, Arrays.asList("333"));

        assertEquals(map, found);
    }
}
