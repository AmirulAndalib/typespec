// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package methodoverride.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;

/**
 * The GroupPartETagOptions model.
 */
@Fluent
public final class GroupPartETagOptions {
    /*
     * The bar property.
     */
    @Generated
    private String bar;

    /*
     * The prop2 property.
     */
    @Generated
    private String prop2;

    /**
     * Creates an instance of GroupPartETagOptions class.
     */
    @Generated
    public GroupPartETagOptions() {
    }

    /**
     * Get the bar property: The bar property.
     * 
     * @return the bar value.
     */
    @Generated
    public String getBar() {
        return this.bar;
    }

    /**
     * Set the bar property: The bar property.
     * 
     * @param bar the bar value to set.
     * @return the GroupPartETagOptions object itself.
     */
    @Generated
    public GroupPartETagOptions setBar(String bar) {
        this.bar = bar;
        return this;
    }

    /**
     * Get the prop2 property: The prop2 property.
     * 
     * @return the prop2 value.
     */
    @Generated
    public String getProp2() {
        return this.prop2;
    }

    /**
     * Set the prop2 property: The prop2 property.
     * 
     * @param prop2 the prop2 value to set.
     * @return the GroupPartETagOptions object itself.
     */
    @Generated
    public GroupPartETagOptions setProp2(String prop2) {
        this.prop2 = prop2;
        return this;
    }
}
