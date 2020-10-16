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
package org.eclipse.kapua.broker.core.plugin;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KapuaDuplicateClientIdExceptionTest extends Assert {

    @Test
    public void kapuaDuplicateClientIdException() {
        String[] clientIds = {"Client Id", "id1234567890", "?><|+_)(*&^%$#@!~`", null};
        for (String id : clientIds) {
            KapuaDuplicateClientIdException kapuaDuplicateClientIdException = new KapuaDuplicateClientIdException(id);
            assertEquals("Expected and actual values should be the same.", KapuaBrokerErrorCodes.DUPLICATED_CLIENT_ID, kapuaDuplicateClientIdException.getCode());
            assertNull("Null expected.", kapuaDuplicateClientIdException.getCause());
            assertEquals("Expected and actual values should be the same.", "Error: " + id, kapuaDuplicateClientIdException.getMessage());
        }
    }

    @Test(expected = KapuaDuplicateClientIdException.class)
    public void throwingKapuaDuplicateClientIdExceptionTest() throws KapuaDuplicateClientIdException {
        throw new KapuaDuplicateClientIdException("Client Id");
    }

    @Test(expected = KapuaDuplicateClientIdException.class)
    public void throwingKapuaDuplicateNullClientIdExceptionTest() throws KapuaDuplicateClientIdException {
        throw new KapuaDuplicateClientIdException(null);
    }
}