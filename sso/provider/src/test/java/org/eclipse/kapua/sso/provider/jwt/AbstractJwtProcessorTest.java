/*******************************************************************************
 * Copyright (c) 2020 Red Hat Inc and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Red Hat Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.sso.provider.jwt;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.sso.exception.SsoException;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.LinkedList;
import java.util.List;

@Category(JUnitTests.class)
public class AbstractJwtProcessorTest extends Assert {

    private class ActualJwtProcessor extends AbstractJwtProcessor {

        public ActualJwtProcessor() throws SsoException {
        }

        @Override
        protected List<String> getJwtExpectedIssuers() throws SsoException {
            return new LinkedList<>();
        }

        @Override
        protected List<String> getJwtAudiences() throws SsoException {
            return new LinkedList<>();
        }
    }

    @Before
    public void setUp() throws SsoException {
    }

    @Test
    public void constructorTest() throws SsoException {
        assertThat(new ActualJwtProcessor(), IsInstanceOf.instanceOf(AbstractJwtProcessor.class));
    }

    @Test
    public void validateTest() throws SsoException {
        AbstractJwtProcessor abstractJwtProcessor = new ActualJwtProcessor();
        abstractJwtProcessor.validate("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2dvb2dsZS5jb20iLCJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImp0aSI6Ijc1MzNjNDBiLTExNjQtNDE3My04NzQ0LWQ3Zjg1ZmRiNDhlOSIsImlhdCI6MTYwNjg1NjAyNywiZXhwIjoxNjA2ODU5Njc5fQ.wrXUtKhdO2m1aQR9yzvd6zR0-W6Fg0pjeZUtoW_b8TA");
    }

    @Test
    public void getJwtExpectedIssuersTest() throws SsoException {
        AbstractJwtProcessor abstractJwtProcessor = new ActualJwtProcessor();
        assertTrue(abstractJwtProcessor.validate("whatever"));
    }

    @Test
    public void blablaTest() throws SsoException {
        AbstractJwtProcessor abstractJwtProcessor = new ActualJwtProcessor();
        assertTrue(abstractJwtProcessor.validate("whatever"));
    }
}