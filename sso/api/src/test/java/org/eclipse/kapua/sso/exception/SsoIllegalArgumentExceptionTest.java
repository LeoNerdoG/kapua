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
import org.eclipse.kapua.KapuaIllegalArgumentException;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class SsoIllegalArgumentExceptionTest extends Assert {
    String[] argumentName;
    String[] argumentValue;
    KapuaErrorCodes[] kapuaErrorCodes;

    @Before
    public void SetUp() {
        argumentName = new String[]{"Argument Name", null};
        argumentValue = new String[]{"Argument Value", null};

    }

    @Test
    public void ssoExceptionKapuaErrorCodeParameterTest() {
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
