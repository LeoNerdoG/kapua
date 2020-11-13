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
package org.eclipse.kapua.sso.provider.keycloak.setting;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KeycloakSsoSettingKeysTest extends Assert {

    @Test
    public void keycloakUriTest() {
        assertNotNull(KeycloakSsoSettingKeys.valueOf("KEYCLOAK_URI"));
        assertEquals("Expected and actual values should be the same!", "sso.keycloak.uri", KeycloakSsoSettingKeys.KEYCLOAK_URI.key());
    }

    @Test
    public void keycloakRealmTest() {
        assertNotNull(KeycloakSsoSettingKeys.valueOf("KEYCLOAK_REALM"));
        assertEquals("Expected and actual values should be the same!", "sso.keycloak.realm", KeycloakSsoSettingKeys.KEYCLOAK_REALM.key());
    }
//    UPORABI REFLECTIONZA CONSTRUCTOR
}
