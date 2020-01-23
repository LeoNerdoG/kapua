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
package org.eclipse.kapua.integration.service.endpoint;

import cucumber.api.CucumberOptions;
import org.eclipse.kapua.qa.common.cucumber.CucumberWithProperties;
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
public class RunEndpointServiceI9nTest {
}
