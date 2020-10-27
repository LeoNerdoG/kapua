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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Category(JUnitTests.class)
public class ParentEndPointTest extends Assert {

    ParentEndPoint parentEndPoint;
    Exchange exchange;
    Object[] values;
    String[] previousList;
    Map<String, Object> properties;
    Message message;
    List<EndPoint> endPoints;
    StringBuffer stringBuffer;
    String prefix;

    @Before
    public void initialize() {
        parentEndPoint = new ParentEndPoint();
        exchange = Mockito.mock(Exchange.class);
        values = new Object[]{null, true, false, 'c', "String", 10, 10L, 10.11f, 10.11d, 1, 0};
        // COMMENT: If you are have a string array, I suggest you pu in all possible alphanumeric characters
        // combined with all other symbols.
        previousList = new String[]{"Previous", "Previous1234567890", "Previous!@#$%^&*()_+?><|/."};
        properties = new HashMap<>();
        message = Mockito.mock(Message.class);
        endPoints = new ArrayList<>();
        stringBuffer = new StringBuffer();
        prefix = "Prefix";
    }

    @Test
    public void matchesTest() {
        for (Object value : values) {
            for (String previous : previousList) {
                assertFalse("False expected.", parentEndPoint.matches(exchange, value, previous, properties));
            }
        }
    }

    @Test
    public void matchesNullExchangeTest() {
        for (Object value : values) {
            for (String previous : previousList) {
                assertFalse("False expected.", parentEndPoint.matches(null, value, previous, properties));
            }
        }
    }

    @Test
    public void matchesNullPropertiesTest() {
        for (Object value : values) {
            for (String previous : previousList) {
                assertFalse("False expected.", parentEndPoint.matches(exchange, value, previous, null));
            }
        }
    }

    @Test(expected = NullPointerException.class)
    public void matchesNullPreviousTest() {
        for (Object value : values) {
            Mockito.when(exchange.getIn()).thenReturn(message);
            Mockito.when(message.getHeader(MessageConstants.PROPERTY_ORIGINAL_TOPIC, String.class)).thenReturn("Topic");
            assertFalse("False expected.", parentEndPoint.matches(exchange, value, null, properties));
        }
    }

    @Test
    public void matchesNullPreviousFalseTest() {
        parentEndPoint.setRegex("Different topic");
        for (Object value : values) {
            Mockito.when(exchange.getIn()).thenReturn(message);
            Mockito.when(message.getHeader(MessageConstants.PROPERTY_ORIGINAL_TOPIC, String.class)).thenReturn("Topic");
            assertFalse("False expected.", parentEndPoint.matches(exchange, value, null, properties));
        }
    }

    @Test
    public void matchesNullPreviousTrueTest() {
        parentEndPoint.setRegex("Topic");
        for (Object value : values) {
            Mockito.when(exchange.getIn()).thenReturn(message);
            Mockito.when(message.getHeader(MessageConstants.PROPERTY_ORIGINAL_TOPIC, String.class)).thenReturn("Topic");
            assertTrue("True expected.", parentEndPoint.matches(exchange, value, null, properties));
        }
    }

    @Test
    // COMMENT: Why is this method called ...ChainEndPointTest() ?
    public void setAndGetChainEndPointTest() {
        EndPoint endPoint = new EndChainEndPoint();
        endPoints.add(endPoint);
        parentEndPoint.setEndPoints(endPoints);
        for (Object value : values) {
            for (String previous : previousList) {
                //COMMENT: method getEndPoint(Exchange exchange, Object value, String previous, Map<String, Object> properties) {
                //      in EndChainEndPoint class always returns null
                assertNull("Null expected.", parentEndPoint.getEndPoint(exchange, value, previous, properties));
            }
        }
    }

    @Test
    public void setAndGetEndPointTest() {
        EndPoint endPoint = Mockito.mock(EndPoint.class);
        endPoints.add(endPoint);
        parentEndPoint.setEndPoints(endPoints);
        for (Object value : values) {
            for (String previous : previousList) {
                Mockito.when((endPoint.matches(exchange, value, previous, properties))).thenReturn(false);
                assertNull("Null expected.", parentEndPoint.getEndPoint(exchange, value, previous, properties));
            }
        }
    }

    @Test
    public void setAndGetEndPointsTest() {
        List<EndPoint> endPoints = new ArrayList<>();
        EndPoint endPoint = new EndChainEndPoint();

        parentEndPoint.setEndPoints(endPoints);

        assertEquals("Expected and actual values should be the same.", endPoints, parentEndPoint.getEndPoints());
        assertTrue("True expected.", parentEndPoint.getEndPoints().isEmpty());

        endPoints.add(endPoint);
        parentEndPoint.setEndPoints(endPoints);
        assertEquals("Expected and actual values should be the same.", endPoints, parentEndPoint.getEndPoints());
        assertFalse("False expected.", parentEndPoint.getEndPoints().isEmpty());

        parentEndPoint.setEndPoints(null);
        assertNull("Null expected.", parentEndPoint.getEndPoints());
    }

    @Test
    public void setAndGetRegexTest() {
        // COMMENT: Can you add additional tests/strings, that include various escape characters, spaces and
        //  other symbols?
        assertNull("Null expected.", parentEndPoint.getRegex());
        parentEndPoint.setRegex("Regex");
        assertEquals("Expected and actual values should be the same.", "Regex", parentEndPoint.getRegex());
        parentEndPoint.setRegex(null);
        assertNull("Null expected.", parentEndPoint.getRegex());
    }


    @Test(expected = NullPointerException.class)
    public void toLogEndPointsNotSetTest() {
        parentEndPoint.toLog(stringBuffer, prefix);
    }

    @Test
    public void toLogTest() {
        parentEndPoint.setRegex("Regex");
        List<EndPoint> endPoints = new ArrayList<>();
        EndPoint endPoint = new EndChainEndPoint();
        endPoints.add(endPoint);
        parentEndPoint.setEndPoints(endPoints);
        try {
            parentEndPoint.toLog(stringBuffer, prefix);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test(expected = NullPointerException.class)
    public void toLogNullBufferTest() {
        parentEndPoint.setRegex("Regex");
        List<EndPoint> endPoints = new ArrayList<>();
        EndPoint endPoint = new EndChainEndPoint();
        endPoints.add(endPoint);
        parentEndPoint.setEndPoints(endPoints);

        parentEndPoint.toLog(null, prefix);
    }

    @Test
    public void toLogNullPrefixTest() {
        parentEndPoint.setRegex("Regex");
        List<EndPoint> endPoints = new ArrayList<>();
        EndPoint endPoint = new EndChainEndPoint();
        endPoints.add(endPoint);
        parentEndPoint.setEndPoints(endPoints);
        try {
            parentEndPoint.toLog(stringBuffer, null);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }
}