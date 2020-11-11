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

import org.eclipse.kapua.qa.markers.Categories;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

@Category(Categories.junitTests.class)
public class ByteArrayConverterTest extends Assert {

    Byte[] byteClassArray;
    byte[] byteArray;
    String expectedString;

    @Before
    public void initialize() {
        byteClassArray = new Byte[]{-128, -10, 0, 1, 10, 127};
        byteArray = new byte[]{-128, -10, 0, 1, 10, 127};
        expectedString = "gPYAAQp/";
    }

    @Test
    public void byteArrayConverterTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<ByteArrayConverter> byteArrayConverter = ByteArrayConverter.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(byteArrayConverter.getModifiers()));
        byteArrayConverter.setAccessible(true);
        byteArrayConverter.newInstance();
    }

    @Test
    public void toStringByteClassParameterTest() {
        assertEquals("Expected and actual values should be the same.", expectedString, ByteArrayConverter.toString(byteClassArray));
    }

    @Test(expected = NullPointerException.class)
    public void toStringNullByteClassParameterTest() {
        ByteArrayConverter.toString((Byte[]) null);
    }

    @Test
    public void toStringTest() {
        assertEquals("Expected and actual values should be the same.", expectedString, ByteArrayConverter.toString(byteArray));
    }

    @Test(expected = NullPointerException.class)
    public void toStringNullParameterTest() {
        ByteArrayConverter.toString((byte[]) null);
    }

    @Test
    public void fromStringTest() {
        String stringValue = "String Value";
        assertThat("Instance of byte[] expected.", ByteArrayConverter.fromString(stringValue), IsInstanceOf.instanceOf(byte[].class));
    }

    @Test(expected = NullPointerException.class)
    public void fromStringNullParameterTest() {
        ByteArrayConverter.fromString(null);
    }
}