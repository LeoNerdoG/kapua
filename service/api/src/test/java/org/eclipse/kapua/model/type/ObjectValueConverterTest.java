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
package org.eclipse.kapua.model.type;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.lang.reflect.Constructor;
import java.util.Date;

@Category(JUnitTests.class)
public class ObjectValueConverterTest extends Assert {

    @Test
    public void constructorTest() throws Exception {
        Constructor<ObjectValueConverter> objectValueConverter = ObjectValueConverter.class.getDeclaredConstructor();
        objectValueConverter.setAccessible(true);
        objectValueConverter.newInstance();
    }

    @Test
    public void toStringNullTesta() {
        assertNull("Actual value should be null!", ObjectValueConverter.toString(null));
    }

    @Test
    public void toStringTest() {
        Byte[] byteArray = new Byte[]{};
        byte[] byteArray2 = new byte[]{};

        Object[] objectArray = new Object[]{"string", (Integer) 123, (int) 123,
                1234567L, 1.1234567F, 1.123456789D, true, byteArray, byteArray2};
        String[] expectedResultArray = new String[]{"string", "123", "123",
                "1234567", "1.1234567", "1.123456789", "true", "", ""};
        for (int i=0; i<objectArray.length; i++) {
            assertEquals("Expected and actual values should be the same!", expectedResultArray[i],
                    ObjectValueConverter.toString(objectArray[i]));
        }
        // add scenarios for when byte arrays are not empty
    }
}