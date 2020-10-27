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
package org.eclipse.kapua.broker.core.router;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

@Category(JUnitTests.class)
public class PlaceholderReplacerTest extends Assert {

    @Test
    // COMMENT: Can you just please replace all the  exceptions thrown into one "Exception"?
    public void placeholderReplacerTest() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Constructor<PlaceholderReplacer> placeholderReplacer = PlaceholderReplacer.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(placeholderReplacer.getModifiers()));
        placeholderReplacer.setAccessible(true);
        placeholderReplacer.newInstance();
    }

    @Test
    public void replaceTest() {
        // COMMENT: Can you add additional tests for this? Only testing with "test" string is not enough.
        String stringValue = PlaceholderReplacer.replace("test");
        assertEquals("Expected and actual values should be the same.", "test", stringValue);
    }

    @Test
    public void replaceNullTest() {
        String stringValue = PlaceholderReplacer.replace(null);
        assertNull("Null expected.", stringValue);
    }
}