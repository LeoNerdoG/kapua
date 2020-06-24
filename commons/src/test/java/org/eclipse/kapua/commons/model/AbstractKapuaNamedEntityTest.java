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
import org.eclipse.kapua.model.KapuaNamedEntity;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@Category(JUnitTests.class)
@RunWith(value = Parameterized.class)
public class AbstractKapuaNamedEntityTest extends Assert {

    private final String name;

    private final String description;

    private final KapuaId scopeId = new KapuaEid();

    @Parameters
    public static Collection<Object[]> names() {
        return Arrays.asList(new Object[][]{
                {"", ""},
                {"name", "description"},
        });
    }

    public AbstractKapuaNamedEntityTest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private class ConcreteKapuaNamedEntity extends AbstractKapuaNamedEntity {

        @Override
        public String getType() {
            return null;
        }

        public ConcreteKapuaNamedEntity() {
            super();
        }

        public ConcreteKapuaNamedEntity(KapuaId scopeId) {
            super(scopeId);
        }

        public ConcreteKapuaNamedEntity(KapuaId scopeId, String name) {
            super(scopeId, name);
        }

        public ConcreteKapuaNamedEntity(KapuaNamedEntity kapuaNamedEntity) {
            super(kapuaNamedEntity);
        }
    }

    @Test
    public void constructorScopeIdTest() {
        AbstractKapuaNamedEntity namedEntity = new ConcreteKapuaNamedEntity();
        namedEntity.setScopeId(scopeId);
        assertEquals("Expected and actual values should be the same!", scopeId, namedEntity.getScopeId());
    }

    @Test
    public void constructorNameTest() {
        AbstractKapuaNamedEntity namedEntity = new ConcreteKapuaNamedEntity(scopeId);
        namedEntity.setName(name);
        AbstractKapuaNamedEntity namedCopyEntity = new ConcreteKapuaNamedEntity(scopeId, name);
        assertEquals("Expected and actual values should be the same!", namedEntity.getName(), namedCopyEntity.getName());
    }

    @Test
    public void constructorEntityTest() {
        AbstractKapuaNamedEntity namedEntity = new ConcreteKapuaNamedEntity(scopeId);
        namedEntity.setName(name);
        namedEntity.setDescription(description);
        AbstractKapuaNamedEntity namedCopyEntity = new ConcreteKapuaNamedEntity(namedEntity);
        assertEquals("Expected and actual values should be the same!", namedEntity.getName(), namedCopyEntity.getName());
        assertEquals("Expected and actual values should be the same!", namedEntity.getDescription(), namedCopyEntity.getDescription());
    }
}