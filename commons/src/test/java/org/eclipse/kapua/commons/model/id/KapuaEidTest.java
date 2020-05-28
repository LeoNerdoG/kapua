/*******************************************************************************
 * Copyright (c) 2016, 2020 Eurotech and/or its affiliates and others
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
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

@Category(JUnitTests.class)
@RunWith(value = Parameterized.class)
public class KapuaEidTest extends Assert {

    private final static Random RANDOM = RandomUtils.getInstance();

    private final BigInteger eid;

    // bug in eclipse cannot convert this to a single parameter test
    // https://github.com/junit-team/junit4/wiki/Parameterized-tests
    private final long notUsed;

    @Parameters
    public static Collection<Object[]> eids() {
        return Arrays.asList(new Object[][]{
                {new BigInteger(64, RANDOM), 0L},
                {new BigInteger(64, RANDOM), 0L},
        });
    }

    public KapuaEidTest(BigInteger eid, long notUsed) {
        this.eid = eid;
        this.notUsed = notUsed;
    }

    @Test
    public void parseCompactIdTest() {
        KapuaEid kid = new KapuaEid(eid);
        String sid = kid.toCompactId();

        assertEquals(eid, kid.getId());
        assertEquals(eid.toString(), kid.toString());

        KapuaEid kid1 = KapuaEid.parseCompactId(sid);
        assertEquals(eid, kid1.getId());
        assertEquals(kid.toString(), kid1.toString());
        assertEquals(kid.toCompactId(), kid1.toCompactId());
    }

    @Test
    public void getIdTest(){
        KapuaEid kapuaEid = new KapuaEid(eid);
        assertEquals(eid, kapuaEid.getId());
    }

    @Test
    public void toStringTest(){
        BigInteger eid = new BigInteger("123");
        KapuaEid kapuaEid = new KapuaEid(eid);
        assertEquals("123", kapuaEid.toString());
    }

    @Test
    public void parseKapuaIdTest() {
        KapuaEid kapuaEid = new KapuaEid(eid);
        KapuaEid parsedKapuaEid = KapuaEid.parseKapuaId(kapuaEid);

        assertTrue(kapuaEid == parsedKapuaEid);
        assertEquals(kapuaEid, parsedKapuaEid);
        assertEquals(eid, kapuaEid.getId());
        assertEquals(eid.toString(), kapuaEid.toString());

        KapuaId kapuaIdAny = KapuaId.ANY;
        KapuaEid parsedKapuaIdAny = KapuaEid.parseKapuaId(kapuaIdAny);

        assertTrue(parsedKapuaIdAny != kapuaIdAny);
        assertEquals(parsedKapuaIdAny.getId(), kapuaIdAny.getId());
    }

    @Test
    public void hashCodeTest() {
        KapuaEid kapuaEid = new KapuaEid(eid);
        KapuaEid kapuaEidNull = new KapuaEid();

        int actualHash = kapuaEid.hashCode();
        int expectedHash = 31 + eid.hashCode();
        int actualHashNull = kapuaEidNull.hashCode();
        int expectedHashNull = 31;

        assertEquals("eid != null", expectedHash, actualHash);
        assertEquals("eid == null", expectedHashNull, actualHashNull);
    }

    @Test
    public void equalsTest() {
        KapuaEid kapuaEid = new KapuaEid(eid);
        short s = 10000;
        KapuaEid kapuaEidNull = new KapuaEid();
        BigInteger otherId = new BigInteger(64, RANDOM);
        KapuaEid kapuaEidOtherId = new KapuaEid(otherId);
        KapuaEid kapuaEidSameId = new KapuaEid(eid);


        assertTrue("Expected true", kapuaEid.equals(kapuaEid));
        assertFalse("Expected false", kapuaEid.equals(null));

        Object[] values = new Object[]{"name", 1, 's', 1.25f, 1.50d, 3L, s, false};
        for (Object object : values) {
            assertFalse("Expected false", kapuaEid.equals(object));
        }

        assertFalse("Expected false", kapuaEidNull.equals(kapuaEid));
        assertFalse("Expected false", kapuaEid.equals(kapuaEidOtherId));
        assertTrue("Expected true", kapuaEid.equals(kapuaEidSameId));
    }

}
