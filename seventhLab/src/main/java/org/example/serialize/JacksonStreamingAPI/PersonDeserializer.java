package org.example.serialize.JacksonStreamingAPI;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.example.serialize.Person;

import java.io.IOException;

public class PersonDeserializer extends StdDeserializer<Person> {
    public PersonDeserializer() {
        super(Person.class);
    }

    @Override
    public Person deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        String surname = "";
        String name = "";
        String patronymic = "";
        String birthDate = "";

        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jp.getCurrentName();
            jp.nextToken();

            switch (fieldName) {
                case "fullName":
                    String[] parts = jp.getText().split(" ");
                    surname = parts[0];
                    name = parts[1];
                    patronymic = parts[2];
                    break;
                case "birthDate":
                    birthDate = jp.getText();
            }
        }
        return new Person(surname, name, patronymic, birthDate);
    }
}
