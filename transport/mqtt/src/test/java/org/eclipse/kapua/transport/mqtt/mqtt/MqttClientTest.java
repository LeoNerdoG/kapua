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
package org.eclipse.kapua.transport.mqtt.mqtt;


import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.transport.message.mqtt.MqttMessage;
import org.eclipse.kapua.transport.message.mqtt.MqttPayload;
import org.eclipse.kapua.transport.message.mqtt.MqttTopic;
import org.eclipse.kapua.transport.mqtt.MqttClient;
import org.eclipse.kapua.transport.mqtt.MqttClientConnectionOptions;
import org.eclipse.kapua.transport.mqtt.MqttResponseCallback;
import org.eclipse.kapua.transport.mqtt.exception.MqttClientConnectException;
import org.eclipse.kapua.transport.mqtt.exception.MqttClientAlreadyConnectedException;
import org.eclipse.kapua.transport.mqtt.exception.MqttClientCallbackSetException;
import org.eclipse.kapua.transport.mqtt.exception.MqttClientDisconnectException;
import org.eclipse.kapua.transport.mqtt.exception.MqttClientTerminateException;
import org.eclipse.kapua.transport.mqtt.exception.MqttClientPublishException;
import org.eclipse.kapua.transport.mqtt.exception.MqttClientSubscribeException;
import org.eclipse.kapua.transport.mqtt.exception.MqttClientCleanException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Category(JUnitTests.class)
public class MqttClientTest extends Assert {

    private char[] password = ("kapua-password").toCharArray();
    private String username = "kapua-sys";
    List<MqttTopic> expectedValue = new ArrayList<>();
    List<MqttMessage> responses = new ArrayList<>();

    MqttClientConnectionOptions connectionOptions;
    MqttClient mqttClient;
    MqttTopic mqttTopic;

    @Before
    public void createInstancesOfClasses() {
        connectionOptions = new MqttClientConnectionOptions();
        mqttClient = new MqttClient();
        mqttTopic = new MqttTopic("mqttTopic");
    }

    private void connectClientWithValidOptions() throws Exception {
        connectionOptions.setClientId("1");
        connectionOptions.setEndpointURI(new URI("tcp://0.0.0.0"));
        connectionOptions.setUsername(username);
        connectionOptions.setPassword(password);
        mqttClient.connectClient(connectionOptions);
    }

    // tale
    @Test
    public void connectClientValidTest() throws Exception {
        connectionOptions.setClientId("1");
        connectionOptions.setEndpointURI(new URI("tcp://0.0.0.0"));
        connectionOptions.setUsername(username);
        connectionOptions.setPassword(password);
        mqttClient.connectClient(connectionOptions);
        assertTrue("The connection should be successful!", mqttClient.isConnected());
    }

    @Test(expected = IllegalArgumentException.class)
    public void connectClientWithNullIdTest() throws Exception {
        connectionOptions.setClientId(null);
        connectionOptions.setEndpointURI(new URI("tcp://0.0.0.0"));
        connectionOptions.setUsername(username);
        connectionOptions.setPassword(password);
        mqttClient.connectClient(connectionOptions);
    }

    @Test(expected = MqttClientConnectException.class)
    public void connectClientWithNullEndpointUriTest() throws Exception {
        connectionOptions.setClientId("1");
        connectionOptions.setEndpointURI(null);
        connectionOptions.setUsername(username);
        connectionOptions.setPassword(password);
        mqttClient.connectClient(connectionOptions);
    }

    @Test(expected = MqttClientConnectException.class)
    public void connectClientWithNullUsernameTest() throws Exception {
        connectionOptions.setClientId("1");
        connectionOptions.setEndpointURI(new URI("tcp://0.0.0.0"));
        connectionOptions.setUsername(null);
        connectionOptions.setPassword(password);
        mqttClient.connectClient(connectionOptions);
    }

    @Test(expected = NullPointerException.class)
    public void connectClientWithNullPasswordTest() throws Exception {
        connectionOptions.setClientId("1");
        connectionOptions.setEndpointURI(new URI("tcp://0.0.0.0"));
        connectionOptions.setUsername(username);
        connectionOptions.setPassword(null);
        mqttClient.connectClient(connectionOptions);
    }

    @Test(expected = MqttClientConnectException.class)
    public void connectClientWithAllNullFieldsTest() throws Exception {
        connectionOptions.setClientId(null);
        connectionOptions.setEndpointURI(null);
        connectionOptions.setUsername(null);
        connectionOptions.setPassword(null);
        mqttClient.connectClient(connectionOptions);
    }

    // tale
    @Test(expected = MqttClientAlreadyConnectedException.class)
    public void connectSeveralClientAtSameTimeTest() throws Exception {
        connectClientWithValidOptions();
        connectClientWithValidOptions();
    }

    @Test(expected = IllegalArgumentException.class)
    public void connectClientWithWrongUriFormatTest() throws Exception {
        connectionOptions.setClientId("1");
        connectionOptions.setEndpointURI(new URI("localhost"));
        connectionOptions.setUsername(username);
        connectionOptions.setPassword(password);
        mqttClient.connectClient(connectionOptions);
    }

    // tale
    @Test
    public void disconnectClientValidTest() throws Exception {
        connectClientWithValidOptions();
        assertTrue("The connection should be successful!", mqttClient.isConnected());
        mqttClient.disconnectClient();
        assertFalse("The client should be successfully disconnected!", mqttClient.isConnected());
    }

    @Test(expected = MqttClientDisconnectException.class)
    public void disconnectAnUnconnectedClientTest() throws Exception {
        mqttClient.disconnectClient();
    }

    // tale
    @Test(expected = MqttClientDisconnectException.class)
    public void disconnectClientTwiceTest() throws Exception {
        connectClientWithValidOptions();
        assertTrue("The connection should be successful!", mqttClient.isConnected());
        mqttClient.disconnectClient();
        mqttClient.disconnectClient();
    }

    // tale
    @Test
    public void terminateClientValidTest() throws Exception {
        connectClientWithValidOptions();
        mqttClient.terminateClient();
        assertFalse("The client should be successfully disconnected/terminated!", mqttClient.isConnected());
    }

    @Test(expected = MqttClientTerminateException.class)
    public void terminateAnUnconnectedClientTest() throws Exception {
        mqttClient.terminateClient();
    }

    // tale
    @Test(expected = MqttClientTerminateException.class)
    public void terminateClientTwiceTest() throws Exception {
        connectClientWithValidOptions();
        assertTrue("The connection should be successful!", mqttClient.isConnected());
        mqttClient.terminateClient();
        mqttClient.terminateClient();
    }

    // tale
    @Test
    public void publishMqttMessageValidTest() throws Exception {
        connectClientWithValidOptions();
        String payload = "response.code";
        MqttPayload mqttPayload = new MqttPayload(payload.getBytes());
        Date date = new Date();
        MqttMessage mqttMessage = new MqttMessage(mqttTopic, date, mqttPayload);
        mqttClient.publish(mqttMessage);

        assertTrue("The connection should be successful!", mqttClient.isConnected());
        assertEquals("Expected and actual values should be the same!", mqttTopic, mqttMessage.getRequestTopic());
        assertEquals("Expected and actual values should be the same!", mqttPayload, mqttMessage.getPayload());
        assertEquals("Expected and actual values should be the same!", date, mqttMessage.getTimestamp());
    }

    @Test(expected = MqttClientPublishException.class)
    public void publishMqttMessageWithUnconnectedClientTest() throws Exception {
        String payload = "response.code";
        MqttPayload mqttPayload = new MqttPayload(payload.getBytes());
        Date date = new Date();
        MqttMessage mqttMessage = new MqttMessage(mqttTopic, date, mqttPayload);
        mqttClient.publish(mqttMessage);
        assertFalse("The client should should not be connected!", mqttClient.isConnected());
    }

    // tale
    @Test(expected = NullPointerException.class)
    public void publishMqttMessageWithAllNullValue() throws Exception {
        connectClientWithValidOptions();
        MqttMessage mqttMessage = new MqttMessage(null, (Date) null, null);
        mqttClient.publish(mqttMessage);
    }

    // tale
    @Test(expected = NullPointerException.class)
    public void publishMqttMessageWithRequestTopicNullValue() throws Exception {
        connectClientWithValidOptions();
        String payload = "response.code";
        MqttPayload mqttPayload = new MqttPayload(payload.getBytes());
        Date date = new Date();
        MqttMessage mqttMessage = new MqttMessage(null, date, mqttPayload);
        mqttClient.publish(mqttMessage);
    }

    // tale
    @Test
    public void publishMqttMessageWithDateNullValue() throws Exception {
        connectClientWithValidOptions();
        String payload = "response.code";
        MqttPayload mqttPayload = new MqttPayload(payload.getBytes());
        MqttMessage mqttMessage = new MqttMessage(mqttTopic, (Date) null, mqttPayload);
        mqttClient.publish(mqttMessage);
    }

    // tale
    @Test(expected = NullPointerException.class)
    public void publishMqttMessageWithPayloadBodyNullValue() throws Exception {
        connectClientWithValidOptions();
        Date date = new Date();
        MqttPayload mqttPayload = new MqttPayload(null);
        MqttMessage mqttMessage = new MqttMessage(mqttTopic, date, mqttPayload);
        mqttClient.publish(mqttMessage);
    }

    // tale
    @Test
    public void subscribeClientToMessageValidTest() throws Exception {
        connectClientWithValidOptions();
        mqttClient.subscribe(mqttTopic);

        expectedValue.add(mqttTopic);
        Field field = MqttClient.class.getDeclaredField("subscribedTopics");
        field.setAccessible(true);
        Object fieldValue = field.get(mqttClient);
        assertEquals("Expected and actual values should be the same!", expectedValue, fieldValue);
    }

    @Test(expected = MqttClientSubscribeException.class)
    public void subscribeClientToMessageWithUnconnectedClientTest() throws Exception {
        mqttClient.subscribe(mqttTopic);
        assertFalse("The client should should not be connected!", mqttClient.isConnected());
    }

    // tale
    @Test(expected = NullPointerException.class)
    public void subscribeClientWithNullTopicTest() throws Exception {
        connectClientWithValidOptions();
        mqttClient.subscribe(null);
    }

    // tale
    @Test
    public void unsubscribeAllValidTest() throws Exception {
        connectClientWithValidOptions();
        mqttClient.subscribe(mqttTopic);
        mqttClient.unsubscribeAll();

        Field field = MqttClient.class.getDeclaredField("subscribedTopics");
        field.setAccessible(true);
        Object fieldValue = field.get(mqttClient);
        assertEquals("Expected and actual values should be the same!", expectedValue, fieldValue);
    }

    // tale
    @Test
    public void unsubscribeAllTwiceTest() throws Exception {
        connectClientWithValidOptions();
        mqttClient.subscribe(mqttTopic);
        mqttClient.unsubscribeAll();
        try {
            mqttClient.unsubscribeAll();
        } catch (Exception ex) {
            // expected
        }


        Field field = MqttClient.class.getDeclaredField("subscribedTopics");
        field.setAccessible(true);
        Object fieldValue = field.get(mqttClient);
        assertEquals("Expected and actual values should be the same!", expectedValue, fieldValue);
    }

    @Test(expected = MqttClientSubscribeException.class)
    public void unsubscribeWithUnconnectedClientTest() throws Exception {
        mqttClient.subscribe(mqttTopic);
        mqttClient.unsubscribeAll();
    }

    // tale
    @Test
    public void unsubscribeWithoutSubscribedClientTest() throws Exception {
        connectClientWithValidOptions();
        mqttClient.unsubscribeAll();
        Field field = MqttClient.class.getDeclaredField("subscribedTopics");
        field.setAccessible(true);
        Object fieldValue = field.get(mqttClient);
        assertEquals("Expected and actual values should be the same!", expectedValue, fieldValue);
    }

    // tale
    @Test
    public void setCallbackValidTest() throws Exception {
        try {
            connectClientWithValidOptions();
            String payload = "payload.code";
            MqttPayload mqttPayload = new MqttPayload(payload.getBytes());
            MqttMessage mqttMessage = new MqttMessage(mqttTopic, mqttTopic, mqttPayload);
            responses.add(mqttMessage);
            MqttResponseCallback mqttResponseCallback = new MqttResponseCallback(mqttTopic, responses);
            mqttClient.setCallback(mqttResponseCallback);
        } catch (Exception ex) {
            fail("No exception expected!");
        }
    }

    // tale
    @Test
    public void setCallbackNullTest() throws Exception {
        try {
            connectClientWithValidOptions();
            mqttClient.setCallback(null);
        } catch (Exception ex) {
            fail("No exception expected!");
        }
    }

    @Test(expected = MqttClientCallbackSetException.class)
    public void setCallbackWithUnconnectedClientTest() throws Exception {
        String payload = "payload.code";
        MqttPayload mqttPayload = new MqttPayload(payload.getBytes());
        MqttMessage mqttMessage = new MqttMessage(mqttTopic, mqttTopic, mqttPayload);
        responses.add(mqttMessage);
        MqttResponseCallback mqttResponseCallback = new MqttResponseCallback(mqttTopic, responses);
        mqttClient.setCallback(mqttResponseCallback);
    }

    //  tale
    @Test
    public void cleanValidTest() throws Exception {
        connectClientWithValidOptions();
        mqttClient.subscribe(mqttTopic);
        mqttClient.clean();

        Field field = MqttClient.class.getDeclaredField("subscribedTopics");
        field.setAccessible(true);
        Object fieldValue = field.get(mqttClient);
        assertEquals("Expected and actual values should be the same!", expectedValue, fieldValue);
    }

    @Test (expected = MqttClientCleanException.class)
    public void cleanWithUnconnectedClient() throws Exception {
        mqttClient.clean();
    }

    // tale
    @Test
    public void getClientIdTest() throws Exception {
        connectClientWithValidOptions();
        assertEquals("Expected and actual values should be the same!", "1", mqttClient.getClientId());
    }
}