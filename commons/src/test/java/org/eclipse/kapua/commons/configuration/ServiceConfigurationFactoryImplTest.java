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
import org.eclipse.kapua.service.config.ServiceComponentConfiguration;
import org.eclipse.kapua.service.config.ServiceConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;


@Category(JUnitTests.class)
public class ServiceConfigurationFactoryImplTest {

    @Test
    public void testNewComponentConfigurationInstance() {
        ServiceConfigurationFactoryImpl configurationFactory = new ServiceConfigurationFactoryImpl();
        ServiceComponentConfiguration componentConfiguration = configurationFactory.newComponentConfigurationInstance("testInstance");
        Assert.assertNotNull(componentConfiguration);
    }

    @Test
    public void testNewConfigurationInstance() {
        ServiceConfigurationFactoryImpl configurationFactory = new ServiceConfigurationFactoryImpl();
        ServiceConfiguration configuration = configurationFactory.newConfigurationInstance();
        Assert.assertNotNull(configuration);
    }
}