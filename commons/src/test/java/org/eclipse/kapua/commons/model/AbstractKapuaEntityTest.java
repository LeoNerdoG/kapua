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
import org.eclipse.kapua.model.KapuaEntity;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.Date;

@Category(JUnitTests.class)
public class AbstractKapuaEntityTest extends Assert {

    private class ConcreteKapuaEntity extends AbstractKapuaEntity {

        public ConcreteKapuaEntity(KapuaId scopeId) {
            super(scopeId);
        }

        public ConcreteKapuaEntity(KapuaEntity entity) {
            super(entity);
        }

        @Override
        public String getType() {
            return null;
        }
    }

    @Test
    public void copyConstructorTest() {
        KapuaId scopeId = new KapuaEid();
        AbstractKapuaEntity kapuaEntity = new ConcreteKapuaEntity(scopeId);
        kapuaEntity.setId(new KapuaEid());
        kapuaEntity.setCreatedBy(new KapuaEid());
        kapuaEntity.setCreatedOn(new Date());
        AbstractKapuaEntity kapuaCopyEntity = new ConcreteKapuaEntity(kapuaEntity);
        assertEquals(" kapuaCopyEntity.getId()", kapuaEntity.getId(), kapuaCopyEntity.getId());
        assertEquals("kapuaCopyEntity.getScopeId()", kapuaEntity.getScopeId(), kapuaCopyEntity.getScopeId());
        assertEquals("kapuaCopyEntity.getCreatedBy()", kapuaEntity.getCreatedBy(), kapuaCopyEntity.getCreatedBy());
        assertEquals("kapuaCopyEntity.getCreatedOn()", kapuaEntity.getCreatedOn(), kapuaCopyEntity.getCreatedOn());
    }

    @Test
    public void prePersistsActionTest() {
        KapuaId scopeId = new KapuaEid();
        KapuaSession kapuaSession = Mockito.spy(new KapuaSession());
        AbstractKapuaEntity kapuaEntity = new ConcreteKapuaEntity(scopeId);
        KapuaSecurityUtils.setSession(kapuaSession);
        kapuaEntity.prePersistsAction();
        AbstractKapuaEntity kapuaCopyEntity = new ConcreteKapuaEntity(kapuaEntity);
        assertEquals("kapuaCopyEntity.getId()", kapuaEntity.getId(), kapuaCopyEntity.getId());
        assertEquals("kapuaCopyEntity.getCreatedBy()", kapuaEntity.getCreatedBy(), kapuaCopyEntity.getCreatedBy());
        assertEquals("kapuaCopyEntity.getCreatedOn()", kapuaEntity.getCreatedOn(), kapuaCopyEntity.getCreatedOn());
    }
}
