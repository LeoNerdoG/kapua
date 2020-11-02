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
package org.eclipse.kapua.sso.provider.keycloak.setting;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KeycloakSsoSettingKeysTest extends Assert {

    @Test
    public void keycloakUriTest() {
        assertEquals("Expected and actual values should be the same!", "sso.keycloak.uri", KeycloakSsoSettingKeys.KEYCLOAK_URI.key());
    }

    @Test
    public void keycloakRealmTest() {
        assertEquals("Expected and actual values should be the same!", "sso.keycloak.realm", KeycloakSsoSettingKeys.KEYCLOAK_REALM.key());
    }
}
