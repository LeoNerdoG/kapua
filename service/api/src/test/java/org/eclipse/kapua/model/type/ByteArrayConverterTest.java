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

@Category(JUnitTests.class)
public class ByteArrayConverterTest extends Assert {

    @Test
    public void constructorTest() throws Exception {
        Constructor<ByteArrayConverter> byteArrayConverterConstructor = ByteArrayConverter.class.getDeclaredConstructor();
        byteArrayConverterConstructor.setAccessible(true);
        byteArrayConverterConstructor.newInstance();
    }

    @Test
    public void toStringNoArgumentsTest() {
        Byte[] byteArray = new Byte[]{-128, 0, 127};
        Byte[] byteArrayEmpty = new Byte[]{};

        assertEquals("Compared and Expected string are not the same!", "gAB/", ByteArrayConverter.toString(byteArray));
        assertEquals("Compared and Expected string are not the same!", "", ByteArrayConverter.toString(byteArrayEmpty));
    }

    @Test(expected = NullPointerException.class)
    public void toStringNullArgumentsTest() {
        Byte[] byteArrayNull = new Byte[]{null};
        ByteArrayConverter.toString(byteArrayNull);
    }

    @Test
    public void fromStringTest() {
        String[] stringComparisonArray = new String[]{"[B@504bae78", "[B@3b764bce", "[B@759ebb3d", "[B@484b61fc", "[B@45fe3ee3"};
        String[] stringArray = new String[]{"123", "abc", "ABC", "!#$%&'()=?*-.,<_:;>", "123abcBAC!#I$=)"};
        for (int i=0; i < stringArray.length; i++) {
            assertEquals("Compared and Expected string are not the same!", stringComparisonArray[i], ByteArrayConverter.fromString(stringArray[i]).toString());
        }
    }
}