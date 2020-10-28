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
public class ByteArrayParamTest extends Assert {

    @Test
    public void byteArrayParamTest() {
        String[] base64encoded = {"", "Some text", "1234567890", "!@#$%^&*()_+';.,|<>"};
        for (String encoded : base64encoded) {
            ByteArrayParam byteArrayParam = new ByteArrayParam(encoded);
            assertNotNull("NotNull expected.", byteArrayParam.getValue());
        }
    }

    @Test
    public void byteArrayParamNullTest() {
        ByteArrayParam byteArrayParam = new ByteArrayParam(null);
        assertNull("Null expected.", byteArrayParam.getValue());
    }
}