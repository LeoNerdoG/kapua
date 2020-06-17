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

import org.eclipse.kapua.event.ServiceEventBusListener;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class ServiceEventClientConfigurationTest extends Assert {

    ServiceEventBusListener serviceEventBusListener;

    @Test
    public void constructorRegularTest() {
        ServiceEventClientConfiguration serviceEventClientConfiguration = new ServiceEventClientConfiguration("address", "subscriberName", serviceEventBusListener);
        assertEquals("not_equals", "address", serviceEventClientConfiguration.getAddress());
        assertEquals("not_equals", "subscriberName", serviceEventClientConfiguration.getClientName());
        assertEquals("not_equals", serviceEventBusListener, serviceEventClientConfiguration.getEventListener());
    }

    @Test
    public void constructorNullTest() {
        ServiceEventClientConfiguration serviceEventClientConfiguration = new ServiceEventClientConfiguration(null , null, null);
        assertNull("not_null", serviceEventClientConfiguration.getAddress());
        assertNull("not_null", serviceEventClientConfiguration.getClientName());
        assertNull("not_null", serviceEventClientConfiguration.getEventListener());
    }

    @Test
    public void constructorAddressNullTest() {
        ServiceEventClientConfiguration serviceEventClientConfiguration = new ServiceEventClientConfiguration(null , "subscriberName", serviceEventBusListener);
        assertNull("not_null", serviceEventClientConfiguration.getAddress());
        assertEquals("not_equals", "subscriberName", serviceEventClientConfiguration.getClientName());
        assertEquals("not_equals", serviceEventBusListener, serviceEventClientConfiguration.getEventListener());
    }

    @Test
    public void constructorClientNameNullTest() {
        ServiceEventClientConfiguration serviceEventClientConfiguration = new ServiceEventClientConfiguration("address", null, serviceEventBusListener);
        assertEquals("not_equals", "address", serviceEventClientConfiguration.getAddress());
        assertNull("not_null", serviceEventClientConfiguration.getClientName());
        assertEquals("not_equals", serviceEventBusListener, serviceEventClientConfiguration.getEventListener());
    }

    @Test
    public void constructorServiceEventNullTest() {
        ServiceEventClientConfiguration serviceEventClientConfiguration = new ServiceEventClientConfiguration("address", "subscriberName", null);
        assertEquals("not_equals", "address", serviceEventClientConfiguration.getAddress());
        assertEquals("not_equals", "subscriberName", serviceEventClientConfiguration.getClientName());
        assertNull("not_null", serviceEventClientConfiguration.getEventListener());
    }

    @Test
    public void constructorAddressAndNameNullTest() {
        ServiceEventClientConfiguration serviceEventClientConfiguration = new ServiceEventClientConfiguration(null, null, serviceEventBusListener);
        assertNull("not_null", serviceEventClientConfiguration.getAddress());
        assertNull("not_null", serviceEventClientConfiguration.getClientName());
        assertEquals("not_equals", serviceEventBusListener, serviceEventClientConfiguration.getEventListener());
    }

    @Test
    public void constructorNameAndServiceEventNullTest() {
        ServiceEventClientConfiguration serviceEventClientConfiguration = new ServiceEventClientConfiguration("address", null, null);
        assertEquals("not_equals", "address", serviceEventClientConfiguration.getAddress());
        assertNull("not_null", serviceEventClientConfiguration.getClientName());
        assertNull("not_null", serviceEventClientConfiguration.getEventListener());
    }

    @Test
    public void constructorAddressAndNameCharCheckTest() {
        String[] permittedValues = {"", "!@#$%^^&**(-()_)+/|", "regularNaming", "regular Naming", "49", "regularNaming49", "NAMING", "246465494135646120009090049684646496468456468496846464968496844"};
        for (String value : permittedValues) {
                ServiceEventClientConfiguration serviceEventClientConfiguration = new ServiceEventClientConfiguration(value, value, serviceEventBusListener);
                assertEquals("not_equals", value, serviceEventClientConfiguration.getAddress());
                assertEquals("not_equals", value, serviceEventClientConfiguration.getClientName());
                assertEquals("not_equals", serviceEventBusListener, serviceEventClientConfiguration.getEventListener());
        }
    }
}