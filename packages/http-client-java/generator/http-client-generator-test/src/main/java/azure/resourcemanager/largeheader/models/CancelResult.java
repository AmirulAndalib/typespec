// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package azure.resourcemanager.largeheader.models;

import azure.resourcemanager.largeheader.fluent.models.CancelResultInner;

/**
 * An immutable client-side representation of CancelResult.
 */
public interface CancelResult {
    /**
     * Gets the succeeded property: The succeeded property.
     * 
     * @return the succeeded value.
     */
    boolean succeeded();

    /**
     * Gets the inner azure.resourcemanager.largeheader.fluent.models.CancelResultInner object.
     * 
     * @return the inner object.
     */
    CancelResultInner innerModel();
}
