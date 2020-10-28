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

import org.eclipse.kapua.KapuaIllegalArgumentException;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.glassfish.jersey.server.ParamException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class ParamExceptionMapperTest extends Assert {

    @Test
    public void toResponseTest() {
        ParamExceptionMapper paramExceptionMapper = new ParamExceptionMapper();
        Throwable[] throwables = {new Throwable(), new KapuaIllegalArgumentException("name", "value")};

        for (Throwable throwable : throwables) {
            assertEquals("Expected and actual values should be the same.", 400, paramExceptionMapper.toResponse(new ParamException.PathParamException(throwable, "name", "default value")).getStatus());
            assertEquals("Expected and actual values should be the same.", "Bad Request", paramExceptionMapper.toResponse(new ParamException.PathParamException(throwable, "name", "default value")).getStatusInfo().toString());
        }
    }

    @Test(expected = NullPointerException.class)
    public void toResponseNullTest() {
        ParamExceptionMapper paramExceptionMapper = new ParamExceptionMapper();
        paramExceptionMapper.toResponse(null);
    }
}