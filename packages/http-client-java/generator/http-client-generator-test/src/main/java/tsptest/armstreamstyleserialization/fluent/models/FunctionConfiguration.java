// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package tsptest.armstreamstyleserialization.fluent.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The FunctionConfiguration model.
 */
@Fluent
public final class FunctionConfiguration implements JsonSerializable<FunctionConfiguration> {
    /*
     * The input property.
     */
    private String input;

    /*
     * The output property.
     */
    private String output;

    /**
     * Creates an instance of FunctionConfiguration class.
     */
    public FunctionConfiguration() {
    }

    /**
     * Get the input property: The input property.
     * 
     * @return the input value.
     */
    public String input() {
        return this.input;
    }

    /**
     * Set the input property: The input property.
     * 
     * @param input the input value to set.
     * @return the FunctionConfiguration object itself.
     */
    public FunctionConfiguration withInput(String input) {
        this.input = input;
        return this;
    }

    /**
     * Get the output property: The output property.
     * 
     * @return the output value.
     */
    public String output() {
        return this.output;
    }

    /**
     * Set the output property: The output property.
     * 
     * @param output the output value to set.
     * @return the FunctionConfiguration object itself.
     */
    public FunctionConfiguration withOutput(String output) {
        this.output = output;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("input", this.input);
        jsonWriter.writeStringField("output", this.output);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of FunctionConfiguration from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of FunctionConfiguration if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IOException If an error occurs while reading the FunctionConfiguration.
     */
    public static FunctionConfiguration fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            FunctionConfiguration deserializedFunctionConfiguration = new FunctionConfiguration();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("input".equals(fieldName)) {
                    deserializedFunctionConfiguration.input = reader.getString();
                } else if ("output".equals(fieldName)) {
                    deserializedFunctionConfiguration.output = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedFunctionConfiguration;
        });
    }
}