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
package org.eclipse.kapua.integration.misc;

import org.eclipse.kapua.KapuaException;

import org.eclipse.kapua.commons.jpa.EntityManagerFactory;
import org.eclipse.kapua.commons.model.id.KapuaEid;
import org.eclipse.kapua.commons.service.event.store.api.EventStoreRecord;
import org.eclipse.kapua.commons.service.event.store.api.EventStoreRecordCreator;
import org.eclipse.kapua.commons.service.event.store.internal.EventStoreRecordCreatorImpl;
import org.eclipse.kapua.commons.service.event.store.internal.EventStoreRecordImpl;
import org.eclipse.kapua.commons.service.event.store.internal.EventStoreServiceImpl;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.math.BigInteger;


@Category(JUnitTests.class)
public class EventStoreServiceImplTest extends Assert {


    @Test
    public void constructorTest() {
        EntityManagerFactory entityManagerFactory = Mockito.mock(EntityManagerFactory.class);
        EventStoreServiceImpl eventStoreServiceImpl = new EventStoreServiceImpl(entityManagerFactory);
        assertNotNull(eventStoreServiceImpl);
    }

    @Test
    public void createTest() {
        EntityManagerFactory entityManagerFactory = Mockito.mock(EntityManagerFactory.class);
        EventStoreServiceImpl eventStoreServiceImpl = new EventStoreServiceImpl(entityManagerFactory);
        EventStoreRecordCreator creator = new EventStoreRecordCreatorImpl(new KapuaEid(BigInteger.TEN));
        UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException();

        try {
            eventStoreServiceImpl.create(creator);
        } catch (Exception e) {
            assertEquals(unsupportedOperationException.toString(), e.toString());
        }
    }

    @Test
    public void updateTest() throws KapuaException {
        EventStoreRecord eventStoreRecord = new EventStoreRecordImpl();
        eventStoreRecord.setId(new KapuaEid(BigInteger.ONE));
        EntityManagerFactory entityManagerFactory = Mockito.mock(EntityManagerFactory.class);
        EventStoreServiceImpl eventStoreServiceImpl = new EventStoreServiceImpl(entityManagerFactory);
        eventStoreServiceImpl.update(eventStoreRecord);
    }
}