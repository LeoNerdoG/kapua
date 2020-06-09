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
    public void scriptSessionWithNullArgumentsTest() {
        KapuaConfigurableServiceSchemaUtils.scriptSession(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void scriptSessionWithNullPathTest() {
        KapuaConfigurableServiceSchemaUtils.scriptSession(null, KapuaConfigurableServiceSchemaUtils.DEFAULT_FILTER);
    }

    @Test(expected = NullPointerException.class)
    public void scriptSessionWithNullFilterTest() {
        KapuaConfigurableServiceSchemaUtils.scriptSession(KapuaConfigurableServiceSchemaUtils.DEFAULT_PATH, null);
    }

    @Test
    public void scriptSessionWithDefaultValuesTest() {
        KapuaConfigurableServiceSchemaUtils.scriptSession(KapuaConfigurableServiceSchemaUtils.DEFAULT_PATH, KapuaConfigurableServiceSchemaUtils.DEFAULT_FILTER);
    }

    @Test
    public void scriptSessionWithEmptyPathValueTest() {
        KapuaConfigurableServiceSchemaUtils.scriptSession("", KapuaConfigurableServiceSchemaUtils.DEFAULT_FILTER);
    }

    @Test
    public void scriptSessionWithWrongPathValueTest() {
        KapuaConfigurableServiceSchemaUtils.scriptSession("wrong/path", KapuaConfigurableServiceSchemaUtils.DEFAULT_FILTER);
    }

    @Test
    public void scriptSessionWithEmptyFilterValueTest() {
        KapuaConfigurableServiceSchemaUtils.scriptSession(KapuaConfigurableServiceSchemaUtils.DEFAULT_PATH, "");
    }

    @Test
    public void scriptSessionWithWrongFilterValueTest() {
        KapuaConfigurableServiceSchemaUtils.scriptSession(KapuaConfigurableServiceSchemaUtils.DEFAULT_PATH, "*.wrong");
    }

    @Test
    public void createSchemaObjectsWithDefaultPathTest() throws KapuaException {
        KapuaConfigurableServiceSchemaUtils.createSchemaObjects(KapuaConfigurableServiceSchemaUtils.DEFAULT_PATH);
    }

    @Test
    public void createSchemaObjectsWithWrongPathTest() throws KapuaException {
        KapuaConfigurableServiceSchemaUtils.createSchemaObjects("wrong/path");
    }

    @Test
    public void createSchemaObjectsWithEmptyPathTest() throws KapuaException {
        KapuaConfigurableServiceSchemaUtils.createSchemaObjects("");
    }

    @Test(expected = NullPointerException.class)
    public void createSchemaObjectsWithNullPathTest() throws KapuaException {
        KapuaConfigurableServiceSchemaUtils.createSchemaObjects(null);
    }

    @Test
    public void dropSchemaObjectsWithDefaultPathTest() {
        KapuaConfigurableServiceSchemaUtils.dropSchemaObjects(KapuaConfigurableServiceSchemaUtils.DEFAULT_PATH);
    }

    @Test
    public void dropSchemaObjectsWithWrongPathTest() {
        KapuaConfigurableServiceSchemaUtils.dropSchemaObjects("wrong/path");
    }

    @Test
    public void dropSchemaObjectsWithEmptyPathTest() {
        KapuaConfigurableServiceSchemaUtils.dropSchemaObjects("");
    }

    @Test(expected = NullPointerException.class)
    public void dropSchemaObjectsWithNullPathTest() {
        KapuaConfigurableServiceSchemaUtils.dropSchemaObjects(null);
    }
}
