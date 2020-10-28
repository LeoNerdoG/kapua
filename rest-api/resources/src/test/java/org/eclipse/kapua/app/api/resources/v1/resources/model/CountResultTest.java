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
package org.eclipse.kapua.app.api.resources.v1.resources.model;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class CountResultTest extends Assert {

    @Test
    public void countResultWithoutParameterTest() {
        CountResult countResult = new CountResult();
        assertEquals("Expected and actual values should be the same.", 0, countResult.getCount());
    }

    @Test
    public void countResultWithParameterTest() {
        long[] countValues = {-9223372036854775808L, -10000000L, -10, -1L, 0L, 1L, 10, 9223372036854775807L};

        for (long countValue : countValues) {
            CountResult countResult = new CountResult(countValue);
            assertEquals("Expected and actual values should be the same.", countValue, countResult.getCount());
        }
    }

    @Test
    public void setAndGetCount() {
        long[] countValues = {-9223372036854775808L, -10000000L, -10, -1L, 0L, 1L, 10, 9223372036854775807L};
        CountResult countResult1 = new CountResult();
        CountResult countResult2 = new CountResult(100L);

        for (long countValue : countValues) {
            countResult1.setCount(countValue);
            countResult2.setCount(countValue);

            assertEquals("Expected and actual values should be the same.", countValue, countResult1.getCount());
            assertEquals("Expected and actual values should be the same.", countValue, countResult2.getCount());
        }
    }
}