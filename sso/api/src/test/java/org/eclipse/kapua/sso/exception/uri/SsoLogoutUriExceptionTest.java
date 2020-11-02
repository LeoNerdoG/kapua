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
public class SsoLogoutUriExceptionTest extends Assert {

    @Test
    public void ssoLogoutUriExceptionThrowableTest() {
        Throwable throwable = new Throwable();
        SsoLogoutUriException ssoLogoutUriException = new SsoLogoutUriException(throwable);
        assertEquals("Expected and actual values should be the same!", SsoErrorCodes.LOGOUT_URI_ERROR, ssoLogoutUriException.getCode());
        assertEquals("Expected and actual values should be the same!", throwable, ssoLogoutUriException.getCause());
        assertEquals("Expected and actual values should be the same!", "An error occurred while retrieving the SSO logout URI", ssoLogoutUriException.getMessage());
    }

    @Test
    public void ssoLogoutUriExceptionNullThrowableTest() {
        SsoLogoutUriException ssoLogoutUriException = new SsoLogoutUriException(null);
        assertEquals("Expected and actual values should be the same!", SsoErrorCodes.LOGOUT_URI_ERROR, ssoLogoutUriException.getCode());
        assertEquals("Expected and actual values should be the same!", null, ssoLogoutUriException.getCause());
        assertEquals("Expected and actual values should be the same!", "An error occurred while retrieving the SSO logout URI", ssoLogoutUriException.getMessage());
    }
}
