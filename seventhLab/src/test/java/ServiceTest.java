import org.example.serialize.Flat;
import org.example.serialize.House;
import org.example.serialize.Person;
import org.example.serialize.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    private Service service;
    private House testHouse;

    @BeforeEach
    void setUp() {
        service = new Service();
        List<Person> listOwners = Arrays.asList(
                new Person("Зуев", "Иван", "Алексеевич", "12.02.1999"),
                new Person("Кузнецов", "Роман", null, "03.12.2002"));

        Flat flat = new Flat(143, 200, listOwners);
        Person manager = new Person("Иван", "Иванов", "Иванович", "01.01.2000");

        testHouse = new House("77:01:0001008:56", "ул. Белая, д. 42", manager, List.of(flat));
    }

    @Test
    void testBinarySerialization() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        service.serializeHouse(testHouse, out);

        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        House restoredHouse = service.deserializeHouse(in);

        assertNotNull(restoredHouse);
        assertEquals(testHouse.getAddress(), restoredHouse.getAddress());
        assertEquals(testHouse.getHouseManager().getSurname(), restoredHouse.getHouseManager().getSurname());
    }

    @Test
    void testJsonSerialization() throws IOException {

        String json = service.toJson(testHouse);

        assertTrue(json.contains("ул. Белая, д. 42"));
        assertTrue(json.contains("77:01:0001008:56"));

        House restoredHouse = service.fromJson(json);

        assertEquals(testHouse.getAddress(), restoredHouse.getAddress());
        assertEquals(testHouse.getHouseManager().getSurname(), restoredHouse.getHouseManager().getSurname());
    }

    @Test
    void testJsonEmptyFields() throws IOException {
        House emptyHouse = new House(null, null, null, null);
        String json = service.toJson(emptyHouse);

        House restored = service.fromJson(json);
        assertNull(restored.getAddress());
    }

    @Test
    void testMakeJsonFile() throws IOException {
        File jsonFile = new File("house_test_result.json");

        String jsonString = service.toJson(testHouse);

        try (FileWriter writer = new FileWriter(jsonFile, StandardCharsets.UTF_8)) {
            writer.write(jsonString);
        }

        String content = Files.readString(jsonFile.toPath(), StandardCharsets.UTF_8);

        House restoredHouse = service.fromJson(content);

        assertEquals(testHouse.getAddress(), restoredHouse.getAddress());
        assertEquals(testHouse.getHouseManager().getSurname(), restoredHouse.getHouseManager().getSurname());
    }
}