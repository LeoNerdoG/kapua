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
package org.eclipse.kapua.app.api.resources.v1.resources.model;

import org.eclipse.kapua.KapuaIllegalArgumentException;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Date;

@Category(JUnitTests.class)
public class DateParamTest extends Assert {

    @Test
    public void dateParamTest() throws KapuaIllegalArgumentException {
        String[] dates = {"2020-10-27T21:32:52", " 2020-10-27T21:32:52+02:00", " 2020-10-27T19:32:52Z", " 2020-10-27T19:32:52+00:00"};

        for (int i = 0; i < dates.length; i++) {
            DateParam dateParam = new DateParam(dates[i]);
            assertTrue("True expected", dateParam.getDate() instanceof Date);
            assertNotNull("Null not expected.", dateParam.getDate());
        }
    }

    @Test
    public void dateParamNullTest() throws KapuaIllegalArgumentException {
        DateParam dateParam = new DateParam(null);
        try {
            dateParam.getDate();
            fail("NullPointerException expected.");
        } catch (Exception e) {
            assertEquals("NullPointerException expected.", new NullPointerException().toString(), e.toString());
        }
    }

    @Test(expected = KapuaIllegalArgumentException.class)
    public void dateParamInvalidDateTest() throws KapuaIllegalArgumentException {
        String[] invalidDates = {"", "2020-10-27", " 2020-10-27T21:32 ", " 2020-10-27T25:32:52+02:00", " 2020-10-27T21:32"};

        for (String invalidDate : invalidDates) {
            new DateParam(invalidDate);
        }
    }
}