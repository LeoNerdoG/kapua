package org.eclipse.kapua.sso.provider.generic;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.sso.JwtProcessor;
import org.eclipse.kapua.sso.exception.SsoException;
import org.eclipse.kapua.sso.provider.generic.jwt.GenericJwtProcessor;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class GenericSingleSignOnLocatorTest extends Assert {

    GenericSingleSignOnLocator genericSingleSignOnLocator;

    @Before
    public void setUp() {
        genericSingleSignOnLocator = new GenericSingleSignOnLocator();
    }

    @Test
    public void getServiceTest() {
        assertThat(genericSingleSignOnLocator.getService(), IsInstanceOf.instanceOf(GenericSingleSignOnService.class));
    }

    //    to ne dela
    @Test
    public void getProcessorTest() throws SsoException {
        assertThat(genericSingleSignOnLocator.getProcessor(), IsInstanceOf.instanceOf(GenericJwtProcessor.class));
    }

    //    this test method will be upgraded once method close() gets a body
    @Test
    public void closeTest() throws Exception {
        genericSingleSignOnLocator.close();
    }

}
