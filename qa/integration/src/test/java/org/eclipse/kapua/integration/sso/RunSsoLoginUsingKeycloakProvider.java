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
package org.eclipse.kapua.integration.sso;
import cucumber.api.CucumberOptions;
import org.eclipse.kapua.qa.common.cucumber.CucumberWithProperties;
import org.junit.runner.RunWith;

@RunWith(CucumberWithProperties.class)
@CucumberOptions(
        features = {
                "classpath:features/sso/SsoLoginUsingKeycloakProvider.feature"
        },
        glue = {
                "org.eclipse.kapua.qa.common",
                "org.eclipse.kapua.service.sso.steps",
                "org.eclipse.kapua.qa.integration.steps",
                "org.eclipse.kapua.service.account.steps",
                "org.eclipse.kapua.service.user.steps"
        },
        plugin = {
                "pretty",
                "html:target/SsoUnitTests",
                "json:target/SsoUnitTests_cucumber.json"
        },
        strict = true,
        monochrome = true)
public class RunSsoLoginUsingKeycloakProvider {
}
