/*******************************************************************************
 * Copyright (c) 2020 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.app.api.core.setting;

import org.eclipse.kapua.app.api.core.settings.KapuaApiSetting;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KapuaApiSettingTest extends Assert {

    @Test
    public void kapuaApiSettingTest() {
        assertThat("Instance of KapuaApiSetting expected.", KapuaApiSetting.getInstance(), IsInstanceOf.instanceOf(KapuaApiSetting.class));
    }
}
