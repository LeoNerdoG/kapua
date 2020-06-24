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

import org.eclipse.kapua.KapuaEntityCloneException;
import org.eclipse.kapua.commons.model.id.KapuaEid;
import org.eclipse.kapua.commons.service.event.store.api.EventStoreRecord;
import org.eclipse.kapua.model.id.KapuaId;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.math.BigInteger;

@Category(JUnitTests.class)
public class EventStoreFactoryImplTest extends Assert {

    @Test
    public void newEntityTest() {
        EventStoreFactoryImpl eventStoreFactoryImpl = new EventStoreFactoryImpl();
        KapuaId[] scopeIdList = {null, new KapuaEid(BigInteger.TEN), new KapuaEid(BigInteger.ZERO), new KapuaEid(BigInteger.ONE)};
        // COMMENT: Can you use the shorter version of for loop? for (int i: scopeIdList)
        for (int i = 0; i < scopeIdList.length; i++) {
            // COMMENT: can you mock or spy EventStoreRecordImpl? Becasue this test is not fully isolated
            // from this other method and if you can mock it, this would be perfect.
            EventStoreRecordImpl eventStoreRecordImpl = new EventStoreRecordImpl(scopeIdList[i]);
            assertNotNull(eventStoreFactoryImpl.newEntity(scopeIdList[i]));
            assertEquals(eventStoreRecordImpl.getScopeId(), eventStoreFactoryImpl.newEntity(scopeIdList[i]).getScopeId());
        }
    }

    @Test
    public void newCreatorTest() {
        EventStoreFactoryImpl eventStoreFactoryImpl = new EventStoreFactoryImpl();
        KapuaId[] scopeIdList = {null, new KapuaEid(BigInteger.TEN), new KapuaEid(BigInteger.ZERO), new KapuaEid(BigInteger.ONE)};
        // COMMENT: Can you use the shorter version of for loop? for (int i: scopeIdList)
        for (int i = 0; i < scopeIdList.length; i++) {
            // COMMENT: can you mock or spy EventStoreRecordCreatorImpl? Because this test is not fully isolated
            // from this other method and if you can mock it, this would be perfect.
            EventStoreRecordCreatorImpl eventStoreRecordCreatorImpl = new EventStoreRecordCreatorImpl(scopeIdList[i]);
            assertNotNull(eventStoreFactoryImpl.newCreator(scopeIdList[i]));
            assertEquals(eventStoreRecordCreatorImpl.getScopeId(), eventStoreFactoryImpl.newCreator(scopeIdList[i]).getScopeId());

        }
    }

    @Test
    public void newQueryTest() {
        EventStoreFactoryImpl eventStoreFactoryImpl = new EventStoreFactoryImpl();
        KapuaId[] scopeIdList = {null, new KapuaEid(BigInteger.TEN), new KapuaEid(BigInteger.ZERO), new KapuaEid(BigInteger.ONE)};
        // COMMENT: Can you use the shorter version of for loop? for (int i: scopeIdList)
        for (int i = 0; i < scopeIdList.length; i++) {
            // COMMENT: can you mock or spy EventStoreQueryImpl? Because this test is not fully isolated
            // from this other method and if you can mock it, this would be perfect.
            EventStoreQueryImpl eventStoreQueryImpl = new EventStoreQueryImpl(scopeIdList[i]);
            assertNotNull(eventStoreFactoryImpl.newQuery(scopeIdList[i]));
            assertEquals(eventStoreQueryImpl.getScopeId(), eventStoreFactoryImpl.newQuery(scopeIdList[i]).getScopeId());
        }
    }

    @Test
    public void newListResultTest() {
        EventStoreFactoryImpl eventStoreFactoryImpl = new EventStoreFactoryImpl();
        KapuaId[] scopeIdList = {null, new KapuaEid(BigInteger.TEN), new KapuaEid(BigInteger.ZERO), new KapuaEid(BigInteger.ONE)};
        // COMMENT: Can you use the shorter version of for loop? for (int i: scopeIdList)
        for (int i = 0; i < scopeIdList.length; i++) {
            // COMMENT: Can you use assertThat or at least check if the instance of the returned object is
            // of the right class? I would keep the NotNull test.
            assertNotNull(eventStoreFactoryImpl.newListResult());
        }
    }

    @Test
    public void cloneTest() {
        EventStoreFactoryImpl eventStoreFactoryImpl = new EventStoreFactoryImpl();
        EventStoreRecord eventStoreRecord = new EventStoreRecordImpl();
        EventStoreRecord nullEventStoreRecord = null;
        KapuaEntityCloneException kapuaEntityCloneException = new KapuaEntityCloneException(new Exception(), EventStoreRecord.TYPE, nullEventStoreRecord);
        // COMMENT: Can you use assertThat or at least check if the instance of the returned object is
        // of the right class? I would keep the NotNull test.
        assertNotNull(eventStoreFactoryImpl.clone(eventStoreRecord));

        try {
            eventStoreFactoryImpl.clone(nullEventStoreRecord);
        } catch (Exception e) {
            assertEquals("KapuaEntityCloneException expected",kapuaEntityCloneException.toString(), e.toString());
        }
    }
}