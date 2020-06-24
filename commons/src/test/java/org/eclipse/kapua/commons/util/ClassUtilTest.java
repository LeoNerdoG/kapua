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
package org.eclipse.kapua.commons.util;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.event.ServiceEntry;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.lang.reflect.Constructor;

@Category(JUnitTests.class)
public class ClassUtilTest extends Assert {

    String serviceName, address;

    @Test
    public void constructorTest() throws Exception {
        Constructor<ClassUtil> classUtilConstructor = ClassUtil.class.getDeclaredConstructor();
        classUtilConstructor.setAccessible(true);
        classUtilConstructor.newInstance();
    }

    @Test(expected = NullPointerException.class)
    public void newInstanceNullTest() throws KapuaException {
        assertNull("The class does not exist.", ClassUtil.newInstance(null, null));
    }

    @Test
    public void newInstanceTest() throws KapuaException {
        assertNotNull("The class does not exist.", ClassUtil.newInstance("org.eclipse.kapua.commons.event.ServiceEntry", ServiceEntry.class, new Class<?>[]{String.class, String.class}, new Object[]{serviceName, address}));
    }

    @Test
    public void newInstanceEmptyClazzTest() throws KapuaException {
        assertNotNull("The class does not exist.", ClassUtil.newInstance("", ServiceEntry.class, new Class<?>[]{String.class, String.class}, new Object[]{serviceName, address}));
    }

    @Test(expected = KapuaException.class)
    public void newInstanceParameterTypeNullTest() throws KapuaException {
        assertNull("The class does not exist.", ClassUtil.newInstance("org.eclipse.kapua.commons.event.ServiceEntry", ServiceEntry.class, null, new Object[]{serviceName, address}));
    }

    @Test(expected = KapuaException.class)
    public void newInstanceParameterNullTest() throws KapuaException {
        assertNull("The class does not exist.", ClassUtil.newInstance("org.eclipse.kapua.commons.event.ServiceEntry", ServiceEntry.class, new Class<?>[]{String.class, String.class}, null));
    }

    @Test(expected = KapuaException.class)
    public void newInstanceParametersNullTest() throws KapuaException {
        assertNull("The class does not exist.", ClassUtil.newInstance("org.eclipse.kapua.commons.event.ServiceEntry", ServiceEntry.class, null, null));
    }

    @Test(expected = KapuaException.class)
    public void newInstanceWrongPackageNameTest() throws KapuaException {
        assertNull("The class does not exist.", ClassUtil.newInstance("org.eclipse.kapua.commons.ServiceEntry", ServiceEntry.class, null, null));
    }

    @Test(expected = KapuaException.class)
    public void newInstanceWrongParametersTypeTest() throws KapuaException {
        assertNull("PARAMETER_ERROR_MSG", ClassUtil.newInstance("org.eclipse.kapua.commons.event.ServiceEntry", ServiceEntry.class, new Class<?>[]{String.class, Object.class}, new Object[]{serviceName, address}));
    }

    @Test(expected = KapuaException.class)
    public void newInstanceWrongParametersTypeNumberTest() throws KapuaException {
        assertNull("PARAMETER_ERROR_MSG", ClassUtil.newInstance("org.eclipse.kapua.commons.event.ServiceEntry", ServiceEntry.class, new Class<?>[]{String.class, String.class, String.class}, new Object[]{serviceName, address}));
    }

    @Test(expected = NullPointerException.class)
    public void newInstanceAllNullTest() throws KapuaException {
        assertNull("The class does not exist.", ClassUtil.newInstance(null, null, null, null));
    }
}
