package org.eclipse.kapua.sso.provider.keycloak.setting;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.sso.provider.generic.setting.GenericSsoSettingKeys;
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
