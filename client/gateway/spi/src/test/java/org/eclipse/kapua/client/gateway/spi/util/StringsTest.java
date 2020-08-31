/*******************************************************************************
 * Copyright (c) 2017, 2020 Eurotech and/or its affiliates and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.client.gateway.spi.util;

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
public class StringsTest extends Assert {

    private final String string;

    private final String fieldName;

    public StringsTest(String string, String fieldName) {
        this.string = string;
        this.fieldName = fieldName;
    }

    @Parameters
    public static Collection<Object[]> names() {
        return Arrays.asList(new Object[][]{
                {"String", null},
                {"String", ""},
                {"string", "fieldName"},
                {"STRING", "FIELDNAME"},
                {"string name", "field name"},
                {"0123456789", "0123456789"},
                {"!#$%&'()=?⁄@‹›€°·‚,.-;:_Èˇ¿<>«‘”’ÉØ∏{}|ÆæÒuF8FFÔÓÌÏÎÅ«»Ç◊Ñˆ¯Èˇ", "!#$%&'()=?⁄@‹›€°·‚,.-;:_Èˇ¿<>«‘”’ÉØ∏{}|ÆæÒuF8FFÔÓÌÏÎÅ«»Ç◊Ñˆ¯Èˇ"},
                {"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefg", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefg"},
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonEmptyTextNullStringTest() {
        Strings.nonEmptyText(null, fieldName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonEmptyTextEmptyStringTest() {
        Strings.nonEmptyText("", fieldName);
    }

    @Test
    public void nonEmptyTextTest() {
        Strings.nonEmptyText(string, fieldName);
    }

   /* @Test(expected = IllegalArgumentException.class)
    public void test1() {
        Strings.nonEmptyText(null, "foo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test2() {
        Strings.nonEmptyText("", "foo");
    }

    @Test
    public void test3() {
        Strings.nonEmptyText("foo", "foo");
    }*/
}
