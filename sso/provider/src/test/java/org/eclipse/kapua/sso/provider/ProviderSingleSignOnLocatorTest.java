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
package org.eclipse.kapua.sso.provider;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.sso.JwtProcessor;
import org.eclipse.kapua.sso.SingleSignOnService;
import org.eclipse.kapua.sso.exception.SsoException;
import org.eclipse.kapua.sso.provider.setting.SsoSetting;
import org.eclipse.kapua.sso.provider.setting.SsoSettingKeys;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.ServiceLoader;

@Category(JUnitTests.class)
public class ProviderSingleSignOnLocatorTest extends Assert {
    SsoSetting mockedSsoSetting;

    @Before
    public void setUp() {
        mockedSsoSetting = Mockito.mock(SsoSetting.class);
    }

    @Test
    public void constructorNullProviderIdTest() {
        ProviderSingleSignOnLocator providerSingleSignOnLocator = new ProviderSingleSignOnLocator(mockedSsoSetting);
        assertTrue("True expected.", providerSingleSignOnLocator instanceof ProviderSingleSignOnLocator);
    }

    @Test
    public void constructorFindProvider() throws SsoException {
        ProviderSingleSignOnLocator providerSingleSignOnLocatorNullLocator = new ProviderSingleSignOnLocator();
        Mockito.doReturn("string").when(mockedSsoSetting).getString(SsoSettingKeys.SSO_PROVIDER, null);
        ProviderSingleSignOnLocator providerSingleSignOnLocator = new ProviderSingleSignOnLocator(mockedSsoSetting);
        assertNotNull(providerSingleSignOnLocator.getService());
        assertNotNull(providerSingleSignOnLocator.getProcessor());
    }

    @Test
    public void constructorSsoProviderNullLocatorTest() {
//        tukaj moras se preveriti kako dela tista for zanka in ce potem res nared nov locator
        int i = 0;
        synchronized (ProviderSingleSignOnLocator.class) {
            for (final SingleSignOnProvider provider : ServiceLoader.load(SingleSignOnProvider.class)) {
                System.out.println(i);
                System.out.println(provider);
                i++;
            }
        }
//        Mockito.doReturn("keycloak").when(mockedSsoSetting).getString(SsoSettingKeys.SSO_PROVIDER, null);
//        ProviderSingleSignOnLocator providerSingleSignOnLocator1 = new ProviderSingleSignOnLocator(mockedSsoSetting);
//        assertTrue("True expected.", providerSingleSignOnLocator1 instanceof ProviderSingleSignOnLocator);
    }

    @Test
    public void constructorSsoProviderThrowingIllegalArgumentExceptionTest() {
        try {
            Mockito.doReturn("string").when(mockedSsoSetting).getString(SsoSettingKeys.SSO_PROVIDER, null);
            ProviderSingleSignOnLocator providerSingleSignOnLocator1 = new ProviderSingleSignOnLocator(mockedSsoSetting);
        } catch (IllegalArgumentException iae) {
            assertEquals("Unable to find single sign-on provider 'string'", iae.getMessage());
        }
    }

    @Test
    public void constructorNoArgumentTest() {
        assertTrue("True expected.", new ProviderSingleSignOnLocator() instanceof ProviderSingleSignOnLocator);
    }

    //        this test method will be upgraded once method close() gets a body
    @Test
    public void closeTest() {
        new ProviderSingleSignOnLocator().close();
    }

    @Test
    public void getServiceTest() {
        assertTrue("True expected.", (new ProviderSingleSignOnLocator()).getService() instanceof SingleSignOnService);
    }

    @Test
    public void getProcessorTest() throws SsoException {
        assertTrue("True expected.", (new ProviderSingleSignOnLocator()).getProcessor() instanceof JwtProcessor);
    }
}