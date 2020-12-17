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
package internal;

import org.eclipse.kapua.model.id.KapuaId;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.service.account.internal.AccountCreatorImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.math.BigInteger;

@Category(JUnitTests.class)
public class AccountCreatorImplTest extends Assert {

    @Test
    public void setAndGetOrganizationNameTest() {

        KapuaId kapuaId = new KapuaId() {
            @Override
            public BigInteger getId() {
                return null;
            }
        };
        String name = "name";

        AccountCreatorImpl accountCreator = new AccountCreatorImpl(kapuaId, name);
        accountCreator.setOrganizationName("organizationName");
        assertEquals("expected and actual values should be the same", "organizationName", accountCreator.getOrganizationName());
    }
}
