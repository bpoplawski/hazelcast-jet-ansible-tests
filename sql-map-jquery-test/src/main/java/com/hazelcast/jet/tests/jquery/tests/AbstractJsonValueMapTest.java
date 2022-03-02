/*
 * Copyright (c) 2008-2018, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.jet.tests.jquery.tests;

import java.io.IOException;


public abstract class AbstractJsonValueMapTest extends AbstractJsonMapTest {

    public AbstractJsonValueMapTest(String mapName, String sqlQuery, String jsonPath, Boolean resultRequiredSort)
            throws IOException {
        super(mapName, sqlQuery, jsonPath, resultRequiredSort);
    }

    @Override
    protected String retrieveExpectedJsonStructure(String jsonInputString, String jsonPath) {
        return JsonExtractor.getJsonValueByJsonPath(jsonInputString, jsonPath);
    }
}