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
package org.eclipse.kapua.app.api.web;

import org.eclipse.kapua.app.api.core.KapuaSerializableBodyWriter;
import org.eclipse.kapua.app.api.core.ListBodyWriter;
import org.eclipse.kapua.app.api.core.MoxyJsonConfigContextResolver;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.glassfish.jersey.server.filter.UriConnegFilter;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.xml.bind.JAXBException;

@Category(JUnitTests.class)
public class RestApisApplicationTest extends Assert {

    @Test
    public void restApisApplicationTest() throws JAXBException {
        RestApisApplication restApisApplication = new RestApisApplication();

        assertEquals("{jersey.config.server.mediaTypeMappings={xml=application/xml, json=application/json}, jersey.config.server.wadl.disableWadl=true}", restApisApplication.getProperties().toString());
        assertTrue("True expected.", restApisApplication.isRegistered(UriConnegFilter.class));
        assertTrue("True expected.", restApisApplication.isRegistered(JaxbContextResolver.class));
        assertTrue("True expected.", restApisApplication.isRegistered(RestApiJAXBContextProvider.class));
        assertTrue("True expected.", restApisApplication.isRegistered(KapuaSerializableBodyWriter.class));
        assertTrue("True expected.", restApisApplication.isRegistered(ListBodyWriter.class));
        assertTrue("True expected.", restApisApplication.isRegistered(MoxyJsonConfigContextResolver.class));

        assertFalse("False expected.", restApisApplication.isRegistered(ContainerLifecycleListener.class));
        assertFalse("False expected.", restApisApplication.isRegistered(String.class));

        restApisApplication.register(ContainerLifecycleListener.class);
        assertTrue("True expected.", restApisApplication.isRegistered(ContainerLifecycleListener.class));
    }
}