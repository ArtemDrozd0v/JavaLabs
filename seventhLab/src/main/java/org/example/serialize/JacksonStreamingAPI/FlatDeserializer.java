package org.example.serialize.JacksonStreamingAPI;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import org.example.serialize.Flat;
import org.example.serialize.Person;

import java.io.IOException;
import java.util.ArrayList;

public class FlatDeserializer extends StdScalarDeserializer<Flat> {
    public FlatDeserializer() {
        super(Flat.class);
    }

    @Override
    public Flat deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        int number = 0;
        double area = 0;
        ArrayList<Person> owners = new ArrayList<>();

        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jp.getCurrentName();
            jp.nextToken();

            switch (fieldName) {
                case "number":
                    number = jp.getIntValue();
                    break;
                case "area":
                    area = jp.getDoubleValue();
                    break;
                case "owners":
                    if (jp.getCurrentToken() == JsonToken.START_ARRAY) {
                        while (jp.nextToken() != JsonToken.END_ARRAY) {
                            owners.add(jp.readValueAs(Person.class));
                        }
                    }
                    break;
            }
        }
        return new Flat(number, area, owners);
    }
}
