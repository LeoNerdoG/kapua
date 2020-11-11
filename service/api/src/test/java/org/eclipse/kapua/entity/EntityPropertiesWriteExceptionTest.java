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
package org.eclipse.kapua.entity;

import org.eclipse.kapua.KapuaRuntimeErrorCodes;
import org.eclipse.kapua.qa.markers.Categories;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Properties;

@Category(Categories.junitTests.class)
public class EntityPropertiesWriteExceptionTest extends Assert {

    @Test
    public void entityPropertiesWriteExceptionTest() {
        Exception[] exception = {new Exception(), null};
        String[] attributes = {"Attribute", null};
        Properties[] properties = {new Properties(),null};

        for (Exception ex : exception) {
            for (String attribute : attributes) {
                for (Properties property : properties) {
                    EntityPropertiesWriteException entityPropertiesWriteException = new EntityPropertiesWriteException(ex, attribute, property);

                    assertEquals("Expected and actual values should be the same.", attribute, entityPropertiesWriteException.getAttribute());
                    assertEquals("Expected and actual values should be the same.", property, entityPropertiesWriteException.getProperties());
                    assertEquals("Expected and actual values should be the same.", KapuaRuntimeErrorCodes.ENTITY_PROPERTIES_READ_ERROR, entityPropertiesWriteException.getCode());
                    assertEquals("Expected and actual values should be the same.", ex, entityPropertiesWriteException.getCause());
                }
            }
        }
    }
}