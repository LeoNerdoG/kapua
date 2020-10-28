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
package org.eclipse.kapua.app.api.core;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class MoxyJsonConfigContextResolverTest extends Assert {

    @Test
    public void moxyJsonConfigContextResolverTest() {
        Class[] classes = {null, String.class, Integer.class, Short.class, Long.class, Character.class, Double.class, Float.class, String.class};
        MoxyJsonConfigContextResolver moxyJsonConfigContextResolver = new MoxyJsonConfigContextResolver();

        for (Class clazz : classes) {
            assertThat("Instance of MoxyJsonConfig expected.", moxyJsonConfigContextResolver.getContext(clazz), IsInstanceOf.instanceOf(MoxyJsonConfig.class));
            assertEquals("Expected and actual values should be the same.", moxyJsonConfigContextResolver.config, moxyJsonConfigContextResolver.getContext(clazz));
            assertEquals("Expected and actual values should be the same.", "{jaxb.formatted.output=false, eclipselink.json.namespace-separator=., eclipselink.json.include-root=false, eclipselink.json.wrapper-as-array-name=true, eclipselink.json.marshal-empty-collections=true}", moxyJsonConfigContextResolver.config.getMarshallerProperties().toString());
        }
    }
}