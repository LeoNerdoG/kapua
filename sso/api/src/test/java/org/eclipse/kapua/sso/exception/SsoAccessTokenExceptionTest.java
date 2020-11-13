package org.eclipse.kapua.sso.exception;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class SsoAccessTokenExceptionTest extends Assert{

    @Before
    public void setUp() {

    }

    @Test
    public void ssoAccessTokenExceptionThrowableTest() {
        Throwable throwable = new Throwable();
        SsoAccessTokenException ssoAccessTokenException = new SsoAccessTokenException(throwable);
        assertEquals("Expected and actual values should be the same!", SsoErrorCodes.ACCESS_TOKEN_ERROR, ssoAccessTokenException.getCode());
        assertEquals("Expected and actual values should be the same!", throwable, ssoAccessTokenException.getCause());
        assertEquals("Expected and actual values should be the same!", "An error occurred while getting the access token", ssoAccessTokenException.getMessage());
    }

    @Test
    public void ssoAccessTokenExceptionNullThrowableTest() {
        SsoAccessTokenException ssoAccessTokenException = new SsoAccessTokenException(null);
        assertEquals("Expected and actual values should be the same!", SsoErrorCodes.ACCESS_TOKEN_ERROR, ssoAccessTokenException.getCode());
        assertEquals("Expected and actual values should be the same!", null, ssoAccessTokenException.getCause());
        assertEquals("Expected and actual values should be the same!", "An error occurred while getting the access token", ssoAccessTokenException.getMessage());
    }
}
