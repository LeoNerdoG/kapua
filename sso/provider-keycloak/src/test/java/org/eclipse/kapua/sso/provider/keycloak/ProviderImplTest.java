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
package org.eclipse.kapua.sso.provider.keycloak;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class ProviderImplTest extends Assert {

    ProviderImpl providerImpl;

    @Before
    public void setUp() {
        providerImpl = new ProviderImpl();
    }

    @Test
    public void getIdTest() {
        assertEquals("keycloak", providerImpl.getId());
    }

    @Test
    public void createLocatorTest() {
        assertThat("Instance of KeycloakSingleSignOnLocator expected.", providerImpl.createLocator(), IsInstanceOf.instanceOf(KeycloakSingleSignOnLocator.class));
    }
}
