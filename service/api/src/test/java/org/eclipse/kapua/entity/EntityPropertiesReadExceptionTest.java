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

@Category(Categories.junitTests.class)
public class EntityPropertiesReadExceptionTest extends Assert {

    @Test
    public void entityPropertiesReadExceptionTest() {
        Exception[] exception = {new Exception(), null};
        String[] attributes = {"Attribute", null};
        String[] stringProperties = {"String Properties", null};

        for (Exception ex : exception) {
            for (String attribute : attributes) {
                for (String properties : stringProperties) {
                    EntityPropertiesReadException entityPropertiesReadException = new EntityPropertiesReadException(ex, attribute, properties);

                    assertEquals("Expected and actual values should be the same.", attribute, entityPropertiesReadException.getAttribute());
                    assertEquals("Expected and actual values should be the same.", properties, entityPropertiesReadException.getStringProperties());
                    assertEquals("Expected and actual values should be the same.", KapuaRuntimeErrorCodes.ENTITY_PROPERTIES_READ_ERROR, entityPropertiesReadException.getCode());
                    assertEquals("Expected and actual values should be the same.", ex, entityPropertiesReadException.getCause());
                }
            }
        }
    }
}