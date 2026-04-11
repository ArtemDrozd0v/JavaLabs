package org.example.serialize.JacksonStreamingAPI;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.example.serialize.Flat;
import org.example.serialize.Person;

import java.io.IOException;

public class FlatSerializer extends StdSerializer<Flat> {
    public FlatSerializer() {
        super(Flat.class);
    }

    @Override
    public void serialize(Flat flat, JsonGenerator jGen, SerializerProvider provider) throws IOException {
        jGen.writeStartObject();

        jGen.writeNumberField("number", flat.getNumber());
        jGen.writeNumberField("area", flat.getArea());

        jGen.writeFieldName("owners");

        jGen.writeStartArray();
        for (Person owner : flat.getOwners()) {
            provider.defaultSerializeValue(owner, jGen);
        }
        jGen.writeEndArray();

        jGen.writeEndObject();
    }
}
