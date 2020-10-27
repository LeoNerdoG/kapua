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
import org.apache.camel.Message;
import org.eclipse.kapua.broker.core.message.MessageConstants;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

@Category(JUnitTests.class)
public class SimpleEndPointTest extends Assert {

    SimpleEndPoint simpleEndPoint;
    Exchange exchange;
    Object[] values;
    String[] previousList;
    Map<String, Object> properties;
    String[] topics;
    Message message;
    StringBuffer stringBuffer;
    String prefix;

    @Before
    public void initialize() {
        simpleEndPoint = new SimpleEndPoint();
        exchange = Mockito.mock(Exchange.class);
        values = new Object[]{null, true, false, 'c', "String", 10, 10L, 10.11f, 10.11d, 1, 0};
        // COMMENT: Please add additional strings that include aplhanumeric characters and symbols
        previousList = new String[]{"Previous", "Previous1234567890", "Previous!@#$%^&*()_+?><|/."};
        properties = new HashMap<>();
        // COMMENT: Same as above.
        topics = new String[]{"originalTopic", "Topic", "Topic1234567890", "Topic!@#$%^&*()_=-/."};
        message = Mockito.mock(Message.class);
        stringBuffer = new StringBuffer();
        prefix = "Prefix";
    }

    @Test
    public void matchesTest() {
        for (Object value : values) {
            for (String previous : previousList) {
                assertFalse("False expected.", simpleEndPoint.matches(exchange, value, previous, properties));
            }
        }
    }

    @Test
    public void matchesNullExchangeTest() {
        for (Object value : values) {
            for (String previous : previousList) {
                assertFalse("False expected.", simpleEndPoint.matches(null, value, previous, properties));
            }
        }
    }

    @Test
    public void matchesNullPropertiesTest() {
        for (Object value : values) {
            for (String previous : previousList) {
                assertFalse("False expected.", simpleEndPoint.matches(exchange, value, previous, null));
            }
        }
    }


    @Test(expected = NullPointerException.class)
    public void matchesNullPreviousTest() {
        for (Object value : values) {
            Mockito.when(exchange.getIn()).thenReturn(message);
            Mockito.when(message.getHeader(MessageConstants.PROPERTY_ORIGINAL_TOPIC, String.class)).thenReturn("Topic");
            assertFalse("False expected.", simpleEndPoint.matches(exchange, value, null, properties));
        }
    }

    @Test
    public void matchesNullPreviousFalseTest() {
        simpleEndPoint.setRegex("Different topic");
        for (Object value : values) {
            Mockito.when(exchange.getIn()).thenReturn(message);
            Mockito.when(message.getHeader(MessageConstants.PROPERTY_ORIGINAL_TOPIC, String.class)).thenReturn("Topic");
            assertFalse("False expected.", simpleEndPoint.matches(exchange, value, null, properties));
        }
    }
    // COMMENT: Adding tests for null "value" parameter?

    @Test
    public void matchesNullPreviousTrueTest() {
        simpleEndPoint.setRegex("Topic");
        for (Object value : values) {
            Mockito.when(exchange.getIn()).thenReturn(message);
            Mockito.when(message.getHeader(MessageConstants.PROPERTY_ORIGINAL_TOPIC, String.class)).thenReturn("Topic");
            assertTrue("True expected.", simpleEndPoint.matches(exchange, value, null, properties));
        }
    }

    @Test
    public void setAndGetEndPointWithParametersTest() {
        for (Object value : values) {
            for (String previous : previousList) {
                simpleEndPoint.setEndPoint("End Point");
                assertEquals("Expected and actual values should be the same.", "End Point", simpleEndPoint.getEndPoint(exchange, value, previous, properties));
                simpleEndPoint.setEndPoint(null);
                assertNull("Null expected.", simpleEndPoint.getEndPoint(exchange, value, previous, properties));
            }
        }
    }

    @Test
    public void setAndGetEndPointWithParametersNullTest() {
        simpleEndPoint.setEndPoint("End Point");
        assertEquals("Expected and actual values should be the same.", "End Point", simpleEndPoint.getEndPoint(null, null, null, null));
        simpleEndPoint.setEndPoint(null);
        assertNull("Null expected.", simpleEndPoint.getEndPoint(null, null, null, null));
    }

    @Test
    public void setAndGetEndPointWithParametersNullExchangeTest() {
        for (Object value : values) {
            for (String previous : previousList) {
                simpleEndPoint.setEndPoint("End Point");
                assertEquals("Expected and actual values should be the same.", "End Point", simpleEndPoint.getEndPoint(null, value, previous, properties));
                simpleEndPoint.setEndPoint(null);
                assertNull("Null expected.", simpleEndPoint.getEndPoint(null, value, previous, properties));
            }
        }
    }

    @Test
    public void setAndGetEndPointWithParametersNullPreviousTest() {
        for (Object value : values) {
            simpleEndPoint.setEndPoint("End Point");
            assertEquals("Expected and actual values should be the same.", "End Point", simpleEndPoint.getEndPoint(exchange, value, null, properties));
            simpleEndPoint.setEndPoint(null);
            assertNull("Null expected.", simpleEndPoint.getEndPoint(exchange, value, null, properties));
        }
    }

    @Test
    public void setAndGetEndPointWithParametersNullPropertiesTest() {
        for (Object value : values) {
            for (String previous : previousList) {
                simpleEndPoint.setEndPoint("End Point");
                assertEquals("Expected and actual values should be the same.", "End Point", simpleEndPoint.getEndPoint(exchange, value, previous, null));
                simpleEndPoint.setEndPoint(null);
                assertNull("Null expected.", simpleEndPoint.getEndPoint(exchange, value, previous, null));
            }
        }
    }

    @Test
    public void setAndGetEndPointTest() {
        // COMMENT: Please try with oter string also (alphanumerci characters, symbols...)
        simpleEndPoint.setEndPoint("End Point");
        assertEquals("Expected and actual values should be the same.", "End Point", simpleEndPoint.getEndPoint());
        simpleEndPoint.setEndPoint(null);
        assertNull("Null expected.", simpleEndPoint.getEndPoint());
    }

    // COMMENT: Please move this test above, before tests for set and get endpoint, becasue that is
    // the order in the tested class.
    @Test
    public void setAndGetRegexTest() {
        // COMMENT: Please add additional stings as regex-es, including escape characters...?
        assertNull("Null expected.", simpleEndPoint.getRegex());
        simpleEndPoint.setRegex("Regex");
        assertEquals("Expected and actual values should be the same.", "Regex", simpleEndPoint.getRegex());
        simpleEndPoint.setRegex(null);
        assertNull("Null expected.", simpleEndPoint.getRegex());
    }

    @Test
    public void toLogTest() {
        try {
            simpleEndPoint.toLog(stringBuffer, prefix);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test(expected = NullPointerException.class)
    public void toLogNullBufferTest() {
        simpleEndPoint.toLog(null, prefix);
    }

    @Test
    public void toLogNullPrefixTest() {
        try {
            simpleEndPoint.toLog(stringBuffer, null);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }
}