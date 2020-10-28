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

import org.eclipse.kapua.commons.service.internal.KapuaServiceDisabledException;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KapuaServiceDisabledExceptionMapperTest extends Assert {

    @Test
    public void toResponseTest() {
        KapuaServiceDisabledExceptionMapper kapuaServiceDisabledExceptionMapper = new KapuaServiceDisabledExceptionMapper();

        assertEquals("Expected and actual values should be the same.", 404, kapuaServiceDisabledExceptionMapper.toResponse(new KapuaServiceDisabledException("name")).getStatus());
        assertEquals("Expected and actual values should be the same.", "Not Found", kapuaServiceDisabledExceptionMapper.toResponse(new KapuaServiceDisabledException("name")).getStatusInfo().toString());
    }

    @Test(expected = NullPointerException.class)
    public void toResponseNullTest() {
        KapuaServiceDisabledExceptionMapper kapuaServiceDisabledExceptionMapper = new KapuaServiceDisabledExceptionMapper();
        kapuaServiceDisabledExceptionMapper.toResponse(null);
    }
}