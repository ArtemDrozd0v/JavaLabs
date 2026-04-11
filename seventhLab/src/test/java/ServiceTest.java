import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.serialize.Flat;
import org.example.serialize.House;
import org.example.serialize.Person;
import org.example.serialize.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
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
                new Person("Кузнецов", "Роман", "Олегович", "03.12.2002"));

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

    @Test
    void testSaveToCsv() throws IOException {
        Person head = new Person("Сидор", "Иванов", "Петрович", "01.01.1970");
        House house = new House("55:12345", "г. Омск, пр. Мира, 55a", head, new ArrayList<>());

        Person p1 = new Person("Алексей", "Петров", "Викторович", "10.10.1990");
        house.getFlats().add(new Flat(1, 40.0, List.of(p1)));

        Person p2_1 = new Person("Ольга", "Сидорова", "Михайловна", "12.12.1985");
        Person p2_2 = new Person("Игорь", "Сидоров", "Владимирович", "05.05.1983");
        house.getFlats().add(new Flat(2, 65.0, List.of(p2_1, p2_2)));

        List<Person> bigFamily = List.of(
                new Person("Иван", "Васечкин", "Павлович", "1980-01-01"),
                new Person("Мария", "Васечкина", "Игоревна", "1982-01-01"),
                new Person("Анна", "Васечкина", "Ивановна", "2005-01-01"),
                new Person("Петр", "Васечкин", "Иванович", "2008-01-01"),
                new Person("Сергей", "Золотов", "Борисович", "1955-01-01")
        );

        house.getFlats().add(new Flat(3, 120.5, bigFamily));

        Service service2 = new Service();
        service2.saveToCsv(house);
    }

    @Test
    void comparingJsonString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String json1 = mapper.writeValueAsString(testHouse);

        List<Person> ownersIdentical = Arrays.asList(
                new Person("Зуев", "Иван", "Алексеевич", "12.02.1999"),
                new Person("Кузнецов", "Роман", "Олегович", "03.12.2002"));

        Flat flatIdentical = new Flat(143, 200, ownersIdentical);
        Person managerIdentical = new Person("Иван", "Иванов", "Иванович", "01.01.2000");

        House houseIdentical = new House("77:01:0001008:56", "ул. Белая, д. 42", managerIdentical, List.of(flatIdentical));

        String json2 = mapper.writeValueAsString(houseIdentical);

        Flat flatDifferent = new Flat(1000000000, 111, ownersIdentical);
        House houseDifferent = new House("77:01:0001008:56", "ул. Белая, д. 42", managerIdentical, List.of(flatDifferent));

        String json3 = mapper.writeValueAsString(houseDifferent);

        boolean flag1 = service.comparingJsonStrings(json1, json2);
        boolean flag2 = service.comparingJsonStrings(json1, json3);

        assertTrue(flag1);
        assertFalse(flag2);
    }
}