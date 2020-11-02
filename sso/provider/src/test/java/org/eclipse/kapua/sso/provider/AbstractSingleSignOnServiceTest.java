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
package org.eclipse.kapua.sso.provider;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.sso.exception.SsoAccessTokenException;
import org.eclipse.kapua.sso.exception.SsoException;
import org.eclipse.kapua.sso.exception.uri.SsoLoginUriException;
import org.eclipse.kapua.sso.exception.uri.SsoLogoutUriException;
import org.eclipse.kapua.sso.provider.setting.SsoSetting;
import org.eclipse.kapua.sso.provider.setting.SsoSettingKeys;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.net.URI;
import java.net.URISyntaxException;

@Category(JUnitTests.class)
public class AbstractSingleSignOnServiceTest extends Assert {

    private class ActualSingleSignOnService extends AbstractSingleSignOnService {

        public ActualSingleSignOnService(SsoSetting ssoSettings) {
            super(ssoSettings);
        }

        @Override
        protected String getAuthUri() throws SsoException {
            return "";
        }

        @Override
        protected String getTokenUri() throws SsoException {
            return "token-uri";
        }

        @Override
        protected String getLogoutUri() throws SsoException {
            return "logout-uri";
        }
    }

    AbstractSingleSignOnService singleSignOnService;
    SsoSetting mockedSsoSetting;

    @Before
    public void setUp() {
        mockedSsoSetting = Mockito.mock(SsoSetting.class);
        singleSignOnService = new ActualSingleSignOnService(mockedSsoSetting);
    }

    @Test
    public void isEnabledTest() {
        assertTrue("True expected", singleSignOnService.isEnabled());
    }

    @Test
    public void getClientIdTest() {
        Mockito.when(mockedSsoSetting.getString(SsoSettingKeys.SSO_OPENID_CLIENT_ID)).thenReturn("foobar");
        assertEquals("Expected and actual values should be the same.", "foobar", singleSignOnService.getClientId());
    }

    @Test
    public void getClientSecretTest() {
        Mockito.when(mockedSsoSetting.getString(SsoSettingKeys.SSO_OPENID_CLIENT_SECRET)).thenReturn("foobar");
        assertEquals("Expected and actual values should be the same.", "foobar", singleSignOnService.getClientSecret());
    }

    @Test
    public void getLoginUriTest() throws URISyntaxException, SsoException {
        URI redirectUri = new URI(Thread.currentThread().getContextClassLoader().getResource("toti-fajl.json").toString());
//        kako assertat tole, da se prav izpisuje glede na to da je uri noter
        System.out.println(singleSignOnService.getLoginUri("foobar", redirectUri));
    }

    @Test
    public void getLoginUriUriSyntaxExceptionTest() throws URISyntaxException, SsoLoginUriException {
//        URI redirectUri = new URI(Thread.currentThread().getContextClassLoader().getResource("toti-fajl.json").toString());
        System.out.println(singleSignOnService.getLoginUri("foobar", new URI("blabla")));

    }

    @Test
    public void getLoginUriSsoExceptionTest() {

    }

    @Test
    public void getLogoutUriTest() throws URISyntaxException, SsoException {
        URI redirectUri = new URI(Thread.currentThread().getContextClassLoader().getResource("toti-fajl.json").toString());
//        kako assertat tole, da se prav izpisuje glede na to da je uri noter
        System.out.println(singleSignOnService.getLogoutUri("idTokenHint", redirectUri, "state"));
    }

    @Test
    public void getLogoutUriUriSyntaxExceptionTest() {

    }

    @Test
    public void getLogoutUriSsoExceptionTest() {

    }

    @Test
    public void getAccessTokenTest() throws URISyntaxException, SsoAccessTokenException {
        URI redirectUri = new URI(Thread.currentThread().getContextClassLoader().getResource("toti-fajl.json").toString());
        System.out.println(singleSignOnService.getAccessToken("authCode", redirectUri));

    }
}