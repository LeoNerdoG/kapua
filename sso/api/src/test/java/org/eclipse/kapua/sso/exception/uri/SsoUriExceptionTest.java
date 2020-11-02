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

import org.eclipse.kapua.KapuaErrorCode;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.sso.exception.SsoErrorCodes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;


@Category(JUnitTests.class)
public class SsoUriExceptionTest extends Assert {
    Throwable[] throwables;
    String argument1, argument2, argument3;

    private class ActualSsoUriException extends SsoUriException {

        public ActualSsoUriException(KapuaErrorCode code, Throwable cause, Object... arguments) {
            super(code, cause, arguments);
        }
    }

    @Before
    public void setUp() {
        throwables = new Throwable[]{null, new Throwable()};
        argument1 = "arg1";
        argument2 = "arg2";
        argument3 = "arg3";
    }

    @Test
    public void ssoUriExceptionKapuaErrorCodeThrowableObjectParametersTest() {
        for (Throwable throwable : throwables) {
            SsoUriException ssoUriException = new ActualSsoUriException(SsoErrorCodes.ILLEGAL_ARGUMENT, throwable, argument1, argument2);

            assertEquals("Expected and actual values should be the same.", SsoErrorCodes.ILLEGAL_ARGUMENT, ssoUriException.getCode());
            assertEquals("Expected and actual values should be the same.", throwable, ssoUriException.getCause());
            assertEquals("Expected and actual values should be the same.", "An illegal value was provided for the argument " + argument1 + ": " + argument2 + ".", ssoUriException.getMessage());
            assertEquals("Expected and actual values should be the same.", "An illegal value was provided for the argument " + argument1 + ": " + argument2 + ".", ssoUriException.getLocalizedMessage());
        }
    }

    @Test
    public void ssoUriExceptionNullKapuaErrorCodeThrowableObjectParametersTest() {
        for (Throwable throwable : throwables) {
            SsoUriException ssoUriException = new ActualSsoUriException(null, throwable, argument1, argument2, argument3);
            assertEquals("Expected and actual values should be the same.", throwable, ssoUriException.getCause());
            assertNull("Null expected.", ssoUriException.getCode());
            try {
                ssoUriException.getMessage();
                fail("Null pointer exception expected.");
            } catch (Exception e) {
                assertEquals(new NullPointerException().toString(), e.toString());
            }
            try {
                ssoUriException.getLocalizedMessage();
                fail("Null pointer exception expected.");
            } catch (Exception e) {
                assertEquals(new NullPointerException().toString(), e.toString());
            }
        }
    }

    @Test
    public void ssoUriExceptionKapuaErrorCodeThrowableNullObjectParametersTest() {
        for (Throwable throwable : throwables) {
            SsoUriException ssoUriException = new ActualSsoUriException(SsoErrorCodes.ILLEGAL_ARGUMENT, throwable, null);

            assertEquals("Expected and actual values should be the same.", SsoErrorCodes.ILLEGAL_ARGUMENT, ssoUriException.getCode());
            assertEquals("Expected and actual values should be the same.", throwable, ssoUriException.getCause());
            assertEquals("Expected and actual values should be the same.", "An illegal value was provided for the argument {0}: {1}.", ssoUriException.getMessage());
            assertEquals("Expected and actual values should be the same.", "An illegal value was provided for the argument {0}: {1}.", ssoUriException.getLocalizedMessage());
        }
    }

    @Test
    public void ssoUriExceptionNullKapuaErrorCodeThrowableNullObjectParametersTest() {
        for (Throwable throwable : throwables) {
            SsoUriException ssoUriException = new ActualSsoUriException(null, throwable, null);
            assertEquals("Expected and actual values should be the same.", throwable, ssoUriException.getCause());
            assertNull("Null expected.", ssoUriException.getCode());
            try {
                ssoUriException.getMessage();
                fail("Null pointer exception expected.");
            } catch (Exception e) {
                assertEquals(new NullPointerException().toString(), e.toString());
            }
            try {
                ssoUriException.getLocalizedMessage();
                fail("Null pointer exception expected.");
            } catch (Exception e) {
                assertEquals("Null pointer exception expected.", new NullPointerException().toString(), e.toString());
            }
        }
    }

    @Test(expected = SsoUriException.class)
    public void throwingExceptionKapuaErrorCodeObjectThrowableParameterTest() throws SsoUriException {
        for (Throwable throwable : throwables) {
            throw new ActualSsoUriException(SsoErrorCodes.ILLEGAL_ARGUMENT, throwable, argument1, argument2, argument3);
        }
    }

    @Test(expected = SsoUriException.class)
    public void throwingExceptionNullKapuaErrorCodeObjectThrowableParameterTest() throws SsoUriException {
        for (Throwable throwable : throwables) {
            throw new ActualSsoUriException(null, throwable, argument1, argument2, argument3);
        }
    }

    @Test(expected = SsoUriException.class)
    public void throwingExceptionKapuaErrorCodeNullObjectThrowableParameterTest() throws SsoUriException {
        for (Throwable throwable : throwables) {
            throw new ActualSsoUriException(SsoErrorCodes.ILLEGAL_ARGUMENT, throwable, null);
        }
    }
}
