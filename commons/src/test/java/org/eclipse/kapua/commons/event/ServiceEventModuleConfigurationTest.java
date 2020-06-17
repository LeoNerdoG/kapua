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

import org.eclipse.kapua.commons.jpa.EntityManagerFactory;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class ServiceEventModuleConfigurationTest extends Assert {

    EntityManagerFactory entityManagerFactory;

    ServiceEventClientConfiguration[] serviceEventClientConfiguration = new ServiceEventClientConfiguration[]
            { new ServiceEventClientConfiguration("address", "subscriberName", null),
             new ServiceEventClientConfiguration("address2", "subscriberName2", null) };

    @Test
    public void constructorRegularTest() {
        ServiceEventModuleConfiguration moduleConfiguration = new ServiceEventModuleConfiguration("internalAddress", entityManagerFactory, serviceEventClientConfiguration);
        assertEquals("not_equals", "internalAddress", moduleConfiguration.getInternalAddress());
        assertNull("not_null", moduleConfiguration.getEntityManagerFactory());
        assertArrayEquals(serviceEventClientConfiguration, moduleConfiguration.getServiceEventClientConfigurations());
    }

    @Test
    public void constructorAddressNullTest() {
        ServiceEventModuleConfiguration moduleConfiguration = new ServiceEventModuleConfiguration(null, entityManagerFactory, serviceEventClientConfiguration);
        assertNull("not_null", moduleConfiguration.getInternalAddress());
        assertNull("not_null", moduleConfiguration.getEntityManagerFactory());
        assertArrayEquals(serviceEventClientConfiguration, moduleConfiguration.getServiceEventClientConfigurations());
    }

    @Test
    public void constructorEntityNullTest() {
        ServiceEventModuleConfiguration moduleConfiguration = new ServiceEventModuleConfiguration("internalAddress", null, serviceEventClientConfiguration);
        assertEquals("not_equals", "internalAddress", moduleConfiguration.getInternalAddress());
        assertNull("not_null", moduleConfiguration.getEntityManagerFactory());
        assertArrayEquals(serviceEventClientConfiguration, moduleConfiguration.getServiceEventClientConfigurations());
    }

    @Test
    public void constructorClientConfigNullTest() {
        ServiceEventModuleConfiguration moduleConfiguration = new ServiceEventModuleConfiguration("internalAddress", entityManagerFactory, null);
        assertEquals("not_equals", "internalAddress", moduleConfiguration.getInternalAddress());
        assertNull("not_null", moduleConfiguration.getEntityManagerFactory());
        assertNull("not_null", moduleConfiguration.getServiceEventClientConfigurations());
    }

    @Test
    public void constructorAddressAndEntityNullTest() {
        ServiceEventModuleConfiguration moduleConfiguration = new ServiceEventModuleConfiguration(null, null, serviceEventClientConfiguration);
        assertNull("not_null", moduleConfiguration.getInternalAddress());
        assertNull("not_null", moduleConfiguration.getEntityManagerFactory());
        assertArrayEquals(serviceEventClientConfiguration, moduleConfiguration.getServiceEventClientConfigurations());
    }

    @Test
    public void constructorEntityAndClientConfigNullTest() {
        ServiceEventModuleConfiguration moduleConfiguration = new ServiceEventModuleConfiguration("internalAddress", null, null);
        assertEquals("not_equals", "internalAddress", moduleConfiguration.getInternalAddress());
        assertNull("not_null", moduleConfiguration.getEntityManagerFactory());
        assertNull("not_null", moduleConfiguration.getServiceEventClientConfigurations());
    }

    @Test
    public void constructorAllNullTest() {
        ServiceEventModuleConfiguration moduleConfiguration = new ServiceEventModuleConfiguration(null, null, null);
        assertNull("not_null", moduleConfiguration.getInternalAddress());
        assertNull("not_null", moduleConfiguration.getEntityManagerFactory());
        assertNull("not_null", moduleConfiguration.getServiceEventClientConfigurations());
    }

    @Test
    public void constructorAddressCharCheckTest() {
        String[] permittedValues = {"", "!@#$%^^&**(-()_)+/|", "regularNaming", "regular Naming", "49", "regularNaming49", "NAMING", "246465494135646120009090049684646496468456468496846464968496844"};
        for (String value : permittedValues) {
            ServiceEventModuleConfiguration moduleConfiguration = new ServiceEventModuleConfiguration(value, entityManagerFactory, serviceEventClientConfiguration);
            assertEquals("not_equals", value, moduleConfiguration.getInternalAddress());
            assertNull("not_null", moduleConfiguration.getEntityManagerFactory());
            assertArrayEquals(serviceEventClientConfiguration, moduleConfiguration.getServiceEventClientConfigurations());
        }
    }
}