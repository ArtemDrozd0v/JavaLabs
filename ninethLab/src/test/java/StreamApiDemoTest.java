import org.example.Human;
import org.example.LambdaRunner;
import org.example.StreamApiDemo;
import org.example.Student;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class StreamApiDemoTest {
    private final Human human1 = new Human("Drozdov", "Artem", "Alexandrovich", 19, Human.Gender.MALE);
    private final Human human2 = new Human("Voloshin", "Andrey", "Romanovich", 66, Human.Gender.MALE);
    private final Human human3 = new Human("Leonova", "Ksenia", "Mironovna", 42, Human.Gender.FEMALE);
    private final Student student = new Student("Voloshin", "Maxim", "Mikhailovich", 17,
            Human.Gender.MALE, "OmSU", "IT", "PMI");

    @Test
    void removeNullsTest() {
        List<Human> listWithNulls = Arrays.asList(human1, null, human2, null, human3);
        List<?> result = LambdaRunner.run(StreamApiDemo.removeNulls, listWithNulls);

        assertEquals(Arrays.asList(human1, human2, human3), result);
    }

    @Test
    void countPositiveTest() {
        Set<Integer> numbers = new HashSet<>(Arrays.asList(-1, -10, 0, 1, 2, 3));
        long count = LambdaRunner.run(StreamApiDemo.countPositive, numbers);

        assertEquals(3, count);
    }

    @Test
    void lastThreeTest() {
        List<Human> people = Arrays.asList(human1, human2, human3, student);
        List<?> result = LambdaRunner.run(StreamApiDemo.lastTree, people);

        assertEquals(Arrays.asList(human2, human3, student), result);
    }

    @Test
    void firstEvenTest() {
        List<Integer> ages = Arrays.asList(1, 23, 55, 42, 22, 2);
        int firstEven = LambdaRunner.run(StreamApiDemo.firstEven, ages);

        assertEquals(42, firstEven);
    }

    @Test
    void uniqueSquaresTest() {
        int[] array = {2, -2, 3, 3, 4};
        List<Integer> result = LambdaRunner.run(StreamApiDemo.uniqueSquares, array);

        assertEquals(Arrays.asList(4, 9, 16), result);
    }

    @Test
    void filteredListTest() {
        List<String> surnames = Arrays.asList("Voloshin", "", "Drozdov", null, "Leonova");
        List<String> result = LambdaRunner.run(StreamApiDemo.filteredList, surnames);

        assertEquals(Arrays.asList("Drozdov", "Leonova", "Voloshin"), result);
    }

    @Test
    void setToListTest() {
        Set<String> names = new HashSet<>(Arrays.asList("Artem", "Andrey", "Ksenia", "Maxim"));
        List<String> result = LambdaRunner.run(StreamApiDemo.setToList, names);

        assertEquals(Arrays.asList("Maxim", "Ksenia", "Artem", "Andrey"), result);
    }

    @Test
    void sumSquaresTest() {
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));
        int sum = LambdaRunner.run(StreamApiDemo.sumSquares, set);

        assertEquals(14, sum);
    }

    @Test
    void maxAgeTest() {
        Collection<Human> group = Arrays.asList(human1, human2, human3, student);
        int maxAge = LambdaRunner.run(StreamApiDemo.maxAge, group);

        assertEquals(66, maxAge);
    }

    @Test
    void sortByGenderAndAgeTest() {
        Collection<Human> group = Arrays.asList(human1, human2, human3, student);

        List<Human> sorted = LambdaRunner.run(StreamApiDemo.sortPeople, group);
        List<Human> assertSorted = Arrays.asList(student, human1, human2, human3);

        assertEquals(sorted, assertSorted);
    }
}
