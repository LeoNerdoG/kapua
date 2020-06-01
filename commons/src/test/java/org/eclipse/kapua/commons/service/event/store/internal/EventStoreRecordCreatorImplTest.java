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

import java.math.BigInteger;

@Category(JUnitTests.class)
public class EventStoreRecordCreatorImplTest extends Assert {
    @Test
    public void constructorTest() {
        KapuaId[] scopeIdList = {null, new KapuaIdStatic(BigInteger.ONE), new KapuaIdStatic(BigInteger.TEN), new KapuaIdStatic(BigInteger.ZERO)};

        for (int i = 0; i < scopeIdList.length; i++) {
            EventStoreRecordCreatorImpl eventStoreRecordCreatorImpl = new EventStoreRecordCreatorImpl(scopeIdList[i]);
            assertNotNull(eventStoreRecordCreatorImpl);
            assertEquals(scopeIdList[i],eventStoreRecordCreatorImpl.getScopeId());
        }
    }
}