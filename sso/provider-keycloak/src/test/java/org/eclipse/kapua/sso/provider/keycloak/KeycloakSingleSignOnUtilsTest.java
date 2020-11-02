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
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KeycloakSingleSignOnUtilsTest extends Assert {

    @Test
    public void getRealmTest() throws SsoIllegalArgumentException {
        try {
            KeycloakSingleSignOnUtils.getRealm();
        } catch (SsoIllegalArgumentException iae) {
            assertEquals("Expected and actual values should be the same", "An illegal value was provided for the argument sso.keycloak.realm: null.", iae.getMessage());
        }
        System.setProperty("sso.keycloak.realm", "sso.keycloak.realm");
        assertEquals("sso.keycloak.realm", KeycloakSingleSignOnUtils.getRealm());
    }

    @Test
    public void getProviderUriTest() throws SsoIllegalArgumentException {
        System.setProperty("sso.keycloak.uri", "sso.keycloak.uri");
        assertEquals("sso.keycloak.uri", KeycloakSingleSignOnUtils.getProviderUri());
    }

    @Test(expected = SsoIllegalArgumentException.class)
    public void getProviderUriNullTest() throws SsoIllegalArgumentException {
        System.clearProperty("sso.keycloak.uri");
        KeycloakSingleSignOnUtils.getProviderUri();
    }
}
