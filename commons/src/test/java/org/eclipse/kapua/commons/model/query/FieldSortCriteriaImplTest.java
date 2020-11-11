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
package org.eclipse.kapua.commons.model.query;

import org.eclipse.kapua.model.query.SortOrder;
import org.eclipse.kapua.qa.markers.Categories;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

@Category(Categories.junitTests.class)
@RunWith(value = Parameterized.class)
public class FieldSortCriteriaImplTest extends Assert {

    private final String attributeName;
    private SortOrder sortOrder;

    public FieldSortCriteriaImplTest(String attributeName) {
        this.attributeName = attributeName;
    }

    @Parameters
    public static Iterable<Object[]> attributeNames() {
        return Arrays.asList(
                new Object[]{""},
                new Object[]{"NAME"},
                new Object[]{"attributeName"},
                new Object[]{"attribute name"},
                new Object[]{"0123456789"},
                new Object[]{"!#$%&'()=?⁄@‹›€°·‚,.-;:_Èˇ¿<>«‘”’ÉØ∏{}|ÆæÒuF8FFÔÓÌÏÎÅ«»Ç◊Ñˆ¯Èˇ"},
                new Object[]{"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefg"});
    }

    @Test
    public void fieldSortCriteriaImplTest() {
        SortOrder sortOrderAscending = SortOrder.ASCENDING;
        FieldSortCriteriaImpl fieldSortCriteriaAscending = new FieldSortCriteriaImpl(attributeName, sortOrderAscending);
        assertEquals("Actual and expected values are not the same!", attributeName, fieldSortCriteriaAscending.getAttributeName());
        assertEquals("Actual and expected values are not the same!", sortOrderAscending, fieldSortCriteriaAscending.getSortOrder());
        SortOrder sortOrderDescending = SortOrder.DESCENDING;
        FieldSortCriteriaImpl fieldSortCriteriaDescending = new FieldSortCriteriaImpl(attributeName, sortOrderDescending);
        assertEquals("Actual and expected values are not the same!", attributeName, fieldSortCriteriaDescending.getAttributeName());
        assertEquals("Actual and expected values are not the same!", sortOrderDescending, fieldSortCriteriaDescending.getSortOrder());
    }
}