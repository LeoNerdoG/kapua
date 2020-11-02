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
import org.eclipse.kapua.sso.exception.SsoIllegalArgumentException;
import org.eclipse.kapua.sso.exception.uri.SsoIllegalUriException;
import org.eclipse.kapua.sso.exception.uri.SsoJwtUriException;
import org.eclipse.kapua.sso.exception.uri.SsoUriException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Category(JUnitTests.class)
public class SingleSignOnUtilsTest extends Assert {

    @Test
    public void getConfigUriTest() throws SsoUriException, URISyntaxException {
        URI uri = new URI("https://abcd");
        assertEquals("Expected amd actual values should be the same.", Optional.of(uri).get(), SingleSignOnUtils.getConfigUri("foobar", Thread.currentThread().getContextClassLoader().getResource("toti-fajl.json").toString()).get());
    }

    @Test
    public void getConfigUriUriJsonValueNotStringTest() throws SsoUriException {
        assertFalse("Optional should be empty.", SingleSignOnUtils.getConfigUri("foobar", Thread.currentThread().getContextClassLoader().getResource("file-intValue.json").toString()).isPresent());
    }

    @Test(expected = SsoJwtUriException.class)
    public void malformedURLExceptionTest() throws SsoUriException {
        SingleSignOnUtils.getConfigUri("foobar", "malformed-uri");
    }

    @Test
    public void ioeExceptionTest() throws SsoUriException {
//        SingleSignOnUtils.getConfigUri("foobar", Thread.currentThread().getContextClassLoader().getResource(".well-known/openid-configuration").toString());
    }

    @Test
    public void uriSyntaxExceptionTest() throws SsoUriException {
//        System.out.println(SingleSignOnUtils.getConfigUri("foobar", Thread.currentThread().getContextClassLoader().getResource("file://foobar").toString()));
    }

    @Test
    public void getOpenIdConfPathUriIssuer() throws URISyntaxException, SsoIllegalArgumentException {
        System.setProperty("sso.openid.conf.path", ".well-known/openid-configuration");
        URI issuer = new URI("file:/");
        assertEquals("Expected and actual values should be the same.", "file://.well-known/openid-configuration", SingleSignOnUtils.getOpenIdConfPath(issuer));
        System.clearProperty("sso.openid.conf.path");
    }

    @Test
    public void getOpenIdCOnfPathThrowingExceptionTest() throws SsoIllegalArgumentException {
        System.setProperty("sso.openid.conf.path", "ssoOpenidConfPath");
        try {
            SingleSignOnUtils.getOpenIdConfPath("issuer");
        } catch (SsoIllegalUriException e) {
            System.clearProperty("sso.openid.conf.path");
            assertEquals("An illegal value was provided for the URI openIdConfPath: issuer/ssoOpenidConfPath.", e.getMessage());
        }
    }

    @Test
    public void getOpenIdConfPathNullPathSuffixTest() {
        System.setProperty("sso.openid.conf.path", "");
        try {
            SingleSignOnUtils.getOpenIdConfPath("file:/");
        } catch (SsoIllegalArgumentException e) {
            System.clearProperty("sso.openid.conf.path");
            assertEquals("An illegal value was provided for the argument sso.openid.conf.path: .", e.getMessage());
        }
    }

    @Test
    public void getOpenIdConfPathTest() throws SsoIllegalArgumentException {
        System.setProperty("sso.openid.conf.path", "foobar");
        assertEquals("Expected and actual values should be the same.", "file://foobar", SingleSignOnUtils.getOpenIdConfPath("file:/"));
    }

    @Test
    public void getOpenIdConfPathDefaultConfPathTest() throws SsoIllegalArgumentException {
        synchronized (SingleSignOnUtilsTest.class) {
            System.clearProperty("sso.openid.conf.path");
            assertEquals("Expected and actual values should be the same.", "file://.well-known/openid-configuration", SingleSignOnUtils.getOpenIdConfPath("file:/"));
            SingleSignOnUtils.getOpenIdConfPath("file:/");
        }
    }
}