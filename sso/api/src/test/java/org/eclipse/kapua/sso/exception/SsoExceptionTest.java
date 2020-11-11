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
import org.eclipse.kapua.KapuaErrorCodes;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class SsoExceptionTest extends Assert {

    String expectedErrorMessage;
    KapuaErrorCode[] kapuaErrorCodes;
    Object argument1, argument2, argument3;

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
    public void SetUp() {
        expectedErrorMessage = "sso-error-messages";
        kapuaErrorCodes = new KapuaErrorCodes[]{KapuaErrorCodes.ILLEGAL_ARGUMENT, KapuaErrorCodes.ENTITY_NOT_FOUND};
        argument1 = "arg1";
        argument2 = 2;
        argument3 = "arg3";
    }

    @Test
    public void ssoExceptionKapuaErrorCodeParameterTest() {
        for (KapuaErrorCode kapuaErrorCode : kapuaErrorCodes) {
            SsoException ssoException = new ActualSsoException(kapuaErrorCode);
            //Izpisuje "Error: "
            assertEquals("An illegal value was provided for the argument {0}: {1}.", ssoException.getMessage());
            assertEquals(kapuaErrorCode, ssoException.getCode());
            assertNull("Null expected.", ssoException.getCause());
            assertEquals(expectedErrorMessage, ssoException.getKapuaErrorMessagesBundle());
        }
    }

    @Test
    public void ssoExceptionNullKapuaErrorCodeParameterTest() {
        SsoException ssoException = new ActualSsoException(null);
        assertNull("Null expected.", ssoException.getCode());
        assertNull("Null expected.", ssoException.getCause());
        assertEquals("Expected and actual values should be the same.", expectedErrorMessage, ssoException.getKapuaErrorMessagesBundle());
        try {
            ssoException.getMessage();
            fail("Null pointer expected");
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
//        SsoException ssoException = new ActualSsoException(kapuaErrorCode, argument1, argument2, argument3);

    }

    @Test
    public void ssoExceptionNullKapuaErrorCodeObjectParametersTest() {
        SsoException ssoException = new ActualSsoException(null, argument1, argument2, argument3);
        assertNull("Null expected.", ssoException.getCode());
        assertNull("Null expected.", ssoException.getCause());

    }

    @Test
    public void ssoExceptionKapuaErrorCodeNullObjectParametersTest() {
//        SsoException ssoException = new ActualSsoException(kapuaErrorCode, argument1, argument2, argument3);

    }

    @Test
    public void ssoExceptionKapuaErrorCodeThrowableObjectParametersTest() {
//        SsoException ssoException = new ActualSsoException(kapuaErrorCode, argument1, argument2, argument3);

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
