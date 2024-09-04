// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.model.visibility.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * RoundTrip model with readonly optional properties.
 */
@Immutable
public final class ReadOnlyModel implements JsonSerializable<ReadOnlyModel> {
    /*
     * Optional readonly nullable int list.
     */
    @Generated
    private List<Integer> optionalNullableIntList;

    /*
     * Optional readonly string dictionary.
     */
    @Generated
    private Map<String, String> optionalStringRecord;

    /**
     * Creates an instance of ReadOnlyModel class.
     */
    @Generated
    public ReadOnlyModel() {
    }

    /**
     * Get the optionalNullableIntList property: Optional readonly nullable int list.
     * 
     * @return the optionalNullableIntList value.
     */
    @Generated
    public List<Integer> getOptionalNullableIntList() {
        return this.optionalNullableIntList;
    }

    /**
     * Get the optionalStringRecord property: Optional readonly string dictionary.
     * 
     * @return the optionalStringRecord value.
     */
    @Generated
    public Map<String, String> getOptionalStringRecord() {
        return this.optionalStringRecord;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ReadOnlyModel from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ReadOnlyModel if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the ReadOnlyModel.
     */
    @Generated
    public static ReadOnlyModel fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ReadOnlyModel deserializedReadOnlyModel = new ReadOnlyModel();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("optionalNullableIntList".equals(fieldName)) {
                    List<Integer> optionalNullableIntList = reader.readArray(reader1 -> reader1.getInt());
                    deserializedReadOnlyModel.optionalNullableIntList = optionalNullableIntList;
                } else if ("optionalStringRecord".equals(fieldName)) {
                    Map<String, String> optionalStringRecord = reader.readMap(reader1 -> reader1.getString());
                    deserializedReadOnlyModel.optionalStringRecord = optionalStringRecord;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedReadOnlyModel;
        });
    }
}