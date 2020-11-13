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
package org.eclipse.kapua.sso.provider.generic.setting;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class GenericSsoSettingKeysTest extends Assert {

    @Test
    public void ssoOpenIdServerEndpointAuthTest() {
        assertNotNull("Not null expected.", GenericSsoSettingKeys.valueOf("SSO_OPENID_SERVER_ENDPOINT_AUTH"));
        assertEquals("Expected and actual values should be the same!", "sso.generic.openid.server.endpoint.auth", GenericSsoSettingKeys.SSO_OPENID_SERVER_ENDPOINT_AUTH.key());
    }

    @Test
    public void ssoOpenIdServerEndpointTokenTest() {
        assertNotNull("Not null expected.", GenericSsoSettingKeys.valueOf("SSO_OPENID_SERVER_ENDPOINT_TOKEN"));
        assertEquals("Expected and actual values should be the same!", "sso.generic.openid.server.endpoint.token", GenericSsoSettingKeys.SSO_OPENID_SERVER_ENDPOINT_TOKEN.key());
    }

    @Test
    public void ssoOpenIdServerEndpointLogoutTest() {
        assertNotNull("Not null expected.", GenericSsoSettingKeys.valueOf("SSO_OPENID_SERVER_ENDPOINT_LOGOUT"));
        assertEquals("Expected and actual values should be the same!", "sso.generic.openid.server.endpoint.logout", GenericSsoSettingKeys.SSO_OPENID_SERVER_ENDPOINT_LOGOUT.key());
    }

    @Test
    public void ssoOpenIdJwtAudienceAllowedTest() {
        assertNotNull("Not null expected.", GenericSsoSettingKeys.valueOf("SSO_OPENID_JWT_AUDIENCE_ALLOWED"));
        assertEquals("Expected and actual values should be the same!", "sso.generic.openid.jwt.audience.allowed", GenericSsoSettingKeys.SSO_OPENID_JWT_AUDIENCE_ALLOWED.key());
    }

    @Test
    public void ssoOpenIdJwtIssuerAllowedTest() {
        assertNotNull("Not null expected.", GenericSsoSettingKeys.valueOf("SSO_OPENID_JWT_ISSUER_ALLOWED"));
        assertEquals("Expected and actual values should be the same!", "sso.generic.openid.jwt.issuer.allowed", GenericSsoSettingKeys.SSO_OPENID_JWT_ISSUER_ALLOWED.key());
    }

//    UPORABI REFLECTION
//    @Test
//    public void ConstructorGenericSsoSettingKeysTest() {
//        GenericSsoSettingKeys genericSsoSettingKeys = new GenericSsoSettingKeys("sso.generic.openid.server.endpoint.auth");
//        assertEquals("sso.generic.openid.server.endpoint.auth", genericSsoSettingKeys.key());
//    }

}
