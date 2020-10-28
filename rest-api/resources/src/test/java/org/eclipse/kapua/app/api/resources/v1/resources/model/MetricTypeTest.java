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
package org.eclipse.kapua.app.api.resources.v1.resources.model;

import org.eclipse.kapua.KapuaIllegalArgumentException;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Date;

@Category(JUnitTests.class)
public class MetricTypeTest extends Assert {

    @Test
    public void metricTypeTest() throws KapuaIllegalArgumentException {
        String[] types = {"string", "integer", "int", "long", "float", "double", "boolean", "date", "binary"};
        Class[] expectedClasses = {String.class, Integer.class, Integer.class, Long.class, Float.class, Double.class, Boolean.class, Date.class, byte[].class};

        for (int i = 0; i < types.length; i++) {
            MetricType metricType = new MetricType(types[i]);
            assertEquals("Expected and actual values should be the same.", expectedClasses[i], metricType.getType());
        }
    }

    @Test
    public void metricEmptyTypeTest() throws KapuaIllegalArgumentException {
        MetricType metricType = new MetricType("");
        assertNull("Null expected.", metricType.getType());
    }

    @Test
    public void metricNullTypeTest() throws KapuaIllegalArgumentException {
        MetricType metricType = new MetricType(null);
        assertNull("Null expected.", metricType.getType());
    }

    @Test(expected = KapuaIllegalArgumentException.class)
    public void metricInvalidTypeTest() throws KapuaIllegalArgumentException {
        new MetricType("invalid type");
    }
}