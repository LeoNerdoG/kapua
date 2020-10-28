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

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Providers;
import javax.xml.bind.JAXBContext;

@Category(JUnitTests.class)
public class RestApiJAXBContextProviderTest extends Assert {

    RestApiJAXBContextProvider restApiJAXBContextProvider;
    Providers providers;
    ContextResolver<JAXBContext> contextResolver;
    JAXBContext jaxbContext;

    @Before
    public void initialize() {
        restApiJAXBContextProvider = new RestApiJAXBContextProvider();
        providers = Mockito.mock(Providers.class);
        contextResolver = Mockito.mock(ContextResolver.class);
        jaxbContext = Mockito.mock(JAXBContext.class);
    }

    @Test(expected = KapuaException.class)
    public void getJAXBContextNullProvidersTest() throws KapuaException {
        restApiJAXBContextProvider.providers = null;

        restApiJAXBContextProvider.getJAXBContext();
    }

    @Test(expected = KapuaException.class)
    public void getJAXBNullContextTest() throws KapuaException {
        restApiJAXBContextProvider.providers = providers;

        Mockito.when(providers.getContextResolver(JAXBContext.class, MediaType.APPLICATION_XML_TYPE)).thenReturn(contextResolver);
        Mockito.when(contextResolver.getContext(JAXBContext.class)).thenReturn(null);

        restApiJAXBContextProvider.getJAXBContext();
    }

    @Test
    public void getJAXBContextTest() throws KapuaException {
        restApiJAXBContextProvider.providers = providers;

        Mockito.when(providers.getContextResolver(JAXBContext.class, MediaType.APPLICATION_XML_TYPE)).thenReturn(contextResolver);
        Mockito.when(contextResolver.getContext(JAXBContext.class)).thenReturn(jaxbContext);

        assertNotNull("NotNull expected.", restApiJAXBContextProvider.getJAXBContext());
    }
}