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
package org.eclipse.kapua.app.api.resources.v1.resources.exception;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class SessionNotPopulatedExceptionTest extends Assert {

    @Test
    public void sessionNotPopulatedExceptionTest() {
        SessionNotPopulatedException sessionNotPopulatedException = new SessionNotPopulatedException();

        assertEquals("Expected and actual values should be the same.", RestApiErrorCodes.SESSION_NOT_POPULATED, sessionNotPopulatedException.getCode());
        assertEquals("Expected and actual values should be the same.", "Error: ", sessionNotPopulatedException.getMessage());
        assertNull("Null expected.", sessionNotPopulatedException.getCause());
    }

    @Test(expected = SessionNotPopulatedException.class)
    public void throwingSessionNotPopulatedExceptionTest() {
        throw new SessionNotPopulatedException();
    }
}