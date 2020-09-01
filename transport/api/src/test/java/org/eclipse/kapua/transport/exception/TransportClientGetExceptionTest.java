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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class TransportClientGetExceptionTest extends Assert {

    TransportClientGetException exception;

    @Before
    public void start() {
        exception = new TransportClientGetException("127.0.0.1");
    }

    @Test
    public void constructorNullTest() {
        try {
            TransportClientGetException transportClientGetException = new TransportClientGetException(null);
        } catch (Exception e) {
            fail("Exception should not be thrown.");
        }
    }

    @Test
    public void constructorRandomStringsTest() {
        String[] randomStrings = {"a", "0.0.0.0", "asdfasfasfaf", "{@}]~ˇ^°˘˘˛˘`", "test", "127.0.0.0"};
        for (String randomString : randomStrings) {
            try {
                TransportClientGetException transportClientGetException = new TransportClientGetException(randomString);
            } catch (Exception e) {
                fail("Exception should not be thrown. The problem occurs when constructor gets \"" + randomString + "\" as a parameter.");
            }
        }
    }

    @Test
    public void secondConstructorNullTest() {
        try {
            TransportClientGetException transportClientGetException = new TransportClientGetException(null, null);
        } catch (Exception e) {
            fail("Exception should not be thrown.");
        }
    }

    @Test
    public void secondConstructorRandomStringsTest() {
        Throwable throwable = new Throwable();
        String[] randomStrings = {"a", "0.0.0.0", "asdfasfasfaf", "{@}]~ˇ^°˘˘˛˘`", "test", "127.0.0.0"};
        for (String randomString : randomStrings) {
            try {
                TransportClientGetException transportClientGetException = new TransportClientGetException(throwable, randomString);
            } catch (Exception e) {
                fail("Exception should not be thrown. The problem occurs when constructor gets \"" + randomString + "\" as a parameter.");
            }
        }
    }

    @Test(expected = TransportClientGetException.class)
    public void throwingExceptionTest() throws TransportClientGetException {
        throw exception;
    }

    @Test(expected = TransportClientGetException.class)
    public void throwingExceptionUsingFirstConstructorTest() throws TransportClientGetException {
        throw new TransportClientGetException("127.0.0.1");
    }

    @Test(expected = TransportClientGetException.class)
    public void throwingExceptionUsingSecondConstructorTest() throws TransportClientGetException {
        throw new TransportClientGetException(new Throwable(), "127.0.0.1");
    }

    @Test
    public void getRequestMessageNullTest() {
        TransportClientGetException transportClientGetException = new TransportClientGetException(null);
        assertNull(transportClientGetException.getRequestMessage());
    }

    @Test
    public void getRequestMessageRandomStringTest() {
        Throwable throwable = new Throwable();
        String[] randomStrings = {"a", "0.0.0.0", "asdfasfasfaf", "{@}]~ˇ^°˘˘˛˘`", "test", "127.0.0.0"};
        for (String randomString : randomStrings) {
            try {
                TransportClientGetException transportClientGetException = new TransportClientGetException(throwable, randomString);
                assertEquals(randomString, transportClientGetException.getRequestMessage());
            } catch (Exception e) {
                fail("Exception should not be thrown. The problem occurs when constructor gets \"" + randomString + "\" as a parameter.");
            }
        }
    }

}
