package org.eclipse.kapua.sso.provider.generic.setting;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class GenericSsoSettingKeysTest extends Assert {

    @Test
    public void ssoOpenIdServerEndpointAuthTest() {
        assertNotNull(GenericSsoSettingKeys.valueOf("SSO_OPENID_SERVER_ENDPOINT_AUTH"));
        assertEquals("Expected and actual values should be the same!", "sso.generic.openid.server.endpoint.auth", GenericSsoSettingKeys.SSO_OPENID_SERVER_ENDPOINT_AUTH.key());
    }

    @Test
    public void ssoOpenIdServerEndpointTokenTest() {
        assertNotNull(GenericSsoSettingKeys.valueOf("SSO_OPENID_SERVER_ENDPOINT_TOKEN"));
        assertEquals("Expected and actual values should be the same!", "sso.generic.openid.server.endpoint.token", GenericSsoSettingKeys.SSO_OPENID_SERVER_ENDPOINT_TOKEN.key());
    }

    @Test
    public void ssoOpenIdServerEndpointLogoutTest() {
        assertNotNull(GenericSsoSettingKeys.valueOf("SSO_OPENID_SERVER_ENDPOINT_LOGOUT"));
        assertEquals("Expected and actual values should be the same!", "sso.generic.openid.server.endpoint.logout", GenericSsoSettingKeys.SSO_OPENID_SERVER_ENDPOINT_LOGOUT.key());
    }

    @Test
    public void ssoOpenIdJwtAudienceAllowedTest() {
        assertNotNull(GenericSsoSettingKeys.valueOf("SSO_OPENID_JWT_AUDIENCE_ALLOWED"));
        assertEquals("Expected and actual values should be the same!", "sso.generic.openid.jwt.audience.allowed", GenericSsoSettingKeys.SSO_OPENID_JWT_AUDIENCE_ALLOWED.key());
    }

    @Test
    public void ssoOpenIdJwtIssuerAllowedTest() {
        assertNotNull(GenericSsoSettingKeys.valueOf("SSO_OPENID_JWT_ISSUER_ALLOWED"));
        assertEquals("Expected and actual values should be the same!", "sso.generic.openid.jwt.issuer.allowed", GenericSsoSettingKeys.SSO_OPENID_JWT_ISSUER_ALLOWED.key());
    }

//    UPORABI REFLECTION
//    @Test
//    public void ConstructorGenericSsoSettingKeysTest() {
//        GenericSsoSettingKeys genericSsoSettingKeys = new GenericSsoSettingKeys("sso.generic.openid.server.endpoint.auth");
//        assertEquals("sso.generic.openid.server.endpoint.auth", genericSsoSettingKeys.key());
//    }

}
