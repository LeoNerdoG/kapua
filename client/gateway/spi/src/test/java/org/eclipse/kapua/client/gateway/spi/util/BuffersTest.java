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
package org.eclipse.kapua.client.gateway.spi.util;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;

@Category(JUnitTests.class)
public class BuffersTest extends Assert {

    @Test
    public void buffersTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Buffers> constructor = Buffers.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void wrapByteArrayDataNullTest() {
        assertEquals("Expected and actual value should be the same", null, Buffers.wrap(null));
    }

    @Test
    public void wrapTest() {
        final byte[] byteArray = new byte[]{1, 3, 5, 7, 9};
        final ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        byteBuffer.position(byteBuffer.limit());
        assertEquals("Expected and actual value should be the same", byteBuffer, Buffers.wrap(byteArray));
    }

    @Test
    public void toByteArrayBufferNullTest() {
        assertEquals("Expected and actual value should be the same", null, Buffers.toByteArray(null));
    }

    @Test
    public void toByteArrayTest() {
        ByteBuffer byteBuffer = Mockito.mock(ByteBuffer.class);
        assertThat("Instance of Buffers expected.", Buffers.toByteArray(byteBuffer), IsInstanceOf.instanceOf(byte[].class));
    }

    /*@Test
    public void test1() {
        final ByteBuffer result = Buffers.wrap(new byte[0]);
        Assert.assertNotNull(result);
        Assert.assertFalse(result.hasRemaining());
    }

    @Test
    public void test2() {
        final ByteBuffer result = Buffers.wrap(null);
        Assert.assertNull(result);
    }

    @Test
    public void test3() {
        final ByteBuffer result = Buffers.wrap(new byte[] { 12 });
        Assert.assertNotNull(result);
        result.flip();
        Assert.assertEquals(1, result.remaining());
        Assert.assertEquals(12, result.get());
    }

    @Test
    public void test4() {
        final ByteBuffer input = ByteBuffer.allocate(1);
        input.put((byte) 12);

        input.flip();

        final byte[] result = Buffers.toByteArray(input);
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.length);
        Assert.assertEquals(12, result[0]);
    }

    @Test
    public void test5() {
        final byte[] result = Buffers.toByteArray(null);
        Assert.assertNull(result);
    }*/

}
