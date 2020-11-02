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
public class SsoLoginUriExceptionTest extends Assert {

    @Test
    public void ssoLoginUriExceptionThrowableTest() {
        Throwable throwable = new Throwable();
        SsoLoginUriException ssoLoginUriException = new SsoLoginUriException(throwable);
        assertEquals("Expected and actual values should be the same!", SsoErrorCodes.LOGIN_URI_ERROR, ssoLoginUriException.getCode());
        assertEquals("Expected and actual values should be the same!", throwable, ssoLoginUriException.getCause());
        assertEquals("Expected and actual values should be the same!", "An error occurred while retrieving the SSO login URI", ssoLoginUriException.getMessage());
    }

    @Test
    public void ssoLoginUriExceptionNullThrowableTest() {
        SsoLoginUriException ssoLoginUriException = new SsoLoginUriException(null);
        assertEquals("Expected and actual values should be the same!", SsoErrorCodes.LOGIN_URI_ERROR, ssoLoginUriException.getCode());
        assertEquals("Expected and actual values should be the same!", null, ssoLoginUriException.getCause());
        assertEquals("Expected and actual values should be the same!", "An error occurred while retrieving the SSO login URI", ssoLoginUriException.getMessage());
    }
}
