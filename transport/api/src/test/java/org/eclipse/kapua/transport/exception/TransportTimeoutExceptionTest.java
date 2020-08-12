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
public class TransportTimeoutExceptionTest extends Assert {

    TransportTimeoutException transportTimeoutException;

    @Before
    public void start() {
        transportTimeoutException = new TransportTimeoutException(Long.parseLong("1"));
    }

    @Test
    public void getTimeoutTest() {
        Long one = new Long("1");
        assertEquals(one, transportTimeoutException.getTimeout());
    }

    @Test
    public void getTimeoutIfNullTest() {
        TransportTimeoutException exception = new TransportTimeoutException(null);
        assertNull(exception.getTimeout());
    }

}