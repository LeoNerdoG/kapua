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

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class ActionsTest extends Assert {

    @Test
    public void actionsTest() {
        String[] enumArray = new String[]{"read", "write", "delete", "connect", "execute"};
        for (String enumString : enumArray) {
            try {
                assertNotNull(Actions.valueOf(enumString));
            } catch (Exception ex) {
                fail(enumString + "enum does not exist.");
            }
        }
    }
}
