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
package org.eclipse.kapua.sso.exception.uri;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.sso.exception.SsoErrorCodes;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class SsoJwtUriExceptionTest extends Assert {

    @Test
    public void ssoJwtUriExceptionThrowableTest() {
        Throwable throwable = new Throwable();
        SsoJwtUriException ssoJwtUriException = new SsoJwtUriException(throwable);
        assertEquals("Expected and actual values should be the same!", SsoErrorCodes.JWT_URI_ERROR, ssoJwtUriException.getCode());
        assertEquals("Expected and actual values should be the same!", throwable, ssoJwtUriException.getCause());
        assertEquals("Expected and actual values should be the same!", "An error occurred while retrieving the SSO Jwt URI", ssoJwtUriException.getMessage());
    }

    @Test
    public void ssoJwtUriExceptionNullThrowableTest() {
        SsoJwtUriException ssoJwtUriException = new SsoJwtUriException(null);
        assertEquals("Expected and actual values should be the same!", SsoErrorCodes.JWT_URI_ERROR, ssoJwtUriException.getCode());
        assertEquals("Expected and actual values should be the same!", null, ssoJwtUriException.getCause());
        assertEquals("Expected and actual values should be the same!", "An error occurred while retrieving the SSO Jwt URI", ssoJwtUriException.getMessage());
    }
}
