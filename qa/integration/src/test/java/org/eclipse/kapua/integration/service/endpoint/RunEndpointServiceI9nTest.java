/*******************************************************************************
 * Copyright (c) 2020, 2021 Eurotech and/or its affiliates and others
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.integration.service.endpoint;

import cucumber.api.CucumberOptions;
import org.eclipse.kapua.qa.common.cucumber.CucumberWithProperties;
import org.eclipse.kapua.qa.markers.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(CucumberWithProperties.class)
@CucumberOptions(
        features = { "classpath:features/endpoint/EndpointServiceI9n.feature" },
        glue = {
                "org.eclipse.kapua.service.user.steps",
                "org.eclipse.kapua.service.endpoint.steps",
                "org.eclipse.kapua.qa.common",
                "org.eclipse.kapua.service.account.steps"
        },
        plugin = { "pretty",
                "html:target/cucumber",
                "json:target/cucumber.json" },
        strict = true,
        monochrome = true)

@Category(value = Categories.integrationTests.class)
public class RunEndpointServiceI9nTest {
}
