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

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.service.authentication.KapuaAuthenticationErrorCodes;
import org.eclipse.kapua.service.authentication.shiro.KapuaAuthenticationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KapuaAuthenticationExceptionMapperTest extends Assert {

    @Test
    public void toResponseTest() {
        KapuaAuthenticationExceptionMapper kapuaAuthenticationExceptionMapper = new KapuaAuthenticationExceptionMapper();
        KapuaAuthenticationErrorCodes[] errorCodes = {KapuaAuthenticationErrorCodes.SUBJECT_ALREADY_LOGGED, KapuaAuthenticationErrorCodes.INVALID_CREDENTIALS_TYPE_PROVIDED,
                KapuaAuthenticationErrorCodes.AUTHENTICATION_ERROR, KapuaAuthenticationErrorCodes.CREDENTIAL_CRYPT_ERROR, KapuaAuthenticationErrorCodes.INVALID_LOGIN_CREDENTIALS,
                KapuaAuthenticationErrorCodes.EXPIRED_LOGIN_CREDENTIALS, KapuaAuthenticationErrorCodes.LOCKED_LOGIN_CREDENTIAL, KapuaAuthenticationErrorCodes.DISABLED_LOGIN_CREDENTIAL,
                KapuaAuthenticationErrorCodes.UNKNOWN_SESSION_CREDENTIAL, KapuaAuthenticationErrorCodes.INVALID_SESSION_CREDENTIALS, KapuaAuthenticationErrorCodes.EXPIRED_SESSION_CREDENTIALS,
                KapuaAuthenticationErrorCodes.LOCKED_SESSION_CREDENTIAL, KapuaAuthenticationErrorCodes.DISABLED_SESSION_CREDENTIAL, KapuaAuthenticationErrorCodes.JWK_FILE_ERROR,
                KapuaAuthenticationErrorCodes.REFRESH_ERROR, KapuaAuthenticationErrorCodes.JWK_GENERATION_ERROR, KapuaAuthenticationErrorCodes.JWT_CERTIFICATE_NOT_FOUND};

        for (KapuaAuthenticationErrorCodes code : errorCodes) {
            KapuaAuthenticationException kapuaAuthenticationException = new KapuaAuthenticationException(code);

            assertEquals("Expected and actual values should be the same.", 401, kapuaAuthenticationExceptionMapper.toResponse(kapuaAuthenticationException).getStatus());
            assertEquals("Expected and actual values should be the same.", "Unauthorized", kapuaAuthenticationExceptionMapper.toResponse(kapuaAuthenticationException).getStatusInfo().toString());
        }
    }

    @Test
    public void toResponseNullCodeTest() {
        KapuaAuthenticationExceptionMapper kapuaAuthenticationExceptionMapper = new KapuaAuthenticationExceptionMapper();
        KapuaAuthenticationException kapuaAuthenticationException = new KapuaAuthenticationException(null);

        assertEquals("Expected and actual values should be the same.", 401, kapuaAuthenticationExceptionMapper.toResponse(kapuaAuthenticationException).getStatus());
        assertEquals("Expected and actual values should be the same.", "Unauthorized", kapuaAuthenticationExceptionMapper.toResponse(kapuaAuthenticationException).getStatusInfo().toString());
    }

    @Test
    public void toResponseNullTest() {
        KapuaAuthenticationExceptionMapper kapuaAuthenticationExceptionMapper = new KapuaAuthenticationExceptionMapper();

        assertEquals("Expected and actual values should be the same.", 401, kapuaAuthenticationExceptionMapper.toResponse(null).getStatus());
        assertEquals("Expected and actual values should be the same.", "Unauthorized", kapuaAuthenticationExceptionMapper.toResponse(null).getStatusInfo().toString());
    }
}