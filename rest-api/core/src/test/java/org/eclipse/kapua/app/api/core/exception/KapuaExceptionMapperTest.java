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

import org.eclipse.kapua.KapuaErrorCodes;
import org.eclipse.kapua.KapuaException;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KapuaExceptionMapperTest extends Assert{

    @Test
    public void toResponseTest() {
        KapuaExceptionMapper kapuaExceptionMapper = new KapuaExceptionMapper();

        assertEquals("Expected and actual values should be the same.", 500, kapuaExceptionMapper.toResponse(new KapuaException(KapuaErrorCodes.ADMIN_ROLE_DELETED_ERROR)).getStatus());
        assertEquals("Expected and actual values should be the same.", "Internal Server Error", kapuaExceptionMapper.toResponse(new KapuaException(KapuaErrorCodes.ADMIN_ROLE_DELETED_ERROR)).getStatusInfo().toString());
    }

    @Test(expected = NullPointerException.class)
    public void toResponseNullTest() {
        KapuaExceptionMapper kapuaExceptionMapper = new KapuaExceptionMapper();
        kapuaExceptionMapper.toResponse(null);
    }
}