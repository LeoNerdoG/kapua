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
package org.eclipse.kapua.model.query;

import org.eclipse.kapua.KapuaIllegalArgumentException;
import org.eclipse.kapua.qa.markers.Categories;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Categories.junitTests.class)
public class SortOrderTest extends Assert {

    @Test
    public void fromStringTest() throws KapuaIllegalArgumentException {
        String[] stringValue = {"Ascending", "Descending"};
        SortOrder[] expectedValues = {SortOrder.ASCENDING, SortOrder.DESCENDING};

        for (int i = 0; i < stringValue.length; i++) {
            assertEquals("Expected and actual values should be the same.", expectedValues[i], SortOrder.fromString(stringValue[i]));
        }
    }

    @Test(expected = KapuaIllegalArgumentException.class)
    public void fromStringInvalidStringValueTest() throws KapuaIllegalArgumentException {
        String invalidStringValue = "invalidStringValue";
        SortOrder.fromString(invalidStringValue);
    }
}