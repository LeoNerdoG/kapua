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
package org.eclipse.kapua.commons.util;

import org.junit.Assert;
import org.junit.Test;

public class SemanticVersionTest extends Assert {

    @Test
    public void testAfterLiquibaseVersion() {
        assertFalse(new SemanticVersion("3.0.5").after(new SemanticVersion("3.3.3")));
    }

    @Test
    public void testAfterOrMatchesLiquibaseVersion() {
        assertFalse(new SemanticVersion("3.0.5").afterOrMatches(new SemanticVersion("3.3.3")));
    }

    @Test
    public void testMatchesLiquibaseVersion() {
        assertFalse(new SemanticVersion("3.0.5").matches(new SemanticVersion("3.3.3")));
    }

    @Test
    public void testBeforeOrMatchesLiquibaseVersion() {
        assertTrue(new SemanticVersion("3.0.5").beforeOrMatches(new SemanticVersion("3.3.3")));
    }

    @Test
    public void testBeforeLiquibaseVersion() {
        assertTrue(new SemanticVersion("3.0.5").before(new SemanticVersion("3.3.3")));
    }

    @Test
    public void testAfter() {
        assertTrue(new SemanticVersion("1.1.0").after(new SemanticVersion("1.0.0")));
        assertTrue(new SemanticVersion("1.0.1").after(new SemanticVersion("1.0.0")));
        assertTrue(new SemanticVersion("1.1.1").after(new SemanticVersion("1.0.0")));

        assertTrue(new SemanticVersion("2.0.0").after(new SemanticVersion("1.0.0")));
        assertTrue(new SemanticVersion("2.0.0").after(new SemanticVersion("1.9.0")));
        assertTrue(new SemanticVersion("2.0.0").after(new SemanticVersion("1.9.9")));

        assertFalse(new SemanticVersion("1.0.0").after(new SemanticVersion("1.0.0")));
        assertFalse(new SemanticVersion("0.1.0").after(new SemanticVersion("0.1.0")));
        assertFalse(new SemanticVersion("0.0.1").after(new SemanticVersion("0.0.1")));

        assertFalse(new SemanticVersion("1.0.0").after(new SemanticVersion("1.1.0")));
        assertFalse(new SemanticVersion("1.0.0").after(new SemanticVersion("1.0.1")));
        assertFalse(new SemanticVersion("1.0.0").after(new SemanticVersion("1.1.1")));

        assertFalse(new SemanticVersion("1.0.0").after(new SemanticVersion("2.0.0")));
        assertFalse(new SemanticVersion("1.9.0").after(new SemanticVersion("2.0.0")));
        assertFalse(new SemanticVersion("1.9.9").after(new SemanticVersion("2.0.0")));

    }

    @Test
    public void testAfterOrMatches() {
        assertTrue(new SemanticVersion("1.1.0").afterOrMatches(new SemanticVersion("1.0.0")));
        assertTrue(new SemanticVersion("1.0.1").afterOrMatches(new SemanticVersion("1.0.0")));
        assertTrue(new SemanticVersion("1.1.1").afterOrMatches(new SemanticVersion("1.0.0")));

        assertTrue(new SemanticVersion("2.0.0").afterOrMatches(new SemanticVersion("1.0.0")));
        assertTrue(new SemanticVersion("2.0.0").afterOrMatches(new SemanticVersion("1.9.0")));
        assertTrue(new SemanticVersion("2.0.0").afterOrMatches(new SemanticVersion("1.9.9")));

        assertTrue(new SemanticVersion("1.0.0").afterOrMatches(new SemanticVersion("1.0.0")));
        assertTrue(new SemanticVersion("0.1.0").afterOrMatches(new SemanticVersion("0.1.0")));
        assertTrue(new SemanticVersion("0.0.1").afterOrMatches(new SemanticVersion("0.0.1")));

        assertFalse(new SemanticVersion("1.0.0").afterOrMatches(new SemanticVersion("1.1.0")));
        assertFalse(new SemanticVersion("1.0.0").afterOrMatches(new SemanticVersion("1.0.1")));
        assertFalse(new SemanticVersion("1.0.0").afterOrMatches(new SemanticVersion("1.1.1")));

        assertFalse(new SemanticVersion("1.0.0").afterOrMatches(new SemanticVersion("2.0.0")));
        assertFalse(new SemanticVersion("1.9.0").afterOrMatches(new SemanticVersion("2.0.0")));
        assertFalse(new SemanticVersion("1.9.9").afterOrMatches(new SemanticVersion("2.0.0")));
    }

    @Test
    public void testMatches() {
        assertFalse(new SemanticVersion("1.0.0").matches(new SemanticVersion("1.1.0")));
        assertFalse(new SemanticVersion("1.0.0").matches(new SemanticVersion("1.0.1")));
        assertFalse(new SemanticVersion("1.0.0").matches(new SemanticVersion("1.1.1")));

        assertFalse(new SemanticVersion("1.0.0").matches(new SemanticVersion("2.0.0")));
        assertFalse(new SemanticVersion("1.9.0").matches(new SemanticVersion("2.0.0")));
        assertFalse(new SemanticVersion("1.9.9").matches(new SemanticVersion("2.0.0")));

        assertTrue(new SemanticVersion("1.0.0").matches(new SemanticVersion("1.0.0")));
        assertTrue(new SemanticVersion("0.1.0").matches(new SemanticVersion("0.1.0")));
        assertTrue(new SemanticVersion("0.0.1").matches(new SemanticVersion("0.0.1")));

        assertFalse(new SemanticVersion("1.1.0").matches(new SemanticVersion("1.0.0")));
        assertFalse(new SemanticVersion("1.0.1").matches(new SemanticVersion("1.0.0")));
        assertFalse(new SemanticVersion("1.1.1").matches(new SemanticVersion("1.0.0")));

        assertFalse(new SemanticVersion("2.0.0").matches(new SemanticVersion("1.0.0")));
        assertFalse(new SemanticVersion("2.0.0").matches(new SemanticVersion("1.9.0")));
        assertFalse(new SemanticVersion("2.0.0").matches(new SemanticVersion("1.9.9")));
    }

    @Test
    public void testBeforeOrMatches() {
        assertTrue(new SemanticVersion("1.0.0").beforeOrMatches(new SemanticVersion("1.1.0")));
        assertTrue(new SemanticVersion("1.0.0").beforeOrMatches(new SemanticVersion("1.0.1")));
        assertTrue(new SemanticVersion("1.0.0").beforeOrMatches(new SemanticVersion("1.1.1")));

        assertTrue(new SemanticVersion("1.0.0").beforeOrMatches(new SemanticVersion("2.0.0")));
        assertTrue(new SemanticVersion("1.9.0").beforeOrMatches(new SemanticVersion("2.0.0")));
        assertTrue(new SemanticVersion("1.9.9").beforeOrMatches(new SemanticVersion("2.0.0")));

        assertTrue(new SemanticVersion("1.0.0").beforeOrMatches(new SemanticVersion("1.0.0")));
        assertTrue(new SemanticVersion("0.1.0").beforeOrMatches(new SemanticVersion("0.1.0")));
        assertTrue(new SemanticVersion("0.0.1").beforeOrMatches(new SemanticVersion("0.0.1")));

        assertFalse(new SemanticVersion("1.1.0").beforeOrMatches(new SemanticVersion("1.0.0")));
        assertFalse(new SemanticVersion("1.0.1").beforeOrMatches(new SemanticVersion("1.0.0")));
        assertFalse(new SemanticVersion("1.1.1").beforeOrMatches(new SemanticVersion("1.0.0")));

        assertFalse(new SemanticVersion("2.0.0").beforeOrMatches(new SemanticVersion("1.0.0")));
        assertFalse(new SemanticVersion("2.0.0").beforeOrMatches(new SemanticVersion("1.9.0")));
        assertFalse(new SemanticVersion("2.0.0").beforeOrMatches(new SemanticVersion("1.9.9")));
    }

    @Test
    public void testBefore() {
        assertTrue(new SemanticVersion("1.0.0").before(new SemanticVersion("1.1.0")));
        assertTrue(new SemanticVersion("1.0.0").before(new SemanticVersion("1.0.1")));
        assertTrue(new SemanticVersion("1.0.0").before(new SemanticVersion("1.1.1")));

        assertTrue(new SemanticVersion("1.0.0").before(new SemanticVersion("2.0.0")));
        assertTrue(new SemanticVersion("1.9.0").before(new SemanticVersion("2.0.0")));
        assertTrue(new SemanticVersion("1.9.9").before(new SemanticVersion("2.0.0")));

        assertFalse(new SemanticVersion("1.0.0").before(new SemanticVersion("1.0.0")));
        assertFalse(new SemanticVersion("0.1.0").before(new SemanticVersion("0.1.0")));
        assertFalse(new SemanticVersion("0.0.1").before(new SemanticVersion("0.0.1")));

        assertFalse(new SemanticVersion("1.1.0").before(new SemanticVersion("1.0.0")));
        assertFalse(new SemanticVersion("1.0.1").before(new SemanticVersion("1.0.0")));
        assertFalse(new SemanticVersion("1.1.1").before(new SemanticVersion("1.0.0")));

        assertFalse(new SemanticVersion("2.0.0").before(new SemanticVersion("1.0.0")));
        assertFalse(new SemanticVersion("2.0.0").before(new SemanticVersion("1.9.0")));
        assertFalse(new SemanticVersion("2.0.0").before(new SemanticVersion("1.9.9")));
    }

    @Test
    public void testNullPatchVersion() {
        assertTrue(new SemanticVersion("1.1").after(new SemanticVersion("1.0")));
        assertTrue(new SemanticVersion("2.0").after(new SemanticVersion("1.9")));

        assertTrue(new SemanticVersion("1.0").before(new SemanticVersion("1.1")));
        assertTrue(new SemanticVersion("1.9").before(new SemanticVersion("2.0")));

        assertTrue(new SemanticVersion("1.0").matches(new SemanticVersion("1.0")));
        assertTrue(new SemanticVersion("0.1").matches(new SemanticVersion("0.1")));

        assertTrue(new SemanticVersion("1.0").beforeOrMatches(new SemanticVersion("1.1")));
        assertTrue(new SemanticVersion("1.0").beforeOrMatches(new SemanticVersion("2.0")));
        assertTrue(new SemanticVersion("1.9").beforeOrMatches(new SemanticVersion("2.0")));
        assertTrue(new SemanticVersion("1.0").beforeOrMatches(new SemanticVersion("1.0")));

        assertTrue(new SemanticVersion("1.1").afterOrMatches(new SemanticVersion("1.0")));
        assertTrue(new SemanticVersion("1.0").afterOrMatches(new SemanticVersion("0.1")));
        assertTrue(new SemanticVersion("2.0").afterOrMatches(new SemanticVersion("1.9")));
        assertTrue(new SemanticVersion("1.0").afterOrMatches(new SemanticVersion("1.0")));

        assertFalse(new SemanticVersion("1.0").matches(new SemanticVersion("1.1")));
        assertFalse(new SemanticVersion("1.0").matches(new SemanticVersion("2.0")));
        assertFalse(new SemanticVersion("1.1").matches(new SemanticVersion("1.0")));
        assertFalse(new SemanticVersion("2.0").matches(new SemanticVersion("1.0")));

        assertFalse(new SemanticVersion("1.0").after(new SemanticVersion("1.0")));
        assertFalse(new SemanticVersion("0.1").after(new SemanticVersion("0.1")));
        assertFalse(new SemanticVersion("1.0").after(new SemanticVersion("1.1")));
        assertFalse(new SemanticVersion("1.0").after(new SemanticVersion("2.0")));
        assertFalse(new SemanticVersion("1.9").after(new SemanticVersion("2.0")));

        assertFalse(new SemanticVersion("1.0").before(new SemanticVersion("1.0")));
        assertFalse(new SemanticVersion("0.1").before(new SemanticVersion("0.1")));
        assertFalse(new SemanticVersion("1.1").before(new SemanticVersion("1.0")));
        assertFalse(new SemanticVersion("2.0").before(new SemanticVersion("1.9")));

        assertFalse(new SemanticVersion("1.1").beforeOrMatches(new SemanticVersion("1.0")));
        assertFalse(new SemanticVersion("2.0").beforeOrMatches(new SemanticVersion("1.0")));
        assertFalse(new SemanticVersion("2.0").beforeOrMatches(new SemanticVersion("1.9")));

        assertFalse(new SemanticVersion("1.0").afterOrMatches(new SemanticVersion("1.1")));
        assertFalse(new SemanticVersion("0.1").afterOrMatches(new SemanticVersion("1.0")));
        assertFalse(new SemanticVersion("1.9").afterOrMatches(new SemanticVersion("2.0")));
    }

    @Test
    public void testNullMinorVersion() {
        assertTrue(new SemanticVersion("1").before(new SemanticVersion("2")));
        assertFalse(new SemanticVersion("2").before(new SemanticVersion("1")));
    }

    @Test
    public void stringVersionTest() {
        assertTrue(new SemanticVersion("d.e.f").after(new SemanticVersion("a.b.c")));
        assertFalse(new SemanticVersion("d.e.f").before(new SemanticVersion("a.b.c")));
    }

    @Test
    public void versionStringTest() {
        assertEquals("1.1.1", new SemanticVersion("1.1.1").toString());
    }
}