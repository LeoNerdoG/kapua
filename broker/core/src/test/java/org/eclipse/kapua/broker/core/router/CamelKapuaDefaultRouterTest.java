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
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.KapuaRuntimeException;
import org.eclipse.kapua.broker.core.KapuaBrokerJAXBContextLoader;

import org.eclipse.kapua.broker.core.listener.CamelConstants;
import org.eclipse.kapua.broker.core.message.MessageConstants;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

@Category(JUnitTests.class)
public class CamelKapuaDefaultRouterTest extends Assert {

    CamelKapuaDefaultRouter defaultRouter;
    private KapuaBrokerJAXBContextLoader kapuaBrokerJAXBContextLoader;
    Exchange exchange;
    Object value;
    String previous;
    Map<String, Object> properties;
    Message message;
    String expectedValue;

    @Before
    public void initialize() throws KapuaException {
        kapuaBrokerJAXBContextLoader = new KapuaBrokerJAXBContextLoader();
        kapuaBrokerJAXBContextLoader.init();
        defaultRouter = new CamelKapuaDefaultRouter();
        exchange = Mockito.mock(Exchange.class);
        value = new Object();
        previous = "Previous";
        properties = new HashMap<>();
        message = Mockito.mock(Message.class);
        expectedValue = "bean:kapuaDataConverter?method=convertToData,bean:dataStorageMessageProcessor?method=processMessage";
    }

    @After
    public void resetJAXBContext() {
        kapuaBrokerJAXBContextLoader.reset();
    }

    @Test(expected = KapuaRuntimeException.class)
    public void camelKapuaDefaultRouterExceptionTest() {
        kapuaBrokerJAXBContextLoader.reset();
        CamelKapuaDefaultRouter defaultRouter = new CamelKapuaDefaultRouter();
    }

    @Test
    public void defaultRouteTest() {
        Mockito.when(exchange.getIn()).thenReturn(message);
        assertNull("Null expected.", defaultRouter.defaultRoute(exchange, value, previous, properties));
    }

    @Test
    public void defaultRouteNullPreviousTest() {
        Mockito.when(exchange.getIn()).thenReturn(message);
        Mockito.when(message.getHeader(MessageConstants.PROPERTY_ORIGINAL_TOPIC, String.class)).thenReturn("originalTopic");
        Mockito.when(message.getHeader(CamelConstants.JMS_CORRELATION_ID)).thenReturn("JMSCorrelationID");

        assertEquals("Expected and actual values should be the same.", expectedValue, defaultRouter.defaultRoute(exchange, value, null, properties));
    }

    @Test(expected = NullPointerException.class)
    public void defaultRouteNullTest() {
        defaultRouter.defaultRoute(null, null, null, null);
    }

    @Test(expected = NullPointerException.class)
    public void defaultRouteNullExchangeTest() {
        assertNull("Null expected.", defaultRouter.defaultRoute(null, value, previous, properties));
    }

    @Test
    public void defaultRouteNullValueTest() {
        Mockito.when(exchange.getIn()).thenReturn(message);
        Mockito.when(message.getHeader(MessageConstants.PROPERTY_ORIGINAL_TOPIC, String.class)).thenReturn("originalTopic");
        Mockito.when(message.getHeader(CamelConstants.JMS_CORRELATION_ID)).thenReturn("JMSCorrelationID");

        assertNull("Null expected.", defaultRouter.defaultRoute(exchange, null, previous, properties));
    }

    @Test
    public void defaultRouteNullPropertiesTest() {
        Mockito.when(exchange.getIn()).thenReturn(message);
        Mockito.when(message.getHeader(MessageConstants.PROPERTY_ORIGINAL_TOPIC, String.class)).thenReturn("originalTopic");
        Mockito.when(message.getHeader(CamelConstants.JMS_CORRELATION_ID)).thenReturn("JMSCorrelationID");

        assertNull("Null expected.", defaultRouter.defaultRoute(exchange, value, previous, null));
    }
}