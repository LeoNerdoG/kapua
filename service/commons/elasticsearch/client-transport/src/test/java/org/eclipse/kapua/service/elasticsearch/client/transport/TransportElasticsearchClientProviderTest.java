/*******************************************************************************
 * Copyright (c) 2017, 2020 Red Hat Inc and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.service.elasticsearch.client.transport;

import org.eclipse.kapua.qa.markers.Categories;
import org.eclipse.kapua.service.elasticsearch.client.exception.ClientProviderInitException;
import org.eclipse.kapua.service.elasticsearch.client.transport.model.NoOpModelContext;
import org.eclipse.kapua.service.elasticsearch.client.transport.model.NoOpQueryConverter;
import org.eclipse.kapua.service.elasticsearch.client.transport.model.TransportElasticsearchConfiguration;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Collections;

@Category(Categories.junitTests.class)
public class TransportElasticsearchClientProviderTest {

    @Test(expected = ClientProviderInitException.class)
    public void testNodesEmpty() throws ClientProviderInitException {
        TransportElasticsearchConfiguration esConfiguration = new TransportElasticsearchConfiguration();
        esConfiguration.setNodes(Collections.emptyList());

        TransportElasticsearchClientProvider transportElasticsearchClientProvider = new TransportElasticsearchClientProvider();
        transportElasticsearchClientProvider
                .withClientConfiguration(esConfiguration)
                .withModelConverter(new NoOpQueryConverter())
                .withModelContext(new NoOpModelContext())
                .init();
    }

    @Test(expected = ClientProviderInitException.class)
    public void testNoClientConfiguration() throws ClientProviderInitException {
        TransportElasticsearchClientProvider transportElasticsearchClientProvider = new TransportElasticsearchClientProvider();
        transportElasticsearchClientProvider
                .withModelConverter(new NoOpQueryConverter())
                .withModelContext(new NoOpModelContext())
                .init();
    }

    @Test(expected = ClientProviderInitException.class)
    public void testNoModelConverter() throws ClientProviderInitException {
        TransportElasticsearchConfiguration esConfiguration = new TransportElasticsearchConfiguration();

        TransportElasticsearchClientProvider transportElasticsearchClientProvider = new TransportElasticsearchClientProvider();
        transportElasticsearchClientProvider
                .withClientConfiguration(esConfiguration)
                .withModelContext(new NoOpModelContext())
                .init();
    }

    @Test(expected = ClientProviderInitException.class)
    public void testNoModelContext() throws ClientProviderInitException {
        TransportElasticsearchConfiguration esConfiguration = new TransportElasticsearchConfiguration();

        TransportElasticsearchClientProvider transportElasticsearchClientProvider = new TransportElasticsearchClientProvider();
        transportElasticsearchClientProvider
                .withClientConfiguration(esConfiguration)
                .withModelConverter(new NoOpQueryConverter())
                .init();
    }
}
