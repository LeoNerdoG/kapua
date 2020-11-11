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
package org.eclipse.kapua.commons.service.event.store.internal;

import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.model.id.KapuaIdStatic;
import org.eclipse.kapua.qa.markers.Categories;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.math.BigInteger;

@Category(Categories.junitTests.class)
public class EventStoreRecordCreatorImplTest extends Assert {
    @Test
    public void eventStoreRecordCreatorImplTest() {
        KapuaId[] scopeIdList = {null, new KapuaIdStatic(BigInteger.ONE)};

        for (KapuaId scopeId : scopeIdList) {
            EventStoreRecordCreatorImpl eventStoreRecordCreatorImpl = new EventStoreRecordCreatorImpl(scopeId);
            assertEquals("Expected and actual values should be the same.", scopeId, eventStoreRecordCreatorImpl.getScopeId());
        }
    }
}  