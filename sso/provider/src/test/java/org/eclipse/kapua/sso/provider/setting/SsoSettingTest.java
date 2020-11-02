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
package org.eclipse.kapua.sso.provider.setting;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class SsoSettingTest extends Assert {

    @Test
    public void constructorTest() {
        SsoSetting ssoSetting = new SsoSetting("sso-setting.properties");
        assertThat("Instance of SsoSetting expected.", ssoSetting, IsInstanceOf.instanceOf(SsoSetting.class));
    }

    @Test
    public void getInstanceTest() {
        assertThat("Instance of SsoSetting expected.", SsoSetting.getInstance(), IsInstanceOf.instanceOf(SsoSetting.class));
    }
}
