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
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;


@Category(JUnitTests.class)
public class SsoIllegalUriExceptionTest extends Assert {
    String[] argumentName;
    String[] argumentValue;
    SsoErrorCodes[] ssoErrorCodes;

    @Before
    public void setUp() {
        argumentName = new String[]{null, "Argument Name"};
        argumentValue = new String[]{null, "Argument Value"};
        ssoErrorCodes = new SsoErrorCodes[]{SsoErrorCodes.ACCESS_TOKEN_ERROR, SsoErrorCodes.ILLEGAL_ARGUMENT, SsoErrorCodes.ILLEGAL_URI, SsoErrorCodes.JWT_EXTRACTION_ERROR,
                SsoErrorCodes.JWT_PROCESS_ERROR, SsoErrorCodes.JWT_URI_ERROR, SsoErrorCodes.LOGIN_URI_ERROR, SsoErrorCodes.LOGOUT_URI_ERROR};
    }

    @Test
    public void ssoIllegalUriExceptionStringParameterTest() {
        for (String name : argumentName) {
            for (String value : argumentValue) {
                SsoIllegalUriException ssoIllegalUriException = new SsoIllegalUriException(name, value);
                assertEquals("Expected and actual values should be the same!", SsoErrorCodes.ILLEGAL_URI, ssoIllegalUriException.getCode());
                assertEquals("Expected and actual values should be the same!", name, ssoIllegalUriException.getArgumentName());
                assertEquals("Expected and actual values should be the same!", value, ssoIllegalUriException.getArgumentValue());
                assertEquals("An illegal value was provided for the URI " + name + ": " + value + ".", ssoIllegalUriException.getMessage());
                assertNull(ssoIllegalUriException.getCause());
            }
        }
    }

    @Test(expected = SsoIllegalUriException.class)
    public void throwingExceptionStringParametersTest() throws SsoIllegalUriException {
        for (String name : argumentName) {
            for (String value : argumentValue) {
                throw new SsoIllegalUriException(name, value);
            }
        }
    }

    @Test(expected = SsoIllegalUriException.class)
    public void throwingExceptionKapuaErrorCodesStringParametersTest() throws SsoIllegalUriException {
        for (String name : argumentName) {
            for (String value : argumentValue) {
                for (SsoErrorCodes ssoErrorCode : ssoErrorCodes) {
                    throw new SsoIllegalUriException(ssoErrorCode, name, value);
                }
            }
        }
    }

    @Test(expected = SsoIllegalUriException.class)
    public void throwingExceptionNullKapuaErrorCodesStringParametersTest() throws SsoIllegalUriException {
        for (String name : argumentName) {
            for (String value : argumentValue) {
                throw new SsoIllegalUriException(null, name, value);
            }
        }
    }
}
