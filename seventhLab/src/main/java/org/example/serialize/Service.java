package org.example.serialize;

import java.io.*;
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
}
