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
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class ServiceEntryTest extends Assert {

    @Test
    public void constructorRegularTest() throws Exception {
        ServiceEntry serviceEntry = new ServiceEntry("serviceName", "serviceAddress");
        assertEquals("not_equals", "serviceName", serviceEntry.getServiceName());
        assertEquals("not_equals", "serviceAddress", serviceEntry.getAddress());
    }

    @Test
    public void constructorNullTest() throws Exception {
        ServiceEntry serviceEntry = new ServiceEntry(null, null);
        assertNull("not_null", serviceEntry.getServiceName());
        assertNull("not_null", serviceEntry.getAddress());
    }

    @Test
    public void constructorNameNullTest() throws Exception {
        ServiceEntry serviceEntry = new ServiceEntry(null, "serviceAddress");
        assertNull("not_null", serviceEntry.getServiceName());
        assertEquals("not_equals", "serviceAddress", serviceEntry.getAddress());
    }

    @Test
    public void constructorAddressNullTest() throws Exception {
        ServiceEntry serviceEntry = new ServiceEntry("serviceName", null);
        assertEquals("not_equals", "serviceName", serviceEntry.getServiceName());
        assertNull("not_null", serviceEntry.getAddress());
    }
}
