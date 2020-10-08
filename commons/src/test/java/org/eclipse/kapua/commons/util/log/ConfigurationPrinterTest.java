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
package org.eclipse.kapua.commons.util.log;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;

@Category(JUnitTests.class)
public class ConfigurationPrinterTest extends Assert {

    ConfigurationPrinter configurationPrinter;
    Logger logger = LoggerFactory.getLogger(ConfigurationPrinter.class);

    @Before
    public void setUp() {
        configurationPrinter = new ConfigurationPrinter();
    }

    @Test
    public void getParentLoggerNullTest() {
        configurationPrinter.withLogger(null);
        assertNull("Returned value should be null!", configurationPrinter.getParentLogger());
    }

    @Test
    public void getParentLoggerTest() {
        configurationPrinter.withLogger(logger);
        assertTrue("Returned value is not instance of Logger!", configurationPrinter.getParentLogger() instanceof Logger);
    }

    @Test
    public void getLogLevelNullTest() {
        configurationPrinter.withLogLevel(null);
        assertNull(configurationPrinter.getLogLevel());
    }

    @Test
    public void getLogLevelTest() {
        configurationPrinter.withLogLevel(ConfigurationPrinter.LogLevel.DEBUG);
        assertTrue("Expected and actual values are not the same!", configurationPrinter.getLogLevel() instanceof ConfigurationPrinter.LogLevel);
    }

    @Test
    public void getTitleNullTest() {
        configurationPrinter.withTitle(null);
        assertNull("Should be instance of String, but it's not!", configurationPrinter.getTitle());
    }

    @Test
    public void getTitleTest() {
        String[] specialSymbols = new String[]{"!","\"","#","$","%","&","'","(",")","=","/","?","+","*","<",">",",",";",
                ".",":","-","_","⁄","@","‹","›","€","€","–","°","·","","Œ","„","‰","”","’","Ø","∏","{","}","Æ","æ","Ò","","Å",
                "Í","Î","~","«","◊","Ñ","¯","È","ˇ"," "};
        String[] title = new String[]{"","a","qwertyuiiopasdfghjklšđčćžzxcvbnm!","1234567890", "QWERTYUIOPŠĐASDFGHJKLČĆŽZXCVBNM"};
        for (String stringVal:title) {
            for(String specSymVal:specialSymbols) {
                configurationPrinter.withTitle(stringVal + specSymVal);
                assertEquals("Expected and actual values are not the same!", stringVal + specSymVal, configurationPrinter.getTitle());
            }
        }
    }

    @Test
    public void getTitleAlignmentTest() {
        assertEquals("Expected and actual values are not the same!", ConfigurationPrinter.TitleAlignment.CENTER, configurationPrinter.getTitleAlignment());
        configurationPrinter.withTitleAlignment(ConfigurationPrinter.TitleAlignment.CENTER);
        assertEquals("Expected and actual values are not the same!", ConfigurationPrinter.TitleAlignment.CENTER, configurationPrinter.getTitleAlignment());
        configurationPrinter.withTitleAlignment(ConfigurationPrinter.TitleAlignment.LEFT);
        assertEquals("Expected and actual values are not the same!", ConfigurationPrinter.TitleAlignment.LEFT, configurationPrinter.getTitleAlignment());
        configurationPrinter.withTitleAlignment(ConfigurationPrinter.TitleAlignment.RIGHT);
        assertEquals("Expected and actual values are not the same!", ConfigurationPrinter.TitleAlignment.RIGHT, configurationPrinter.getTitleAlignment());
    }

    @Test
    public void getConfigurationsNullTest() {
        assertEquals("Expected and actual values are not the same!", new ArrayList<>(), configurationPrinter.getConfigurations());
    }

    // There is no "setter" for variable "configurations".

    @Test
    public void addHeaderTest() throws NoSuchFieldException {
//        Field configurationsField = ConfigurationPrinter.class.getDeclaredField("configurations");
//        configurationsField.setAccessible(true);
//        String[] stringArray = new String[]{"string","string1", "string2"};
//        ArrayList expectedArray = new ArrayList<>();
//        Object stringObj = "[string]";
//        expectedArray.add(stringObj);
//        String string = "string œ'()!&#!('!!!=)!";
        assertTrue("not the type as expected!", configurationPrinter.addHeader("string") instanceof ConfigurationPrinter);
    }

    @Test
    public void addParameterNullTest() {

    }

    @Test
    public void addParameterTest() {
        Object stringObj = "[string]";
        assertTrue("not the type as expected!", configurationPrinter.addParameter("string", stringObj) instanceof ConfigurationPrinter);
    }

    @Test
    public void increaseIndentationTest() {
        for(int i=0; i<10; i++){
            assertTrue("Expected and actual values are not the same!", configurationPrinter.increaseIndentation() instanceof ConfigurationPrinter);
        }
    }

    @Test
    public void decreaseIndentationTest() throws NoSuchFieldException {
        for(int i=0; i<10; i++){
            configurationPrinter.increaseIndentation();
        }
        for(int i=10; i>=0; i--){
            assertTrue("Expected and actual values are not the same!", configurationPrinter.decreaseIndentation() instanceof ConfigurationPrinter);
        }
    }

    @Test
    public void printLogNullTest() {
        assertNull(configurationPrinter.getParentLogger());
        configurationPrinter.printLog();
    }

    @Test
    public void printLogValidTest() {
        Object[] objects = new Object[]{"[string]", 1, 1.1234, 1123456789L, 1.123456D, 123912839F, true, false, 'a', (byte) 100, (short) 10,};
        configurationPrinter.withLogger(logger);
        configurationPrinter.withTitle("Title");
        configurationPrinter.addHeader("Header");
        configurationPrinter.withLogLevel(ConfigurationPrinter.LogLevel.DEBUG);
        for (Object object:objects){
            configurationPrinter.addParameter("parameter",object);
            configurationPrinter.printLog();
        }
    }

    @Test
    public void createTest() {
        assertTrue("expected value should be an instance of ConfigurationPrinter!", ConfigurationPrinter.create() instanceof ConfigurationPrinter);
    }

    @Test
    public void logLevelEnumTest() {
        assertNotNull(ConfigurationPrinter.LogLevel.valueOf("DEBUG"));
        assertNotNull(ConfigurationPrinter.LogLevel.valueOf("ERROR"));
        assertNotNull(ConfigurationPrinter.LogLevel.valueOf("INFO"));
        assertNotNull(ConfigurationPrinter.LogLevel.valueOf("TRACE"));
        assertNotNull(ConfigurationPrinter.LogLevel.valueOf("WARN"));
    }

    @Test
    public void titleAlignmentTest() {
        assertNotNull(ConfigurationPrinter.TitleAlignment.valueOf("LEFT"));
        assertNotNull(ConfigurationPrinter.TitleAlignment.valueOf("RIGHT"));
        assertNotNull(ConfigurationPrinter.TitleAlignment.valueOf("CENTER"));
    }
}