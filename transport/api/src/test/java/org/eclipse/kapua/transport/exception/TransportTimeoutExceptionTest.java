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

import org.eclipse.kapua.qa.markers.Categories;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Categories.junitTests.class)
public class TransportTimeoutExceptionTest extends Assert {

    @Test
    public void getTimeoutTest() {
        long[] values = {1, 3, -1000, -645361239, 1235563423, 0};
        for (long value : values) {
            TransportTimeoutException exception = new TransportTimeoutException(value);
            assertEquals(value, exception.getTimeout().longValue());
        }
    }

    @Test
    public void getTimeoutIfNullTest() {
        TransportTimeoutException exception = new TransportTimeoutException(null);
        assertNull(exception.getTimeout());
    }
}