package org.example.serialize.JacksonStreamingAPI;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.example.serialize.Person;

import java.io.IOException;

public class PersonSerializer extends StdSerializer<Person> {
    public PersonSerializer() {
        super(Person.class);
    }

    @Override
    public void serialize(Person person, JsonGenerator jGen, SerializerProvider provider) throws IOException {
        jGen.writeStartObject();
        String fullName = String.format("%s %s %s", person.getSurname(), person.getName(), person.getPatronymic());
        jGen.writeStringField("fullName", fullName);
        jGen.writeStringField("birthDate", person.getBirthDate());
        jGen.writeEndObject();
    }
}

