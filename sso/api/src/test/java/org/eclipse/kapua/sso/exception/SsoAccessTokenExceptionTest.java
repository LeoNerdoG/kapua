/*******************************************************************************
 * Copyright (c) 2020 Red Hat Inc and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Red Hat Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.sso.exception;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class SsoAccessTokenExceptionTest extends Assert {

    @Test
    public void ssoAccessTokenExceptionThrowableTest() {
        Throwable throwable = new Throwable();
        SsoAccessTokenException ssoAccessTokenException = new SsoAccessTokenException(throwable);
        assertEquals("Expected and actual values should be the same!", SsoErrorCodes.ACCESS_TOKEN_ERROR, ssoAccessTokenException.getCode());
        assertEquals("Expected and actual values should be the same!", throwable, ssoAccessTokenException.getCause());
        assertEquals("Expected and actual values should be the same!", "An error occurred while getting the access token", ssoAccessTokenException.getMessage());
    }

    @Test
    public void ssoAccessTokenExceptionNullThrowableTest() {
        SsoAccessTokenException ssoAccessTokenException = new SsoAccessTokenException(null);
        assertEquals("Expected and actual values should be the same!", SsoErrorCodes.ACCESS_TOKEN_ERROR, ssoAccessTokenException.getCode());
        assertEquals("Expected and actual values should be the same!", null, ssoAccessTokenException.getCause());
        assertEquals("Expected and actual values should be the same!", "An error occurred while getting the access token", ssoAccessTokenException.getMessage());
    }
}
