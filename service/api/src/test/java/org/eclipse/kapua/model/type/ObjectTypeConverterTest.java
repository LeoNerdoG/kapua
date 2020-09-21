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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

@Category(JUnitTests.class)
public class ObjectTypeConverterTest extends Assert {

    @Test
    public void constructorTest() throws Exception {
        Constructor<ObjectTypeConverter> objectTypeConverter = ObjectTypeConverter.class.getDeclaredConstructor();
        objectTypeConverter.setAccessible(true);
        objectTypeConverter.newInstance();
    }

    @Test
    public void toStringNullTesta() {
        assertNull("Actual value should be null!", ObjectTypeConverter.toString(null));
    }

    @Test
    public void toStringTest() throws ParseException {

        Class[] classArray = new Class[]{String.class, Integer.class, int.class,
                Long.class, Float.class, Double.class, Boolean.class, Date.class,
                Byte[].class, byte[].class};
        String[] expectedResultArray = new String[]{"string", "integer", "int",
                "long", "float", "double", "boolean", "date", "binary", "binary"};
        for (int i=0; i<classArray.length; i++) {
            assertEquals("Expected and actual values should be the same!", expectedResultArray[i],
                    ObjectTypeConverter.toString(classArray[i]));
        }
    }

    @Test
    public void fromStringNullTesta() throws ClassNotFoundException {
        assertNull("Actual value should be null!", ObjectTypeConverter.fromString(null));
    }

    @Test
    public void fromStringTest() throws ClassNotFoundException {

        Class[] expectedClassArray = new Class[]{String.class, Integer.class, Integer.class,
                Long.class, Float.class, Double.class, Boolean.class, Date.class,
                byte[].class, ArrayList.class};

        String[] stringArray = new String[]{"string", "integer", "int", "long", "float",
                "double", "boolean", "date", "binary", "java.util.ArrayList"};

        for(int i=0; i<stringArray.length; i++) {
            assertEquals("Expected and actual values should be the same!", expectedClassArray[i],
                    ObjectTypeConverter.fromString(stringArray[i]));
        }
    }
}