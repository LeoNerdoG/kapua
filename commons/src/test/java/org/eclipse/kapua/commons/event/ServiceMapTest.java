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
package org.eclipse.kapua.commons.event;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;


@Category(JUnitTests.class)
public class ServiceMapTest {

    @Test
    public void constructorTest() throws Exception {
        Constructor<ServiceMap> serviceMapConstructor = ServiceMap.class.getDeclaredConstructor();
        serviceMapConstructor.setAccessible(true);
        serviceMapConstructor.newInstance();
    }

    @Test
    public void registerServicesTest() throws Exception {
        Method registerServices = ServiceMap.class.getDeclaredMethod("registerServices", String.class, List.class);
    }
}
