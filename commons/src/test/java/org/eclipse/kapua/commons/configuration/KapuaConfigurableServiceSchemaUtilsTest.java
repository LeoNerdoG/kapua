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

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KapuaConfigurableServiceSchemaUtilsTest extends Assert {

    @Test(expected = NullPointerException.class)
    public void testScriptSessionWithNullArguments() {
        KapuaConfigurableServiceSchemaUtils.scriptSession(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testScriptSessionWithNullPath() {
        KapuaConfigurableServiceSchemaUtils.scriptSession(null, KapuaConfigurableServiceSchemaUtils.DEFAULT_FILTER);
    }

    @Test(expected = NullPointerException.class)
    public void testScriptSessionWithNullFilter() {
        KapuaConfigurableServiceSchemaUtils.scriptSession(KapuaConfigurableServiceSchemaUtils.DEFAULT_PATH, null);
    }

    @Test
    public void testScriptSessionWithDefaultValues() {
        KapuaConfigurableServiceSchemaUtils.scriptSession(KapuaConfigurableServiceSchemaUtils.DEFAULT_PATH, KapuaConfigurableServiceSchemaUtils.DEFAULT_FILTER);
    }

    @Test
    public void testScriptSessionWithEmptyPathValue() {
        KapuaConfigurableServiceSchemaUtils.scriptSession("", KapuaConfigurableServiceSchemaUtils.DEFAULT_FILTER);
    }

    @Test
    public void testScriptSessionWithWrongPathValue() {
        KapuaConfigurableServiceSchemaUtils.scriptSession("wrong/path", KapuaConfigurableServiceSchemaUtils.DEFAULT_FILTER);
    }

    @Test
    public void testScriptSessionWithEmptyFilterValue() {
        KapuaConfigurableServiceSchemaUtils.scriptSession(KapuaConfigurableServiceSchemaUtils.DEFAULT_PATH, "");
    }

    @Test
    public void testScriptSessionWithWrongFilterValue() {
        KapuaConfigurableServiceSchemaUtils.scriptSession(KapuaConfigurableServiceSchemaUtils.DEFAULT_PATH, "*.wrong");
    }

    @Test
    public void testCreateSchemaObjectsWithDefaultPath() throws KapuaException {
        KapuaConfigurableServiceSchemaUtils.createSchemaObjects(KapuaConfigurableServiceSchemaUtils.DEFAULT_PATH);
    }

    @Test
    public void testCreateSchemaObjectsWithWrongPath() throws KapuaException {
        KapuaConfigurableServiceSchemaUtils.createSchemaObjects("wrong/path");
    }

    @Test
    public void testCreateSchemaObjectsWithEmptyPath() throws KapuaException {
        KapuaConfigurableServiceSchemaUtils.createSchemaObjects("");
    }

    @Test(expected = NullPointerException.class)
    public void testCreateSchemaObjectsWithNullPath() throws KapuaException {
        KapuaConfigurableServiceSchemaUtils.createSchemaObjects(null);
    }

    @Test
    public void testDropSchemaObjectsWithDefaultPath() {
        KapuaConfigurableServiceSchemaUtils.dropSchemaObjects(KapuaConfigurableServiceSchemaUtils.DEFAULT_PATH);
    }

    @Test
    public void testDropSchemaObjectsWithWrongPath() {
        KapuaConfigurableServiceSchemaUtils.dropSchemaObjects("wrong/path");
    }

    @Test
    public void testDropSchemaObjectsWithEmptyPath() {
        KapuaConfigurableServiceSchemaUtils.dropSchemaObjects("");
    }

    @Test(expected = NullPointerException.class)
    public void testDropSchemaObjectsWithNullPath() {
        KapuaConfigurableServiceSchemaUtils.dropSchemaObjects(null);
    }
}
