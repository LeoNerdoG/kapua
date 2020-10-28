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
package org.eclipse.kapua.app.api.resources.v1.resources.model.data;

import org.eclipse.kapua.app.api.resources.v1.resources.model.message.JsonKapuaPayload;
import org.eclipse.kapua.message.KapuaPayload;
import org.eclipse.kapua.message.KapuaPosition;
import org.eclipse.kapua.message.device.data.KapuaDataChannel;
import org.eclipse.kapua.message.device.data.KapuaDataMessage;
import org.eclipse.kapua.message.device.data.KapuaDataPayload;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.service.storable.model.id.StorableId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.Date;
import java.util.UUID;

@Category(JUnitTests.class)
public class JsonKapuaDataMessageTest extends Assert {

    KapuaDataMessage kapuaDataMessage;
    UUID id;
    StorableId storableId;
    Date timestamp;
    KapuaId scopeId;
    KapuaId deviceId;
    String clientId;
    Date receivedOn;
    Date sentOn;
    Date capturedOn;
    KapuaPosition kapuaPosition;
    KapuaDataChannel kapuaDataChannel;
    KapuaDataPayload kapuaPayload;
    JsonKapuaDataMessage jsonKapuaDataMessage1;
    JsonKapuaDataMessage jsonKapuaDataMessage2;

    @Before
    public void initialize() {
        kapuaDataMessage = Mockito.mock(KapuaDataMessage.class);
        id = new UUID(10L, 100L);
        storableId = Mockito.mock(StorableId.class);
        timestamp = new Date();
        scopeId = KapuaId.ONE;
        deviceId = KapuaId.ONE;
        clientId = "clientID";
        receivedOn = new Date();
        sentOn = new Date();
        capturedOn = new Date();
        kapuaPosition = Mockito.mock(KapuaPosition.class);
        kapuaDataChannel = Mockito.mock(KapuaDataChannel.class);
        kapuaPayload = Mockito.mock(KapuaDataPayload.class);

        Mockito.when(kapuaDataMessage.getId()).thenReturn(id);
        Mockito.when(kapuaDataMessage.getScopeId()).thenReturn(scopeId);
        Mockito.when(kapuaDataMessage.getDeviceId()).thenReturn(deviceId);
        Mockito.when(kapuaDataMessage.getClientId()).thenReturn(clientId);
        Mockito.when(kapuaDataMessage.getReceivedOn()).thenReturn(receivedOn);
        Mockito.when(kapuaDataMessage.getSentOn()).thenReturn(sentOn);
        Mockito.when(kapuaDataMessage.getCapturedOn()).thenReturn(capturedOn);
        Mockito.when(kapuaDataMessage.getPosition()).thenReturn(kapuaPosition);
        Mockito.when(kapuaDataMessage.getChannel()).thenReturn(kapuaDataChannel);
        Mockito.when(kapuaDataMessage.getPayload()).thenReturn(kapuaPayload);

        jsonKapuaDataMessage1 = new JsonKapuaDataMessage(kapuaDataMessage);
        jsonKapuaDataMessage2 = new JsonKapuaDataMessage();
    }

    @Test
    public void jsonKapuaDataMessageWithoutParameterTest() {
        JsonKapuaDataMessage jsonKapuaDataMessage = new JsonKapuaDataMessage();

        assertNull("Null expected.", jsonKapuaDataMessage.getId());
        assertNull("Null expected.", jsonKapuaDataMessage.getScopeId());
        assertNull("Null expected.", jsonKapuaDataMessage.getDeviceId());
        assertNull("Null expected.", jsonKapuaDataMessage.getClientId());
        assertNull("Null expected.", jsonKapuaDataMessage.getReceivedOn());
        assertNull("Null expected.", jsonKapuaDataMessage.getSentOn());
        assertNull("Null expected.", jsonKapuaDataMessage.getCapturedOn());
        assertNull("Null expected.", jsonKapuaDataMessage.getPosition());
        assertNull("Null expected.", jsonKapuaDataMessage.getChannel());
        assertNull("Null expected.", jsonKapuaDataMessage.getPayload());
    }

    @Test
    public void jsonKapuaDataMessageWithParameterTest() {
        JsonKapuaDataMessage jsonKapuaDataMessage = new JsonKapuaDataMessage(kapuaDataMessage);

        assertEquals("Expected and actual values should be the same.", id, jsonKapuaDataMessage.getId());
        assertEquals("Expected and actual values should be the same.", scopeId, jsonKapuaDataMessage.getScopeId());
        assertEquals("Expected and actual values should be the same.", deviceId, jsonKapuaDataMessage.getDeviceId());
        assertEquals("Expected and actual values should be the same.", clientId, jsonKapuaDataMessage.getClientId());
        assertEquals("Expected and actual values should be the same.", receivedOn, jsonKapuaDataMessage.getReceivedOn());
        assertEquals("Expected and actual values should be the same.", sentOn, jsonKapuaDataMessage.getSentOn());
        assertEquals("Expected and actual values should be the same.", capturedOn, jsonKapuaDataMessage.getCapturedOn());
        assertEquals("Expected and actual values should be the same.", kapuaPosition, jsonKapuaDataMessage.getPosition());
        assertEquals("Expected and actual values should be the same.", kapuaDataChannel, jsonKapuaDataMessage.getChannel());
        assertNotNull("NotNull expected.", jsonKapuaDataMessage.getPayload());
    }

    @Test(expected = NullPointerException.class)
    public void jsonKapuaDataMessageWithNullParameterTest() {
        new JsonKapuaDataMessage(null);
    }

    @Test
    public void setAndGetIdTest() {
        UUID newId = new UUID(1L, 99L);

        jsonKapuaDataMessage1.setId(newId);
        jsonKapuaDataMessage2.setId(newId);

        assertEquals("Expected and actual values should be the same.", newId, jsonKapuaDataMessage1.getId());
        assertEquals("Expected and actual values should be the same.", newId, jsonKapuaDataMessage2.getId());

        jsonKapuaDataMessage1.setId(null);
        jsonKapuaDataMessage2.setId(null);

        assertNull("Null expected.", jsonKapuaDataMessage1.getId());
        assertNull("Null expected.", jsonKapuaDataMessage2.getId());
    }

    @Test
    public void setAndGetScopeIdTest() {
        KapuaId newScopeId = KapuaId.ANY;

        jsonKapuaDataMessage1.setScopeId(newScopeId);
        jsonKapuaDataMessage2.setScopeId(newScopeId);

        assertEquals("Expected and actual values should be the same.", newScopeId, jsonKapuaDataMessage1.getScopeId());
        assertEquals("Expected and actual values should be the same.", newScopeId, jsonKapuaDataMessage2.getScopeId());

        jsonKapuaDataMessage1.setScopeId(null);
        jsonKapuaDataMessage2.setScopeId(null);

        assertNull("Null expected.", jsonKapuaDataMessage1.getScopeId());
        assertNull("Null expected.", jsonKapuaDataMessage2.getScopeId());
    }

    @Test
    public void setAndGetDeviceIdTest() {
        KapuaId newDeviceId = KapuaId.ANY;

        jsonKapuaDataMessage1.setDeviceId(newDeviceId);
        jsonKapuaDataMessage2.setDeviceId(newDeviceId);

        assertEquals("Expected and actual values should be the same.", newDeviceId, jsonKapuaDataMessage1.getDeviceId());
        assertEquals("Expected and actual values should be the same.", newDeviceId, jsonKapuaDataMessage2.getDeviceId());

        jsonKapuaDataMessage1.setDeviceId(null);
        jsonKapuaDataMessage2.setDeviceId(null);

        assertNull("Null expected.", jsonKapuaDataMessage1.getDeviceId());
        assertNull("Null expected.", jsonKapuaDataMessage2.getDeviceId());
    }

    @Test
    public void setAndGetClientIdTest() {
        String newClientID = "ID";

        jsonKapuaDataMessage1.setClientId(newClientID);
        jsonKapuaDataMessage2.setClientId(newClientID);

        assertEquals("Expected and actual values should be the same.", newClientID, jsonKapuaDataMessage1.getClientId());
        assertEquals("Expected and actual values should be the same.", newClientID, jsonKapuaDataMessage2.getClientId());

        jsonKapuaDataMessage1.setClientId(null);
        jsonKapuaDataMessage2.setClientId(null);

        assertNull("Null expected.", jsonKapuaDataMessage1.getClientId());
        assertNull("Null expected.", jsonKapuaDataMessage2.getClientId());
    }

    @Test
    public void setAndGetReceivedOnTest() {
        Date newReceivedOn = new Date();

        jsonKapuaDataMessage1.setReceivedOn(newReceivedOn);
        jsonKapuaDataMessage2.setReceivedOn(newReceivedOn);

        assertEquals("Expected and actual values should be the same.", newReceivedOn, jsonKapuaDataMessage1.getReceivedOn());
        assertEquals("Expected and actual values should be the same.", newReceivedOn, jsonKapuaDataMessage2.getReceivedOn());

        jsonKapuaDataMessage1.setReceivedOn(null);
        jsonKapuaDataMessage2.setReceivedOn(null);

        assertNull("Null expected.", jsonKapuaDataMessage1.getReceivedOn());
        assertNull("Null expected.", jsonKapuaDataMessage2.getReceivedOn());
    }

    @Test
    public void setAndGetSentOnTest() {
        Date newSentOn = new Date();

        jsonKapuaDataMessage1.setSentOn(newSentOn);
        jsonKapuaDataMessage2.setSentOn(newSentOn);

        assertEquals("Expected and actual values should be the same.", newSentOn, jsonKapuaDataMessage1.getSentOn());
        assertEquals("Expected and actual values should be the same.", newSentOn, jsonKapuaDataMessage2.getSentOn());

        jsonKapuaDataMessage1.setSentOn(null);
        jsonKapuaDataMessage2.setSentOn(null);

        assertNull("Null expected.", jsonKapuaDataMessage1.getSentOn());
        assertNull("Null expected.", jsonKapuaDataMessage2.getSentOn());
    }

    @Test
    public void setAndGetCapturedOnTest() {
        Date newCapturedOn = new Date();

        jsonKapuaDataMessage1.setCapturedOn(newCapturedOn);
        jsonKapuaDataMessage2.setCapturedOn(newCapturedOn);

        assertEquals("Expected and actual values should be the same.", newCapturedOn, jsonKapuaDataMessage1.getCapturedOn());
        assertEquals("Expected and actual values should be the same.", newCapturedOn, jsonKapuaDataMessage2.getCapturedOn());

        jsonKapuaDataMessage1.setCapturedOn(null);
        jsonKapuaDataMessage2.setCapturedOn(null);

        assertNull("Null expected.", jsonKapuaDataMessage1.getCapturedOn());
        assertNull("Null expected.", jsonKapuaDataMessage2.getCapturedOn());
    }

    @Test
    public void setAndGetPositionTest() {
        KapuaPosition newPosition = Mockito.mock(KapuaPosition.class);

        jsonKapuaDataMessage1.setPosition(newPosition);
        jsonKapuaDataMessage2.setPosition(newPosition);

        assertEquals("Expected and actual values should be the same.", newPosition, jsonKapuaDataMessage1.getPosition());
        assertEquals("Expected and actual values should be the same.", newPosition, jsonKapuaDataMessage2.getPosition());

        jsonKapuaDataMessage1.setPosition(null);
        jsonKapuaDataMessage2.setPosition(null);

        assertNull("Null expected.", jsonKapuaDataMessage1.getPosition());
        assertNull("Null expected.", jsonKapuaDataMessage2.getPosition());
    }

    @Test
    public void setAndGetChannelTest() {
        KapuaDataChannel newChannel = Mockito.mock(KapuaDataChannel.class);

        jsonKapuaDataMessage1.setChannel(newChannel);
        jsonKapuaDataMessage2.setChannel(newChannel);

        assertEquals("Expected and actual values should be the same.", newChannel, jsonKapuaDataMessage1.getChannel());
        assertEquals("Expected and actual values should be the same.", newChannel, jsonKapuaDataMessage2.getChannel());

        jsonKapuaDataMessage1.setChannel(null);
        jsonKapuaDataMessage2.setChannel(null);

        assertNull("Null expected.", jsonKapuaDataMessage1.getChannel());
        assertNull("Null expected.", jsonKapuaDataMessage2.getChannel());
    }

    @Test
    public void setAndGetJsonKapuaPayloadTest() {
        JsonKapuaPayload newJsonKapuaPayload = Mockito.mock(JsonKapuaPayload.class);

        jsonKapuaDataMessage1.setPayload(newJsonKapuaPayload);
        jsonKapuaDataMessage2.setPayload(newJsonKapuaPayload);

        assertEquals("Expected and actual values should be the same.", newJsonKapuaPayload, jsonKapuaDataMessage1.getPayload());
        assertEquals("Expected and actual values should be the same.", newJsonKapuaPayload, jsonKapuaDataMessage2.getPayload());

        jsonKapuaDataMessage1.setPayload((JsonKapuaPayload) null);
        jsonKapuaDataMessage2.setPayload((JsonKapuaPayload) null);

        assertNull("Null expected.", jsonKapuaDataMessage1.getPayload());
        assertNull("Null expected.", jsonKapuaDataMessage2.getPayload());
    }

    @Test
    public void setAndGetKapuaPayloadTest() {
        KapuaPayload newKapuaPayload = Mockito.mock(KapuaPayload.class);

        jsonKapuaDataMessage1.setPayload(newKapuaPayload);
        jsonKapuaDataMessage2.setPayload(newKapuaPayload);

        assertNotNull("NotNull expected.", jsonKapuaDataMessage1.getPayload());
        assertNotNull("NotNull expected.", jsonKapuaDataMessage2.getPayload());

        try {
            jsonKapuaDataMessage1.setPayload((KapuaPayload) null);
            fail("NullPointerException expected.");
        } catch (Exception e) {
            assertEquals("NullPointerException expected.", new NullPointerException().toString(), e.toString());
        }

        try {
            jsonKapuaDataMessage2.setPayload((KapuaPayload) null);
            fail("NullPointerException expected.");
        } catch (Exception e) {
            assertEquals("NullPointerException expected.", new NullPointerException().toString(), e.toString());
        }
    }
}