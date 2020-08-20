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
package org.eclipse.kapua.transport.mqtt.mqtt.exception;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.transport.message.mqtt.MqttTopic;
import org.eclipse.kapua.transport.mqtt.exception.MqttClientUnsubscribeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class MqttClientUnsubscribeExceptionTest extends Assert {

    MqttClientUnsubscribeException exception;
    Throwable cause;
    MqttTopic topic;

    private String clientId = "clientName";

    @Before
    public void createInstancesOfClasses() {
        cause = new Throwable();
        topic = new MqttTopic("mqttTopic");
        exception = new MqttClientUnsubscribeException(cause, clientId, topic);
    }

    @Test
    public void constructorValidTest() {
        MqttClientUnsubscribeException exception = new MqttClientUnsubscribeException(cause, clientId, topic);
        assertEquals("Expected and actual values should be the same!", "UNSUBSCRIBE_ERROR", exception.getCode().name());
        assertEquals("Expected and actual values should be the same!", cause, exception.getCause());
        assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
        assertEquals("Expected and actual values should be the same!", topic, exception.getTopic());
    }

    @Test
    public void constructorCauseNullTest() {
        MqttClientUnsubscribeException exception = new MqttClientUnsubscribeException(null, clientId, topic);
        assertEquals("Expected and actual values should be the same!", "UNSUBSCRIBE_ERROR", exception.getCode().name());
        assertNull("Null expected", exception.getCause());
        assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
        assertEquals("Expected and actual values should be the same!", topic, exception.getTopic());
    }

    @Test
    public void constructorClientIdNullTest() {
        MqttClientUnsubscribeException exception = new MqttClientUnsubscribeException(cause, null, topic);
        assertEquals("Expected and actual values should be the same!", "UNSUBSCRIBE_ERROR", exception.getCode().name());
        assertEquals("Expected and actual values should be the same!", cause, exception.getCause());
        assertNull("Null expected", exception.getClientId());
        assertEquals("Expected and actual values should be the same!", topic, exception.getTopic());
    }

    @Test (expected = NullPointerException.class)
    public void constructorTopicNullTest() {
        MqttClientUnsubscribeException exception = new MqttClientUnsubscribeException(cause, clientId, null);
        assertEquals("Expected and actual values should be the same!", "UNSUBSCRIBE_ERROR", exception.getCode().name());
        assertEquals("Expected and actual values should be the same!", cause, exception.getCause());
        assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
        assertNull("Null expected", exception.getTopic());
    }

    @Test (expected = NullPointerException.class)
    public void constructorAllNullTest() {
        MqttClientUnsubscribeException exception = new MqttClientUnsubscribeException(null, null, null);
        assertEquals("Expected and actual values should be the same!", "UNSUBSCRIBE_ERROR", exception.getCode().name());
        assertNull("Null expected", exception.getCause());
        assertNull("Null expected", exception.getClientId());
        assertNull("Null expected", exception.getTopic());
    }

    @Test (expected = MqttClientUnsubscribeException.class)
    public void throwingExceptionUsingConstructor() throws MqttClientUnsubscribeException {
        throw exception;
    }
}
