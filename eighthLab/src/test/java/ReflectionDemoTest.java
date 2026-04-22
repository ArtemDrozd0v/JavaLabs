import org.example.Classes.Car;
import org.example.Classes.Human;
import org.example.Classes.Student;
import org.example.ReflectionDemo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReflectionDemoTest {
    @Test
    void countHumansTest() {
        ReflectionDemo demo = new ReflectionDemo();

        List<Object> list = Arrays.asList(
                new Human("Олег", 20),
                new Student("Иван", 18, "ОмГУ"),
                new Car("123", 200));

        assertEquals(2, demo.countHumans(list));
    }

    @Test
    void getPublicMethodsTest() {
        ReflectionDemo demo = new ReflectionDemo();
        Human h = new Human("Олег", 20);

        List<String> result = demo.getPublicMethods(h);

        assertTrue(result.contains("getName"));
        assertTrue(result.contains("setName"));
        assertTrue(result.contains("getAge"));
        assertTrue(result.contains("setAge"));
        assertTrue(result.contains("eat"));

        for (String s : result) {
            System.out.println(s);
        }
        System.out.println();
    }

    @Test
    void getSuperClassNamesTest() {
        ReflectionDemo demo = new ReflectionDemo();
        Student s = new Student("Олег", 20, "ОмГУ");

        List<String> result = demo.getSuperClassNames(s);

        List<String> expected = Arrays.asList(
                "org.example.Classes.Student",
                "org.example.Classes.Human",
                "java.lang.Object");

        assertEquals(expected, result);
    }

    @Test
    void countExecutableObjTest() {
        ReflectionDemo demo = new ReflectionDemo();

        TestExecutable e1 = new TestExecutable();
        TestExecutable e2 = new TestExecutable();
        Student s = new Student("Олег", 20, "ОмГУ");

        List<Object> list = Arrays.asList(e1, e2, s);

        int count = demo.countExecutableObj(list);

        assertEquals(2, count);
    }

    @Test
    void getGetterAndSetterTest() {
        ReflectionDemo demo = new ReflectionDemo();
        Human h = new Human("Олег", 20);

        List<String> result = demo.getGetterAndSetter(h);

        for (String s : result) {
            System.out.println(s);
        }
        System.out.println();
    }
}
