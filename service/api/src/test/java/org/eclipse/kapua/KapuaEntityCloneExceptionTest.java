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
package org.eclipse.kapua;

import org.eclipse.kapua.model.KapuaEntity;
import org.eclipse.kapua.qa.markers.Categories;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

@Category(Categories.junitTests.class)
public class KapuaEntityCloneExceptionTest extends Assert {

    String[] entityType;
    KapuaEntity[] kapuaEntity;
    Throwable[] throwables;

    @Before
    public void initialize() {
        entityType = new String[]{"Entity Type", null};
        kapuaEntity = new KapuaEntity[]{Mockito.mock(KapuaEntity.class), null};
        throwables = new Throwable[]{new Throwable(), null};
    }

    @Test
    public void kapuaEntityCloneExceptionStringKapuaEntityParametersTest() {
        for (String type : entityType) {
            for (KapuaEntity entity : kapuaEntity) {
                KapuaEntityCloneException kapuaEntityCloneException = new KapuaEntityCloneException(type, entity);
                assertEquals("Expected and actual values should be the same.", KapuaRuntimeErrorCodes.ENTITY_CLONE_ERROR, kapuaEntityCloneException.getCode());
                assertEquals("Expected and actual values should be the same.", type, kapuaEntityCloneException.getEntityType());
                assertEquals("Expected and actual values should be the same.", entity, kapuaEntityCloneException.getEntity());
                assertNull("Null expected.", kapuaEntityCloneException.getCause());
                assertEquals("Expected and actual values should be the same.", "Severe error while cloning: " + type, kapuaEntityCloneException.getMessage());
            }
        }
    }

    @Test
    public void kapuaEntityCloneExceptionThrowableStringKapuaEntityParametersTest() {
        for (String type : entityType) {
            for (KapuaEntity entity : kapuaEntity) {
                for (Throwable throwable : throwables) {
                    KapuaEntityCloneException kapuaEntityCloneException = new KapuaEntityCloneException(throwable, type, entity);
                    assertEquals("Expected and actual values should be the same.", KapuaRuntimeErrorCodes.ENTITY_CLONE_ERROR, kapuaEntityCloneException.getCode());
                    assertEquals("Expected and actual values should be the same.", type, kapuaEntityCloneException.getEntityType());
                    assertEquals("Expected and actual values should be the same.", entity, kapuaEntityCloneException.getEntity());
                    assertEquals("Expected and actual values should be the same.", throwable, kapuaEntityCloneException.getCause());
                    assertEquals("Expected and actual values should be the same.", "Severe error while cloning: " + type, kapuaEntityCloneException.getMessage());
                }
            }
        }
    }

    @Test(expected = KapuaEntityCloneException.class)
    public void throwingExceptionStringKapuaEntityParametersTest() {
        for (String type : entityType) {
            for (KapuaEntity entity : kapuaEntity) {
                throw new KapuaEntityCloneException(type, entity);
            }
        }
    }

    @Test(expected = KapuaEntityCloneException.class)
    public void throwingExceptionThrowableStringKapuaEntityParametersTest() {
        for (String type : entityType) {
            for (KapuaEntity entity : kapuaEntity) {
                for (Throwable throwable : throwables) {
                    throw new KapuaEntityCloneException(throwable, type, entity);
                }
            }
        }
    }
}  