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
    // COMMENT: Please rename this method to: "EventStoreQueryImplTest1()"
    public void constructor1Test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // COMMENT: All of this i not necessary, because constructors are public =)
        // You can just use: EventStoreQueryImpl eventStoreQueryImpl = new EventStoreQueryImpl(); and you will
        // create an instance of the class.
        Constructor<EventStoreQueryImpl> eventStoreQueryImpl = EventStoreQueryImpl.class.getDeclaredConstructor();
        eventStoreQueryImpl.setAccessible(true);
        eventStoreQueryImpl.newInstance();
        // COMMENT: Can you please check if the instance is the one as expected? With assertThat or instanceof
        // operator.

    }

    @Test
    // COMMENT: Please rename this method to: "EventStoreQueryImplTest2()"
    public void constructor2Test() {
        KapuaId[] scopeIdList = {new KapuaIdStatic(BigInteger.ONE), new KapuaIdStatic(BigInteger.TEN), new KapuaIdStatic(BigInteger.ZERO)};

        EventStoreQueryImpl eventStoreQuery1 = new EventStoreQueryImpl(null);
        assertNotNull(eventStoreQuery1);
        assertNull(eventStoreQuery1.getScopeId());
        // COMMENT: Can you use the shorter version of for loop? for (int i: scopeIdList)
        for (int i = 0; i < scopeIdList.length; i++) {
            EventStoreQueryImpl eventStoreQuery2 = new EventStoreQueryImpl(scopeIdList[i]);
            // COMMENT: Is it possible to add additional check other than NotNull?
            assertNotNull(eventStoreQuery2);
            assertNotNull(eventStoreQuery2.getScopeId());
        }
    }
}