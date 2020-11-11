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
