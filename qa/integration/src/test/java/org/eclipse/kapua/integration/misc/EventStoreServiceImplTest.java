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
package org.eclipse.kapua.integration.misc;

import org.eclipse.kapua.commons.jpa.EntityManagerFactory;
import org.eclipse.kapua.commons.model.id.KapuaEid;
import org.eclipse.kapua.commons.service.event.store.api.EventStoreRecordCreator;
import org.eclipse.kapua.commons.service.event.store.internal.EventStoreRecordCreatorImpl;
import org.eclipse.kapua.commons.service.event.store.internal.EventStoreServiceImpl;


import org.eclipse.kapua.qa.markers.Categories;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.math.BigInteger;

@Category(value = Categories.junitTests.class)
public class EventStoreServiceImplTest extends Assert {

    @Test
    public void eventStoreServiceImplTest() {
        EntityManagerFactory entityManagerFactory = Mockito.mock(EntityManagerFactory.class);
        EventStoreServiceImpl eventStoreServiceImpl = new EventStoreServiceImpl(entityManagerFactory);
        assertThat("EventStoreServiceImpl object expected.", eventStoreServiceImpl, IsInstanceOf.instanceOf(EventStoreServiceImpl.class));
    }

    @Test
    public void createTest() {
        EntityManagerFactory entityManagerFactory = Mockito.mock(EntityManagerFactory.class);
        EventStoreServiceImpl eventStoreServiceImpl = new EventStoreServiceImpl(entityManagerFactory);
        EventStoreRecordCreator[] creator = {null, new EventStoreRecordCreatorImpl(new KapuaEid(BigInteger.ONE))};
        UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException();

        for (EventStoreRecordCreator eventStoreRecordCreator : creator) {
            try {
                eventStoreServiceImpl.create(eventStoreRecordCreator);
            } catch (Exception e) {
                assertEquals("Expected and actual values should be the same.", unsupportedOperationException.toString(), e.toString());
            }
        }
    }
} 