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
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class SsoErrorCodesTest extends Assert {

    @Test
    public void ssoErrorCodesEnumTest() {
        assertNotNull(SsoErrorCodes.valueOf("LOGIN_URI_ERROR"));
        assertNotNull(SsoErrorCodes.valueOf("LOGOUT_URI_ERROR"));
        assertNotNull(SsoErrorCodes.valueOf("ACCESS_TOKEN_ERROR"));
        assertNotNull(SsoErrorCodes.valueOf("JWT_EXTRACTION_ERROR"));
        assertNotNull(SsoErrorCodes.valueOf("JWT_PROCESS_ERROR"));
        assertNotNull(SsoErrorCodes.valueOf("JWT_URI_ERROR"));
        assertNotNull(SsoErrorCodes.valueOf("ILLEGAL_ARGUMENT"));
        assertNotNull(SsoErrorCodes.valueOf("ILLEGAL_URI"));
    }
}
