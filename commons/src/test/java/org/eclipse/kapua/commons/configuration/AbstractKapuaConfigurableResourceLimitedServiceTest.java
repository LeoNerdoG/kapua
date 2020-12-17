/*******************************************************************************
 * Copyright (c) 2021 Eurotech and/or its affiliates and others
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.commons.configuration;

import org.eclipse.kapua.commons.jpa.AbstractEntityCacheFactory;
import org.eclipse.kapua.commons.jpa.EntityManagerFactory;
import org.eclipse.kapua.model.domain.Domain;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class AbstractKapuaConfigurableResourceLimitedServiceTest extends Assert {

    private class AbstractKapuaConfigurableResourceLimitedServiceImpl extends AbstractKapuaConfigurableResourceLimitedService {

        protected AbstractKapuaConfigurableResourceLimitedServiceImpl(String pid, Domain domain, EntityManagerFactory entityManagerFactory, AbstractEntityCacheFactory abstractCacheFactory, Class serviceClass, Class factoryClass) {
            super(pid, domain, entityManagerFactory, abstractCacheFactory, serviceClass, factoryClass);
        }
    }

    @Test
    public void test() {
//        AbstractKapuaConfigurableResourceLimitedService akc = new AbstractKapuaConfigurableResourceLimitedServiceImpl("string", new Domain, new EntityManagerFactory, new AbstractEntityCacheFactory, null, null);

    }
}
