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
import org.junit.runners.Parameterized.Parameters;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

@Category(JUnitTests.class)
@RunWith(value = Parameterized.class)
public class KapuaEidConverterTest extends Assert {

    private final static Random RANDOM = RandomUtils.getInstance();

    private final BigInteger eid;

    @Parameters
    public static Iterable<Object[]> eids() {
        return Arrays.asList(
                new Object[]{new BigInteger(64, RANDOM)},
                new Object[] {null});
    }

    public KapuaEidConverterTest(BigInteger eid) {
        this.eid = eid;
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
