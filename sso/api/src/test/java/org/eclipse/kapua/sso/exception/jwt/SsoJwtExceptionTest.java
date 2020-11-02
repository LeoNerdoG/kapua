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

import org.eclipse.kapua.KapuaErrorCode;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.sso.exception.SsoErrorCodes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class SsoJwtExceptionTest extends Assert {
    Throwable[] throwables;
    String argument1, argument2, argument3;
    KapuaErrorCode[] kapuaErrorCodes;
    String kapuaErrorMessage;
    String[] messages = new String[]{"An error occurred while retrieving the SSO login URI", "An error occurred while retrieving the SSO logout URI", "An error occurred while getting the access token",
            "An error occurred while extracting the Jwt from the string: {0}", "An error occurred while processing the Jwt: {0}", "An error occurred while retrieving the SSO Jwt URI",
            "An illegal value was provided for the argument {0}: {1}.", "An illegal value was provided for the URI {0}: {1}."};

    private class ActualSsoJwtException extends SsoJwtException {

        public ActualSsoJwtException(KapuaErrorCode code) {
            super(code);
        }

        public ActualSsoJwtException(KapuaErrorCode code, Throwable cause, Object... arguments) {
            super(code, cause, arguments);
        }
    }

    @Before
    public void setUp() {
        throwables = new Throwable[]{null, new Throwable()};
        argument1 = "arg1";
        argument2 = "arg2";
        argument3 = "arg3";
        kapuaErrorMessage = "sso-error-messages";
        kapuaErrorCodes = new SsoErrorCodes[]{SsoErrorCodes.LOGIN_URI_ERROR, SsoErrorCodes.LOGOUT_URI_ERROR, SsoErrorCodes.ACCESS_TOKEN_ERROR, SsoErrorCodes.JWT_EXTRACTION_ERROR,
                SsoErrorCodes.JWT_PROCESS_ERROR, SsoErrorCodes.JWT_URI_ERROR, SsoErrorCodes.ILLEGAL_ARGUMENT, SsoErrorCodes.ILLEGAL_URI};
    }

    @Test
    public void ssoJwtExceptionKapuaErrorCodeParameterTest() {
        for (int i = 0; i < kapuaErrorCodes.length; i++) {
            SsoJwtException ssoJwtException = new ActualSsoJwtException(kapuaErrorCodes[i]);
            assertNull("Null expected.", ssoJwtException.getCause());
            assertEquals("Expected and actual values should be the same.", kapuaErrorCodes[i], ssoJwtException.getCode());
            assertEquals("Expected and actual values should be the same.", messages[i], ssoJwtException.getMessage());
            assertEquals("Expected and actual values should be the same.", messages[i], ssoJwtException.getLocalizedMessage());
        }
    }

    @Test
    public void ssoExceptionNullKapuaErrorCodeParameterTest() {
        SsoJwtException ssoJwtException = new ActualSsoJwtException(null);
        assertNull("Null expected.", ssoJwtException.getCode());
        assertNull("Null expected.", ssoJwtException.getCause());
        try {
            ssoJwtException.getMessage();
            fail("Null pointer exception expected.");
        } catch (Exception e) {
            assertEquals("Null pointer exception expected.", new NullPointerException().toString(), e.toString());
        }
        try {
            ssoJwtException.getLocalizedMessage();
            fail("Null pointer exception expected.");
        } catch (Exception e) {
            assertEquals("Null pointer exception expected.", new NullPointerException().toString(), e.toString());
        }
    }

    @Test
    public void ssoJwtExtractionExceptionKapuaErrorCodeThrowableObjectParametersTest() {
        for (Throwable throwable : throwables) {
            SsoJwtException ssoJwtExtractionException = new ActualSsoJwtException(SsoErrorCodes.JWT_EXTRACTION_ERROR, throwable, argument1, argument2);

            assertEquals("Expected and actual values should be the same.", SsoErrorCodes.JWT_EXTRACTION_ERROR, ssoJwtExtractionException.getCode());
            assertEquals("Expected and actual values should be the same.", throwable, ssoJwtExtractionException.getCause());
            assertEquals("Expected and actual values should be the same.", "An error occurred while extracting the Jwt from the string: " + argument1, ssoJwtExtractionException.getMessage());
            assertEquals("Expected and actual values should be the same.", "An error occurred while extracting the Jwt from the string: " + argument1, ssoJwtExtractionException.getLocalizedMessage());
        }
    }

    @Test
    public void ssoJwtExtractionExceptionKapuaErrorCodeThrowableNullObjectParametersTest() {
        for (Throwable throwable : throwables) {
            SsoJwtException ssoJwtExtractionException = new ActualSsoJwtException(SsoErrorCodes.JWT_EXTRACTION_ERROR, throwable, null);

            assertEquals("Expected and actual values should be the same.", SsoErrorCodes.JWT_EXTRACTION_ERROR, ssoJwtExtractionException.getCode());
            assertEquals("Expected and actual values should be the same.", throwable, ssoJwtExtractionException.getCause());
            assertEquals("Expected and actual values should be the same.", "An error occurred while extracting the Jwt from the string: {0}", ssoJwtExtractionException.getMessage());
            assertEquals("Expected and actual values should be the same.", "An error occurred while extracting the Jwt from the string: {0}", ssoJwtExtractionException.getLocalizedMessage());
        }
    }

    @Test
    public void ssoJwtExceptionNullKapuaErrorCodeThrowableObjectParametersTest() {
        for (Throwable throwable : throwables) {
            SsoJwtException ssoException = new ActualSsoJwtException(null, throwable, argument1, argument2, argument3);
            assertEquals("Expected and actual values should be the same.", throwable, ssoException.getCause());
            assertNull("Null expected.", ssoException.getCode());
            try {
                ssoException.getMessage();
                fail("Null pointer exception expected.");
            } catch (Exception e) {
                assertEquals(new NullPointerException().toString(), e.toString());
            }
            try {
                ssoException.getLocalizedMessage();
                fail("Null pointer exception expected.");
            } catch (Exception e) {
                assertEquals(new NullPointerException().toString(), e.toString());
            }
        }
    }

    @Test(expected = SsoJwtException.class)
    public void throwingExceptionKapuaErrorCodeParameterTest() throws SsoJwtException {
        throw new ActualSsoJwtException(SsoErrorCodes.ILLEGAL_ARGUMENT);
    }

    @Test(expected = SsoJwtException.class)
    public void throwingExceptionNullKapuaErrorCodeParameterTest() throws SsoJwtException {
        throw new ActualSsoJwtException(null);
    }

    @Test(expected = SsoJwtException.class)
    public void throwingExceptionKapuaErrorCodeObjectThrowableParameterTest() throws SsoJwtException {
        for (Throwable throwable : throwables) {
            throw new ActualSsoJwtException(SsoErrorCodes.ILLEGAL_ARGUMENT, throwable, argument1, argument2, argument3);
        }
    }

    @Test(expected = SsoJwtException.class)
    public void throwingExceptionNullKapuaErrorCodeObjectThrowableParameterTest() throws SsoJwtException {
        for (Throwable throwable : throwables) {
            throw new ActualSsoJwtException(null, throwable, argument1, argument2, argument3);
        }
    }

    @Test(expected = SsoJwtException.class)
    public void throwingExceptionKapuaErrorCodeNullObjectThrowableParameterTest() throws SsoJwtException {
        for (Throwable throwable : throwables) {
            throw new ActualSsoJwtException(SsoErrorCodes.ILLEGAL_ARGUMENT, throwable, null);
        }
    }
}
