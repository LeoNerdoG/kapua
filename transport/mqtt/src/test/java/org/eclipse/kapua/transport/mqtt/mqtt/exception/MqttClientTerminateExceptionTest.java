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
import org.eclipse.kapua.transport.mqtt.exception.MqttClientTerminateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class MqttClientTerminateExceptionTest extends Assert {

    MqttClientTerminateException exception;
    Throwable cause;

    private String clientId = "clientName";

    @Before
    public void createInstancesOfClasses() {
        cause = new Throwable();
        exception = new MqttClientTerminateException(cause, clientId);
    }

    @Test
    public void constructorValidTest() {
        MqttClientTerminateException exception = new MqttClientTerminateException(cause, clientId);
        assertEquals("Expected and actual values should be the same!", "TERMINATE_ERROR", exception.getCode().name());
        assertEquals("Expected and actual values should be the same!", cause, exception.getCause());
        assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
    }

    @Test
    public void constructorCauseNullTest() {
        MqttClientTerminateException exception = new MqttClientTerminateException(null, clientId);
        assertEquals("Expected and actual values should be the same!", "TERMINATE_ERROR", exception.getCode().name());
        assertNull("Null expected!", exception.getCause());
        assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
    }

    @Test
    public void constructorClientIdNullTest() {
        MqttClientTerminateException exception = new MqttClientTerminateException(cause, null);
        assertEquals("Expected and actual values should be the same!", "TERMINATE_ERROR", exception.getCode().name());
        assertEquals("Expected and actual values should be the same!", cause, exception.getCause());
        assertNull("Null expected!", exception.getClientId());
    }

    @Test
    public void constructorAllNullTest() {
        MqttClientTerminateException exception = new MqttClientTerminateException(null, null);
        assertEquals("Expected and actual values should be the same!", "TERMINATE_ERROR", exception.getCode().name());
        assertNull("Null expected!", exception.getCause());
        assertNull("Null expected!", exception.getClientId());
    }

    @Test (expected = MqttClientTerminateException.class)
    public void throwingExceptionUsingConstructor() throws MqttClientTerminateException {
        throw exception;
    }
}
