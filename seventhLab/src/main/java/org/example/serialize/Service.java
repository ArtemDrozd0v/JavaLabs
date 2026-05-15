package org.example.serialize;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Service {
    public void serializeHouse(House house, OutputStream out) throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream(out)) {
            os.writeObject(house);
        }
    }

    public House deserializeHouse(InputStream in) throws IOException, ClassNotFoundException {
        try (ObjectInputStream os = new ObjectInputStream(in)) {
            return (House) os.readObject();
        }
    }

    public String toJson(House house) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(house);
    }

    public House fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, House.class);
    }

    public void saveToCsv(House house) throws IOException {
        String fileName = "house_" + house.getCadastreNumber().replace(":", "_") + ".csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, StandardCharsets.UTF_8))) {

            writer.write('\ufeff');

            writer.println("Данные о доме");
            writer.println("Кадастровый номер; " + house.getCadastreNumber());
            writer.println("Адрес; " + house.getAddress());

            Person head = house.getHouseManager();
            String headName = String.format("%s %s %s", head.getSurname(), head.getName(), head.getPatronymic());
            writer.println("Старший по дому; " + headName);

            writer.println("Данные о квартирах");
            writer.println("№;Площадь, кв. м;Владельцы");

            if (house.getFlats() != null) {
                for (Flat flat : house.getFlats()) {

                    StringBuilder ownersBuilder = new StringBuilder();
                    List<Person> ownersList = flat.getOwners();

                    if (ownersList != null) {
                        for (int i = 0; i < ownersList.size(); i++) {
                            Person p = ownersList.get(i);

                            String shortName = String.format("%s %c.%c.",
                                    p.getSurname(),
                                    p.getName().charAt(0),
                                    p.getPatronymic().charAt(0));

                            ownersBuilder.append(shortName);

                            if (i < ownersList.size() - 1) {
                                ownersBuilder.append(", ");
                            }
                        }
                    }

                    String owners = ownersBuilder.toString();

                    writer.printf("%d;%.2f;%s%n", flat.getNumber(), flat.getArea(), owners);
                }
            }
        }
    }

    public boolean comparingJsonStrings(String json1, String json2) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        JsonNode tree1 = mapper.readTree(json1);
        JsonNode tree2 = mapper.readTree(json2);

        return tree1.equals(tree2);
    }
}
