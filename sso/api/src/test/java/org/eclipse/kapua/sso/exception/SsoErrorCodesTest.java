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
package org.eclipse.kapua.sso.exception;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class SsoErrorCodesTest extends Assert {

    @Test
    public void ssoErrorCodesEnumTest() {
        assertEquals("Expected and actual values should be the same!", "LOGIN_URI_ERROR", SsoErrorCodes.LOGIN_URI_ERROR.name());
        assertEquals("Expected and actual values should be the same!", "LOGOUT_URI_ERROR", SsoErrorCodes.LOGOUT_URI_ERROR.name());
        assertEquals("Expected and actual values should be the same!", "ACCESS_TOKEN_ERROR", SsoErrorCodes.ACCESS_TOKEN_ERROR.name());
        assertEquals("Expected and actual values should be the same!", "JWT_EXTRACTION_ERROR", SsoErrorCodes.JWT_EXTRACTION_ERROR.name());
        assertEquals("Expected and actual values should be the same!", "JWT_PROCESS_ERROR", SsoErrorCodes.JWT_PROCESS_ERROR.name());
        assertEquals("Expected and actual values should be the same!", "JWT_URI_ERROR", SsoErrorCodes.JWT_URI_ERROR.name());
        assertEquals("Expected and actual values should be the same!", "ILLEGAL_ARGUMENT", SsoErrorCodes.ILLEGAL_ARGUMENT.name());
        assertEquals("Expected and actual values should be the same!", "ILLEGAL_URI", SsoErrorCodes.ILLEGAL_URI.name());
    }
}
