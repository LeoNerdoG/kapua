/*******************************************************************************
 * Copyright (c) 2017, 2020 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.commons.util.xml;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.xml.bind.JAXBException;
import java.lang.reflect.Constructor;

@Category(JUnitTests.class)
public class XmlUtilTest extends Assert {

    @Test
    public void constructorTest() throws Exception {
        Constructor<XmlUtil> xmlUtilConstruct = XmlUtil.class.getDeclaredConstructor();
        xmlUtilConstruct.setAccessible(true);
        xmlUtilConstruct.newInstance();
    }

    @Test
    public void setContextNullProviderTest() {
        XmlUtil.setContextProvider(null);
    }

    @Test
    public void marshalTest() {
        Object[] jaxbElements = new Object[]{123, "string", "s"};

        // JAXBException
        for (int i = 0; i < jaxbElements.length; i++) {
            try {
                assertNotNull(XmlUtil.marshal(jaxbElements[i]));
                fail("Exception expected for: " + jaxbElements[i]);
            } catch (JAXBException ex) {
                // Expected
            }
        }
        // Exception
        try {
            XmlUtil.marshal(null);
            fail("Exception expected for null");
        } catch (Exception ex) {
            // Expected
        }
    }

    @Test
    public void marshalJsonTest() {
        Object[] jaxbElements = new Object[]{123, "string", "s"};

        // JAXBException
        for (int i = 0; i < jaxbElements.length; i++) {
            try {
                assertNotNull(XmlUtil.marshalJson(jaxbElements[i]));
                fail("Exception expected for: " + jaxbElements[i]);
            } catch (JAXBException ex) {
                // Expected
            }
        }
        // Exception
        try {
            XmlUtil.marshalJson(null);
            fail("Exception expected for null");
        } catch (Exception ex) {
            // Expected
        }
    }

    @Test
    public void unmarshalTest() {
        String[] listOfStrings = new String[]{"string", "s123@#", null};
        Class[] listOfClasses = new Class[]{Integer.class, Character.class, String.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class};

        for (int i = 0; i < listOfClasses.length; i++) {
            for (int j = 0; j < listOfStrings.length; j++) {
                try {
                    assertNotNull(XmlUtil.unmarshal(listOfStrings[j], listOfClasses[i]));
                    fail("Exception expected for: " + listOfStrings[j]);
                } catch (Exception ex) {
                    // Expected
                }
            }
        }
        for (int i = 0; i < listOfStrings.length; i++) {
            try {
                assertNotNull(XmlUtil.unmarshal(listOfStrings[i], null));
                fail("Exception expected for: " + listOfStrings[i]);
            } catch (Exception ex) {
                // Expected
            }
        }
    }

    @Test
    public void unmarshalWithUriTest() {
        String[] listOfStrings = new String[]{"string", "s123@#", null};
        Class[] listOfClasses = new Class[]{Integer.class, Character.class, String.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class};

        for (int i = 0; i < listOfClasses.length; i++) {
            for (int j = 0; j < listOfStrings.length; j++) {
                try {
                    assertNotNull(XmlUtil.unmarshal(listOfStrings[j], listOfClasses[i], "String"));
                    fail("Exception expected for: " + listOfStrings[j]);
                } catch (Exception ex) {
                    // Expected
                }
            }
        }
        for (int i = 0; i < listOfStrings.length; i++) {
            try {
                assertNotNull(XmlUtil.unmarshal(listOfStrings[i], null, "String"));
                fail("Exception expected for: " + listOfStrings[i]);
            } catch (Exception ex) {
                // Expected
            }
        }
    }

    @Test
    public void unmarshalJsonTest() {
        String[] listOfStrings = new String[]{"string", "s123@#", null};
        Class[] listOfClasses = new Class[]{Integer.class, Character.class, String.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class};

        for (int i = 0; i < listOfClasses.length; i++) {
            for (int j = 0; j < listOfStrings.length; j++) {
                try {
                    assertNotNull(XmlUtil.unmarshalJson(listOfStrings[j], listOfClasses[i], "String"));
                    fail("Exception expected for: " + listOfStrings[j]);
                } catch (Exception ex) {
                    // Expected
                }
            }
        }
        for (int i = 0; i < listOfClasses.length; i++) {
            for (int j = 0; j < listOfStrings.length; j++) {
                try {
                    assertNotNull(XmlUtil.unmarshalJson(listOfStrings[j], listOfClasses[i], null));
                    fail("Exception expected for: " + listOfStrings[j]);
                } catch (Exception ex) {
                    // Expected
                }
            }
        }
        for (int i = 0; i < listOfStrings.length; i++) {
            try {
                assertNotNull(XmlUtil.unmarshalJson(listOfStrings[i], null, "String"));
                fail("Exception expected for: " + listOfStrings[i]);
            } catch (Exception ex) {
                // Expected
            }
        }
        for (int i = 0; i < listOfStrings.length; i++) {
            try {
                assertNotNull(XmlUtil.unmarshalJson(listOfStrings[i], null, null));
                fail("Exception expected for: " + listOfStrings[i]);
            } catch (Exception ex) {
                // Expected
            }
        }
    }
}
