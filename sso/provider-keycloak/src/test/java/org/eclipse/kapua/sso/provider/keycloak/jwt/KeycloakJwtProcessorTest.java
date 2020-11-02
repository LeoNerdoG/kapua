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
package org.eclipse.kapua.sso.provider.keycloak.jwt;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.sso.exception.SsoException;
import org.eclipse.kapua.sso.exception.SsoIllegalArgumentException;
import org.eclipse.kapua.sso.provider.setting.SsoSetting;
import org.eclipse.kapua.sso.provider.setting.SsoSettingKeys;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Category(JUnitTests.class)
public class KeycloakJwtProcessorTest extends Assert {
    SsoSetting ssoSetting;

    @Before
    public void setUp() {
//        System.setProperty("sso.openid.client.id", "console");
//        System.setProperty("sso.keycloak.uri", "sso.keycloak.uri");
//        System.setProperty("sso.keycloak.realm", "sso.keycloak.realm");
        ssoSetting = Mockito.mock(SsoSetting.class);
    }

    @Test
    public void constructorTest() throws SsoException {
        assertThat("An instance of KeycloakJwtProcessor expected!", new KeycloakJwtProcessor(), IsInstanceOf.instanceOf(KeycloakJwtProcessor.class));
    }

    @Test
    public void getJwtExpectedIssuersTest() throws SsoException {
        assertEquals("Expected and actual values should be the same!", Collections.singletonList("sso.keycloak.uri/auth/realms/sso.keycloak.realm"), new KeycloakJwtProcessor().getJwtExpectedIssuers());
    }

    @Test
    public void getJwtExpectedIssuersNullTest() throws SsoException {
//        System.clearProperty("sso.openid.client.id");
//        System.out.println(System.getProperty("sso.openid.client.id"));
//        System.clearProperty("sso.keycloak.uri");
//        System.out.println(System.getProperty("sso.keycloak.uri"));
//        System.clearProperty("sso.keycloak.realm");
//        System.out.println(System.getProperty("sso.keycloak.realm"));
        assertEquals("/auth/realms/", new KeycloakJwtProcessor().getJwtExpectedIssuers());
    }

    @Test
    public void getJwtAudiencesTest() throws SsoIllegalArgumentException, SsoException {
//        List<String> list = new LinkedList<>();
//        list.add("console");
        System.setProperty("sso.openid.client.id", "http://localhost:9090/auth/admin/realms/kapua/users/");
//        System.setProperty("sso.openid.client.id", "foobar");
        System.setProperty("sso.keycloak.uri", "http://localhost:9090");
        System.setProperty("sso.keycloak.realm", "kapua");
        KeycloakJwtProcessor keycloakJwtProcessor = new KeycloakJwtProcessor();
//        Mockito.when(ssoSetting.getList(String.class, SsoSettingKeys.SSO_OPENID_CLIENT_ID)).thenReturn(list);
//        assertEquals("Expected and actual values should be the same!", Collections.singletonList("console"), new KeycloakJwtProcessor().getJwtAudiences());
    }

    @Test
    public void getJwtAudiencesEmptyListTest() throws SsoException {
        try {
//            System.setProperty("sso.openid.client.id", "");
            Mockito.when(ssoSetting.getList(String.class, SsoSettingKeys.SSO_OPENID_CLIENT_ID)).thenReturn(new LinkedList<String>());
            new KeycloakJwtProcessor().getJwtAudiences();
        } catch (SsoIllegalArgumentException iae) {
            assertEquals("Expected and actual values should be the same!", "An illegal value was provided for the argument sso.openid.client.id: .", iae.getMessage());
        }
    }

    @Test
    public void getJwtAudiencesNullListTest() throws SsoException {
        try {
//            System.clearProperty("sso.openid.client.id");
            Mockito.doReturn(null).when(ssoSetting).getList(String.class, SsoSettingKeys.SSO_OPENID_CLIENT_ID);
            new KeycloakJwtProcessor().getJwtAudiences();
        } catch (SsoIllegalArgumentException iae) {
            assertEquals("Expected and actual values should be the same!", "An illegal value was provided for the argument sso.openid.client.id: null.", iae.getMessage());
        }
    }
}