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
package org.eclipse.kapua.app.api.core.exception;

import org.eclipse.kapua.KapuaEntityNotFoundException;
import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KapuaEntityNotFoundExceptionMapperTest extends Assert {

    @Test
    public void toResponseTest() {
        KapuaEntityNotFoundExceptionMapper kapuaEntityNotFoundExceptionMapper = new KapuaEntityNotFoundExceptionMapper();

        assertEquals("Expected and actual values should be the same.", 404, kapuaEntityNotFoundExceptionMapper.toResponse(new KapuaEntityNotFoundException("entity type", KapuaId.ANY)).getStatus());
        assertEquals("Expected and actual values should be the same.", "Not Found", kapuaEntityNotFoundExceptionMapper.toResponse(new KapuaEntityNotFoundException("entity type", KapuaId.ANY)).getStatusInfo().toString());
    }

    @Test(expected = NullPointerException.class)
    public void toResponseNullTest() {
        KapuaEntityNotFoundExceptionMapper kapuaEntityNotFoundExceptionMapper = new KapuaEntityNotFoundExceptionMapper();
        kapuaEntityNotFoundExceptionMapper.toResponse(null);
    }
}