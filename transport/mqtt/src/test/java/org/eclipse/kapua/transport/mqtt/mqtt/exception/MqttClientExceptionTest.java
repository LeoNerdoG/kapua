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
import org.eclipse.kapua.transport.mqtt.exception.MqttClientErrorCodes;
import org.eclipse.kapua.transport.mqtt.exception.MqttClientException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class MqttClientExceptionTest extends Assert {

    private String clientId = "clientName";
    private Object[] arguments = new Object[]{5, 5.88f, 'D', true, false, "eurotech", -128, 127, -32768, 32767, -2147483648, 2147483647, -9223372036854775808L, 9223372036854775807L};

    MqttClientException exception1, exception2, exception3;
    Throwable cause;

    @Before
    public void createInstancesOfClasses() {
        exception1 = new MqttClientException(MqttClientErrorCodes.ALREADY_CONNECTED, clientId);
        exception2 = new MqttClientException(MqttClientErrorCodes.ALREADY_CONNECTED, clientId, arguments);
        exception3 = new MqttClientException(MqttClientErrorCodes.ALREADY_CONNECTED, cause, clientId, arguments);
        cause = new Throwable();
    }

    @Test
    public void firstConstructorValidTest() {
        MqttClientException exception = new MqttClientException(MqttClientErrorCodes.ALREADY_CONNECTED, clientId);
        assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
        assertEquals("Expected and actual values should be the same!", "ALREADY_CONNECTED", exception.getCode().name());
    }

    @Test
    public void firstConstructorErrorCodeNullTest() {
        try {
            MqttClientException exception = new MqttClientException(null, clientId);
            assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
            assertNull("Null expected!", exception.getCode());
        } catch (Exception ex) {
            fail("No exception expected!");
        }
    }

    @Test
    public void firstConstructorClientIdNullTest() {
        try {
            MqttClientException exception = new MqttClientException(MqttClientErrorCodes.ALREADY_CONNECTED, null);
            assertEquals("Expected and actual values should be the same!", "ALREADY_CONNECTED", exception.getCode().name());
            assertNull("Null expected!", exception.getClientId());
        } catch (Exception ex) {
            fail("No exception expected!");
        }
    }

    @Test
    public void firstConstructorAllNullTest() {
        try {
            MqttClientException exception = new MqttClientException(null, null);
            assertNull("Null expected!", exception.getCode());
            assertNull("Null expected!", exception.getClientId());
        } catch (Exception ex) {
            fail("No exception expected!");
        }
    }

    @Test (expected = MqttClientException.class)
    public void throwingExceptionUsingFirstConstructor() throws MqttClientException {
        throw exception1;
    }

    @Test
    public void secondConstructorValidTest() {
        for(Object value : arguments) {
            MqttClientException exception = new MqttClientException(MqttClientErrorCodes.ALREADY_CONNECTED, clientId, value);
            assertEquals("Expected and actual values should be the same!", "ALREADY_CONNECTED", exception.getCode().name());
            assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
        }
    }

    @Test
    public void secondConstructorErrorCodeNullTest() {
        try {
            for(Object value : arguments) {
                MqttClientException exception = new MqttClientException(null, clientId, value);
                assertNull("Null expected!", exception.getCode());
                assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
            }
        } catch (Exception ex) {
            fail("No exception expected!");
        }
    }

    @Test
    public void secondConstructorArgumentsNullTest() {
        try {
            MqttClientException exception = new MqttClientException(MqttClientErrorCodes.ALREADY_CONNECTED, clientId, (Object) null);
            assertEquals("Expected and actual values should be the same!", "ALREADY_CONNECTED", exception.getCode().name());
            assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
        } catch (Exception ex) {
            fail("No exception expected!");
        }
    }

    @Test
    public void secondConstructorAllNullTest() {
        try {
            MqttClientException exception = new MqttClientException(null, null, (Object) null);
            assertNull("Null expected!", exception.getCode());
            assertNull("Null expected!", exception.getClientId());
        } catch (Exception ex) {
            fail("No exception expected!");
        }
    }

    @Test (expected = MqttClientException.class)
    public void throwingExceptionUsingSecondConstructor() throws MqttClientException {
        throw exception2;
    }

    @Test
    public void thirdConstructorValidTest() {
        for(Object value : arguments) {
            MqttClientException exception = new MqttClientException(MqttClientErrorCodes.ALREADY_CONNECTED, cause, clientId, value);
            assertEquals("Expected and actual values should be the same!", "ALREADY_CONNECTED", exception.getCode().name());
            assertEquals("Expected and actual values should be the same!", cause, exception.getCause());
            assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
        }
    }

    @Test (expected = NullPointerException.class)
    public void thirdConstructorErrorCodeNullTest() {
        for(Object value : arguments) {
            MqttClientException exception = new MqttClientException(null, cause, clientId, value);
            assertNull("Null expected!", exception.getCode().name());
            assertEquals("Expected and actual values should be the same!", cause, exception.getCause());
            assertEquals("Expected and actual values should be the same!", clientId, exception.getClientId());
        }
    }

    @Test
    public void thirdConstructorThrowableNullTest() {
        try {
            for(Object value : arguments) {
                MqttClientException exception = new MqttClientException(MqttClientErrorCodes.ALREADY_CONNECTED, (String) null, clientId, value);
                assertEquals("Expected and actual values should be the same!", "ALREADY_CONNECTED", exception.getCode().name());
                assertNull("Null expected!", exception.getCause());
                assertNull("Null expected!", exception.getClientId());
            }
        } catch (Exception ex) {
            fail("No exception expected!");
        }
    }

    @Test
    public void thirdConstructorClientIdNullTest() {
        try {
            for(Object value : arguments) {
                MqttClientException exception = new MqttClientException(MqttClientErrorCodes.ALREADY_CONNECTED, cause, null, value);
                assertEquals("Expected and actual values should be the same!", "ALREADY_CONNECTED", exception.getCode().name());
                assertEquals("Expected and actual values should be the same!", cause, exception.getCause());
                assertNull("Null expected!", exception.getClientId());
            }
        } catch (Exception ex) {
            fail("No exception expected!");
        }
    }
}
