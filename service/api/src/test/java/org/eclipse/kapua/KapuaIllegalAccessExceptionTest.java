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
package org.eclipse.kapua;

import org.eclipse.kapua.qa.markers.Categories;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Categories.junitTests.class)
public class KapuaIllegalAccessExceptionTest extends Assert {

    String[] operationName;

    @Before
    public void initialize() {
        operationName = new String[]{"Operation Name", null};
    }

    @Test
    public void kapuaIllegalAccessExceptionTest() {
        for (String name : operationName) {
            KapuaIllegalAccessException kapuaIllegalAccessException = new KapuaIllegalAccessException(name);
            assertEquals("Expected and actual values should be the same.", KapuaErrorCodes.ILLEGAL_ACCESS, kapuaIllegalAccessException.getCode());
            assertEquals("Expected and actual values should be the same.", "The current subject is not authorized for " + name + ".", kapuaIllegalAccessException.getMessage());
            assertNull("Null expected.", kapuaIllegalAccessException.getCause());
        }
    }

    @Test(expected = KapuaIllegalAccessException.class)
    public void throwingExceptionTest() throws KapuaIllegalAccessException {
        for (String name : operationName) {
            throw new KapuaIllegalAccessException(name);
        }
    }
}  