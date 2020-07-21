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
package org.eclipse.kapua.commons.setting;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class SimpleSettingKeyTest extends Assert {

    @Test
    public void constructorRegularTest() {
        SimpleSettingKey settingKey = new SimpleSettingKey("string");
        assertEquals("Expected and actual values should be the same!", "string", settingKey.key());
    }

    @Test
    public void constructorNullTest() {
        SimpleSettingKey settingKey = new SimpleSettingKey(null);
        assertNull("Null expected!", settingKey.key());
    }

    @Test
    public void keyRegularTest() {
        String[] permittedValues = {"", "!#$%&'()=?⁄@‹›€°·‚,.-;:_Èˇ¿<>«‘”’ÉØ∏{}|ÆæÒuF8FFÔÓÌÏÎÅ«»Ç◊Ñˆ¯Èˇ", "regularNaming", "regular Naming", "49", "regularNaming49", "NAMING", "246465494135646120009090049684646496468456468496846464968496844"};
        for(String value : permittedValues) {
            SimpleSettingKey key1 = new SimpleSettingKey(value);
            assertEquals("Expected and actual values should be the same!", value, key1.key());
        }
    }
}