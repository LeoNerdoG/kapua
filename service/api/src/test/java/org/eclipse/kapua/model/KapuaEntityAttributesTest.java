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
package org.eclipse.kapua.model;

import org.eclipse.kapua.qa.markers.Categories;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

@Category(Categories.junitTests.class)
public class KapuaEntityAttributesTest extends Assert {

    @Test
    public void kapuaEntityAttributesTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<KapuaEntityAttributes> kapuaEntityAttributes = KapuaEntityAttributes.class.getDeclaredConstructor();
        assertTrue(Modifier.isProtected(kapuaEntityAttributes.getModifiers()));
        kapuaEntityAttributes.setAccessible(true);
        kapuaEntityAttributes.newInstance();
    }
}