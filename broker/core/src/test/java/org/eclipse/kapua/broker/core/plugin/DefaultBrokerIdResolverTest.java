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
package org.eclipse.kapua.broker.core.plugin;

import org.apache.activemq.broker.BrokerFilter;
import org.apache.activemq.command.BrokerId;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

@Category(JUnitTests.class)
public class DefaultBrokerIdResolverTest extends Assert {

    DefaultBrokerIdResolver defaultBrokerIdResolver;

    @Before
    public void initialize() {
        defaultBrokerIdResolver = new DefaultBrokerIdResolver();
    }

    @Test
    public void getBrokerIdTest() {
        String[] ids = {"", "broker id", "id1234567890", "id_)(*&^%$#@!?.,,.|"};
        BrokerFilter brokerFilter = Mockito.mock(BrokerFilter.class);

        for (String id : ids) {
            BrokerId brokerId = new BrokerId(id);
            Mockito.when(brokerFilter.getBrokerId()).thenReturn(brokerId);
            assertEquals("Expected and actual values should be the same", id, defaultBrokerIdResolver.getBrokerId(brokerFilter));
        }
    }

    @Test(expected = NullPointerException.class)
    public void getBrokerIdNullTest() {
        defaultBrokerIdResolver.getBrokerId(null);
    }
}