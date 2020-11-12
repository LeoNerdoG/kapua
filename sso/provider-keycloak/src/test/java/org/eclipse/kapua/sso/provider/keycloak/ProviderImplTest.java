package org.eclipse.kapua.sso.provider.keycloak;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class ProviderImplTest extends Assert {

    ProviderImpl providerImpl;

    @Before
    public void setUp() {
        providerImpl = new ProviderImpl();
    }

    @Test
    public void getIdTest() {
        assertEquals("keycloak", providerImpl.getId());
    }

    @Test
    public void createLocatorTest() {
        assertThat("Instance of KeycloakSingleSignOnLocator expected.", providerImpl.createLocator(), IsInstanceOf.instanceOf(KeycloakSingleSignOnLocator.class));
    }
}
