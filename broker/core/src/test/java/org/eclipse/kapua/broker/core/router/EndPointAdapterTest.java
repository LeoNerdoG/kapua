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
package org.eclipse.kapua.broker.core.router;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.persistence.jaxb.JAXBMarshaller;
import org.eclipse.persistence.jaxb.JAXBUnmarshaller;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.LinkedList;
import java.util.List;

@Category(JUnitTests.class)
public class EndPointAdapterTest extends Assert {

    EndPointAdapter endPointAdapter;

    @Before
    public void initialize() {
        endPointAdapter = new EndPointAdapter();
    }

    @Test(expected = ClassCastException.class)
    public void marshalTest() throws Exception {
        EndPoint endPoint = new EndChainEndPoint();
        List<EndPoint> endPointList = new LinkedList<>();
        endPointList.add(endPoint);
        endPointAdapter.marshal(endPointList);
    }

    @Test
    public void unmarshalTest() throws Exception {
        Element element = Mockito.mock(Element.class);
        NodeList nodeList = Mockito.mock(NodeList.class);
        Text textNode = Mockito.mock(Text.class);
        Mockito.when(element.getChildNodes()).thenReturn(nodeList);
        Mockito.when(element.getChildNodes().getLength()).thenReturn(1);
        Mockito.when(element.getChildNodes().item(0)).thenReturn(textNode);

        assertThat("Instance of List expected.", endPointAdapter.unmarshal(element), IsInstanceOf.instanceOf(List.class));
        assertTrue("True expected", endPointAdapter.unmarshal(element).isEmpty());
    }

    @Test
    public void jaxbContextHandlerTest() {
        Marshaller marshaller = JaxbContextHandler.getMarshaller();
        Unmarshaller unmarshaller = JaxbContextHandler.getUnmarshaller();
        assertNotNull("Null not expected.", marshaller);
        assertNotNull("Null not expected.", unmarshaller);
        assertEquals("Expected and actual values should be the same.", JAXBMarshaller.class, marshaller.getClass());
        assertEquals("Expected and actual values should be the same.", JAXBUnmarshaller.class, unmarshaller.getClass());
    }
}