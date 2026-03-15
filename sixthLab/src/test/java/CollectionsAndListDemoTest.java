import org.junit.jupiter.api.BeforeEach;
import org.example.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionsAndListDemoTest {

    private CollectionsDemo collectionsDemo;
    private ListDemo listDemo;
    private Human human1;
    private Human human2;
    private Human human3;
    private Human human4;
    private Student student;
    private List<Human> humans;
    private final Map<Integer, Human> idToHumanMap = new HashMap<>();

    @BeforeEach
    void setUp() {
        collectionsDemo = new CollectionsDemo();
        listDemo = new ListDemo();

        human1 = new Human("Sazonov", "Pavel", "Pavlovich", 10);
        human2 = new Human("Alexandrov ", "Sergey", "Vyacheslavovich", 25);
        human3 = new Human("Sazonov", "Lev", "Kirillovich", 20);
        human4 = new Human("Kondratiev", "Mikhail", "Egorovich", 25);
        student = new Student("Vinogradov", "Grigory", "Danilovich", 17, "FDTC");

        humans = Arrays.asList(human1, human2, human3, human4, student);

        idToHumanMap.put(1, human1);
        idToHumanMap.put(2, human2);
        idToHumanMap.put(3, human3);
        idToHumanMap.put(4, human4);
        idToHumanMap.put(5, student);
    }

    @Test
    void countStringTest() {
        List<String> strings = Arrays.asList("Ivan", "Artem", "Nikita", "Anton");

        assertEquals(2, collectionsDemo.countString(strings, 'A'));
        assertEquals(1, collectionsDemo.countString(strings, 'I'));
        assertEquals(1, collectionsDemo.countString(strings, 'N'));
        assertEquals(0, collectionsDemo.countString(strings, 'W'));
    }

    @Test
    void findSameSurnamesPeopleTest() {
        List<Human> result = listDemo.findSameSurnamesPeople(humans, human1);
        assertEquals(Arrays.asList(human3), result);
    }

    @Test
    void copyWithoutHumanTest() {
        List<Human> result = listDemo.copyWithoutHuman(humans, human1);
        List<Human> listWithoutHuman = Arrays.asList(human2, human3, human4, student);
        assertEquals(result, listWithoutHuman);
    }

    @Test
    void setWithoutInterceptionsTest() {
        List<Set<Integer>> list = new ArrayList<>();

        list.add(new HashSet<>(Arrays.asList(1, 2, 3)));
        list.add(new HashSet<>(Arrays.asList(4, 5, 6)));
        list.add(new HashSet<>(Arrays.asList(7, 8, 9)));

        Set<Integer> set = new HashSet<>(Arrays.asList(1, 9, 10));
        Set<Integer> set2 = new HashSet<>(List.of());

        List<Set<Integer>> result = listDemo.setWithoutInterceptions(list, set);
        List<Set<Integer>> result2 = listDemo.setWithoutInterceptions(list, set2);

        assertEquals(Arrays.asList(new HashSet<>(Arrays.asList(4, 5, 6))), result);
        assertEquals(Arrays.asList(
                new HashSet<>((Arrays.asList(1, 2, 3))),
                new HashSet<>((Arrays.asList(4, 5, 6))),
                new HashSet<>(Arrays.asList(7, 8, 9))),
                result2);
    }

    @Test
    void getPeopleWithMaxAgeTest() {
        Set<Human> result = listDemo.getPeopleWithMaxAge(humans);
        assertEquals(new HashSet<>(Arrays.asList(human2, human4)), result);
    }

    @Test
    void sortListByFullNameTest() {
        Human h1 = new Human("B", "B", "B", 1);
        Human h2 = new Human("A", "A", "A", 2);
        Human h3 = new Human("A", "B", "C", 3);

        Set<Human> unsortedSet = new HashSet<>(Arrays.asList(h1, h2, h3));
        List<Human> result = listDemo.sortListByFullName(unsortedSet);

        List<Human> sortedList = Arrays.asList(h2, h3, h1);

        assertEquals(sortedList, result);
    }

    @Test
    void getPeopleByIdsTest() {
        Set<Integer> targetIds = new HashSet<>(Arrays.asList(4, 5));
        Set<Human> result = listDemo.getPeopleByIds(idToHumanMap, targetIds);
        Set<Human> set = new HashSet<>(Arrays.asList(human4, student));

        assertEquals(set, result);
    }

    @Test
    void getAdultIdsTest() {
        Set<Integer> ids = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> result = listDemo.getAdultIds(idToHumanMap, ids);
        List<Integer> correctIds = Arrays.asList(2, 3, 4);
        assertEquals(correctIds, result);

    }

    @Test
    void createAgeMapTest() {
        Set<Integer> ids = new HashSet<>(Arrays.asList(1, 2));
        Map<Integer, Integer> result = listDemo.createAgeMap(idToHumanMap, ids);

        Map<Integer, Integer> newMap = new HashMap<>();
        newMap.put(1, 10);
        newMap.put(2, 25);

        assertEquals(newMap, result);
    }

    @Test
    void listPeopleByAgeTest() {
        Set<Human> peopleSet = new HashSet<>(humans);
        Map<Integer, List<Human>> result = listDemo.listPeopleByAge(peopleSet);

        Map<Integer, List<Human>> expectedMap = new HashMap<>();

        expectedMap.put(10, Arrays.asList(human1));
        expectedMap.put(20,  Arrays.asList(human3));
        expectedMap.put(17,  Arrays.asList(student));

        List<Human> expectedAge25List = new ArrayList<>();
        for (Human h : peopleSet) {
            if (h.getAge() == 25) {
                expectedAge25List.add(h);
            }
        }
        expectedMap.put(25, expectedAge25List);

        assertEquals(expectedMap, result);
    }

    @Test
    void listPeopleByAgeAndFirstLetterTest() {
        Human human5 = new Human("Sazonov", "Anton", "Antonovich", 10);

        Set<Human> testSet = new HashSet<>(humans);
        testSet.add(human5);

        Map<Integer, Map<Character, List<Human>>> result = listDemo.listPeopleByAgeAndFirstLetter(testSet);

        Map<Integer, Map<Character, List<Human>>> expected = new HashMap<>();

        Map<Character, List<Human>> age10Map = new HashMap<>(); // S - Sazonov Pavel, Sazonov Anton
        age10Map.put('S', Arrays.asList(human1, human5));
        expected.put(10, age10Map);

        Map<Character, List<Human>> age25Map = new HashMap<>();
        age25Map.put('A', Arrays.asList(human2)); // Alexandrov
        age25Map.put('K', Arrays.asList(human4)); // Kondratiev
        expected.put(25, age25Map);

        Map<Character, List<Human>> age20Map = new HashMap<>();
        age20Map.put('S', Collections.singletonList(human3)); // Sazonov Lev
        expected.put(20, age20Map);

        Map<Character, List<Human>> age17Map = new HashMap<>();
        age17Map.put('V', Collections.singletonList(student)); // Vinogradov
        expected.put(17, age17Map);

        assertEquals(expected, result);
    }

}
