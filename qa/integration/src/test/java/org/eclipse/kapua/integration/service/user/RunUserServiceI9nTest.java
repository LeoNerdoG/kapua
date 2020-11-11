/*******************************************************************************
 * Copyright (c) 2017, 2020 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *     Red Hat Inc
 *******************************************************************************/
package org.eclipse.kapua.integration.service.user;

import cucumber.api.CucumberOptions;
import org.eclipse.kapua.qa.common.cucumber.CucumberProperty;
import org.eclipse.kapua.qa.common.cucumber.CucumberWithProperties;
import org.eclipse.kapua.qa.markers.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(CucumberWithProperties.class)
@CucumberOptions(
        features = {
                "classpath:features/user/UserServiceI9n.feature"
        },
        glue = {"org.eclipse.kapua.qa.common",
                "org.eclipse.kapua.service.account.steps",
                "org.eclipse.kapua.service.user.steps",
                "org.eclipse.kapua.service.authorization.steps",
                "org.eclipse.kapua.service.device.registry.steps",
                "org.eclipse.kapua.service.job.steps",
                "org.eclipse.kapua.service.tag.steps",
                "org.eclipse.kapua.service.datastore.steps"
        },
        plugin = {"pretty",
                "html:target/cucumber/UserServiceI9n",
                "json:target/UserServiceI9n_cucumber.json"
        },
        strict = true,
        monochrome = true)

@Category(value = Categories.integrationTests.class)
@CucumberProperty(key = "broker.ip", value = "192.168.33.10")
@CucumberProperty(key = "kapua.config.url", value = "")
@CucumberProperty(key = "datastore.elasticsearch.provider", value = "org.eclipse.kapua.service.elasticsearch.client.rest.RestElasticsearchClient")
@CucumberProperty(key = "org.eclipse.kapua.qa.datastore.extraStartupDelay", value = "5")
@CucumberProperty(key = "org.eclipse.kapua.qa.broker.extraStartupDelay", value = "5")
public class RunUserServiceI9nTest {
}
