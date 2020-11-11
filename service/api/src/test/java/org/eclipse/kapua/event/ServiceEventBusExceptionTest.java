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
package org.eclipse.kapua.event;

import org.eclipse.kapua.KapuaErrorCodes;
import org.eclipse.kapua.qa.markers.Categories;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Categories.junitTests.class)
public class ServiceEventBusExceptionTest extends Assert {

    @Test
    public void serviceEventBusExceptionThrowableCauseTest() {
        Throwable[] throwables = {new Throwable(), null};

        for (Throwable throwable : throwables) {
            ServiceEventBusException serviceEventBusException = new ServiceEventBusException(throwable);
            assertEquals("Expected and actual values should be the same.", KapuaErrorCodes.INTERNAL_ERROR, serviceEventBusException.getCode());
            assertEquals("Expected and actual values should be the same.", throwable, serviceEventBusException.getCause());
            assertEquals("Expected and actual values should be the same.", "An internal error occurred: {0}.", serviceEventBusException.getMessage());
        }
    }

    @Test
    public void serviceEventBusExceptionStringMessageTest() {
        String[] messages = {"message", null};

        for (String message : messages) {
            ServiceEventBusException serviceEventBusException = new ServiceEventBusException(message);
            assertEquals("Expected and actual values should be the same.", KapuaErrorCodes.INTERNAL_ERROR, serviceEventBusException.getCode());
            assertNull("Null expected.", serviceEventBusException.getCause());
            assertEquals("Expected and actual values should be the same.", "An internal error occurred: " + message + ".", serviceEventBusException.getMessage());
        }
    }
}