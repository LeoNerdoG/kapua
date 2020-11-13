/*******************************************************************************
 * Copyright (c) 2017, 2020 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.sso.exception;

import org.eclipse.kapua.KapuaErrorCode;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class SsoExceptionTest extends Assert {

    String kapuaErrorMessages;
    KapuaErrorCode[] kapuaErrorCodes;
    Object argument1, argument2, argument3;
    String[] messages = new String[]{"An error occurred while retrieving the SSO login URI", "An error occurred while retrieving the SSO logout URI", "An error occurred while getting the access token",
            "An error occurred while extracting the Jwt from the string: {0}", "An error occurred while processing the Jwt: {0}", "An error occurred while retrieving the SSO Jwt URI",
            "An illegal value was provided for the argument {0}: {1}.", "An illegal value was provided for the URI {0}: {1}."};
    Throwable[] throwables;

    private class ActualSsoException extends SsoException {

        private static final String KAPUA_ERROR_MESSAGES = "sso-error-messages";

        public ActualSsoException(KapuaErrorCode code) {
            super(code);
        }

        public ActualSsoException(KapuaErrorCode code, Object... arguments) {
            super(code, arguments);
        }

        public ActualSsoException(KapuaErrorCode code, Throwable cause, Object... arguments) {
            super(code, cause, arguments);
        }

        @Override
        protected String getKapuaErrorMessagesBundle() {
            return KAPUA_ERROR_MESSAGES;
        }
    }

    @Before
    public void setUp() {
        kapuaErrorMessages = "sso-error-messages";
        kapuaErrorCodes = new SsoErrorCodes[]{SsoErrorCodes.LOGIN_URI_ERROR, SsoErrorCodes.LOGOUT_URI_ERROR, SsoErrorCodes.ACCESS_TOKEN_ERROR, SsoErrorCodes.JWT_EXTRACTION_ERROR,
                SsoErrorCodes.JWT_PROCESS_ERROR, SsoErrorCodes.JWT_URI_ERROR, SsoErrorCodes.ILLEGAL_ARGUMENT, SsoErrorCodes.ILLEGAL_URI};
        argument1 = "arg1";
        argument2 = "arg2";
        argument3 = "arg3";
        throwables = new Throwable[]{new Throwable(), null};
    }

    @Test
    public void ssoExceptionKapuaErrorCodeParameterTest() {
        int i = 0;
        for (KapuaErrorCode kapuaErrorCode : kapuaErrorCodes) {
            SsoException ssoException = new ActualSsoException(kapuaErrorCode);
            assertNull("Null expected.", ssoException.getCause());
            assertEquals("Expected and actual values should be the same.", kapuaErrorCode, ssoException.getCode());
            assertEquals("Expected and actual values should be the same.", kapuaErrorMessages, ssoException.getKapuaErrorMessagesBundle());
            assertEquals("Expected and actual values should be the same.", messages[i], ssoException.getMessage());
            assertEquals("Expected and actual values should be the same.", messages[i], ssoException.getLocalizedMessage());
            i++;
        }
    }

    @Test
    public void ssoExceptionNullKapuaErrorCodeParameterTest() {
        SsoException ssoException = new ActualSsoException(null);
        assertNull("Null expected.", ssoException.getCode());
        assertNull("Null expected.", ssoException.getCause());
        assertEquals("Expected and actual values should be the same.", kapuaErrorMessages, ssoException.getKapuaErrorMessagesBundle());
        try {
            ssoException.getMessage();
            fail("Null pointer expected.");
        } catch (Exception e) {
            assertEquals("Null pointer exception expected.", new NullPointerException().toString(), e.toString());
        }
        try {
            ssoException.getLocalizedMessage();
            fail("NullPointerException expected.");
        } catch (Exception e) {
            assertEquals("NullPointerException expected.", new NullPointerException().toString(), e.toString());
        }

    }

    @Test
    public void ssoExceptionKapuaErrorCodeObjectParametersTest() {
        String[] messages = new String[]{"An error occurred while retrieving the SSO login URI", "An error occurred while retrieving the SSO logout URI", "An error occurred while getting the access token",
                "An error occurred while extracting the Jwt from the string: " + argument1, "An error occurred while processing the Jwt: " + argument1, "An error occurred while retrieving the SSO Jwt URI",
                "An illegal value was provided for the argument " + argument1 + ": " + argument2 + ".", "An illegal value was provided for the URI " + argument1 + ": " + argument2 + "."};

        int i = 0;
        for (KapuaErrorCode kapuaErrorCode : kapuaErrorCodes) {
            SsoException ssoException = new ActualSsoException(kapuaErrorCode, argument1, argument2, argument3);

            assertNull("Null expected.", ssoException.getCause());
            assertEquals("Expected and actual values should be the same.", kapuaErrorCode, ssoException.getCode());
            assertEquals("Expected and actual values should be the same.", kapuaErrorMessages, ssoException.getKapuaErrorMessagesBundle());

            assertEquals("Expected and actual values should be the same.", messages[i], ssoException.getMessage());
            assertEquals("Expected and actual values should be the same.", messages[i], ssoException.getLocalizedMessage());
            i++;
        }

    }

    @Test
    public void ssoExceptionNullKapuaErrorCodeObjectParametersTest() {
        SsoException ssoException = new ActualSsoException(null, argument1, argument2, argument3);
        assertNull("Null expected.", ssoException.getCode());
        assertNull("Null expected.", ssoException.getCause());
        try {
            ssoException.getMessage();
            fail("Null pointer expected.");
        } catch (Exception e) {
            assertEquals(new NullPointerException().toString(), e.toString());
        }
        try {
            ssoException.getLocalizedMessage();
            fail("Null pointer expected.");
        } catch (Exception e) {
            assertEquals(new NullPointerException().toString(), e.toString());
        }

    }

    @Test
    public void ssoExceptionKapuaErrorCodeNullObjectParametersTest() {
//        SsoException ssoException = new ActualSsoException(kapuaErrorCode, argument1, argument2, argument3);

    }

    @Test
    public void ssoExceptionKapuaErrorCodeThrowableObjectParametersTest() {
        for (Throwable throwable : throwables) {
            SsoException ssoException = new ActualSsoException(SsoErrorCodes.ILLEGAL_ARGUMENT, throwable, argument1, argument2);

            assertEquals("Expected and actual values should be the same.", SsoErrorCodes.ILLEGAL_ARGUMENT, ssoException.getCode());
            assertEquals("Expected and actual values should be the same.", throwable, ssoException.getCause());
            assertEquals("Expected and actual values should be the same.", kapuaErrorMessages, ssoException.getKapuaErrorMessagesBundle());
            assertEquals("Expected and actual values should be the same.", "An illegal value was provided for the argument " + argument1 + ": " + argument2 + ".", ssoException.getMessage());
            assertEquals("Expected and actual values should be the same.", "An illegal value was provided for the argument " + argument1 + ": " + argument2 + ".", ssoException.getLocalizedMessage());

        }
    }

    @Test
    public void ssoExceptionNullKapuaErrorCodeThrowableObjectParametersTest() {
        SsoException ssoException = new ActualSsoException(null, argument1, argument2, argument3);

    }

    @Test
    public void ssoExceptionKapuaErrorCodeNullThrowableObjectParametersTest() {
//        SsoException ssoException = new ActualSsoException(kapuaErrorCode, argument1, argument2, argument3);

    }
}
