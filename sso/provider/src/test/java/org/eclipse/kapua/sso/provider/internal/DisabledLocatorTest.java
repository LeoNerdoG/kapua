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
package org.eclipse.kapua.sso.provider.internal;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.sso.JwtProcessor;
import org.eclipse.kapua.sso.SingleSignOnService;
import org.eclipse.kapua.sso.exception.SsoAccessTokenException;
import org.eclipse.kapua.sso.exception.uri.SsoUriException;
import org.eclipse.kapua.sso.provider.SingleSignOnProvider;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class DisabledLocatorTest extends Assert {
    SingleSignOnProvider.ProviderLocator disabledLocator;

    @Before
    public void setUp() {
        disabledLocator = DisabledLocator.INSTANCE;
    }

    @Test
    public void getServiceTest() throws SsoUriException, SsoAccessTokenException {
        assertThat(disabledLocator.getService(), IsInstanceOf.instanceOf(SingleSignOnService.class));
        assertEquals(false, disabledLocator.getService().isEnabled());
        assertNull("Null expected.", disabledLocator.getService().getLoginUri(null, null));
        assertNull("Null expected.", disabledLocator.getService().getAccessToken(null, null));
        assertNull("Null expected.", disabledLocator.getService().getLogoutUri(null, null, null));
    }

    //    this test method will be upgraded once method close() gets a body
    @Test
    public void getProcessorTest() throws Exception {
        assertThat(disabledLocator.getProcessor(), IsInstanceOf.instanceOf(JwtProcessor.class));
        assertFalse("False expected.", disabledLocator.getProcessor().validate(null));
        assertNull("Null expected.", disabledLocator.getProcessor().process(null));
        disabledLocator.getProcessor().close();
    }

    //    this test method will be upgraded once method close() gets a body
    @Test
    public void closeTest() throws Exception {
        disabledLocator.close();
    }
}
