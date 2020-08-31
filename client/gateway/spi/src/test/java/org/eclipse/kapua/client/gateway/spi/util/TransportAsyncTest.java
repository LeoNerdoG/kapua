/*******************************************************************************
 * Copyright (c) 2017, 2020 Eurotech and/or its affiliates and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.client.gateway.spi.util;

import org.eclipse.kapua.client.gateway.Transport;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Category(JUnitTests.class)
public class TransportAsyncTest extends Assert {

    final ExecutorService executorService = Mockito.mock(ExecutorService.class);
    TransportAsync transportAsync = new TransportAsync(executorService);

    @Test
    public void transportAsyncExecutorIdTest() {
        assertNotNull("Expected not null.", transportAsync);
    }

    @Test
    public void handleConnectedTest() {
        assertThat("Instance of Future expected", transportAsync.handleConnected(), IsInstanceOf.instanceOf(Future.class));
    }

    @Test
    public void handleDisconnectedTest() {
        assertThat("Instance of Future expected", transportAsync.handleDisconnected(), IsInstanceOf.instanceOf(Future.class));
    }

    @Test
    public void listenTest() {
        final Transport.Listener listener = Mockito.mock(Transport.Listener.class);
        assertThat("Instance of ListenerHandle expected.", transportAsync.listen(listener), IsInstanceOf.instanceOf(Transport.ListenerHandle.class));
    }

    @Test
    public void listenListenerNullTest() {
        assertThat("Instance of ListenerHandle expected.", transportAsync.listen(null), IsInstanceOf.instanceOf(Transport.ListenerHandle.class));
    }

    /*@Test
    public void test1() throws InterruptedException {
        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        try {
            final TransportAsync transport = new TransportAsync(executor);

            final Instant start = Instant.now();
            executor.schedule(() -> {
                transport.handleConnected();
            }, 100, TimeUnit.MILLISECONDS);

            Transport.waitForConnection(transport);

            Duration duration = Duration.between(start, Instant.now());
            System.out.println(duration);

            Assert.assertTrue(duration.compareTo(Duration.ofMillis(100)) >= 0);

        } finally {
            executor.shutdown();
        }
    }

    @Test
    public void test2() throws InterruptedException {
        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        try {
            final TransportAsync transport = new TransportAsync(executor);
            transport.events(events -> {
            });
        } finally {
            executor.shutdown();
        }
    }

    @Test
    public void test3() throws Exception {
        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        final TransportAsync transport = new TransportAsync(executor);

        final AtomicBoolean b1 = new AtomicBoolean(true);
        final AtomicBoolean b2 = new AtomicBoolean(true);

        try (
                ListenerHandle h1 = transport.listen(b1::set);
                ListenerHandle h2 = transport.listen(b2::set);) {

            // we can't sync to the initial even

            Thread.sleep(100);

            // expect them to be true and false initially

            Assert.assertEquals(false, b1.get());
            Assert.assertEquals(false, b2.get());

            transport.handleConnected().get();

            // now they should be true

            Assert.assertEquals(true, b1.get());
            Assert.assertEquals(true, b2.get());

            transport.handleDisconnected().get();

            // and false again

            Assert.assertEquals(false, b1.get());
            Assert.assertEquals(false, b2.get());
        }

        transport.handleConnected().get();

        // as both are disconnected, they should remain false

        Assert.assertEquals(false, b1.get());
        Assert.assertEquals(false, b2.get());
    }*/
}
