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
package org.eclipse.kapua.transport.mqtt.mqtt.exception;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.transport.mqtt.exception.MqttClientConnectException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.URI;
import java.net.URISyntaxException;

@Category(JUnitTests.class)
public class MqttClientConnectExceptionTest extends Assert {

    @Test
    public void constructorValidTest() throws URISyntaxException {
        URI[] uriPermittedFormats = { new URI("https://john.doe@www.example.com:123/forum/questions/?tag=networking&order=newest#top"), new URI("ldap://[2001:db8::7]/c=GB?objectClass?one"), new URI("mailto:Username.example@example.com"), new URI("news:comp.infosystems.www.servers.unix"), new URI("tel:+1-816-555-1212"), new URI("telnet://192.0.2.16:80/"), new URI("urn:oasis:names:specification:docbook:dtd:xml:4.1.2")};
        for (URI value : uriPermittedFormats) {
            MqttClientConnectException exception = new MqttClientConnectException("clientId", "username", value);
            assertEquals("Expected and actual values should be the same!", "clientId", exception.getClientId());
            assertEquals("Expected and actual values should be the same!", "username", exception.getUsername());
            assertEquals(value, exception.getUri());
        }
    }

    @Test
    public void constructorURINullTest() {
        MqttClientConnectException exception = new MqttClientConnectException("clientId", "username", null);
        assertEquals("Expected and actual values should be the same!", "clientId", exception.getClientId());
        assertEquals("Expected and actual values should be the same!", "username", exception.getUsername());
        assertNull("Null expected!", exception.getUri());
    }
}
