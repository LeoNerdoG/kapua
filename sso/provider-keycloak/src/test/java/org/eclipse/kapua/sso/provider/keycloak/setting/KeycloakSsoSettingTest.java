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

import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KeycloakSsoSettingTest extends Assert {

    @Test
    public void constructorTest() {
        KeycloakSsoSetting keycloakSsoSetting = new KeycloakSsoSetting("sso-keycloak-setting.properties");
        assertThat("Instance of KeycloakSsoSetting expected.", keycloakSsoSetting, IsInstanceOf.instanceOf(KeycloakSsoSetting.class));
    }

    @Test
    public void getInstanceTest() {
        assertThat("Instance of KeycloakSsoSetting expected.", KeycloakSsoSetting.getInstance(), IsInstanceOf.instanceOf(KeycloakSsoSetting.class));
    }
}
