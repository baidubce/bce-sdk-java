/*
 * Copyright  2019 Baidu, Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.cfc.model;

import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request object for generating the Baidu CFC function
 */
public abstract class GenericFunctionRequest extends AbstractBceRequest {

    /**
     * The min length of the CFC function name
     */
    private static final int MIN_FUNCTION_NAME_LENGTH = 1;

    /**
     * The max length of the CFC function name
     */
    private static final int MAX_FUNCTION_NAME_LENGTH = 64;

    /**
     * The max length of the CFC function description
     */
    private static final int MAX_DESCRIPTION_LENGTH = 256;

    /**
     * Function name
     */
    @JsonProperty(value = "FunctionName")
    private String FunctionName;

    /**
     * Function description
     */
    @JsonProperty(value = "Description")
    private String Description;

    public GenericFunctionRequest() {

    }

    public GenericFunctionRequest(String functionName, String description) {

        if (description == null) {
            description = "";
        }
        this.setFunctionName(functionName);
        this.setDescription(description);
    }

    @JsonProperty(value = "FunctionName")
    public String getFunctionName() {
        return this.FunctionName;
    }

    /**
     * Function name, consisting of numbers, letters, - or _, limited to 64 characters in length
     * @param functionName
     */
    public void setFunctionName(String functionName) {

        if (functionName == null) {
            throw new IllegalArgumentException("Invalid functionName: functionName should not be empty");
        }

        functionName = functionName.trim();
        if (functionName.length() < MIN_FUNCTION_NAME_LENGTH) {
            throw new IllegalArgumentException("Invalid functionName: " + functionName + ". " +
                    "functionName should not be less than " + MIN_FUNCTION_NAME_LENGTH +
                    ".");
        }

        if (functionName.length() > MAX_FUNCTION_NAME_LENGTH) {
            throw new IllegalArgumentException("Invalid functionName: " + functionName + ". " +
                    "functionName should not be greater than " + MAX_FUNCTION_NAME_LENGTH);
        }

        if (!isLegalFunctionName(functionName)) {
            throw new IllegalArgumentException("Invalid functionName: " + functionName + ". " +
                    "functionName should be lowercase letters or digit or - or _");
        }

        this.FunctionName = functionName;

    }

    public abstract GenericFunctionRequest withFunctionName(String functionName);

    private static boolean isLegalFunctionName(String functionName) {

        String regex = "^[a-z0-9A-Z-_]+$";
        return functionName.matches(regex);
    }

    @JsonProperty(value = "Description")
    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String description) {

        if (description == null) {
            this.Description = description;
        }

        description = description.trim();
        if (description.length() > 256) {
            throw new IllegalArgumentException("Invalid description: " + description + ". " +
                    "description should not be greater than " + MAX_DESCRIPTION_LENGTH);
        }

        this.Description = description;
    }

    public abstract GenericFunctionRequest withDescription(String description);

}

