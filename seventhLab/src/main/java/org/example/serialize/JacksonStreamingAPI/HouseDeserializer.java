package org.example.serialize.JacksonStreamingAPI;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.example.serialize.Flat;
import org.example.serialize.House;
import org.example.serialize.Person;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HouseDeserializer extends StdDeserializer<House> {
    public HouseDeserializer() { 
        super(House.class);
    }

    @Override
    public House deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        String cadastreNumber = "";
        String address = "";
        Person houseManager = null;
        List<Flat> flats = new ArrayList<>();

        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jp.getCurrentName();
            jp.nextToken();

            switch (fieldName) {
                case "cadastreNumber":
                    cadastreNumber = jp.getText();
                    break;
                case "address":
                    address = jp.getText();
                    break;
                case "houseManager":
                    houseManager = jp.readValueAs(Person.class);
                    break;
                case "flats":
                    if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
                        while (jp.nextToken() != JsonToken.END_ARRAY) {
                            flats.add(jp.readValueAs(Flat.class));
                        }
                    }
                    break;
            }
        }
        return new House(cadastreNumber, address, houseManager, flats);
    }
}