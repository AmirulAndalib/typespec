// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package tsptest.armresourceprovider.fluent.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.logging.ClientLogger;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import tsptest.armresourceprovider.models.ProvisioningState;

/**
 * Top Level Arm Resource Properties.
 */
@Fluent
public final class TopLevelArmResourceProperties implements JsonSerializable<TopLevelArmResourceProperties> {
    /*
     * Configuration Endpoints.
     */
    private List<String> configurationEndpoints;

    /*
     * The userName property.
     */
    private String userName;

    /*
     * The userNames property.
     */
    private String userNames;

    /*
     * The accuserName property.
     */
    private String accuserName;

    /*
     * The startTimeStamp property.
     */
    private OffsetDateTime startTimeStamp;

    /*
     * The status of the last operation.
     */
    private ProvisioningState provisioningState;

    /**
     * Creates an instance of TopLevelArmResourceProperties class.
     */
    public TopLevelArmResourceProperties() {
    }

    /**
     * Get the configurationEndpoints property: Configuration Endpoints.
     * 
     * @return the configurationEndpoints value.
     */
    public List<String> configurationEndpoints() {
        return this.configurationEndpoints;
    }

    /**
     * Get the userName property: The userName property.
     * 
     * @return the userName value.
     */
    public String userName() {
        return this.userName;
    }

    /**
     * Set the userName property: The userName property.
     * 
     * @param userName the userName value to set.
     * @return the TopLevelArmResourceProperties object itself.
     */
    public TopLevelArmResourceProperties withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * Get the userNames property: The userNames property.
     * 
     * @return the userNames value.
     */
    public String userNames() {
        return this.userNames;
    }

    /**
     * Set the userNames property: The userNames property.
     * 
     * @param userNames the userNames value to set.
     * @return the TopLevelArmResourceProperties object itself.
     */
    public TopLevelArmResourceProperties withUserNames(String userNames) {
        this.userNames = userNames;
        return this;
    }

    /**
     * Get the accuserName property: The accuserName property.
     * 
     * @return the accuserName value.
     */
    public String accuserName() {
        return this.accuserName;
    }

    /**
     * Set the accuserName property: The accuserName property.
     * 
     * @param accuserName the accuserName value to set.
     * @return the TopLevelArmResourceProperties object itself.
     */
    public TopLevelArmResourceProperties withAccuserName(String accuserName) {
        this.accuserName = accuserName;
        return this;
    }

    /**
     * Get the startTimeStamp property: The startTimeStamp property.
     * 
     * @return the startTimeStamp value.
     */
    public OffsetDateTime startTimeStamp() {
        return this.startTimeStamp;
    }

    /**
     * Set the startTimeStamp property: The startTimeStamp property.
     * 
     * @param startTimeStamp the startTimeStamp value to set.
     * @return the TopLevelArmResourceProperties object itself.
     */
    public TopLevelArmResourceProperties withStartTimeStamp(OffsetDateTime startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
        return this;
    }

    /**
     * Get the provisioningState property: The status of the last operation.
     * 
     * @return the provisioningState value.
     */
    public ProvisioningState provisioningState() {
        return this.provisioningState;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (userName() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException(
                    "Missing required property userName in model TopLevelArmResourceProperties"));
        }
        if (userNames() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException(
                    "Missing required property userNames in model TopLevelArmResourceProperties"));
        }
        if (accuserName() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException(
                    "Missing required property accuserName in model TopLevelArmResourceProperties"));
        }
        if (startTimeStamp() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException(
                    "Missing required property startTimeStamp in model TopLevelArmResourceProperties"));
        }
    }

    private static final ClientLogger LOGGER = new ClientLogger(TopLevelArmResourceProperties.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("userName", this.userName);
        jsonWriter.writeStringField("userNames", this.userNames);
        jsonWriter.writeStringField("accuserName", this.accuserName);
        jsonWriter.writeStringField("startTimeStamp",
            this.startTimeStamp == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.startTimeStamp));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of TopLevelArmResourceProperties from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of TopLevelArmResourceProperties if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the TopLevelArmResourceProperties.
     */
    public static TopLevelArmResourceProperties fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            TopLevelArmResourceProperties deserializedTopLevelArmResourceProperties
                = new TopLevelArmResourceProperties();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("userName".equals(fieldName)) {
                    deserializedTopLevelArmResourceProperties.userName = reader.getString();
                } else if ("userNames".equals(fieldName)) {
                    deserializedTopLevelArmResourceProperties.userNames = reader.getString();
                } else if ("accuserName".equals(fieldName)) {
                    deserializedTopLevelArmResourceProperties.accuserName = reader.getString();
                } else if ("startTimeStamp".equals(fieldName)) {
                    deserializedTopLevelArmResourceProperties.startTimeStamp = reader
                        .getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()));
                } else if ("configurationEndpoints".equals(fieldName)) {
                    List<String> configurationEndpoints = reader.readArray(reader1 -> reader1.getString());
                    deserializedTopLevelArmResourceProperties.configurationEndpoints = configurationEndpoints;
                } else if ("provisioningState".equals(fieldName)) {
                    deserializedTopLevelArmResourceProperties.provisioningState
                        = ProvisioningState.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedTopLevelArmResourceProperties;
        });
    }
}
