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

import org.eclipse.kapua.commons.jpa.EntityManager;
import org.eclipse.kapua.commons.service.event.store.api.EventStoreRecord;
import org.eclipse.kapua.commons.service.event.store.internal.EventStoreDAO;
import org.eclipse.kapua.commons.service.event.store.internal.EventStoreRecordImpl;
import org.eclipse.kapua.commons.service.internal.ServiceDAO;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.lang.reflect.Constructor;


@Category(JUnitTests.class)
public class EventStoreDAOTest extends Assert {

    @Test
    public void constructorTest() throws Exception {
        Constructor<EventStoreDAO> eventStoreDAO = EventStoreDAO.class.getDeclaredConstructor();
        eventStoreDAO.setAccessible(true);
        eventStoreDAO.newInstance();
    }

    @Test
    public void createTest() throws Exception {
        javax.persistence.EntityManager persistenceEntityManage = Mockito.mock(javax.persistence.EntityManager.class);
        EntityManager entityManager = new EntityManager(persistenceEntityManage);
        EventStoreRecord eventStoreRecord = new EventStoreRecordImpl();
        assertEquals(ServiceDAO.create(entityManager, eventStoreRecord), EventStoreDAO.create(entityManager, eventStoreRecord));
    }
}
