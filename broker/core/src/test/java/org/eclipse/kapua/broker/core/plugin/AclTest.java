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
package org.eclipse.kapua.broker.core.plugin;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class AclTest extends Assert {

    boolean[] readPermissions;
    boolean[] writePermissions;
    boolean[] adminPermission;

    @Before
    public void initialize() {
        readPermissions = new boolean[]{true, false};
        writePermissions = new boolean[]{true, false};
        adminPermission = new boolean[]{true, false};
    }

    @Test
    public void aclTest() {
        for (boolean read : readPermissions) {
            for (boolean write : writePermissions) {
                for (boolean admin : adminPermission) {
                    Acl acl = new Acl(read, write, admin);
                    assertEquals("Expected and actual values should be the same.", read, acl.isRead());
                    assertEquals("Expected and actual values should be the same.", write, acl.isWrite());
                    assertEquals("Expected and actual values should be the same.", admin, acl.isAdmin());
                }
            }
        }
    }
}