package org.eclipse.kapua.sso.provider.keycloak;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.sso.exception.SsoException;
import org.eclipse.kapua.sso.provider.keycloak.jwt.KeycloakJwtProcessor;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class KeycloakSingleSignOnLocatorTest extends Assert {

    KeycloakSingleSignOnLocator keycloakSingleSignOnLocator;

    @Before
    public void setUp() {
        keycloakSingleSignOnLocator = new KeycloakSingleSignOnLocator();
    }

    @Test
    public void getServiceTest() {
        assertThat(keycloakSingleSignOnLocator.getService(), IsInstanceOf.instanceOf(KeycloakSingleSignOnLocator.class));
    }

    //    to ne dela
    @Test
    public void getProcessorTest() throws SsoException {
        assertThat(keycloakSingleSignOnLocator.getProcessor(), IsInstanceOf.instanceOf(KeycloakJwtProcessor.class));
    }

    //    this test method will be upgraded once method close() gets a body
    @Test
    public void closeTest() throws Exception {
        keycloakSingleSignOnLocator.close();
    }

}
