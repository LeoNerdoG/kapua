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
package org.eclipse.kapua.sso.provider.generic.jwt;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.sso.exception.SsoException;
import org.eclipse.kapua.sso.exception.SsoIllegalArgumentException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Collections;

@Category(JUnitTests.class)
public class GenericJwtProcessorTest extends Assert {

    @Test
    public void getJwtExpectedIssuersTest() throws SsoException {
        System.clearProperty("sso.generic.openid.jwt.issuer.allowed");
        System.clearProperty("sso.generic.openid.jwt.audience.allowed");
        try {
            new GenericJwtProcessor().getJwtExpectedIssuers();
        } catch (SsoIllegalArgumentException iae) {
            assertEquals("Expected and actual values should be the same!", "An illegal value was provided for the argument sso.generic.openid.jwt.audience.allowed: .", iae.getMessage());
        }
        System.setProperty("sso.generic.openid.jwt.audience.allowed", "sso.generic.openid.jwt.audience.allowed");
        System.setProperty("sso.generic.openid.jwt.issuer.allowed", "sso.generic.openid.jwt.issuer.allowed");
        assertEquals("Expected and actual values should be the same!", Collections.singletonList("sso.generic.openid.jwt.issuer.allowed"), new GenericJwtProcessor().getJwtExpectedIssuers());
    }

    @Test(expected = SsoIllegalArgumentException.class)
    public void getJwtExpectedIssuersNullTest() throws SsoException {
        System.clearProperty("sso.generic.openid.jwt.issuer.allowed");
        System.clearProperty("sso.generic.openid.jwt.audience.allowed");
        System.out.println(System.getProperty("sso.generic.openid.jwt.issuer.allowed"));
        new GenericJwtProcessor().getJwtExpectedIssuers();
    }

    @Test
    public void getJwtAudiencesTest() throws SsoException {
        System.clearProperty("sso.generic.openid.jwt.issuer.allowed");
        System.clearProperty("sso.generic.openid.jwt.audience.allowed");
        try {
            new GenericJwtProcessor().getJwtAudiences();
        } catch (SsoIllegalArgumentException iae) {
            assertEquals("Expected and actual values should be the same!", "An illegal value was provided for the argument sso.generic.openid.jwt.audience.allowed: .", iae.getMessage());
        }
        System.setProperty("sso.generic.openid.jwt.audience.allowed", "sso.generic.openid.jwt.audience.allowed");
        System.setProperty("sso.generic.openid.jwt.issuer.allowed", "sso.generic.openid.jwt.issuer.allowed");
        assertEquals("Expected and actual values should be the same!", Collections.singletonList("sso.generic.openid.jwt.audience.allowed"), new GenericJwtProcessor().getJwtAudiences());
    }

    @Test(expected = SsoIllegalArgumentException.class)
    public void getJwtAudiencesNullTest() throws SsoException {
        System.clearProperty("sso.generic.openid.jwt.audience.allowed");
        System.clearProperty("sso.generic.openid.jwt.issuer.allowed");
        new GenericJwtProcessor().getJwtAudiences();
    }
}