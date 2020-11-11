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

import org.eclipse.kapua.KapuaRuntimeException;
import org.eclipse.kapua.event.ServiceEvent;
import org.eclipse.kapua.qa.markers.Categories;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.lang.reflect.Field;
import java.util.Stack;

@Category(Categories.junitTests.class)
public class ServiceEventScopeTest extends Assert {

    ServiceEvent serviceEvent;

    @Before
    public void createInstanceOfClasses() {
        serviceEvent = new ServiceEvent();
    }

    @Test
    public void serviceEventBeginNullTest() throws Exception {
        ServiceEventScope.begin();
        Field privateEventContextThdLocal = ServiceEventScope.class.getDeclaredField("eventContextThdLocal");
        privateEventContextThdLocal.setAccessible(true);
        ThreadLocal<Stack<ServiceEvent>> fieldValue = (ThreadLocal<Stack<ServiceEvent>>) privateEventContextThdLocal.get(null);
        assertNotNull(fieldValue.get().peek().getContextId());
    }

    @Test(expected = KapuaRuntimeException.class)
    public void serviceEventEndNullTest() throws Exception {
        Field privateEventContextThdLocal = ServiceEventScope.class.getDeclaredField("eventContextThdLocal");
        privateEventContextThdLocal.setAccessible(true);
        ThreadLocal<Stack<ServiceEvent>> fieldValue = (ThreadLocal<Stack<ServiceEvent>>) privateEventContextThdLocal.get(null);
        fieldValue.set(null);
        ServiceEventScope.end();
    }

    @Test
    public void serviceEventBeginEndTest() throws Exception {
        ServiceEventScope.begin();
        Field privateEventContextThdLocal = ServiceEventScope.class.getDeclaredField("eventContextThdLocal");
        privateEventContextThdLocal.setAccessible(true);
        ThreadLocal<Stack<ServiceEvent>> fieldValue = (ThreadLocal<Stack<ServiceEvent>>) privateEventContextThdLocal.get(null);
        ServiceEventScope.end();
        assertNull("not_null", fieldValue.get());
    }

    @Test
    public void serviceEventSetAndGetRegularTest() {
        ServiceEventScope.set(serviceEvent);
        assertEquals("not_equals", serviceEvent, ServiceEventScope.get());
    }
}