// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package type.property.nullable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class CollectionsStringClientTest {

    private final CollectionsStringClient client = new NullableClientBuilder().buildCollectionsStringClient();

    @Test
    public void patchNonNull() {
        CollectionsStringProperty property = new CollectionsStringProperty().setRequiredProperty("foo")
            .setNullableProperty(Arrays.asList("hello", "world"));
        client.patchNonNull(property);
    }

    @Disabled
    @Test
    public void patchNull() {
        client.patchNull(new CollectionsStringProperty().setRequiredProperty("foo").setNullableProperty(null));
    }

    @Test
    public void getNonNull() {
        CollectionsStringProperty response = client.getNonNull();
        assertNotNull(response.getRequiredProperty());
        assertNotNull(response.getNullableProperty());
        assertEquals("foo", response.getRequiredProperty());
        assertIterableEquals(Arrays.asList("hello", "world"), response.getNullableProperty());
    }

    @Test
    public void getNull() {
        CollectionsStringProperty response = client.getNull();
        assertNotNull(response.getRequiredProperty());
        assertEquals("foo", response.getRequiredProperty());
        assertNull(response.getNullableProperty());
    }
}
