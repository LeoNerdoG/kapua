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
package org.eclipse.kapua.sso.provider.setting;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class SsoSettingKeysTest extends Assert {

    @Test
    public void ssoProviderTest() {
        assertEquals("Expected and actual values should be the same!", "sso.provider", SsoSettingKeys.SSO_PROVIDER.key());
    }

    @Test
    public void ssoOpenIdConfPathTest() {
        assertEquals("Expected and actual values should be the same!", "sso.openid.conf.path", SsoSettingKeys.SSO_OPENID_CONF_PATH.key());
    }

    @Test
    public void ssoOpenIdClientIdTest() {
        assertEquals("Expected and actual values should be the same!", "sso.openid.client.id", SsoSettingKeys.SSO_OPENID_CLIENT_ID.key());
    }

    @Test
    public void ssoOpenIdClientSecretTest() {
        assertEquals("Expected and actual values should be the same!", "sso.openid.client.secret", SsoSettingKeys.SSO_OPENID_CLIENT_SECRET.key());
    }

    @Test
    public void ssoOpenIdJwtProcessorTimeoutTest() {
        assertEquals("Expected and actual values should be the same!", "sso.openid.jwt_processor_timeout", SsoSettingKeys.SSO_OPENID_JWT_PROCESSOR_TIMEOUT.key());
    }
}
