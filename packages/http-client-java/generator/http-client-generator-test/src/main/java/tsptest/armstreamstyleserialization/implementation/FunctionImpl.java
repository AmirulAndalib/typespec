// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package tsptest.armstreamstyleserialization.implementation;

import tsptest.armstreamstyleserialization.fluent.models.FunctionInner;
import tsptest.armstreamstyleserialization.models.Function;
import tsptest.armstreamstyleserialization.models.FunctionProperties;

public final class FunctionImpl implements Function {
    private FunctionInner innerObject;

    private final tsptest.armstreamstyleserialization.ArmResourceProviderManager serviceManager;

    FunctionImpl(FunctionInner innerObject,
        tsptest.armstreamstyleserialization.ArmResourceProviderManager serviceManager) {
        this.innerObject = innerObject;
        this.serviceManager = serviceManager;
    }

    public FunctionProperties properties() {
        return this.innerModel().properties();
    }

    public FunctionInner innerModel() {
        return this.innerObject;
    }

    private tsptest.armstreamstyleserialization.ArmResourceProviderManager manager() {
        return this.serviceManager;
    }
}
