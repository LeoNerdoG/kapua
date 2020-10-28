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

import org.eclipse.kapua.message.KapuaPayload;
import org.eclipse.kapua.message.KapuaPosition;
import org.eclipse.kapua.message.device.data.KapuaDataChannel;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.service.datastore.model.DatastoreMessage;
import org.eclipse.kapua.service.storable.model.id.StorableId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.Date;
import java.util.UUID;

@Category(JUnitTests.class)
public class JsonDatastoreMessageTest extends Assert {

    DatastoreMessage datastoreMessage;
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
    KapuaPayload kapuaPayload;

    @Before
    public void initialize() {
        datastoreMessage = Mockito.mock(DatastoreMessage.class);
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
        kapuaPayload = Mockito.mock(KapuaPayload.class);

        Mockito.when(datastoreMessage.getId()).thenReturn(id);
        Mockito.when(datastoreMessage.getDatastoreId()).thenReturn(storableId);
        Mockito.when(datastoreMessage.getTimestamp()).thenReturn(timestamp);
        Mockito.when(datastoreMessage.getScopeId()).thenReturn(scopeId);
        Mockito.when(datastoreMessage.getDeviceId()).thenReturn(deviceId);
        Mockito.when(datastoreMessage.getClientId()).thenReturn(clientId);
        Mockito.when(datastoreMessage.getReceivedOn()).thenReturn(receivedOn);
        Mockito.when(datastoreMessage.getSentOn()).thenReturn(sentOn);
        Mockito.when(datastoreMessage.getCapturedOn()).thenReturn(capturedOn);
        Mockito.when(datastoreMessage.getPosition()).thenReturn(kapuaPosition);
        Mockito.when(datastoreMessage.getChannel()).thenReturn(kapuaDataChannel);
        Mockito.when(datastoreMessage.getPayload()).thenReturn(kapuaPayload);
    }

    @Test
    public void jsonDatastoreMessageWithoutParameterTest() {
        JsonDatastoreMessage jsonDatastoreMessage = new JsonDatastoreMessage();

        assertNull("Null expected.", jsonDatastoreMessage.getId());
        assertNull("Null expected.", jsonDatastoreMessage.getDatastoreId());
        assertNull("Null expected.", jsonDatastoreMessage.getTimestamp());
        assertNull("Null expected.", jsonDatastoreMessage.getScopeId());
        assertNull("Null expected.", jsonDatastoreMessage.getDeviceId());
        assertNull("Null expected.", jsonDatastoreMessage.getClientId());
        assertNull("Null expected.", jsonDatastoreMessage.getReceivedOn());
        assertNull("Null expected.", jsonDatastoreMessage.getSentOn());
        assertNull("Null expected.", jsonDatastoreMessage.getCapturedOn());
        assertNull("Null expected.", jsonDatastoreMessage.getPosition());
        assertNull("Null expected.", jsonDatastoreMessage.getChannel());
        assertNull("Null expected.", jsonDatastoreMessage.getPayload());
    }

    @Test
    public void jsonDatastoreMessageWithParameterTest() {
        JsonDatastoreMessage jsonDatastoreMessage = new JsonDatastoreMessage(datastoreMessage);

        assertEquals("Expected and actual values should be the same.", id, jsonDatastoreMessage.getId());
        assertEquals("Expected and actual values should be the same.", storableId, jsonDatastoreMessage.getDatastoreId());
        assertEquals("Expected and actual values should be the same.", timestamp, jsonDatastoreMessage.getTimestamp());
        assertEquals("Expected and actual values should be the same.", scopeId, jsonDatastoreMessage.getScopeId());
        assertEquals("Expected and actual values should be the same.", deviceId, jsonDatastoreMessage.getDeviceId());
        assertEquals("Expected and actual values should be the same.", clientId, jsonDatastoreMessage.getClientId());
        assertEquals("Expected and actual values should be the same.", receivedOn, jsonDatastoreMessage.getReceivedOn());
        assertEquals("Expected and actual values should be the same.", sentOn, jsonDatastoreMessage.getSentOn());
        assertEquals("Expected and actual values should be the same.", capturedOn, jsonDatastoreMessage.getCapturedOn());
        assertEquals("Expected and actual values should be the same.", kapuaPosition, jsonDatastoreMessage.getPosition());
        assertEquals("Expected and actual values should be the same.", kapuaDataChannel, jsonDatastoreMessage.getChannel());
        assertNotNull("NotNull expected.", jsonDatastoreMessage.getPayload());
    }

    @Test(expected = NullPointerException.class)
    public void jsonDatastoreMessageWithNullParameterTest() {
        new JsonDatastoreMessage(null);
    }

    @Test
    public void setAndGetDatastoreIdTest() {
        StorableId datastoreId = Mockito.mock(StorableId.class);
        JsonDatastoreMessage jsonDatastoreMessage1 = new JsonDatastoreMessage();
        JsonDatastoreMessage jsonDatastoreMessage2 = new JsonDatastoreMessage(datastoreMessage);

        jsonDatastoreMessage1.setDatastoreId(datastoreId);
        jsonDatastoreMessage2.setDatastoreId(datastoreId);

        assertEquals("Expected and actual values should be the same.", datastoreId, jsonDatastoreMessage1.getDatastoreId());
        assertEquals("Expected and actual values should be the same.", datastoreId, jsonDatastoreMessage2.getDatastoreId());

        jsonDatastoreMessage1.setDatastoreId(null);
        jsonDatastoreMessage2.setDatastoreId(null);

        assertNull("Null expected.", jsonDatastoreMessage1.getDatastoreId());
        assertNull("Null expected.", jsonDatastoreMessage2.getDatastoreId());
    }

    @Test
    public void setAndGetTimestampTest() {
        Date newTimestamp = new Date();
        JsonDatastoreMessage jsonDatastoreMessage1 = new JsonDatastoreMessage();
        JsonDatastoreMessage jsonDatastoreMessage2 = new JsonDatastoreMessage(datastoreMessage);

        jsonDatastoreMessage1.setTimestamp(newTimestamp);
        jsonDatastoreMessage2.setTimestamp(newTimestamp);

        assertEquals("Expected and actual values should be the same.", newTimestamp, jsonDatastoreMessage1.getTimestamp());
        assertEquals("Expected and actual values should be the same.", newTimestamp, jsonDatastoreMessage2.getTimestamp());

        jsonDatastoreMessage1.setTimestamp(null);
        jsonDatastoreMessage2.setTimestamp(null);

        assertNull("Null expected.", jsonDatastoreMessage1.getTimestamp());
        assertNull("Null expected.", jsonDatastoreMessage2.getTimestamp());
    }
}