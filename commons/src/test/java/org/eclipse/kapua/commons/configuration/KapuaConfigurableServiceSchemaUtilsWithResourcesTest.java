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
package org.eclipse.kapua.commons.configuration;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KapuaConfigurableServiceSchemaUtilsWithResourcesTest extends Assert {

    @Test(expected = NullPointerException.class)
    public void testScriptSessionWithNullArguments() {
        KapuaConfigurableServiceSchemaUtilsWithResources.scriptSession(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testScriptSessionWithNullPath() {
        KapuaConfigurableServiceSchemaUtilsWithResources.scriptSession(null, KapuaConfigurableServiceSchemaUtils.DEFAULT_FILTER);
    }

    @Test(expected = NullPointerException.class)
    public void testScriptSessionWithNullFilter() {
        KapuaConfigurableServiceSchemaUtilsWithResources.scriptSession(KapuaConfigurableServiceSchemaUtils.DEFAULT_PATH, null);
    }

    @Test(expected = NullPointerException.class)
    public void testScriptSessionWithEmptyPathValue() {
        KapuaConfigurableServiceSchemaUtilsWithResources.scriptSession("", KapuaConfigurableServiceSchemaUtils.DEFAULT_FILTER);
    }

    @Test(expected = NullPointerException.class)
    public void testScriptSessionWithWrongPathValue() {
        KapuaConfigurableServiceSchemaUtilsWithResources.scriptSession("wrong/path", KapuaConfigurableServiceSchemaUtils.DEFAULT_FILTER);
    }

    @Test(expected = NullPointerException.class)
    public void testScriptSessionWithEmptyFilterValue() {
        KapuaConfigurableServiceSchemaUtilsWithResources.scriptSession(KapuaConfigurableServiceSchemaUtils.DEFAULT_PATH, "");
    }

    @Test(expected = NullPointerException.class)
    public void testScriptSessionWithWrongFilterValue() {
        KapuaConfigurableServiceSchemaUtilsWithResources.scriptSession(KapuaConfigurableServiceSchemaUtils.DEFAULT_PATH, "*.wrong");
    }
}
