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
package org.eclipse.kapua.transport.mqtt.test.message.mqtt;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.transport.message.mqtt.MqttPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class MqttPayloadTest extends Assert {

    MqttPayload mqttPayload;

    @Before
    public void createInstanceOfClass() {
        mqttPayload = new MqttPayload(body);
    }

    byte[] body = "!#$%&'()=?⁄@‹›€°·‚,.-;:_Èˇ¿<>«‘”’ÉØ∏{}|ÆæÒuF8FFÔÓÌÏÎÅ«»Ç◊Ñˆ¯Èˇqwertyuiopasdfghjklzxcvbnm1234567890".getBytes();

    @Test
    public void mqttPayloadSetAndGetTest() {
        mqttPayload.setBody(body);
        assertEquals("Expected and actual values should be the same!", body, mqttPayload.getBody());
    }

    @Test
    public void mqttPayloadSetAndGetNullTest() {
        mqttPayload.setBody(null);
        assertNull("Null expected!", mqttPayload.getBody());
    }

    @Test
    public void hasBodyTest() {
        mqttPayload.setBody(body);
        assertTrue("Should contain body value!", mqttPayload.hasBody());
        assertEquals("Expected and actual values should be the same!", body, mqttPayload.getBody());
    }

    @Test
    public void hasBodyNullTest() {
        mqttPayload.setBody(null);
        assertFalse("Should not contain body value!", mqttPayload.hasBody());
        assertNull("Null expected!", mqttPayload.getBody());
    }

    @Test
    public void toStringTest() {
        assertEquals("ISMkJSYnKCk9P+KBhEDigLnigLrigqzCsMK34oCaLC4tOzpfw4jLh8K/PD7Cq+KAmOKAneKAmcOJw5jiiI97fQ==", mqttPayload.toString());
    }

    @Test
    public void toStringEmptyTest() {
        mqttPayload.setBody("".getBytes());
        assertEquals("Empty string expected!", "", mqttPayload.toString());
    }
}