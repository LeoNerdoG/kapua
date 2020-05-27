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
package org.eclipse.kapua.commons.model.id;

import org.eclipse.kapua.commons.util.RandomUtils;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

@Category(JUnitTests.class)
@RunWith(value = Parameterized.class)
public class KapuaEidConverterTest extends Assert {

    private final static Random RANDOM = RandomUtils.getInstance();

    private final BigInteger eid;

    // bug in eclipse cannot convert this to a single parameter test
    // https://github.com/junit-team/junit4/wiki/Parameterized-tests
    private final long notUsed;

    @Parameterized.Parameters
    public static Collection<Object[]> eids() {
        return Arrays.asList(new Object[][]{
                {new BigInteger(64, RANDOM), 0L},
                {null, 0L},
        });
    }

    public KapuaEidConverterTest(BigInteger eid, long notUsed) {
        this.eid = eid;
        this.notUsed = notUsed;
    }

    @Test
    public void convertToDatabaseColumnTest() {
        KapuaEid kapuaEid = new KapuaEid(eid);
        KapuaEidConverter kapuaEidConverter = new KapuaEidConverter();
        assertEquals("kapuaEidConverter.convertToDatabaseColumn(kapuaEid)", eid, kapuaEidConverter.convertToDatabaseColumn(kapuaEid));
    }

    @Test
    public void convertToEntityAttributeTest() {
        KapuaEidConverter kapuaEidConverter = new KapuaEidConverter();
        KapuaEid kapuaEid = kapuaEidConverter.convertToEntityAttribute(eid);
        assertEquals("kapuaEid.getId()", eid, kapuaEid.getId());
    }

}
