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
package org.eclipse.kapua.integration.misc;

import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.commons.event.JsonServiceEventMarshaler;
import org.eclipse.kapua.commons.util.xml.XmlUtil;
import org.eclipse.kapua.event.ServiceEvent;
import org.eclipse.kapua.event.ServiceEventBusException;
import org.eclipse.kapua.qa.common.TestJAXBContextProvider;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.StringWriter;

@Category(JUnitTests.class)
public class JsonServiceEventMarshalerTest extends Assert {

    ServiceEvent serviceEvent;
    JsonServiceEventMarshaler jsonServiceEventMarshaler;
    StringWriter stringWriter;

    @Before
    public void createInstanceOfClasses() {
        serviceEvent = new ServiceEvent();
        jsonServiceEventMarshaler = new JsonServiceEventMarshaler();
        stringWriter = new StringWriter();
    }

    @Test(expected = ServiceEventBusException.class)
    public void marshalJsonWithNullContextTest() throws Exception {
        jsonServiceEventMarshaler.marshal(null);
    }

    @Test
    public void marshalJsonWithoutContextTest() throws ServiceEventBusException {
        stringWriter.write("{\n}");
        String expectedValues = stringWriter.toString();
        XmlUtil.setContextProvider(new TestJAXBContextProvider());
        assertEquals("not_equals", expectedValues, jsonServiceEventMarshaler.marshal(serviceEvent));
    }

    @Test
    public void marshalJsonWithContextTest() throws ServiceEventBusException {
        stringWriter.write("{\n" +
                "   \"id\" : \"id\",\n" +
                "   \"contextId\" : \"contextId\",\n" +
                "   \"entityType\" : \"entityType\",\n" +
                "   \"status\" : \"SENT\",\n" +
                "   \"note\" : \"note\"\n" +
                "}");

        String expectedValues = stringWriter.toString();
        serviceEvent.setId("id");
        serviceEvent.setContextId("contextId");
        serviceEvent.setEntityType("entityType");
        serviceEvent.setStatus(ServiceEvent.EventStatus.SENT);
        serviceEvent.setNote("note");

        XmlUtil.setContextProvider(new TestJAXBContextProvider());
        assertEquals("not_equals", expectedValues, jsonServiceEventMarshaler.marshal(serviceEvent));
    }

    @Test(expected = NullPointerException.class)
    public void unmarshalJsonWithNullContextTest() throws Exception {
        jsonServiceEventMarshaler.unmarshal(null);
    }

    @Test
    public void unmarshalJsonWithContextTest() throws KapuaException {
        XmlUtil.setContextProvider(new TestJAXBContextProvider());
        ServiceEvent elements = jsonServiceEventMarshaler.unmarshal("{\n" +
                "   \"id\" : \"id\",\n" +
                "   \"contextId\" : \"contextId\",\n" +
                "   \"entityType\" : \"entityType\",\n" +
                "   \"status\" : \"SENT\",\n" +
                "   \"note\" : \"note\"\n" +
                "}");

        assertEquals("not_equals", "id", elements.getId());
        assertEquals("not_equals", "contextId", elements.getContextId());
        assertEquals("not_equals", "entityType", elements.getEntityType());
        assertEquals("not_equals", ServiceEvent.EventStatus.SENT, elements.getStatus());
    }

    @Test
    public void getContentTypeTest() {
        assertEquals("not_equals", "application/json", jsonServiceEventMarshaler.getContentType());
    }
}
