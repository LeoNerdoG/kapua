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
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Properties;

@Category(JUnitTests.class)
public class ServiceConfigImplTest {
    ServiceConfigImpl serviceConfig;

    @Before
    public void createInstenceOfClass() {
        KapuaId id = KapuaId.ONE;
        serviceConfig = new ServiceConfigImpl(id);
    }

    @Test
    public void testConstructorWithNoValues() {
        ServiceConfigImpl config = new ServiceConfigImpl();
        Assert.assertNull(config.getPid());
    }

    @Test
    public void testSetPidRegular() {
        serviceConfig.setPid("123");
        Assert.assertEquals(serviceConfig.getPid(), "123");
    }

    @Test
    public void testSetPidNullValue() {
        serviceConfig.setPid(null);
        Assert.assertNull(serviceConfig.getPid());
    }

    @Test
    public void testSetConfigurationsNullValue() throws KapuaException {
        serviceConfig.setConfigurations(null);
        Assert.assertEquals(serviceConfig.getConfigurations(), new Properties());
    }

    @Test
    public void testSetConfigurationsRegular() throws KapuaException {
        Properties properties = new Properties();
        properties.setProperty("prop1", "value1");
        serviceConfig.setConfigurations(properties);
        Assert.assertEquals(serviceConfig.getConfigurations(), properties);
    }

    @Test
    public void testGetType() {
        Assert.assertEquals(serviceConfig.getType(), "scfg");
    }
}
