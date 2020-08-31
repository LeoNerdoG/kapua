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

import java.util.concurrent.Executor;

@Category(JUnitTests.class)
public class TransportProxyTest extends Assert {

    @Test(expected = IllegalStateException.class)
    public void closeTest() {
        final Transport transport = Mockito.mock(Transport.class);
        final Executor executor = Mockito.mock(Executor.class);
        TransportProxy transportProxy = TransportProxy.proxy(transport, executor);
        transportProxy.close();
        assertNull(transportProxy.listen(Mockito.mock(Transport.Listener.class)));
    }

    @Test
    public void listenTest() {
        final Transport transport = Mockito.mock(Transport.class);
        final Executor executor = Mockito.mock(Executor.class);
        TransportProxy transportProxy = TransportProxy.proxy(transport, executor);
        final Transport.Listener listener = Mockito.mock(Transport.Listener.class);
        assertThat("Instance of ListenerHandle expected.", transportProxy.listen(listener), IsInstanceOf.instanceOf(Transport.ListenerHandle.class));
    }

    @Test(expected = NullPointerException.class)
    public void listenListenerNullTest() {
        final Transport transport = Mockito.mock(Transport.class);
        final Executor executor = Mockito.mock(Executor.class);
        TransportProxy transportProxy = TransportProxy.proxy(transport, executor);
        assertThat("Instance of ListenerHandle expected.", transportProxy.listen(null), IsInstanceOf.instanceOf(Transport.ListenerHandle.class));
    }

    @Test
    public void proxyTest() {
        final Transport transport = Mockito.mock(Transport.class);
        final Executor executor = Mockito.mock(Executor.class);
        assertThat("Instance of TransportProxy expected.", TransportProxy.proxy(transport, executor), IsInstanceOf.instanceOf(TransportProxy.class));
    }

    @Test(expected = NullPointerException.class)
    public void proxyTransportNullTest() {
        final Executor executor = Mockito.mock(Executor.class);
        assertThat("Instance of TransportProxy expected.", TransportProxy.proxy(null, executor), IsInstanceOf.instanceOf(TransportProxy.class));
    }

    @Test(expected = NullPointerException.class)
    public void proxyExecutorNullTest() {
        final Transport transport = Mockito.mock(Transport.class);
        assertThat("Instance of TransportProxy expected.", TransportProxy.proxy(transport, null), IsInstanceOf.instanceOf(TransportProxy.class));
    }

    @Test(expected = NullPointerException.class)
    public void proxyTransportNullAndExecutorNullTest() {
        assertThat("Instance of TransportProxy expected.", TransportProxy.proxy(null, null), IsInstanceOf.instanceOf(TransportProxy.class));
    }

    /*private static final class TestListener {

        private static final long DEFAULT_TIMEOUT = Long.getLong("defaultTimeout", 500L); // 500ms default

        private final Semaphore lock = new Semaphore(0);

        private boolean state;

        public void set(boolean state) {
            this.state = state;
            lock.release();
        }

        public boolean get() {
            return state;
        }

        public Boolean await(long timeoutMillis) throws InterruptedException {
            if (lock.tryAcquire(timeoutMillis, TimeUnit.MILLISECONDS)) {
                return state;
            }
            return null;
        }

        public Boolean await() throws InterruptedException {
            return await(DEFAULT_TIMEOUT);
        }
    }

    private ExecutorService executor;

    @Before
    public void createExecutor() {
        executor = Executors.newSingleThreadExecutor();
    }

    @After
    public void disposeExecutor() {
        executor.shutdown();
    }

    @Test
    public void test1() throws Exception {

        final TestListener b1 = new TestListener();
        final TestListener b2 = new TestListener();

        final TransportAsync transport = new TransportAsync(executor);
        transport.handleConnected();

        try (final TransportProxy transportProxy = TransportProxy.proxy(transport, executor)) {

            Assert.assertEquals(false, b1.get());
            Assert.assertEquals(false, b2.get());

            final ListenerHandle h1 = transportProxy.listen(b1::set);

            Assert.assertEquals(true, b1.await());
            Assert.assertNull(b2.await());

            final ListenerHandle h2 = transportProxy.listen(b2::set);

            Assert.assertNull(b1.await());
            Assert.assertEquals(true, b2.await());

            transport.handleDisconnected();

            Assert.assertEquals(false, b1.await());
            Assert.assertEquals(false, b2.await());

            h2.close();

            transport.handleConnected();

            Assert.assertEquals(true, b1.await());
            Assert.assertNull(b2.await());

            h1.close();

            transport.handleDisconnected();

            Assert.assertNull(b1.await());
            Assert.assertNull(b2.await());

        }
    }

    @Test(expected = NullPointerException.class)
    public void testFail1() {
        TransportProxy.proxy(null, executor);
    }

    @Test(expected = NullPointerException.class)
    public void testFail2() {
        TransportProxy.proxy(new TransportAsync(executor), null);
    }

    @Test(expected = IllegalStateException.class)
    public void testFail3() {
        final TransportProxy proxy = TransportProxy.proxy(new TransportAsync(executor), executor);
        proxy.close();
        proxy.listen((state) -> {
        });
    }*/

}
