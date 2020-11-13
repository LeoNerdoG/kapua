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

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class SsoIllegalArgumentExceptionTest extends Assert {
    String[] argumentName;
    String[] argumentValue;
    SsoErrorCodes[] ssoErrorCodes;

    @Before
    public void setUp() {
        argumentName = new String[]{"Argument Name", null};
        argumentValue = new String[]{"Argument Value", null};
        ssoErrorCodes = new SsoErrorCodes[]{SsoErrorCodes.ACCESS_TOKEN_ERROR, SsoErrorCodes.ILLEGAL_ARGUMENT, SsoErrorCodes.ILLEGAL_URI, SsoErrorCodes.JWT_EXTRACTION_ERROR,
                SsoErrorCodes.JWT_PROCESS_ERROR, SsoErrorCodes.JWT_URI_ERROR, SsoErrorCodes.LOGIN_URI_ERROR, SsoErrorCodes.LOGOUT_URI_ERROR};

    }

    @Test
    public void ssoExceptionKapuaErrorCodeParameterTest() {
        for (String name : argumentName) {
            for (String value : argumentValue) {
                SsoIllegalArgumentException ssoIllegalArgumentException = new SsoIllegalArgumentException(name, value);
                assertEquals("Expected and actual values should be the same!", SsoErrorCodes.ILLEGAL_ARGUMENT, ssoIllegalArgumentException.getCode());
                assertEquals("Expected and actual values should be the same!", name, ssoIllegalArgumentException.getArgumentName());
                assertEquals("Expected and actual values should be the same!", value, ssoIllegalArgumentException.getArgumentValue());
                assertEquals("An illegal value was provided for the argument " + name + ": " + value + ".", ssoIllegalArgumentException.getMessage());
                assertNull(ssoIllegalArgumentException.getCause());
            }
        }
    }

    @Test(expected = SsoIllegalArgumentException.class)
    public void throwingExceptionStringParametersTest() throws SsoIllegalArgumentException {
        for (String name : argumentName) {
            for (String value : argumentValue) {
                throw new SsoIllegalArgumentException(name, value);
            }
        }
    }

    @Test(expected = SsoIllegalArgumentException.class)
    public void throwingExceptionKapuaErrorCodesStringParametersTest() throws SsoIllegalArgumentException {
        for (String name : argumentName) {
            for (String value : argumentValue) {
                for (SsoErrorCodes ssoErrorCode : ssoErrorCodes) {
                    throw new SsoIllegalArgumentException(ssoErrorCode, name, value);
                }
            }
        }
    }

    @Test(expected = SsoIllegalArgumentException.class)
    public void throwingExceptionNullKapuaErrorCodesStringParametersTest() throws SsoIllegalArgumentException {
        for (String name : argumentName) {
            for (String value : argumentValue) {
                throw new SsoIllegalArgumentException(null, name, value);
            }
        }
    }

}
