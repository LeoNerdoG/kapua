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
package org.eclipse.kapua.model.xml;

import org.eclipse.kapua.qa.markers.Categories;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Date;

@Category(Categories.junitTests.class)
public class XmlAdaptedTypeValueObjectTest extends Assert {

    @Test
    public void xmlAdaptedNameTypeValueObjectTest() {
        XmlAdaptedNameTypeValueObject xmlAdaptedNameTypeValueObject = new XmlAdaptedNameTypeValueObject();
        assertNull("Null expected.", xmlAdaptedNameTypeValueObject.getValueType());
        assertNull("Null expected.", xmlAdaptedNameTypeValueObject.getValue());
    }

    @Test
    public void setAndGetValueTypeTest() {
        XmlAdaptedNameTypeValueObject xmlAdaptedNameTypeValueObject = new XmlAdaptedNameTypeValueObject();
        Class[] classes = {String.class, Integer.class, Long.class, Float.class, Double.class, Boolean.class, Date.class, Byte.class};

        assertNull("Null expected.", xmlAdaptedNameTypeValueObject.getValueType());

        for (Class clazz : classes) {
            xmlAdaptedNameTypeValueObject.setValueType(clazz);
            assertEquals("Expected and actual values should be the same.", clazz, xmlAdaptedNameTypeValueObject.getValueType());
        }
    }

    @Test
    public void setAndGetValueTest() {
        XmlAdaptedNameTypeValueObject xmlAdaptedNameTypeValueObject = new XmlAdaptedNameTypeValueObject();
        String[] values = {"Name", null};

        assertNull("Null expected.", xmlAdaptedNameTypeValueObject.getValue());

        for (String value : values) {
            xmlAdaptedNameTypeValueObject.setValue(value);
            assertEquals("Expected and actual values should be the same.", value, xmlAdaptedNameTypeValueObject.getValue());
        }
    }
}