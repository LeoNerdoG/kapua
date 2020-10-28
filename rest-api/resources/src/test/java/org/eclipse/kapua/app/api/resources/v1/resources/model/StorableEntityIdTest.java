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
package org.eclipse.kapua.app.api.resources.v1.resources.model;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class StorableEntityIdTest extends Assert {

    @Test
    public void storableEntityIdWithParameterTest() {
        String[] ids = {"id", "", "id123", "id!@#$%^&*<>|"};

        for (String id : ids) {
            StorableEntityId storableEntityId = new StorableEntityId(id);
            assertEquals("Expected and actual values should be the same.", id, storableEntityId.getId());
            assertEquals("Expected and actual values should be the same.", id, storableEntityId.toString());
        }
    }

    @Test
    public void storableEntityIdWithNullParameterTest() {
        StorableEntityId storableEntityId = new StorableEntityId(null);
        assertNull("Null expected.", storableEntityId.getId());
        assertNull("Null expected.", storableEntityId.toString());
    }

    @Test
    public void storableEntityIdWithoutParameterTest() {
        StorableEntityId storableEntityId = new StorableEntityId();
        assertNull("Null expected.", storableEntityId.getId());
        assertNull("Null expected.", storableEntityId.toString());
    }

    @Test
    public void setAndGetIdTest() {
        StorableEntityId storableEntityId1 = new StorableEntityId();
        StorableEntityId storableEntityId2 = new StorableEntityId("id");

        String[] ids = {"id", "", "id123", "id!@#$%^&*<>|"};

        for (String id : ids) {
            storableEntityId1.setId(id);
            storableEntityId2.setId(id);

            assertEquals("Expected and actual values should be the same.", id, storableEntityId1.getId());
            assertEquals("Expected and actual values should be the same.", id, storableEntityId1.toString());

            assertEquals("Expected and actual values should be the same.", id, storableEntityId2.getId());
            assertEquals("Expected and actual values should be the same.", id, storableEntityId2.toString());
        }
    }
}