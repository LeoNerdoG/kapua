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
package org.eclipse.kapua.sso.provider.generic;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.sso.exception.SsoException;
import org.eclipse.kapua.sso.exception.uri.SsoIllegalUriException;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class GenericSingleSignOnServiceTest extends Assert {
    GenericSingleSignOnService genericSingleSignOnService;

    @Before
    public void setUp() {
        genericSingleSignOnService = new GenericSingleSignOnService();
    }

    @Test
    public void constructorTest() {
        assertThat(genericSingleSignOnService, IsInstanceOf.instanceOf(GenericSingleSignOnService.class));
    }

    @Test(expected = SsoIllegalUriException.class)
    public void getAuthUriNullEndPointAuthTest() throws SsoException {
        System.clearProperty("sso.generic.openid.server.endpoint.auth");
        genericSingleSignOnService.getAuthUri();
    }

    @Test
    public void getAuthUriTest() throws SsoException {
        System.setProperty("sso.generic.openid.server.endpoint.auth", "sso.generic.openid.server.endpoint.auth");
        assertEquals("sso.generic.openid.server.endpoint.auth", genericSingleSignOnService.getAuthUri());
    }

    @Test
    public void getAuthUriGetOpenIdConfPathTest() throws SsoException {
        System.setProperty("sso.openid.conf.path", "");
        System.setProperty("sso.generic.openid.jwt.issuer.allowed", Thread.currentThread().getContextClassLoader().getResource("mockedUriPath.json").toString());
        assertEquals("sso.generic.openid.jwt.issuer.allowed", genericSingleSignOnService.getAuthUri());
    }

    @Test
    public void getTokenUriTest() throws SsoException {
        System.setProperty("sso.generic.openid.server.endpoint.token", "sso.generic.openid.server.endpoint.token");
        assertEquals("sso.generic.openid.server.endpoint.token", genericSingleSignOnService.getTokenUri());
    }

    @Test(expected = SsoIllegalUriException.class)
    public void getTokenUriNullTest() throws SsoException {
        System.clearProperty("sso.generic.openid.server.endpoint.token");
        genericSingleSignOnService.getTokenUri();
    }

    @Test
    public void getLogoutUriTest() throws SsoException {
        System.setProperty("sso.generic.openid.server.endpoint.logout", "http://localhost:9090/auth/realms/kapua/protocol/openid-connect/logout");
        assertEquals("http://localhost:9090/auth/realms/kapua/protocol/openid-connect/logout", genericSingleSignOnService.getLogoutUri());
    }

    @Test(expected = SsoIllegalUriException.class)
    public void getLogoutUriNullTest() throws SsoException {
        System.clearProperty("sso.generic.openid.server.endpoint.logout");
        genericSingleSignOnService.getLogoutUri();
    }
}