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
package org.eclipse.kapua.model.domain;

import org.eclipse.kapua.qa.markers.Categories;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.HashSet;
import java.util.Set;

@Category(Categories.junitTests.class)
public class AbstractDomainTest extends Assert {

    private class AbstractDomainImpl extends AbstractDomain {

        @Override
        public String getName() {
            return "Domain Name";
        }

        @Override
        public Set<Actions> getActions() {
            Set<Actions> actionsSet = new HashSet<>();
            actionsSet.add(Actions.read);
            actionsSet.add(Actions.write);
            actionsSet.add(Actions.delete);
            actionsSet.add(Actions.connect);
            actionsSet.add(Actions.execute);
            return actionsSet;
        }

        @Override
        public boolean getGroupable() {
            return false;
        }
    }

    @Test
    public void equalsTest() {
        AbstractDomain abstractDomain1 = new AbstractDomainImpl();
        AbstractDomain abstractDomain2 = new AbstractDomainImpl();
        Object[] objects = {0, 10, 100000, "String", 'c', -10, -1000000000, -100000000000L, 10L, 10.0f, null, 10.10d, true, false};

        assertTrue("True expected.", abstractDomain1.equals(abstractDomain1));
        assertFalse("False expected.", abstractDomain1.equals(null));
        for (Object object : objects) {
            assertFalse("False expected.", abstractDomain1.equals(object));
        }
        assertTrue("True expected.", abstractDomain1.equals(abstractDomain2));
    }

    @Test
    public void hashCodeTest() {
        AbstractDomain abstractDomain = new AbstractDomainImpl();

        assertThat("Instance of Integer expected.", abstractDomain.hashCode(), IsInstanceOf.instanceOf(Integer.class));
    }
}