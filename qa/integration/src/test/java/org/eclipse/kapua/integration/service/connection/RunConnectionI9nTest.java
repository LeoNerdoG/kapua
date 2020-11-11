/*******************************************************************************
 * Copyright (c) 2017, 2020 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech
 *******************************************************************************/
package org.eclipse.kapua.integration.service.connection;

import org.eclipse.kapua.qa.common.cucumber.CucumberProperty;
import org.eclipse.kapua.qa.common.cucumber.CucumberWithProperties;
import org.eclipse.kapua.qa.markers.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;

@RunWith(CucumberWithProperties.class)
@CucumberOptions(
        features = {"classpath:features/connection/UserCouplingI9n.feature"
        },
        glue = {"org.eclipse.kapua.qa.common",
                "org.eclipse.kapua.service.account.steps",
                "org.eclipse.kapua.service.user.steps",
                "org.eclipse.kapua.service.connection.steps",
                "org.eclipse.kapua.service.device.registry.steps"
        },
        plugin = {"pretty",
                "html:target/cucumber/ConnectionI9n",
                "json:target/ConnectionI9n_cucumber.json"
        },
        strict = true,
        monochrome = true )

@Category(value = Categories.integrationTests.class)
@CucumberProperty(key="broker.ip", value="localhost")
@CucumberProperty(key="kapua.config.url", value="")
public class RunConnectionI9nTest {}

