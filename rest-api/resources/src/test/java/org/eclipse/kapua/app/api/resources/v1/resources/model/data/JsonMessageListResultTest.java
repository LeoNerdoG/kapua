/*******************************************************************************
 * Copyright (c) 2020 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.app.api.resources.v1.resources.model.data;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.service.elasticsearch.client.model.ResultList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

@Category(JUnitTests.class)
public class JsonMessageListResultTest extends Assert {

    ResultList<JsonDatastoreMessage> resultList;
    List expectedList;
    JsonDatastoreMessage item1;
    JsonDatastoreMessage item2;

    @Before
    public void initialize() {
        resultList = new ResultList<>(3);
        item1 = Mockito.mock(JsonDatastoreMessage.class);
        item2 = Mockito.mock(JsonDatastoreMessage.class);

        resultList.add(item1);
        resultList.add(null);
        resultList.add(item2);
        expectedList = new LinkedList<>();

        expectedList.add(item1);
        expectedList.add(null);
        expectedList.add(item2);
    }

    @Test
    public void jsonMessageListResultWithoutParameterTest() {
        JsonMessageListResult jsonMessageListResult = new JsonMessageListResult();

        assertTrue("True expected.", jsonMessageListResult.getItems().isEmpty());
        assertNull("Null expected.", jsonMessageListResult.getTotalCount());
    }

    @Test
    public void jsonMessageListResultWithParameterTest() {
        JsonMessageListResult jsonMessageListResult = new JsonMessageListResult(resultList);

        assertEquals("Expected and actual values should be the same.", expectedList, jsonMessageListResult.getItems());
        assertEquals("Expected and actual values should be the same.", (Long) 3L, jsonMessageListResult.getTotalCount());
    }

    @Test(expected = NullPointerException.class)
    public void jsonMessageListResultWithNullParameterTest() {
        new JsonMessageListResult(null);
    }
}