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
package org.eclipse.kapua.app.api.resources.v1.resources.model.device.management;

import org.eclipse.kapua.app.api.resources.v1.resources.model.message.JsonKapuaPayload;
import org.eclipse.kapua.message.KapuaPayload;
import org.eclipse.kapua.message.KapuaPosition;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.service.device.management.request.message.response.GenericResponseChannel;
import org.eclipse.kapua.service.device.management.request.message.response.GenericResponseMessage;
import org.eclipse.kapua.service.device.management.request.message.response.GenericResponsePayload;
import org.eclipse.kapua.service.storable.model.id.StorableId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.Date;
import java.util.UUID;

@Category(JUnitTests.class)
public class JsonGenericResponseMessageTest extends Assert {

    GenericResponseMessage genericResponseMessage;
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
    GenericResponseChannel genericResponseChannel;
    GenericResponsePayload genericResponsePayload;

    JsonGenericResponseMessage jsonGenericResponseMessage1;
    JsonGenericResponseMessage jsonGenericResponseMessage2;

    @Before
    public void initialize() {
        genericResponseMessage = Mockito.mock(GenericResponseMessage.class);
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
        genericResponseChannel = Mockito.mock(GenericResponseChannel.class);
        genericResponsePayload = Mockito.mock(GenericResponsePayload.class);

        Mockito.when(genericResponseMessage.getId()).thenReturn(id);
        Mockito.when(genericResponseMessage.getScopeId()).thenReturn(scopeId);
        Mockito.when(genericResponseMessage.getDeviceId()).thenReturn(deviceId);
        Mockito.when(genericResponseMessage.getClientId()).thenReturn(clientId);
        Mockito.when(genericResponseMessage.getReceivedOn()).thenReturn(receivedOn);
        Mockito.when(genericResponseMessage.getSentOn()).thenReturn(sentOn);
        Mockito.when(genericResponseMessage.getCapturedOn()).thenReturn(capturedOn);
        Mockito.when(genericResponseMessage.getPosition()).thenReturn(kapuaPosition);
        Mockito.when(genericResponseMessage.getChannel()).thenReturn(genericResponseChannel);
        Mockito.when(genericResponseMessage.getPayload()).thenReturn(genericResponsePayload);

        jsonGenericResponseMessage1 = new JsonGenericResponseMessage();
        jsonGenericResponseMessage2 = new JsonGenericResponseMessage(genericResponseMessage);
    }

    @Test
    public void jsonGenericResponseMessageWithoutParameterTest() {
        JsonGenericResponseMessage jsonGenericResponseMessage = new JsonGenericResponseMessage();

        assertNull("Null expected.", jsonGenericResponseMessage.getId());
        assertNull("Null expected.", jsonGenericResponseMessage.getScopeId());
        assertNull("Null expected.", jsonGenericResponseMessage.getDeviceId());
        assertNull("Null expected.", jsonGenericResponseMessage.getClientId());
        assertNull("Null expected.", jsonGenericResponseMessage.getReceivedOn());
        assertNull("Null expected.", jsonGenericResponseMessage.getSentOn());
        assertNull("Null expected.", jsonGenericResponseMessage.getCapturedOn());
        assertNull("Null expected.", jsonGenericResponseMessage.getPosition());
        assertNull("Null expected.", jsonGenericResponseMessage.getChannel());
        assertNull("Null expected.", jsonGenericResponseMessage.getPayload());
    }

    @Test
    public void jsonGenericResponseMessageWithParameterTest() {
        JsonGenericResponseMessage jsonGenericResponseMessage = new JsonGenericResponseMessage(genericResponseMessage);
        assertEquals("Expected and actual values should be the same.", id, jsonGenericResponseMessage.getId());
        assertEquals("Expected and actual values should be the same.", scopeId, jsonGenericResponseMessage.getScopeId());
        assertEquals("Expected and actual values should be the same.", deviceId, jsonGenericResponseMessage.getDeviceId());
        assertEquals("Expected and actual values should be the same.", clientId, jsonGenericResponseMessage.getClientId());
        assertEquals("Expected and actual values should be the same.", receivedOn, jsonGenericResponseMessage.getReceivedOn());
        assertEquals("Expected and actual values should be the same.", sentOn, jsonGenericResponseMessage.getSentOn());
        assertEquals("Expected and actual values should be the same.", capturedOn, jsonGenericResponseMessage.getCapturedOn());
        assertEquals("Expected and actual values should be the same.", kapuaPosition, jsonGenericResponseMessage.getPosition());
        assertEquals("Expected and actual values should be the same.", genericResponseChannel, jsonGenericResponseMessage.getChannel());
        assertNotNull("NotNull expected.", jsonGenericResponseMessage.getPayload());
    }

    @Test(expected = NullPointerException.class)
    public void jsonGenericResponseMessageWithNullParameterTest() {
        new JsonGenericResponseMessage(null);
    }

    @Test
    public void setAndGetIdTest() {
        UUID newId = new UUID(1L, 99L);

        jsonGenericResponseMessage1.setId(newId);
        jsonGenericResponseMessage2.setId(newId);

        assertEquals("Expected and actual values should be the same.", newId, jsonGenericResponseMessage1.getId());
        assertEquals("Expected and actual values should be the same.", newId, jsonGenericResponseMessage2.getId());

        jsonGenericResponseMessage1.setId(null);
        jsonGenericResponseMessage2.setId(null);

        assertNull("Null expected.", jsonGenericResponseMessage1.getId());
        assertNull("Null expected.", jsonGenericResponseMessage2.getId());
    }

    @Test
    public void setAndGetScopeIdTest() {
        KapuaId newScopeId = KapuaId.ANY;

        jsonGenericResponseMessage1.setScopeId(newScopeId);
        jsonGenericResponseMessage2.setScopeId(newScopeId);

        assertEquals("Expected and actual values should be the same.", newScopeId, jsonGenericResponseMessage1.getScopeId());
        assertEquals("Expected and actual values should be the same.", newScopeId, jsonGenericResponseMessage2.getScopeId());

        jsonGenericResponseMessage1.setScopeId(null);
        jsonGenericResponseMessage2.setScopeId(null);

        assertNull("Null expected.", jsonGenericResponseMessage1.getScopeId());
        assertNull("Null expected.", jsonGenericResponseMessage2.getScopeId());
    }

    @Test
    public void setAndGetDeviceIdTest() {
        KapuaId newDeviceId = KapuaId.ANY;

        jsonGenericResponseMessage1.setDeviceId(newDeviceId);
        jsonGenericResponseMessage2.setDeviceId(newDeviceId);

        assertEquals("Expected and actual values should be the same.", newDeviceId, jsonGenericResponseMessage1.getDeviceId());
        assertEquals("Expected and actual values should be the same.", newDeviceId, jsonGenericResponseMessage2.getDeviceId());

        jsonGenericResponseMessage1.setDeviceId(null);
        jsonGenericResponseMessage2.setDeviceId(null);

        assertNull("Null expected.", jsonGenericResponseMessage1.getDeviceId());
        assertNull("Null expected.", jsonGenericResponseMessage2.getDeviceId());
    }

    @Test
    public void setAndGetClientIdTest() {
        String newClientID = "ID";

        jsonGenericResponseMessage1.setClientId(newClientID);
        jsonGenericResponseMessage2.setClientId(newClientID);

        assertEquals("Expected and actual values should be the same.", newClientID, jsonGenericResponseMessage1.getClientId());
        assertEquals("Expected and actual values should be the same.", newClientID, jsonGenericResponseMessage2.getClientId());

        jsonGenericResponseMessage1.setClientId(null);
        jsonGenericResponseMessage2.setClientId(null);

        assertNull("Null expected.", jsonGenericResponseMessage1.getClientId());
        assertNull("Null expected.", jsonGenericResponseMessage2.getClientId());
    }

    @Test
    public void setAndGetReceivedOnTest() {
        Date newReceivedOn = new Date();

        jsonGenericResponseMessage1.setReceivedOn(newReceivedOn);
        jsonGenericResponseMessage2.setReceivedOn(newReceivedOn);

        assertEquals("Expected and actual values should be the same.", newReceivedOn, jsonGenericResponseMessage1.getReceivedOn());
        assertEquals("Expected and actual values should be the same.", newReceivedOn, jsonGenericResponseMessage2.getReceivedOn());

        jsonGenericResponseMessage1.setReceivedOn(null);
        jsonGenericResponseMessage2.setReceivedOn(null);

        assertNull("Null expected.", jsonGenericResponseMessage1.getReceivedOn());
        assertNull("Null expected.", jsonGenericResponseMessage2.getReceivedOn());
    }

    @Test
    public void setAndGetSentOnTest() {
        Date newSentOn = new Date();

        jsonGenericResponseMessage1.setSentOn(newSentOn);
        jsonGenericResponseMessage2.setSentOn(newSentOn);

        assertEquals("Expected and actual values should be the same.", newSentOn, jsonGenericResponseMessage1.getSentOn());
        assertEquals("Expected and actual values should be the same.", newSentOn, jsonGenericResponseMessage2.getSentOn());

        jsonGenericResponseMessage1.setSentOn(null);
        jsonGenericResponseMessage2.setSentOn(null);

        assertNull("Null expected.", jsonGenericResponseMessage1.getSentOn());
        assertNull("Null expected.", jsonGenericResponseMessage2.getSentOn());
    }

    @Test
    public void setAndGetCapturedOnTest() {
        Date newCapturedOn = new Date();

        jsonGenericResponseMessage1.setCapturedOn(newCapturedOn);
        jsonGenericResponseMessage2.setCapturedOn(newCapturedOn);

        assertEquals("Expected and actual values should be the same.", newCapturedOn, jsonGenericResponseMessage1.getCapturedOn());
        assertEquals("Expected and actual values should be the same.", newCapturedOn, jsonGenericResponseMessage2.getCapturedOn());

        jsonGenericResponseMessage1.setCapturedOn(null);
        jsonGenericResponseMessage2.setCapturedOn(null);

        assertNull("Null expected.", jsonGenericResponseMessage1.getCapturedOn());
        assertNull("Null expected.", jsonGenericResponseMessage2.getCapturedOn());
    }

    @Test
    public void setAndGetPositionTest() {
        KapuaPosition newPosition = Mockito.mock(KapuaPosition.class);

        jsonGenericResponseMessage1.setPosition(newPosition);
        jsonGenericResponseMessage2.setPosition(newPosition);

        assertEquals("Expected and actual values should be the same.", newPosition, jsonGenericResponseMessage1.getPosition());
        assertEquals("Expected and actual values should be the same.", newPosition, jsonGenericResponseMessage2.getPosition());

        jsonGenericResponseMessage1.setPosition(null);
        jsonGenericResponseMessage2.setPosition(null);

        assertNull("Null expected.", jsonGenericResponseMessage1.getPosition());
        assertNull("Null expected.", jsonGenericResponseMessage2.getPosition());
    }

    @Test
    public void setAndGetChannelTest() {
        GenericResponseChannel newGenericResponseChannel = Mockito.mock(GenericResponseChannel.class);

        jsonGenericResponseMessage1.setChannel(newGenericResponseChannel);
        jsonGenericResponseMessage2.setChannel(newGenericResponseChannel);

        assertEquals("Expected and actual values should be the same.", newGenericResponseChannel, jsonGenericResponseMessage1.getChannel());
        assertEquals("Expected and actual values should be the same.", newGenericResponseChannel, jsonGenericResponseMessage2.getChannel());

        jsonGenericResponseMessage1.setChannel(null);
        jsonGenericResponseMessage2.setChannel(null);

        assertNull("Null expected.", jsonGenericResponseMessage1.getChannel());
        assertNull("Null expected.", jsonGenericResponseMessage2.getChannel());
    }

    @Test
    public void setAndGetJsonKapuaPayloadTest() {
        JsonKapuaPayload newJsonKapuaPayload = Mockito.mock(JsonKapuaPayload.class);

        jsonGenericResponseMessage1.setPayload(newJsonKapuaPayload);
        jsonGenericResponseMessage2.setPayload(newJsonKapuaPayload);

        assertEquals("Expected and actual values should be the same.", newJsonKapuaPayload, jsonGenericResponseMessage1.getPayload());
        assertEquals("Expected and actual values should be the same.", newJsonKapuaPayload, jsonGenericResponseMessage2.getPayload());

        jsonGenericResponseMessage1.setPayload((JsonKapuaPayload) null);
        jsonGenericResponseMessage2.setPayload((JsonKapuaPayload) null);

        assertNull("Null expected.", jsonGenericResponseMessage1.getPayload());
        assertNull("Null expected.", jsonGenericResponseMessage2.getPayload());
    }

    @Test
    public void setAndGetKapuaPayloadTest() {
        KapuaPayload newKapuaPayload = Mockito.mock(KapuaPayload.class);

        jsonGenericResponseMessage1.setPayload(newKapuaPayload);
        jsonGenericResponseMessage2.setPayload(newKapuaPayload);

        assertNotNull("NotNull expected.", jsonGenericResponseMessage1.getPayload());
        assertNotNull("NotNull expected.", jsonGenericResponseMessage2.getPayload());

        try {
            jsonGenericResponseMessage1.setPayload((KapuaPayload) null);
            fail("NullPointerException expected.");
        } catch (Exception e) {
            assertEquals("NullPointerException expected.", new NullPointerException().toString(), e.toString());
        }

        try {
            jsonGenericResponseMessage2.setPayload((KapuaPayload) null);
            fail("NullPointerException expected.");
        } catch (Exception e) {
            assertEquals("NullPointerException expected.", new NullPointerException().toString(), e.toString());
        }
    }
}