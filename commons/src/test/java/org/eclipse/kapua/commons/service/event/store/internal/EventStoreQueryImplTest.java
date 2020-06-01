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

import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.id.KapuaIdStatic;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;

@Category(JUnitTests.class)
public class EventStoreQueryImplTest extends Assert {

    @Test
    public void constructor1Test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<EventStoreQueryImpl> eventStoreQueryImpl = EventStoreQueryImpl.class.getDeclaredConstructor();
        eventStoreQueryImpl.setAccessible(true);
        eventStoreQueryImpl.newInstance();
    }

    @Test
    public void constructor2Test() {
        KapuaId[] scopeIdList = {new KapuaIdStatic(BigInteger.ONE), new KapuaIdStatic(BigInteger.TEN), new KapuaIdStatic(BigInteger.ZERO)};

        EventStoreQueryImpl eventStoreQuery1 = new EventStoreQueryImpl(null);
        assertNotNull(eventStoreQuery1);
        assertNull(eventStoreQuery1.getScopeId());

        for (int i = 0; i < scopeIdList.length; i++) {
            EventStoreQueryImpl eventStoreQuery2 = new EventStoreQueryImpl(scopeIdList[i]);
            assertNotNull(eventStoreQuery2);
            assertNotNull(eventStoreQuery2.getScopeId());
        }
    }
}