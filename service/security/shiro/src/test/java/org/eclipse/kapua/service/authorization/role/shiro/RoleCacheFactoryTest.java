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
package org.eclipse.kapua.service.authorization.role.shiro;

import org.eclipse.kapua.qa.markers.Categories;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

@Category(Categories.junitTests.class)
public class RoleCacheFactoryTest extends Assert {

    @Test
    public void roleCacheFactoryTest() throws Exception {
        Constructor<RoleCacheFactory> roleCacheFactory = RoleCacheFactory.class.getDeclaredConstructor();
        roleCacheFactory.setAccessible(true);
        roleCacheFactory.newInstance();
        assertTrue("True expected.", Modifier.isPrivate(roleCacheFactory.getModifiers()));
    }

    @Test
    public void getInstanceTest() {
        assertTrue("True expected.", RoleCacheFactory.getInstance() instanceof RoleCacheFactory);
        assertEquals("Expected and actual values should be the same.", "RoleId", RoleCacheFactory.getInstance().getEntityIdCacheName());
    }
}