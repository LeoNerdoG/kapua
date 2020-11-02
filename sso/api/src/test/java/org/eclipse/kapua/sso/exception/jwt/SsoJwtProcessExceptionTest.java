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
package org.eclipse.kapua.sso.exception.jwt;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.sso.exception.SsoErrorCodes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class SsoJwtProcessExceptionTest extends Assert {
    Throwable[] throwables;
    String argument1, argument2, argument3;

    @Before
    public void setUp() {
        throwables = new Throwable[]{null, new Throwable()};
        argument1 = "arg1";
        argument2 = "arg2";
        argument3 = "arg3";
    }

    @Test
    public void ssoJwtProcessExceptionThrowableObjectParametersTest() {
        for (Throwable throwable : throwables) {
            SsoJwtProcessException ssoJwtProcessException = new SsoJwtProcessException(throwable, argument1, argument2);

            assertEquals("Expected and actual values should be the same.", SsoErrorCodes.JWT_PROCESS_ERROR, ssoJwtProcessException.getCode());
            assertEquals("Expected and actual values should be the same.", throwable, ssoJwtProcessException.getCause());
            assertEquals("Expected and actual values should be the same.", "An error occurred while processing the Jwt: " + argument1, ssoJwtProcessException.getMessage());
            assertEquals("Expected and actual values should be the same.", "An error occurred while processing the Jwt: " + argument1, ssoJwtProcessException.getLocalizedMessage());
        }
    }

    @Test
    public void ssoJwtProcessExceptionThrowableNullObjectParametersTest() {
        for (Throwable throwable : throwables) {
            SsoJwtProcessException ssoJwtProcessException = new SsoJwtProcessException(throwable, null);

            assertEquals("Expected and actual values should be the same.", SsoErrorCodes.JWT_PROCESS_ERROR, ssoJwtProcessException.getCode());
            assertEquals("Expected and actual values should be the same.", throwable, ssoJwtProcessException.getCause());
            assertEquals("Expected and actual values should be the same.", "An error occurred while processing the Jwt: {0}", ssoJwtProcessException.getMessage());
            assertEquals("Expected and actual values should be the same.", "An error occurred while processing the Jwt: {0}", ssoJwtProcessException.getLocalizedMessage());
        }
    }
}
