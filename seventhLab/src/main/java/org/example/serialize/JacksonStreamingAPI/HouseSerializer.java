package org.example.serialize.JacksonStreamingAPI;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.example.serialize.Flat;
import org.example.serialize.House;

import java.io.IOException;

public class HouseSerializer extends StdSerializer<House> {
    public HouseSerializer() {
        super(House.class);
    }

    @Override
    public void serialize(House house, JsonGenerator jGen, SerializerProvider provider) throws IOException {
        jGen.writeStartObject();
        jGen.writeStringField("cadastreNumber", house.getCadastreNumber());
        jGen.writeStringField("address", house.getAddress());

        jGen.writeFieldName("houseManager");
        provider.defaultSerializeValue(house.getHouseManager(), jGen);

        jGen.writeFieldName("flats");

        jGen.writeStartArray();
        for (Flat flat : house.getFlats()) {
            provider.defaultSerializeValue(flat, jGen);
        }
        jGen.writeEndArray();

        jGen.writeEndObject();
    }
}