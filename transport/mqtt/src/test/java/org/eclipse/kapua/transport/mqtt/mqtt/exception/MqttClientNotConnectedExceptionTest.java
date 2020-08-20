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
package org.eclipse.kapua.transport.mqtt.mqtt.exception;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.transport.mqtt.exception.MqttClientNotConnectedException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class MqttClientNotConnectedExceptionTest extends Assert {

    MqttClientNotConnectedException exception;
    private String clientId = "clientName";

    @Before
    public void createInstancesOfClasses() {
        exception = new MqttClientNotConnectedException(clientId);
    }

    @Test
    public void constructorValidTest() {
        MqttClientNotConnectedException exception = new MqttClientNotConnectedException(clientId);
        assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
        assertEquals("Expected and actual values should be the same!", "NOT_CONNECTED", exception.getCode().name());
    }

    @Test
    public void constructorClientIdNullTest() {
        try {
            MqttClientNotConnectedException exception = new MqttClientNotConnectedException(null);
            assertNull("Null expected!", exception.getClientId());
            assertEquals("Expected and actual values should be the same!", "NOT_CONNECTED", exception.getCode().name());
        } catch (Exception ex) {
            fail("No exception expected!");
        }
    }

    @Test (expected = MqttClientNotConnectedException.class)
    public void throwingExceptionUsingConstructor() throws MqttClientNotConnectedException {
        throw exception;
    }
}
