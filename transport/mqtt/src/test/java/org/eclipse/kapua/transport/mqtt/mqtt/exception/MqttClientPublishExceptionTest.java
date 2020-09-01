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
import org.eclipse.kapua.transport.message.mqtt.MqttMessage;
import org.eclipse.kapua.transport.message.mqtt.MqttPayload;
import org.eclipse.kapua.transport.message.mqtt.MqttTopic;
import org.eclipse.kapua.transport.mqtt.exception.MqttClientPublishException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class MqttClientPublishExceptionTest extends Assert {

    MqttClientPublishException exception;
    Throwable cause;
    MqttMessage mqttMessage;
    MqttTopic requestTopic, responseTopic;
    MqttPayload payload;

    private String clientId = "clientName";
    private String topic = "topic";

    @Before
    public void createInstancesOfClasses() {
        requestTopic = new MqttTopic("requestTopic");
        responseTopic = new MqttTopic("responseTopic");
        payload = new MqttPayload("payload.code".getBytes());
        mqttMessage = new MqttMessage(requestTopic, responseTopic, payload);
        exception = new MqttClientPublishException(cause, clientId, topic, mqttMessage);
    }

    @Test
    public void constructorValidTest() {
        MqttClientPublishException exception = new MqttClientPublishException(cause, clientId, topic, mqttMessage);
        assertEquals("Expected and actual values should be the same!", "PUBLISH_EXCEPTION", exception.getCode().name());
        assertEquals("Expected and actual values should be the same!", cause, exception.getCause());
        assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
        assertEquals("Expected and actual values should be the same!", topic, exception.getTopic());
        assertEquals("Expected and actual values should be the same!", requestTopic, exception.getMqttMessage().getRequestTopic());
        assertEquals("Expected and actual values should be the same!", responseTopic, exception.getMqttMessage().getResponseTopic());
        assertEquals("Expected and actual values should be the same!", payload, exception.getMqttMessage().getPayload());
    }

    @Test
    public void constructorCauseNullTest() {
        try {
            MqttClientPublishException exception = new MqttClientPublishException(null, clientId, topic, mqttMessage);
            assertEquals("Expected and actual values should be the same!", "PUBLISH_EXCEPTION", exception.getCode().name());
            assertNull("Null expected!", exception.getCause());
            assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
            assertEquals("Expected and actual values should be the same!", topic, exception.getTopic());
            assertEquals("Expected and actual values should be the same!", requestTopic, exception.getMqttMessage().getRequestTopic());
            assertEquals("Expected and actual values should be the same!", responseTopic, exception.getMqttMessage().getResponseTopic());
            assertEquals("Expected and actual values should be the same!", payload, exception.getMqttMessage().getPayload());
        } catch (Exception ex) {
            fail("No exception expected!");
        }
    }

    @Test
    public void constructorClientIdNullTest() {
        try {
            MqttClientPublishException exception = new MqttClientPublishException(cause, null, topic, mqttMessage);
            assertEquals("Expected and actual values should be the same!", "PUBLISH_EXCEPTION", exception.getCode().name());
            assertEquals("Expected and actual values should be the same!", cause, exception.getCause());
            assertNull("Null expected!", exception.getClientId());
            assertEquals("Expected and actual values should be the same!", topic, exception.getTopic());
            assertEquals("Expected and actual values should be the same!", requestTopic, exception.getMqttMessage().getRequestTopic());
            assertEquals("Expected and actual values should be the same!", responseTopic, exception.getMqttMessage().getResponseTopic());
            assertEquals("Expected and actual values should be the same!", payload, exception.getMqttMessage().getPayload());
        } catch (Exception ex) {
            fail("No exception expected!");
        }
    }

    @Test
    public void constructorTopicNullTest() {
        try {
            MqttClientPublishException exception = new MqttClientPublishException(cause, clientId, null, mqttMessage);
            assertEquals("Expected and actual values should be the same!", "PUBLISH_EXCEPTION", exception.getCode().name());
            assertEquals("Expected and actual values should be the same!", cause, exception.getCause());
            assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
            assertNull("Null expected!", exception.getTopic());
            assertEquals("Expected and actual values should be the same!", requestTopic, exception.getMqttMessage().getRequestTopic());
            assertEquals("Expected and actual values should be the same!", responseTopic, exception.getMqttMessage().getResponseTopic());
            assertEquals("Expected and actual values should be the same!", payload, exception.getMqttMessage().getPayload());
        } catch (Exception ex) {
            fail("No exception expected!");
        }
    }

    @Test (expected = NullPointerException.class)
    public void constructorMqttMessageNullTest() {
        MqttClientPublishException exception = new MqttClientPublishException(cause, clientId, topic, null);
        assertEquals("Expected and actual values should be the same!", cause, exception.getCause());
        assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
        assertEquals("Expected and actual values should be the same!", topic, exception.getTopic());
        assertNull("Null expected!", exception.getMqttMessage());
    }

    @Test (expected = NullPointerException.class)
    public void constructorAllNullTest() {
        MqttClientPublishException exception = new MqttClientPublishException(null, null, null, null);
        assertNull("Null expected!", exception.getCause());
        assertNull("Null expected!", exception.getClientId());
        assertNull("Null expected!", exception.getTopic());
        assertNull("Null expected!", exception.getMqttMessage());
    }

    @Test (expected = MqttClientPublishException.class)
    public void throwingExceptionUsingConstructor() throws MqttClientPublishException {
        throw exception;
    }
}
