// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package azure.resourcemanager.resources.models;

import azure.resourcemanager.resources.fluent.models.ExtensionsResourceInner;
import com.azure.core.management.SystemData;
import com.azure.core.util.Context;

/**
 * An immutable client-side representation of ExtensionsResource.
 */
public interface ExtensionsResource {
    /**
     * Gets the id property: Fully qualified resource Id for the resource.
     * 
     * @return the id value.
     */
    String id();

    /**
     * Gets the name property: The name of the resource.
     * 
     * @return the name value.
     */
    String name();

    /**
     * Gets the type property: The type of the resource.
     * 
     * @return the type value.
     */
    String type();

    /**
     * Gets the properties property: The resource-specific properties for this resource.
     * 
     * @return the properties value.
     */
    ExtensionsResourceProperties properties();

    /**
     * Gets the systemData property: Azure Resource Manager metadata containing createdBy and modifiedBy information.
     * 
     * @return the systemData value.
     */
    SystemData systemData();

    /**
     * Gets the inner azure.resourcemanager.resources.fluent.models.ExtensionsResourceInner object.
     * 
     * @return the inner object.
     */
    ExtensionsResourceInner innerModel();

    /**
     * The entirety of the ExtensionsResource definition.
     */
    interface Definition extends DefinitionStages.Blank, DefinitionStages.WithScope, DefinitionStages.WithCreate {
    }

    /**
     * The ExtensionsResource definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of the ExtensionsResource definition.
         */
        interface Blank extends WithScope {
        }

        /**
         * The stage of the ExtensionsResource definition allowing to specify parent resource.
         */
        interface WithScope {
            /**
             * Specifies resourceUri.
             * 
             * @param resourceUri The fully qualified Azure Resource manager identifier of the resource.
             * @return the next definition stage.
             */
            WithCreate withExistingResourceUri(String resourceUri);
        }

        /**
         * The stage of the ExtensionsResource definition which contains all the minimum required properties for the
         * resource to be created, but also allows for any other optional properties to be specified.
         */
        interface WithCreate extends DefinitionStages.WithProperties {
            /**
             * Executes the create request.
             * 
             * @return the created resource.
             */
            ExtensionsResource create();

            /**
             * Executes the create request.
             * 
             * @param context The context to associate with this operation.
             * @return the created resource.
             */
            ExtensionsResource create(Context context);
        }

        /**
         * The stage of the ExtensionsResource definition allowing to specify properties.
         */
        interface WithProperties {
            /**
             * Specifies the properties property: The resource-specific properties for this resource..
             * 
             * @param properties The resource-specific properties for this resource.
             * @return the next definition stage.
             */
            WithCreate withProperties(ExtensionsResourceProperties properties);
        }
    }

    /**
     * Begins update for the ExtensionsResource resource.
     * 
     * @return the stage of resource update.
     */
    ExtensionsResource.Update update();

    /**
     * The template for ExtensionsResource update.
     */
    interface Update extends UpdateStages.WithProperties {
        /**
         * Executes the update request.
         * 
         * @return the updated resource.
         */
        ExtensionsResource apply();

        /**
         * Executes the update request.
         * 
         * @param context The context to associate with this operation.
         * @return the updated resource.
         */
        ExtensionsResource apply(Context context);
    }

    /**
     * The ExtensionsResource update stages.
     */
    interface UpdateStages {
        /**
         * The stage of the ExtensionsResource update allowing to specify properties.
         */
        interface WithProperties {
            /**
             * Specifies the properties property: The resource-specific properties for this resource..
             * 
             * @param properties The resource-specific properties for this resource.
             * @return the next definition stage.
             */
            Update withProperties(ExtensionsResourceProperties properties);
        }
    }

    /**
     * Refreshes the resource to sync with Azure.
     * 
     * @return the refreshed resource.
     */
    ExtensionsResource refresh();

    /**
     * Refreshes the resource to sync with Azure.
     * 
     * @param context The context to associate with this operation.
     * @return the refreshed resource.
     */
    ExtensionsResource refresh(Context context);
}
