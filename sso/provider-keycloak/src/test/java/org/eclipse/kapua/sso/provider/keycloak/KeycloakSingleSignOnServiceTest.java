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
package org.eclipse.kapua.sso.provider.keycloak;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.sso.exception.SsoIllegalArgumentException;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KeycloakSingleSignOnServiceTest extends Assert {
    KeycloakSingleSignOnService keycloakSingleSignOnService;

    @Before
    public void setUp() {
        keycloakSingleSignOnService = new KeycloakSingleSignOnService();
    }

    @Test
    public void constructorTest() {
        assertThat(keycloakSingleSignOnService, IsInstanceOf.instanceOf(KeycloakSingleSignOnService.class));
    }

    @Test
    public void getAuthUriTest() throws SsoIllegalArgumentException {
        System.setProperty("sso.keycloak.uri", "sso.keycloak.uri");
        System.setProperty("sso.keycloak.realm", "sso.keycloak.realm");
        assertEquals("sso.keycloak.uri" + "/auth/realms/" + "sso.keycloak.realm" + "/protocol/openid-connect/auth", keycloakSingleSignOnService.getAuthUri());
    }

    @Test(expected = SsoIllegalArgumentException.class)
    public void getAuthUriNullConfigurationTest() throws SsoIllegalArgumentException {
        keycloakSingleSignOnService.getAuthUri();
    }

    @Test
    public void getTokenUriTest() throws SsoIllegalArgumentException {
        System.setProperty("sso.keycloak.uri", "sso.keycloak.uri");
        System.setProperty("sso.keycloak.realm", "sso.keycloak.realm");
        assertEquals("sso.keycloak.uri" + "/auth/realms/" + "sso.keycloak.realm" + "/protocol/openid-connect/token", keycloakSingleSignOnService.getTokenUri());
    }

    @Test(expected = SsoIllegalArgumentException.class)
    public void getTokenUriNullConfigurationTest() throws SsoIllegalArgumentException {
        System.clearProperty("sso.keycloak.uri");
        System.clearProperty("sso.keycloak.realm");
        keycloakSingleSignOnService.getTokenUri();
    }

    @Test
    public void getLogoutUriTest() throws SsoIllegalArgumentException {
        System.setProperty("sso.keycloak.uri", "foobar.com");
        System.setProperty("sso.keycloak.realm", "my-realm");
        assertEquals("foobar.com/auth/realms/my-realm/protocol/openid-connect/logout", keycloakSingleSignOnService.getLogoutUri());

    }

    @Test(expected = SsoIllegalArgumentException.class)
    public void getLogoutUriNullConfigurationTest() throws SsoIllegalArgumentException {
        System.clearProperty("sso.keycloak.uri");
        System.clearProperty("sso.keycloak.realm");
        System.out.println(keycloakSingleSignOnService.getLogoutUri());
    }
}
