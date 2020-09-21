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
import java.util.ArrayList;

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
        Byte[] byteArray1 = new Byte[]{1,2,3};
        byte[] byteArray2 = new byte[]{};
        byte[] byteArray3 = new byte[]{1,2,3};

        Object[] objectArray = new Object[]{"string", (Integer) 123, (int) 123,
                1234567L, 1.1234567F, 1.123456789D, true, byteArray, byteArray2};
        Object[] objectArray1 = new Object[]{byteArray1, byteArray3};
        String[] expectedResultArray = new String[]{"string", "123", "123",
                "1234567", "1.1234567", "1.123456789", "true", "", ""};
        for (int i=0; i<objectArray.length; i++) {
            assertEquals("Expected and actual values should be the same!",
                    expectedResultArray[i], ObjectValueConverter.toString(objectArray[i]));
        }
        for (Object objectVal: objectArray1) {
            assertEquals("Return value should start with '[B@', but starts with: ", "AQID", ObjectValueConverter.toString(objectVal).toString());
        }

    }

    @Test
    public void fromStringNullTest() {
        assertNull("Null expected.", ObjectValueConverter.fromString(null, null));
    }

    @Test
    public void fromStringTest() {
        Class[] objectClassArray = new Class[]{String.class, Integer.class, Long.class,
                Float.class, Double.class, Boolean.class, Boolean.class, ArrayList.class, byte[].class, Byte[].class};
        String[] inputStringArray = new String[]{"string", "123", "1234",
                "1.23456F", "1.1234567D", "true", "false", "arrayList"};
        Object[] expectedResultArray = new Object[]{"string", 123, 1234L, 1.23456F, 1.1234567D, true, false, "arrayList"};
        for(int i=0; i<objectClassArray.length; i++){
            if (objectClassArray[i]!=byte[].class && objectClassArray[i] != Byte[].class) {
                assertEquals("Expected and actual values are not the same at " + i + " position" , expectedResultArray[i], ObjectValueConverter.fromString(inputStringArray[i], objectClassArray[i]));
            }
            else {
                assertTrue("Return value should start with '[B@'.", ObjectValueConverter.fromString("123", objectClassArray[i]).toString().startsWith("[B@"));
            }
        }
    }
}