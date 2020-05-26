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
package org.eclipse.kapua.commons.model;

import org.eclipse.kapua.commons.model.id.KapuaEid;
import org.eclipse.kapua.commons.service.internal.cache.EntityCache;
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
public class KapuaEidTest {

    private final static Random RANDOM = RandomUtils.getInstance();

    private final BigInteger eid;

    // bug in eclipse cannot convert this to a single paramater test
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
    public void test() {
        KapuaEid kid = new KapuaEid(eid);
        String sid = kid.toCompactId();

        Assert.assertEquals(eid, kid.getId());
        Assert.assertEquals(eid.toString(), kid.toString());

        KapuaEid kid1 = KapuaEid.parseCompactId(sid);
        Assert.assertEquals(eid, kid1.getId());
        Assert.assertEquals(kid.toString(), kid1.toString());
        Assert.assertEquals(kid.toCompactId(), kid1.toCompactId());
    }

    @Test
    public void testParseKapuaId() {
        KapuaEid kapuaEid = new KapuaEid(eid);
        KapuaEid parsedKapuaEid = KapuaEid.parseKapuaId(kapuaEid);

        Assert.assertTrue(kapuaEid == parsedKapuaEid);
        Assert.assertEquals(kapuaEid, parsedKapuaEid);
        Assert.assertEquals(eid, kapuaEid.getId());
        Assert.assertEquals(eid.toString(), kapuaEid.toString());

        KapuaId kapuaIdAny = KapuaId.ANY;
        KapuaEid parsedKapuaIdAny = KapuaEid.parseKapuaId(kapuaIdAny);

        Assert.assertTrue(parsedKapuaIdAny != kapuaIdAny);
        Assert.assertEquals(parsedKapuaIdAny.getId(), kapuaIdAny.getId());
    }

    @Test
    public void hashCodeTest() {
        KapuaEid kapuaEid = new KapuaEid(eid);

        int actualHash = kapuaEid.hashCode();
        int expectedHash = 31 + eid.hashCode();
        Assert.assertEquals("eid.hashCode()", expectedHash, actualHash);

    }

    @Test
    public void equalsSameReferanceTest() {
        KapuaEid kapuaEid = new KapuaEid(eid);
        Assert.assertTrue("Expected true", kapuaEid.equals(kapuaEid));
    }

    @Test
    public void equalsNullTest() {
        KapuaEid kapuaEid = new KapuaEid(eid);
        Assert.assertFalse("Expected false", kapuaEid.equals(null));
    }

    @Test
    public void equalsTypeTest() {
        KapuaEid kapuaEid = new KapuaEid(eid);
        EntityCache entityCache = new EntityCache("idName");
        Object[] values = new Object[]{"name", 1, 's', 1.25, entityCache};
        for (Object o : values) {
            Assert.assertFalse("Expected false", kapuaEid.equals(o));
        }
    }

    @Test
    public void equalsEidNullTest() {
        KapuaEid kapuaEidNull = new KapuaEid();
        KapuaEid kapuaEid = new KapuaEid(eid);
        Assert.assertFalse("Expected false", kapuaEidNull.equals(kapuaEid));
    }

    @Test
    public void equalsDifferentEidValuesTest() {
        KapuaEid kapuaEid = new KapuaEid(eid);
        BigInteger otherId = new BigInteger(64, RANDOM);
        KapuaEid kapuaEidOtherId = new KapuaEid(otherId);
        Assert.assertFalse("Expected false", kapuaEid.equals(kapuaEidOtherId));
    }

    @Test
    public void equalsSameEidValuesTest() {
        KapuaEid kapuaEid = new KapuaEid(eid);
        KapuaEid kapuaEidSameId = new KapuaEid(eid);
        Assert.assertTrue("Expected true", kapuaEid.equals(kapuaEidSameId));
    }
}
