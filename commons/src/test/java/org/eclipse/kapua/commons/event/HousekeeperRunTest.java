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
package org.eclipse.kapua.commons.event;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Category(JUnitTests.class)
public class HousekeeperRunTest extends Assert {

    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date date;
    {
        try {
            date = format.parse("29/5/2020");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    HousekeeperRun houseKeeper;

    @Before
    public void createInstanceOfClasses() {
        houseKeeper = new HousekeeperRun();
    }

    @Test
    public void setAndGetServiceNullTest() {
        houseKeeper.setService(null);
        assertNull("not_null", houseKeeper.getService());
    }

    @Test
    public void setAndGetServiceTest() {
        String[] permittedValues = {"", "!@#$%^^&**(-()_)+/|", "regularNaming", "regular Naming", "49", "regularNaming49", "NAMING", "246465494135646120009090049684646496468456468496846464968496844"};
        for (String value : permittedValues) {
            houseKeeper.setService(value);
            assertEquals("not_equals", value, houseKeeper.getService());
        }
    }

    @Test
    public void setAndGetLastRunOnNullTest() {
        houseKeeper.setLastRunOn(null);
        assertNull("not_null", houseKeeper.getLastRunOn());
    }

    @Test
    public void setAndGetLastRunOnTest() {
        houseKeeper.setLastRunOn(date);
        assertEquals("not_matching", houseKeeper.getLastRunOn(), date);
    }

    @Test
    public void setAndGetLastRunByNullTest() {
        houseKeeper.setLastRunBy(null);
        assertNull("not_null", houseKeeper.getLastRunBy());
    }

    @Test
    public void setAndGetLastRunByTest() {
        String[] permittedValues = {"", "!@#$%^^&**(-()_)+/|", "regularNaming", "regular Naming", "49", "regularNaming49", "NAMING", "246465494135646120009090049684646496468456468496846464968496844"};
        for (String value : permittedValues) {
            houseKeeper.setLastRunBy(value);
            assertEquals("not_equals", value, houseKeeper.getLastRunBy());
        }
    }

    @Test
    public void setAndGetVersionNullTest() {
        houseKeeper.setVersion(null);
        assertNull("not_null", houseKeeper.getVersion());
    }

    @Test
    public void setAndGetVersionRegularValuesTest() {
        Long[] permittedValues = {-9223372036854775808L, 9223372036854775807L, 12345678910L, 1234L, 88928L, 1L};
        for(Long value : permittedValues) {
            houseKeeper.setVersion(value);
            assertEquals("does_not_contain", value, houseKeeper.getVersion());
        }
    }
}