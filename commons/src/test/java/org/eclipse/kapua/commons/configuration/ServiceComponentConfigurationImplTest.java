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

import org.eclipse.kapua.model.config.metatype.KapuaTad;
import org.eclipse.kapua.model.config.metatype.KapuaTicon;
import org.eclipse.kapua.model.config.metatype.KapuaTocd;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.xml.namespace.QName;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Category(JUnitTests.class)
public class ServiceComponentConfigurationImplTest {

    ServiceComponentConfigurationImpl serviceComponentConfiguration;

    @Before
    public void createInstanceOfClass() {
        serviceComponentConfiguration = new ServiceComponentConfigurationImpl();
    }

    @Test
    public void createInstanceWithOtherConstructor() {
        ServiceComponentConfigurationImpl componentConfiguration = new ServiceComponentConfigurationImpl("12");
    }

    @Test
    public void testGetIdIfNull() {
        Assert.assertNull(serviceComponentConfiguration.getId());
    }

    @Test
    public void testGetIdIfIsSet() {
        serviceComponentConfiguration.setId("1");
        Assert.assertEquals(serviceComponentConfiguration.getId(), "1");
    }

    @Test
    public void testSetIdToNullValue() {
        serviceComponentConfiguration.setId(null);
    }

    @Test
    public void testSetIdToLargeValue() {
        serviceComponentConfiguration.setId("12312312312312331232313123123123");
    }

    @Test
    public void testSetIdToRegularValue() {
        serviceComponentConfiguration.setId("2");
    }

    @Test
    public void testGetNameIfNull() {
        Assert.assertNull(serviceComponentConfiguration.getName());
    }

    @Test
    public void testGetNameIfNotNull() {
        serviceComponentConfiguration.setName("name");
        Assert.assertEquals(serviceComponentConfiguration.getName(), "name");
    }

    @Test
    public void testSetNameToNull() {
        serviceComponentConfiguration.setName(null);
        Assert.assertNull(serviceComponentConfiguration.getName());
    }

    @Test
    public void testSetNameToRegularValue() {
        serviceComponentConfiguration.setName("regularName");
        Assert.assertEquals(serviceComponentConfiguration.getName(), "regularName");
    }

    @Test
    public void testSetNameToNameThatContainsSpaces() {
        serviceComponentConfiguration.setName("regular Name");
        Assert.assertEquals(serviceComponentConfiguration.getName(), "regular Name");
    }

    @Test
    public void testSetDefinitionToNull() {
        serviceComponentConfiguration.setDefinition(null);
        Assert.assertNull(serviceComponentConfiguration.getDefinition());
    }

    public void testSetDefinitionToRegularValue() {
        KapuaTocd tocd = new KapuaTocd() {
            @Override
            public List<KapuaTad> getAD() {
                return null;
            }

            @Override
            public void setAD(List<? extends KapuaTad> icon) {

            }

            @Override
            public List<KapuaTicon> getIcon() {
                return null;
            }

            @Override
            public void setIcon(List<? extends KapuaTicon> icon) {

            }

            @Override
            public List<Object> getAny() {
                return null;
            }

            @Override
            public void setAny(List<Object> any) {

            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public void setName(String value) {

            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public void setDescription(String value) {

            }

            @Override
            public String getId() {
                return null;
            }

            @Override
            public void setId(String value) {

            }

            @Override
            public Map<QName, String> getOtherAttributes() {
                return null;
            }

            @Override
            public void setOtherAttributes(Map<QName, String> otherAttributes) {

            }
        };
        serviceComponentConfiguration.setDefinition(tocd);
        Assert.assertEquals(serviceComponentConfiguration.getDefinition(), tocd);
    }

    @Test
    public void testSetPropertiesToNull() {
        serviceComponentConfiguration.setProperties(null);
        Assert.assertNull(serviceComponentConfiguration.getProperties());
    }

    @Test
    public void testSetPropertiesToRegularValue() {
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
        Assert.assertEquals(serviceComponentConfiguration.getProperties(), properties);
    }
}
