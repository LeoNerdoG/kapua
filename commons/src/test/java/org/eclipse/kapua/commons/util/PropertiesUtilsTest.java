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

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.Properties;

@Category(JUnitTests.class)
public class PropertiesUtilsTest extends Assert {

    @Test
    public void testConstructor() throws Exception {
        Constructor<PropertiesUtils> propertiesUtils = PropertiesUtils.class.getDeclaredConstructor();
        propertiesUtils.setAccessible(true);
        propertiesUtils.newInstance();
    }

    @Test
    public void readPropertiesFromStringTest() throws Exception {
        String[] inputString = new String[]{"StringString", "!string\n !string", "   #string\n String=S123", "     string!     Strin=g     ", "strin#g   :  string", "string         str1, \\\nstr2, \\\nstr3", "string\\ String 12 34", "123\\:\\=12%#34:String", "Str\ting String", "Strin\fg=String", "string\n "};
        String[] emptyOrNullString = new String[]{null, ""};

        Properties prop1 = new Properties();
        prop1.setProperty("StringString", "");
        Properties prop2 = new Properties();
        Properties prop3 = new Properties();
        prop3.setProperty("String", "S123");
        Properties prop4 = new Properties();
        prop4.setProperty("string!", "Strin=g     ");
        Properties prop5 = new Properties();
        prop5.setProperty("strin#g", "string");
        Properties prop6 = new Properties();
        prop6.setProperty("string", "str1, str2, str3");
        Properties prop7 = new Properties();
        prop7.setProperty("string String", "12 34");
        Properties prop8 = new Properties();
        prop8.setProperty("123:=12%#34", "String");
        Properties prop9 = new Properties();
        prop9.setProperty("Str", "ing String");
        Properties prop10 = new Properties();
        prop10.setProperty("Strin", "g=String");
        Properties prop11 = new Properties();
        prop11.setProperty("string", "");

        Properties inProp1 = new Properties();
        inProp1.setProperty("String", "String");
        Properties inProp2 = new Properties();
        inProp2.setProperty("!string", "!string");
        Properties inProp3 = new Properties();
        inProp3.setProperty("#string", "String=S123");
        Properties inProp4 = new Properties();
        inProp4.setProperty("string!     Strin", "g     ");
        Properties inProp5 = new Properties();
        inProp5.setProperty("strin#g", ":string");
        Properties inProp6 = new Properties();
        inProp6.setProperty("string", "str1");
        Properties inProp7 = new Properties();
        inProp7.setProperty("string", "String 12 34");
        Properties inProp8 = new Properties();
        inProp8.setProperty("123", "=12%#34:String");
        Properties inProp9 = new Properties();
        inProp9.setProperty("String", "String");
        Properties inProp10 = new Properties();
        inProp10.setProperty("String", "String");
        Properties inProp11 = new Properties();
        inProp11.setProperty("string", "string");

        Properties emProp1 = new Properties();
        Properties emProp2 = new Properties();
        emProp2.setProperty("", "");

        Properties[] propArray = new Properties[]{prop1, prop2, prop3, prop4, prop5, prop6, prop7, prop8, prop9, prop10, prop11};
        Properties[] invalidPropArray = new Properties[]{inProp1, inProp2, inProp3, inProp4, inProp5, inProp6, inProp7, inProp8, inProp9, inProp10, inProp11};

        //Positive tests
        for (int i = 0; i < inputString.length; i++) {
            try {
                assertEquals(propArray[i], PropertiesUtils.readPropertiesFromString(inputString[i]));
            } catch (AssertionError e) {
                fail("Assertion Error not expected for " + inputString[i]);
            }
        }

        //Negative tests
        for (int i = 0; i < inputString.length; i++) {
            try {
                assertEquals(invalidPropArray[i], PropertiesUtils.readPropertiesFromString(inputString[i]));
                fail("Assertion Error expected");
            } catch (AssertionError e) {
                //Expected
            }
        }

        //Positive tests
        for (int i = 0; i < emptyOrNullString.length; i++) {
            try {
                assertEquals(emProp1, PropertiesUtils.readPropertiesFromString(emptyOrNullString[i]));
            } catch (AssertionError e) {
                fail("Assertion Error not expected for " + emptyOrNullString[i]);
            }
        }

        //Negative tests
        for (int i = 0; i < emptyOrNullString.length; i++) {
            try {
                assertEquals(emProp2, PropertiesUtils.readPropertiesFromString(emptyOrNullString[i]));
                fail("Assertion Error expected");
            } catch (AssertionError e) {
                //Expected
            }
        }
    }

    @Test
    public void writePropertiesToStringTest() throws Exception {
        Properties prop1 = new Properties();
        Properties prop2 = new Properties();
        Properties prop3 = new Properties();
        Properties prop4 = new Properties();
        Properties prop5 = new Properties();
        Properties nullProp = null;

        prop1.setProperty("String", "=");
        prop2.setProperty("String:", " ff!String");
        prop3.setProperty("string", ":str");
        prop4.setProperty("st ring#a", "StriNG aaa';");
        prop5.setProperty(" String", "ssss12_ ;;     ");

        String s = System.lineSeparator();

        String str1 = "#" + new Date().toString() + s + "String=\\=" + s;
        String str2 = "#" + new Date().toString() + s + "String\\:=\\ ff\\!String" + s;
        String str3 = "#" + new Date().toString() + s + "string=\\:str" + s;
        String str4 = "#" + new Date().toString() + s + "st\\ ring\\#a=StriNG aaa';" + s;
        String str5 = "#" + new Date().toString() + s + "\\ String=ssss12_ ;;     " + s;
        String nullString = null;
        String str = "";

        String inStr1 = "#" + new Date().toString() + s + "String=\\=";
        String inStr2 = "String\\:=\\ ff\\!String";
        String inStr3 = new Date().toString() + s + "string=\\:str" + s;
        String inStr4 = "#" + new Date().toString() + s + "st ring#a=StriNG aaa';" + s;
        String inStr5 = "#" + new Date().toString() + "\\ String=ssss12_ ;;     " + s;

        String[] validOutputString = new String[]{str1, str2, str3, str4, str5};
        String[] invalidOutputString = new String[]{inStr1, inStr2, inStr3, inStr4, inStr5};
        Properties[] propArray = new Properties[]{prop1, prop2, prop3, prop4, prop5};

        //Positive tests
        for (int i = 0; i < propArray.length; i++) {
            try {
                assertEquals(validOutputString[i], PropertiesUtils.writePropertiesToString(propArray[i]));
            } catch (ComparisonFailure e) {
                fail("Comparison Failure not expected for " + propArray[i]);
            }
        }

        //Negative tests
        for (int i = 0; i < propArray.length; i++) {
            try {
                assertEquals(invalidOutputString[i], PropertiesUtils.writePropertiesToString(propArray[i]));
                fail("Comparison Failure expected");
            } catch (ComparisonFailure failure) {

            }
        }

        //Positive test
        try {
            assertEquals(nullString, PropertiesUtils.writePropertiesToString(nullProp));
        } catch (AssertionError e) {
            fail("Assertion Error not expected for null properties");
        }

        //Negative test
        try {
            assertEquals(str, PropertiesUtils.writePropertiesToString(nullProp));
            fail("Assertion Error expected");
        } catch (AssertionError e) {
        }
    }
}