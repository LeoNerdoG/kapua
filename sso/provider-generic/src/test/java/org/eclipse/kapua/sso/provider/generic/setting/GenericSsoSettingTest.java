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
package org.eclipse.kapua.sso.provider.generic.setting;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class GenericSsoSettingTest extends Assert {

    @Test
    public void constructorTest() {
        GenericSsoSetting genericSsoSetting = new GenericSsoSetting("sso-generic-setting.properties");
        assertThat("Instance of GenericSsoSetting expected.", genericSsoSetting, IsInstanceOf.instanceOf(GenericSsoSetting.class));
    }

    @Test
    public void getInstanceTest() {
        assertThat("Instance of GenericSsoSetting expected.", GenericSsoSetting.getInstance(), IsInstanceOf.instanceOf(GenericSsoSetting.class));
    }
}
