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
package org.eclipse.kapua.broker.core.router;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.regex.Pattern;

@Category(JUnitTests.class)
public class EndPointTest extends Assert {

    @Test
    public void replacePlaceholderTest() {
        // COMMENT: Missing escape characters in regexValues array: \s, \b, \f, \", \1, \2, \3, \4, \5, \6, \7, \0
        String[] regexValues = {null, "\\", "regex","re gex","", "re\\gex", "re\tge", "reg\nex1234567890", "!@#$%^&*()_+=-/.,?><|:;'|", "\'"};
        for (String value : regexValues) {
            assertEquals("Expected and actual values should be the same.", value, EndPoint.replacePlaceholder(value));
        }
    }

    // COMMENT: Can you achieve an exception in replacePlaceholder method? Is there a string that may provoke
    // this behaviour? Becasue in parseRegex test you achieved this.

    @Test
    public void parseRegexNullTest() {
        String[] regexValue = {null,"\\","re\\gex"};
        for (String value : regexValue) {
        assertNull("Null expected.", EndPoint.parseRegex(value));}
    }

    @Test
    public void parseRegexTest() {
        String[] regexValues = {"", "regex", "re\tge", "reg\nex1234567890", "!@#$%^&*()_+=-/.,?><|:;'|", "\'", "re gex"};
        for (String value : regexValues) {
            assertThat("Instance of Pattern expected.", EndPoint.parseRegex(value), IsInstanceOf.instanceOf(Pattern.class));
        }
    }
}