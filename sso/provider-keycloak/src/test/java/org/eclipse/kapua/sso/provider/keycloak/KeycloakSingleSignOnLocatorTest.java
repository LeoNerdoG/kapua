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
import org.eclipse.kapua.sso.exception.SsoException;
import org.eclipse.kapua.sso.provider.keycloak.jwt.KeycloakJwtProcessor;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KeycloakSingleSignOnLocatorTest extends Assert {

    KeycloakSingleSignOnLocator keycloakSingleSignOnLocator;

    @Before
    public void setUp() {
        keycloakSingleSignOnLocator = new KeycloakSingleSignOnLocator();
    }

    @Test
    public void getServiceTest() {
        assertThat(keycloakSingleSignOnLocator.getService(), IsInstanceOf.instanceOf(KeycloakSingleSignOnService.class));
    }

    @Test
    public void getProcessorTest() throws SsoException {
        System.setProperty("sso.openid.client.id", "id");
        System.setProperty("sso.keycloak.uri", "http://localhost:9090");
        System.setProperty("sso.keycloak.realm", "kapua");
        assertThat(keycloakSingleSignOnLocator.getProcessor(), IsInstanceOf.instanceOf(KeycloakJwtProcessor.class));
    }

    //    this test method will be upgraded once method close() gets a body
    @Test
    public void closeTest() throws Exception {
        keycloakSingleSignOnLocator.close();
    }
}
