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
package org.eclipse.kapua.commons.model;

import org.eclipse.kapua.commons.model.id.KapuaEid;
import org.eclipse.kapua.commons.security.KapuaSecurityUtils;
import org.eclipse.kapua.commons.security.KapuaSession;
import org.eclipse.kapua.commons.util.RandomUtils;
import org.eclipse.kapua.entity.EntityPropertiesReadException;
import org.eclipse.kapua.entity.EntityPropertiesWriteException;
import org.eclipse.kapua.model.KapuaUpdatableEntity;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

@Category(JUnitTests.class)
public class AbstractKapuaUpdatableEntityTest extends Assert {

    private final static Random RANDOM = RandomUtils.getInstance();

    private class ActualKapuaUpdatableEntity extends AbstractKapuaUpdatableEntity {

        @Override
        public String getType() {
            return null;
        }

        public ActualKapuaUpdatableEntity() {
            super();
        }

        public ActualKapuaUpdatableEntity(KapuaId scopeId) {
            super(scopeId);
        }

        public ActualKapuaUpdatableEntity(KapuaUpdatableEntity entity) {
            super(entity);
        }
    }

    @Test
    public void scopeIdConstructorTest() {
        KapuaId scopeId = new KapuaEid();
        AbstractKapuaUpdatableEntity updatableEntity = new ActualKapuaUpdatableEntity(scopeId);
        assertNotNull("Expected true", updatableEntity.getScopeId());
    }

    @Test
    public void copyConstructorTest() {
        KapuaUpdatableEntity entity = new ActualKapuaUpdatableEntity();
        AbstractKapuaUpdatableEntity updatableEntity = new ActualKapuaUpdatableEntity(entity);
        updatableEntity.setModifiedOn(new Date());
        updatableEntity.setModifiedBy(new KapuaEid());
        updatableEntity.setOptlock(10);
        updatableEntity.setEntityAttributes(new Properties());
        updatableEntity.setEntityProperties(new Properties());
        assertNotNull("Expected true", updatableEntity.getModifiedOn());
        assertNotNull("Expected true", updatableEntity.getModifiedBy());
        assertNotNull("Expected true", updatableEntity.getOptlock());
        assertNotNull("Expected true", updatableEntity.getEntityAttributes());
        assertNotNull("Expected true", updatableEntity.getEntityProperties());
    }

    @Test(expected = EntityPropertiesReadException.class)
    public void getEntityAttributesTestMockito() {
        AbstractKapuaUpdatableEntity updatableEntity = Mockito.mock(AbstractKapuaUpdatableEntity.class);
        Mockito.when(updatableEntity.getEntityAttributes()).thenThrow(new EntityPropertiesReadException(new IOException(), "attributes", "%?_="));
        updatableEntity.getEntityAttributes();
    }

    @Test
    public void getEntityAttributesTest() throws IOException {
        AbstractKapuaUpdatableEntity updatableEntity = new ActualKapuaUpdatableEntity();
        Properties properties = new Properties();
        String string = "string";
        InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
        properties.load(inputStream);
        updatableEntity.setEntityAttributes(properties);
        updatableEntity.getEntityAttributes();
        assertEquals("Expected and actual values should be the same!", properties, updatableEntity.getEntityAttributes());
    }

    @Test(expected = EntityPropertiesWriteException.class)
    public void setEntityAttributesTest() {
        AbstractKapuaUpdatableEntity updatableEntity = Mockito.mock(AbstractKapuaUpdatableEntity.class);
        Mockito.doThrow(new EntityPropertiesWriteException(new IOException(), "attributes", new Properties())).when(updatableEntity).setEntityAttributes(new Properties());
        updatableEntity.setEntityAttributes(new Properties());
    }

    @Test
    public void prePersistsActionTest() {
        KapuaId scopeId = new KapuaEid();
        BigInteger eid = new BigInteger(64, RANDOM);
        AbstractKapuaUpdatableEntity updatableEntity = new ActualKapuaUpdatableEntity(scopeId);
        KapuaSession mockedSession = Mockito.mock(KapuaSession.class);
        Mockito.when(mockedSession.getUserId()).thenReturn(new KapuaEid(eid));
        KapuaSecurityUtils.setSession(mockedSession);
        updatableEntity.prePersistsAction();
        assertNotNull("Expected true", updatableEntity.getModifiedBy());
        assertNotNull("Expected true", updatableEntity.getModifiedOn());
    }

    @Test
    public void preUpdateActionTest() {
        BigInteger eid = new BigInteger(64, RANDOM);
        AbstractKapuaUpdatableEntity updatableEntity = new ActualKapuaUpdatableEntity();
        KapuaSession mockedSession = Mockito.mock(KapuaSession.class);
        Mockito.when(mockedSession.getUserId()).thenReturn(new KapuaEid(eid));
        KapuaSecurityUtils.setSession(mockedSession);
        updatableEntity.preUpdateAction();
        assertNotNull("Expected true", updatableEntity.getModifiedBy());
        assertNotNull("Expected true", updatableEntity.getModifiedOn());
    }
}