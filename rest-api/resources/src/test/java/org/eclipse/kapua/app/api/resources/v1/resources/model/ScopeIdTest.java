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

import org.eclipse.kapua.app.api.resources.v1.resources.exception.SessionNotPopulatedException;
import org.eclipse.kapua.commons.security.KapuaSecurityUtils;
import org.eclipse.kapua.commons.security.KapuaSession;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.math.BigInteger;
import java.util.Base64;

@Category(JUnitTests.class)
public class ScopeIdTest extends Assert {

    @Test(expected = NullPointerException.class)
    public void scopeIdNullTest() {
        new ScopeId(null);
    }

    @Test
    public void scopeIdEqualIdsTest() {
        KapuaSession kapuaSession = Mockito.mock(KapuaSession.class);
        KapuaSecurityUtils.setSession(kapuaSession);

        Mockito.when(kapuaSession.getScopeId()).thenReturn(KapuaId.ONE);
        ScopeId scopeId = new ScopeId("_");

        assertEquals("Expected and actual values should be the same.", BigInteger.ONE, scopeId.getId());
    }

    @Test
    public void scopeIdDifferentIdsTest() {
        KapuaSession kapuaSession = Mockito.mock(KapuaSession.class);
        KapuaSecurityUtils.setSession(kapuaSession);

        Mockito.when(kapuaSession.getScopeId()).thenReturn(KapuaId.ONE);
        ScopeId scopeId = new ScopeId("scopeID");

        assertEquals("Expected and actual values should be the same.", new BigInteger(Base64.getUrlDecoder().decode("scopeID")), scopeId.getId());
    }

    @Test(expected = SessionNotPopulatedException.class)
    public void scopeIdNullSessionTest() {
        KapuaSecurityUtils.clearSession();
        new ScopeId("_");
    }
}