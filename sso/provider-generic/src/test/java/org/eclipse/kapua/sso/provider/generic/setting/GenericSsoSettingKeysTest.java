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
package org.eclipse.kapua.sso.provider.generic.setting;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class GenericSsoSettingKeysTest extends Assert {

    @Test
    public void ssoOpenIdServerEndpointAuthTest() {
        assertEquals("Expected and actual values should be the same!", "sso.generic.openid.server.endpoint.auth", GenericSsoSettingKeys.SSO_OPENID_SERVER_ENDPOINT_AUTH.key());
    }

    @Test
    public void ssoOpenIdServerEndpointTokenTest() {
        assertEquals("Expected and actual values should be the same!", "sso.generic.openid.server.endpoint.token", GenericSsoSettingKeys.SSO_OPENID_SERVER_ENDPOINT_TOKEN.key());
    }

    @Test
    public void ssoOpenIdServerEndpointLogoutTest() {
        assertEquals("Expected and actual values should be the same!", "sso.generic.openid.server.endpoint.logout", GenericSsoSettingKeys.SSO_OPENID_SERVER_ENDPOINT_LOGOUT.key());
    }

    @Test
    public void ssoOpenIdJwtAudienceAllowedTest() {
        assertEquals("Expected and actual values should be the same!", "sso.generic.openid.jwt.audience.allowed", GenericSsoSettingKeys.SSO_OPENID_JWT_AUDIENCE_ALLOWED.key());
    }

    @Test
    public void ssoOpenIdJwtIssuerAllowedTest() {
        assertEquals("Expected and actual values should be the same!", "sso.generic.openid.jwt.issuer.allowed", GenericSsoSettingKeys.SSO_OPENID_JWT_ISSUER_ALLOWED.key());
    }
}
