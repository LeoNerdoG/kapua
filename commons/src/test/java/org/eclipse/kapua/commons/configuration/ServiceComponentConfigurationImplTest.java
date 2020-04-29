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
package org.eclipse.kapua.commons.configuration;

import org.eclipse.kapua.commons.configuration.metatype.TocdImpl;
import org.eclipse.kapua.model.config.metatype.KapuaTocd;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Category(JUnitTests.class)
public class ServiceComponentConfigurationImplTest extends Assert {

    ServiceComponentConfigurationImpl serviceComponentConfiguration;

    @Before
    public void createInstanceOfClass() {
        serviceComponentConfiguration = new ServiceComponentConfigurationImpl();
    }

    @Test
    public void createInstanceWithSecondConstructorTest() {
        ServiceComponentConfigurationImpl componentConfiguration = new ServiceComponentConfigurationImpl("12");
        assertEquals(componentConfiguration.getId(), "12");
    }

    @Test
    public void getIdIfNullTest() {
        assertNull(serviceComponentConfiguration.getId());
    }

    @Test
    public void getIdIfIsSetTest() {
        serviceComponentConfiguration.setId("1");
        assertEquals(serviceComponentConfiguration.getId(), "1");
    }

    @Test
    public void setIdToNullValueTest() {
        serviceComponentConfiguration.setId(null);
        assertNull(serviceComponentConfiguration.getId());
    }

    @Test
    public void setIdToLargeValueTest() {
        serviceComponentConfiguration.setId("12312312312312331232313123123123");
        assertEquals(serviceComponentConfiguration.getId(), "12312312312312331232313123123123");
    }

    @Test
    public void setIdToRegularValueTest() {
        serviceComponentConfiguration.setId("2");
        assertEquals(serviceComponentConfiguration.getId(), "2");
    }

    @Test
    public void setIdToSymbolsTest() {
        serviceComponentConfiguration.setId("@!#");
        assertEquals(serviceComponentConfiguration.getId(), "@!#");
    }

    @Test
    public void setIdToEmptyStringTest() {
        serviceComponentConfiguration.setId("");
        assertEquals(serviceComponentConfiguration.getId(), "");
    }

    @Test
    public void getNameIfNullTest() {
        assertNull(serviceComponentConfiguration.getName());
    }

    @Test
    public void setNameIfNotNullTest() {
        serviceComponentConfiguration.setName("name");
        assertEquals(serviceComponentConfiguration.getName(), "name");
    }

    @Test
    public void setNameToNullTest() {
        serviceComponentConfiguration.setName(null);
        assertNull(serviceComponentConfiguration.getName());
    }

    @Test
    public void setNameToRegularValueTest() {
        serviceComponentConfiguration.setName("regularName");
        assertEquals(serviceComponentConfiguration.getName(), "regularName");
    }

    @Test
    public void setNameToNameThatContainsSpacesTest() {
        serviceComponentConfiguration.setName("regular Name");
        assertEquals(serviceComponentConfiguration.getName(), "regular Name");
    }

    @Test
    public void setNameToNameThatContainsSymbolsTest() {
        serviceComponentConfiguration.setName("regular Name !$%&/&@");
        assertEquals(serviceComponentConfiguration.getName(), "regular Name !$%&/&@");
    }

    @Test
    public void setDefinitionToNullTest() {
        serviceComponentConfiguration.setDefinition(null);
        assertNull(serviceComponentConfiguration.getDefinition());
    }

    @Test
    public void setDefinitionToRegularValueTest() {
        KapuaTocd tocd = new TocdImpl();
        serviceComponentConfiguration.setDefinition(tocd);
        assertEquals(serviceComponentConfiguration.getDefinition(), tocd);
    }

    @Test
    public void setPropertiesToNullTest() {
        serviceComponentConfiguration.setProperties(null);
        assertNull(serviceComponentConfiguration.getProperties());
    }

    @Test
    public void setPropertiesToRegularValueTest() {
        Map<String, Object> properties = new Map<String, Object>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public Object get(Object key) {
                return null;
            }

            @Override
            public Object put(String key, Object value) {
                return null;
            }

            @Override
            public Object remove(Object key) {
                return null;
            }

            @Override
            public void putAll(Map<? extends String, ?> m) {

            }

            @Override
            public void clear() {

            }

            @Override
            public Set<String> keySet() {
                return null;
            }

            @Override
            public Collection<Object> values() {
                return null;
            }

            @Override
            public Set<Entry<String, Object>> entrySet() {
                return null;
            }
        };
        properties.put("property1", 10);
        serviceComponentConfiguration.setProperties(properties);
        assertEquals(serviceComponentConfiguration.getProperties(), properties);
    }
}
