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
package org.eclipse.kapua.commons.model;

import org.eclipse.kapua.commons.model.id.KapuaEid;
import org.eclipse.kapua.commons.util.RandomUtils;
import org.eclipse.kapua.model.KapuaEntity;
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
import java.util.Random;

@Category(JUnitTests.class)
@RunWith(value = Parameterized.class)
public class AbstractKapuaEntityCreatorTest extends Assert {

    private final static Random RANDOM = RandomUtils.getInstance();

    private final BigInteger eid;

    @Parameters
    public static Iterable<Object[]> eids() {
        return Arrays.asList(new Object[]{new BigInteger(64, RANDOM)}, new Object[]{null});
    }

    public AbstractKapuaEntityCreatorTest(BigInteger eid) {
        this.eid = eid;
    }

    private class ConcreteKapuaEntityCreator<E extends KapuaEntity> extends AbstractKapuaEntityCreator<E> {

        public ConcreteKapuaEntityCreator(KapuaId scopeId) {
            super(scopeId);
        }

        public ConcreteKapuaEntityCreator(AbstractKapuaEntityCreator<E> abstractEntityCreator) {
            super(abstractEntityCreator);
        }
    }

    @Test
    public void constructorScopeIdTest() {
        KapuaId scopeId = new KapuaEid(eid);
        AbstractKapuaEntityCreator kapuaEntityCreator = new ConcreteKapuaEntityCreator(scopeId);
        AbstractKapuaEntityCreator kapuaCopyEntityCreator = new ConcreteKapuaEntityCreator(kapuaEntityCreator);
        assertEquals("Those entities should have the same scopeId.", kapuaEntityCreator.getScopeId(), kapuaCopyEntityCreator.getScopeId());
    }
}