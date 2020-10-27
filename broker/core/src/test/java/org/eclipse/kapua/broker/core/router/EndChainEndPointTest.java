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

import org.apache.camel.Exchange;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

@Category(JUnitTests.class)
public class EndChainEndPointTest extends Assert {

    EndChainEndPoint endChainEndPoint;
    Exchange[] exchanges;
    Object[] values;
    String[] previous;
    Map<String, Object> properties;
    StringBuffer buffer;
    String prefix;

    @Before
    public void initialize() {
        endChainEndPoint = new EndChainEndPoint();
        exchanges = new Exchange[]{null, Mockito.mock(Exchange.class)};
        values = new Object[]{null, new Object()};
        previous = new String[]{null, "Previous"};
        properties = new HashMap<>();
        buffer = new StringBuffer();
        prefix = "Prefix";
    }

    @Test
    public void matchesTest() {
        for (Exchange exchange : exchanges) {
            for (Object value : values) {
                for (String pr : previous) {
                    assertTrue("True expected.", endChainEndPoint.matches(exchange, value, pr, properties));
                }
            }
        }
    }

    @Test
    // COMMENT: Two "p" in test name, please fix it.
    public void matchesNullPPropertiesTest() {
        for (Exchange exchange : exchanges) {
            for (Object value : values) {
                for (String pr : previous) {
                    assertTrue("True expected.", endChainEndPoint.matches(exchange, value, pr, null));
                }
            }
        }
    }
    // COMMENT: Adding tests for when exchange, value and previous are nulL?

    @Test
    public void getEndPointTest() {
        for (Exchange exchange : exchanges) {
            for (Object value : values) {
                for (String pr : previous) {
                    assertNull("Null expected.", endChainEndPoint.getEndPoint(exchange, value, pr, properties));
                }
            }
        }
    }

    @Test
    public void getEndPointNullPropertiesTest() {
        for (Exchange exchange : exchanges) {
            for (Object value : values) {
                for (String pr : previous) {
                    assertNull("Null expected.", endChainEndPoint.getEndPoint(exchange, value, pr, null));
                }
            }
        }
    }
    // COMMENT: Adding tests for when exchange, value and previous are nulL?

    @Test
    public void toLogTest() {
        endChainEndPoint.toLog(buffer, prefix);
        assertEquals("Expected and actual values should be the same.", "End chain", buffer.toString());
    }

    @Test(expected = NullPointerException.class)
    public void toLogNullBufferTest() {
        endChainEndPoint.toLog(null, prefix);
    }

    @Test
    public void toLogNullPrefixTest() {
        endChainEndPoint.toLog(buffer, null);
        assertEquals("Expected and actual values should be the same.", "End chain", buffer.toString());
    }
}