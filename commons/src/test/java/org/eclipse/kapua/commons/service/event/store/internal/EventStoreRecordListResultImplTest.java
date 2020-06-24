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

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.lang.reflect.Constructor;

@Category(JUnitTests.class)
public class EventStoreRecordListResultImplTest {
    @Test
    public void testConstructor() throws Exception {
        Constructor<EventStoreRecordListResultImpl> eventStoreRecordListResultImpl = EventStoreRecordListResultImpl.class.getDeclaredConstructor();
        eventStoreRecordListResultImpl.setAccessible(true);
        eventStoreRecordListResultImpl.newInstance();
    }



}
