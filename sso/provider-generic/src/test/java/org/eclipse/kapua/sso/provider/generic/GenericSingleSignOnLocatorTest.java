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
package org.eclipse.kapua.sso.provider.generic;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.sso.exception.SsoException;
import org.eclipse.kapua.sso.provider.generic.jwt.GenericJwtProcessor;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class GenericSingleSignOnLocatorTest extends Assert {

    GenericSingleSignOnLocator genericSingleSignOnLocator;

    @Before
    public void setUp() {
        genericSingleSignOnLocator = new GenericSingleSignOnLocator();
    }

    @Test
    public void getServiceTest() {
        assertThat(genericSingleSignOnLocator.getService(), IsInstanceOf.instanceOf(GenericSingleSignOnService.class));
    }

    //    to ne dela
    //    Ta seznam je prazen v src/main/java/org/eclipse/kapua/sso/provider/generic/jwt/GenericJwtProcessor.java
    //            List<String> jwtAudiences = GenericSsoSetting.getInstance().getList(String.class, GenericSsoSettingKeys.SSO_OPENID_JWT_AUDIENCE_ALLOWED);
    @Test
    public void getProcessorTest() throws SsoException {
        System.setProperty("sso.generic.openid.jwt.audience.allowed", "console");
        System.setProperty("sso.generic.openid.jwt.issuer.allowed", "http://localhost:9090/auth/realms/kapua");
        System.setProperty("sso.generic.openid.server.endpoint.auth", "http://localhost:9090/auth/realms/kapua/protocol/openid-connect/auth");
        System.setProperty("sso.generic.openid.server.endpoint.logout", "http://localhost:9090/auth/realms/kapua/protocol/openid-connect/logout");
        System.setProperty("sso.generic.openid.server.endpoint.token", "http://localhost:9090/auth/realms/kapua/protocol/openid-connect/token");
        assertThat(genericSingleSignOnLocator.getProcessor(), IsInstanceOf.instanceOf(GenericJwtProcessor.class));
    }

    //    this test method will be upgraded once method close() gets a body
    @Test
    public void closeTest() throws Exception {
        genericSingleSignOnLocator.close();
    }

}
