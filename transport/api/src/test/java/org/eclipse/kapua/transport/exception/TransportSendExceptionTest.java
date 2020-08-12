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
package org.eclipse.kapua.transport.exception;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.transport.message.TransportMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class TransportSendExceptionTest extends Assert {

    TransportSendException transportSendException;
    TransportMessage transportMessage;

    @Before
    public void start() {
        transportMessage = new TransportMessage() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        };
        transportSendException = new TransportSendException(new Throwable(), transportMessage);
    }

    @Test
    public void firstConstructorTest() {
        try {
            TransportSendException exception = new TransportSendException(transportMessage);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void firstConstructorNullTest() {
        try {
            TransportSendException exception = new TransportSendException(null);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void secondConstructorTest() {
        try {
            TransportSendException exception = new TransportSendException(new Throwable(), transportMessage);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void secondConstructorNullTest() {
        try {
            TransportSendException exception = new TransportSendException(null, null);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void getRequestMessageTest() {
        assertEquals(transportMessage, transportSendException.getRequestMessage());
    }

    @Test
    public void getRequestMessageNullTest() {
        TransportSendException exception = new TransportSendException(null, null);
        assertNull(exception.getRequestMessage());
    }

}
