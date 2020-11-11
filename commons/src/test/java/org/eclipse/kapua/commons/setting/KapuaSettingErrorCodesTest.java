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

import org.eclipse.kapua.qa.markers.Categories;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Categories.junitTests.class)
public class KapuaSettingErrorCodesTest extends Assert {

    @Test
    public void invalidResourcesNameTest() {
        assertEquals("Expected and actual values should be the same!", "INVALID_RESOURCE_NAME", KapuaSettingErrorCodes.INVALID_RESOURCE_NAME.name());
    }

    @Test
    public void invalidResourcesFileTest() {
        assertEquals("Expected and actual values should be the same!", "INVALID_RESOURCE_FILE", KapuaSettingErrorCodes.INVALID_RESOURCE_FILE.name());
    }

    @Test
    public void resourceNotFoundTest() {
        assertEquals("Expected and actual values should be the same!", "RESOURCE_NOT_FOUND", KapuaSettingErrorCodes.RESOURCE_NOT_FOUND.name());
    }
}
