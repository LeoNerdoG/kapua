// COMMENT: Please delete all the "RedHat" stuff from the header:
///*******************************************************************************
// * Copyright (c) 2020 Eurotech and/or its affiliates and others
// *
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the Eclipse Public License v1.0
// * which accompanies this distribution, and is available at
// * http://www.eclipse.org/legal/epl-v10.html
// *
// * Contributors:
// *     Eurotech - initial API and implementation
// *******************************************************************************/

/*******************************************************************************
 * Copyright (c) 2020 Red Hat Inc and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Inc - initial API and implementation
 *     Eurotech
 *******************************************************************************/
package org.eclipse.kapua.commons.service.event.store.internal;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.model.id.KapuaEid;
import org.eclipse.kapua.commons.service.event.store.api.EventStoreRecord;
import org.eclipse.kapua.event.ServiceEvent;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.id.KapuaIdStatic;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.math.BigInteger;
import java.util.Date;

@Category(JUnitTests.class)
public class EventStoreRecordImplTest extends Assert {


    @Test
    // COMMENT: Please rename the test to EventStoreRecordImplTest1()
    public void constructor1Test() {
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();
        assertNotNull(eventStoreRecordImpl);
        assertEquals(ServiceEvent.EventStatus.TRIGGERED, eventStoreRecordImpl.getStatus());
        // COMMENT: Can you check with assertThat that the created object is of the right class? With
        // assertThat of instanceof.
    }

    @Test
    // COMMENT: Please rename the test to EventStoreRecordImplTest2()
    public void constructor2Test() {
        KapuaId[] scopeIdList = {null, new KapuaIdStatic(BigInteger.ONE), new KapuaIdStatic(BigInteger.TEN), new KapuaIdStatic(BigInteger.ZERO)};

        for (int i = 0; i < scopeIdList.length; i++) {
            EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl(scopeIdList[i]);
            assertNotNull(eventStoreRecordImpl);
            assertEquals(scopeIdList[i], eventStoreRecordImpl.getScopeId());
            // COMMENT: Can you check with assertThat that the created object is of the right class? With
            // assertThat of instanceof.
        }
    }

    @Test
    // COMMENT: Please rename the test to EventStoreRecordImplTest3()
    public void constructor3Test() throws KapuaException {
        // COMMENT: using mockito - #soProud :D
        EventStoreRecord eventStoreRecord = Mockito.mock(EventStoreRecord.class);

        Mockito.when(eventStoreRecord.getContextId()).thenReturn("contextId");
        Mockito.when(eventStoreRecord.getTimestamp()).thenReturn(new Date());
        Mockito.when(eventStoreRecord.getUserId()).thenReturn(new KapuaEid(BigInteger.TEN));
        Mockito.when(eventStoreRecord.getService()).thenReturn("service");
        Mockito.when(eventStoreRecord.getEntityType()).thenReturn("entityType");
        Mockito.when(eventStoreRecord.getEntityId()).thenReturn(new KapuaEid(BigInteger.TEN));
        Mockito.when(eventStoreRecord.getOperation()).thenReturn("operation");
        Mockito.when(eventStoreRecord.getInputs()).thenReturn("input");
        Mockito.when(eventStoreRecord.getOutputs()).thenReturn("output");
        Mockito.when(eventStoreRecord.getStatus()).thenReturn(ServiceEvent.EventStatus.TRIGGERED);
        Mockito.when(eventStoreRecord.getNote()).thenReturn("note");

        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl(eventStoreRecord);

        // COMMENT: Can you check with assertThat that the created object is of the right class? With
        // assertThat of instanceof.
        assertNotNull(eventStoreRecordImpl);

        assertEquals("contextId", eventStoreRecordImpl.getContextId());
        assertEquals(new Date().toString(), eventStoreRecordImpl.getTimestamp().toString());
        assertEquals(new KapuaEid(BigInteger.TEN), eventStoreRecordImpl.getUserId());
        assertEquals("service", eventStoreRecordImpl.getService());
        assertEquals("entityType", eventStoreRecordImpl.getEntityType());
        assertEquals(new KapuaEid(BigInteger.TEN), eventStoreRecordImpl.getEntityId());
        assertEquals("operation", eventStoreRecordImpl.getOperation());
        assertEquals("input", eventStoreRecordImpl.getInputs());
        assertEquals("output", eventStoreRecordImpl.getOutputs());
        assertEquals(ServiceEvent.EventStatus.TRIGGERED, eventStoreRecordImpl.getStatus());
        assertEquals("note", eventStoreRecordImpl.getNote());
    }

    @Test
    public void getContextIdTest() {
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();
        // COMMENT: Can you add additional tests with different constructors to see if the
        // contextId is set properly?
        assertNull(eventStoreRecordImpl.getContextId());
    }

    @Test
    public void setContextIdTest() {
        String contextId = "contextId";
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();

        assertNull(eventStoreRecordImpl.getContextId());
        eventStoreRecordImpl.setContextId(contextId);
        assertEquals(contextId, eventStoreRecordImpl.getContextId());
        eventStoreRecordImpl.setContextId(null);
        assertNull(eventStoreRecordImpl.getContextId());
    }

    @Test
    public void getTimestampTest() {
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();
        // COMMENT: Can you add additional tests with different constructors to see if the
        // timestamp is set properly?
        assertNull(eventStoreRecordImpl.getTimestamp());
    }

    @Test
    public void setTimestampTest() {
        Date timeStamp = new Date();
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();

        assertNull(eventStoreRecordImpl.getTimestamp());
        eventStoreRecordImpl.setTimestamp(timeStamp);
        assertEquals(timeStamp, eventStoreRecordImpl.getTimestamp());
        eventStoreRecordImpl.setTimestamp(null);
        assertNull(eventStoreRecordImpl.getTimestamp());
    }

    @Test
    public void getUserIdTest() {
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();
        // COMMENT: Can you add additional tests with different constructors to see if the
        // userId is set properly?
        assertNull(eventStoreRecordImpl.getUserId());
    }

    @Test
    public void setUserIdTest() {
        KapuaId[] userIdList = {new KapuaIdStatic(BigInteger.ONE), new KapuaIdStatic(BigInteger.TEN), new KapuaIdStatic(BigInteger.ZERO)};
        // COMMENT: Can you please reformat this for loop into: for (int i: userIdList)
        for (int i = 0; i < userIdList.length; i++) {
            EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();

            assertNull(eventStoreRecordImpl.getUserId());
            eventStoreRecordImpl.setUserId(userIdList[i]);
            assertEquals(userIdList[i], eventStoreRecordImpl.getUserId());
            eventStoreRecordImpl.setUserId(null);
            assertNull(eventStoreRecordImpl.getUserId());
        }
    }

    @Test
    public void getServiceTest() {
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();
        // COMMENT: Can you add additional tests with different constructors to see if the
        // service is set properly?
        assertNull(eventStoreRecordImpl.getService());
    }

    @Test
    public void setServiceTest() {
        String service = "service";
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();

        assertNull(eventStoreRecordImpl.getService());
        eventStoreRecordImpl.setService(service);
        assertEquals(service, eventStoreRecordImpl.getService());
        eventStoreRecordImpl.setService(null);
        assertNull(eventStoreRecordImpl.getService());
    }

    @Test
    public void getEntityTypeTest() {
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();
        // COMMENT: Can you add additional tests with different constructors to see if the
        // entityType is set properly?
        assertNull(eventStoreRecordImpl.getEntityType());
    }

    @Test
    public void setEntityTypeTest() {
        String entityType = "entityType";
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();

        assertNull(eventStoreRecordImpl.getEntityType());
        eventStoreRecordImpl.setEntityType(entityType);
        assertEquals(entityType, eventStoreRecordImpl.getEntityType());
        eventStoreRecordImpl.setEntityType(null);
        assertNull(eventStoreRecordImpl.getEntityType());

    }

    @Test
    public void getScopeIdTest() {
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();
        // COMMENT: Can you add additional tests with different constructors to see if the
        // scopeId is set properly?
        assertNull(eventStoreRecordImpl.getScopeId());
    }

    @Test
    public void setScopeIdTest() {
        KapuaId[] scopeIdList = {new KapuaIdStatic(BigInteger.ONE), new KapuaIdStatic(BigInteger.TEN), new KapuaIdStatic(BigInteger.ZERO)};
        // COMMENT: Can you please reformat this for loop into: for (int i: scopeIdList)
        for (int i = 0; i < scopeIdList.length; i++) {
            EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();

            assertNull(eventStoreRecordImpl.getScopeId());
            eventStoreRecordImpl.setScopeId(scopeIdList[i]);
            assertEquals(scopeIdList[i], eventStoreRecordImpl.getScopeId());
            eventStoreRecordImpl.setScopeId(null);
            assertNull(eventStoreRecordImpl.getScopeId());
        }
    }

    @Test
    public void getEntityIdTest() {
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();
        // COMMENT: Can you add additional tests with different constructors to see if the
        // entityId is set properly?
        assertNull(eventStoreRecordImpl.getEntityId());
    }

    @Test
    public void setEntityIdTest() {
        KapuaId[] entityIdList = {new KapuaIdStatic(BigInteger.ONE), new KapuaIdStatic(BigInteger.TEN), new KapuaIdStatic(BigInteger.ZERO)};
        // COMMENT: Can you please reformat this for loop into: for (int i: entityIdList)
        for (int i = 0; i < entityIdList.length; i++) {
            EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();

            assertNull(eventStoreRecordImpl.getEntityId());
            eventStoreRecordImpl.setEntityId(entityIdList[i]);
            assertEquals(entityIdList[i], eventStoreRecordImpl.getEntityId());
            eventStoreRecordImpl.setEntityId(null);
            assertNull(eventStoreRecordImpl.getEntityId());
        }
    }

    @Test
    public void getOperationTest() {
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();
        // COMMENT: Can you add additional tests with different constructors to see if the
        // operation is set properly?
        assertNull(eventStoreRecordImpl.getOperation());
    }

    @Test
    public void setOperationIdTest() {
        String operation = "operation";
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();

        assertNull(eventStoreRecordImpl.getOperation());
        eventStoreRecordImpl.setOperation(operation);
        assertEquals(operation, eventStoreRecordImpl.getOperation());
        eventStoreRecordImpl.setOperation(null);
        assertNull(eventStoreRecordImpl.getOperation());
    }

    @Test
    public void getInputsTest() {
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();
        // COMMENT: Can you add additional tests with different constructors to see if the
        // inputs is set properly?
        assertNull(eventStoreRecordImpl.getInputs());
    }

    @Test
    public void setInputsTest() {
        String inputs = "inputs";
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();

        assertNull(eventStoreRecordImpl.getInputs());
        eventStoreRecordImpl.setInputs(inputs);
        assertEquals(inputs, eventStoreRecordImpl.getInputs());
        eventStoreRecordImpl.setInputs(null);
        assertNull(eventStoreRecordImpl.getInputs());
    }

    @Test
    public void getOutputsTest() {
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();
        // COMMENT: Can you add additional tests with different constructors to see if the
        // outputs is set properly?
        assertNull(eventStoreRecordImpl.getOutputs());
    }

    @Test
    public void setOutputsTest() {
        String outputs = "outputs";
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();

        assertNull(eventStoreRecordImpl.getOutputs());
        eventStoreRecordImpl.setOutputs(outputs);
        assertEquals(outputs, eventStoreRecordImpl.getOutputs());
        eventStoreRecordImpl.setOutputs(null);
        assertNull(eventStoreRecordImpl.getOutputs());
    }

    @Test
    public void getStatusTest() {
        EventStoreRecordImpl eventStoreRecordImpl1 = new EventStoreRecordImpl();
        EventStoreRecordImpl eventStoreRecordImpl2 = new EventStoreRecordImpl(new KapuaIdStatic(BigInteger.ONE));

        assertEquals(ServiceEvent.EventStatus.TRIGGERED, eventStoreRecordImpl1.getStatus());
        assertNull(eventStoreRecordImpl2.getStatus());
    }

    @Test
    public void setStatusTest() {
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();

        assertEquals(ServiceEvent.EventStatus.TRIGGERED, eventStoreRecordImpl.getStatus());
        eventStoreRecordImpl.setStatus(ServiceEvent.EventStatus.SENT);
        assertEquals(ServiceEvent.EventStatus.SENT, eventStoreRecordImpl.getStatus());
        eventStoreRecordImpl.setStatus(ServiceEvent.EventStatus.SEND_ERROR);
        assertEquals(ServiceEvent.EventStatus.SEND_ERROR, eventStoreRecordImpl.getStatus());
        eventStoreRecordImpl.setStatus(ServiceEvent.EventStatus.TRIGGERED);
        assertEquals(ServiceEvent.EventStatus.TRIGGERED, eventStoreRecordImpl.getStatus());
        eventStoreRecordImpl.setStatus(null);
        assertNull(eventStoreRecordImpl.getStatus());
    }

    @Test
    public void getNoteTest() {
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();
        // COMMENT: Can you add additional tests with different constructors to see if the
        // note is set properly?
        assertNull(eventStoreRecordImpl.getNote());
    }

    @Test
    public void setNoteTest() {
        String note = "Note";
        EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl();

        assertNull(eventStoreRecordImpl.getNote());
        eventStoreRecordImpl.setNote(note);
        assertEquals(note, eventStoreRecordImpl.getNote());
        eventStoreRecordImpl.setNote(null);
        assertNull(eventStoreRecordImpl.getNote());
    }
}