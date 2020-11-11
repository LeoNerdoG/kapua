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

import org.assertj.core.api.Assertions;
import org.eclipse.kapua.qa.markers.Categories;
import org.eclipse.kapua.service.elasticsearch.client.exception.ClientProviderInitException;
import org.eclipse.kapua.service.elasticsearch.client.transport.model.NoOpModelContext;
import org.eclipse.kapua.service.elasticsearch.client.transport.model.NoOpQueryConverter;
import org.eclipse.kapua.service.elasticsearch.client.transport.model.TransportElasticsearchConfiguration;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Categories.junitTests.class)
public class TransportElasticseatchClientTest {

    @Test
    public void elasticsearchClientInit() throws ClientProviderInitException {
        // When
        TransportElasticsearchClientProvider transportElasticsearchClientProvider = new TransportElasticsearchClientProvider();
        transportElasticsearchClientProvider.withClientConfiguration(new TransportElasticsearchConfiguration())
                .withModelContext(new NoOpModelContext())
                .withModelConverter(new NoOpQueryConverter())
                .init();

        Client client = transportElasticsearchClientProvider.getElasticsearchClient().getClient();

        // Then
        Assertions.assertThat(client).isInstanceOf(TransportClient.class);

        TransportClient transportClient = (TransportClient) client;
        String host = transportClient.listedNodes().get(0).getHostAddress();
        Assertions.assertThat(host).isEqualTo("127.0.0.1");
    }
}
