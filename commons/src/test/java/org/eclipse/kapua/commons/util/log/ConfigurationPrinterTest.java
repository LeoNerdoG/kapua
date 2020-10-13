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
        assertTrue("Returned value is not an instance of Logger!", configurationPrinter.getParentLogger() instanceof Logger);
    }

    @Test
    public void getLogLevelNullTest() {
        configurationPrinter.withLogLevel(null);
        assertNull(configurationPrinter.getLogLevel());
    }

    @Test
    public void getLogLevelTest() {
        configurationPrinter.withLogLevel(ConfigurationPrinter.LogLevel.DEBUG);
        assertNotNull("DEBUG enum should exist!", configurationPrinter.getLogLevel());
        configurationPrinter.withLogLevel(ConfigurationPrinter.LogLevel.ERROR);
        assertNotNull("ERROR enum should exist!", configurationPrinter.getLogLevel());
        configurationPrinter.withLogLevel(ConfigurationPrinter.LogLevel.INFO);
        assertNotNull("INFO enum should exist!", configurationPrinter.getLogLevel());
        configurationPrinter.withLogLevel(ConfigurationPrinter.LogLevel.TRACE);
        assertNotNull("TRACE enum should exist!", configurationPrinter.getLogLevel());
        configurationPrinter.withLogLevel(ConfigurationPrinter.LogLevel.WARN);
        assertNotNull("WARN enum should exist!", configurationPrinter.getLogLevel());
    }

    @Test
    public void getTitleNullTest() {
        configurationPrinter.withTitle(null);
        assertNull("Returned value should be null!", configurationPrinter.getTitle());
    }

    @Test
    public void getTitleTest() {
        String[] specialSymbols = new String[]{"!","\"","#","$","%","&","'","(",")","=","/","?","+","*","<",">",",",";",
                ".",":","-","_","⁄","@","‹","›","€","€","–","°","·","","Œ","„","‰","”","’","Ø","∏","{","}","Æ","æ","Ò","","Å",
                "Í","Î","~","«","◊","Ñ","¯","È","ˇ"," "};
        String[] title = new String[]{"","a","qwertyuiiopasdfghjklšđčćžzxcvbnm!","1234567890", "QWERTYUIOPŠĐASDFGHJKLČĆŽZXCVBNM"};
        for (String titleVal:title) {
            for(String specSymVal:specialSymbols) {
                configurationPrinter.withTitle(titleVal + specSymVal);
                assertEquals("Expected and actual values are not the same!", titleVal + specSymVal, configurationPrinter.getTitle());
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

    @Test
    public void addHeaderTest() throws NoSuchFieldException {
        String[] specialSymbols = new String[]{"!","\"","#","$","%","&","'","(",")","=","/","?","+","*","<",">",",",";",
                ".",":","-","_","⁄","@","‹","›","€","€","–","°","·","","Œ","„","‰","”","’","Ø","∏","{","}","Æ","æ","Ò","","Å",
                "Í","Î","~","«","◊","Ñ","¯","È","ˇ"," "};
        String[] title = new String[]{"","a","qwertyuiiopasdfghjklšđčćžzxcvbnm!","1234567890", "QWERTYUIOPŠĐASDFGHJKLČĆŽZXCVBNM"};
        for (String titleVal:title) {
            for(String specSymVal:specialSymbols) {
                assertTrue("Returned value should an instance of ConfigurationPrinter!", configurationPrinter.addHeader(titleVal + specSymVal) instanceof ConfigurationPrinter);
            }
        }
    }

    @Test
    public void addParameterNullTests() {
        Object object = new Object();
        assertTrue("Returned value should be an instance of ConfigurationPrinter",configurationPrinter.addParameter(null, null) instanceof ConfigurationPrinter);
        assertTrue("Returned value should be an instance of ConfigurationPrinter",configurationPrinter.addParameter("string", null) instanceof ConfigurationPrinter);
        assertTrue("Returned value should be an instance of ConfigurationPrinter",configurationPrinter.addParameter(null, object) instanceof ConfigurationPrinter);
    }

    @Test
    public void addParameterTest() {
        Object[] objects = new Object[]{"[string]", 1, 1.1234, 1123456789L, 1.123456D, 123912839F, true, false, 'a', (byte) 100, (short) 10,};
        String[] stringArray = new String[]{"", "a", "abc", "ABC", "123",
                "qwertyuiopšđasdfghjklčćžzxcvbnmQWERTYUIOPŠĐASDFGHJKLČĆŽZXCVBNM123457890"};
        for(String stringArrayVal:stringArray) {
            for(Object object:objects) {
                assertTrue("Returned value should be an instance of ConfigurationPrinter", configurationPrinter.addParameter(stringArrayVal, object) instanceof ConfigurationPrinter);
            }
        }
    }

    @Test
    public void increaseIndentationTest() {
        for(int i=0; i<10; i++){
            assertTrue("Expected and actual values are not the same!", configurationPrinter.increaseIndentation() instanceof ConfigurationPrinter);
        }
    }

    @Test
    public void decreaseIndentationTest() {
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
        assertEquals("Expected and actual values are not the same!", "Info", configurationPrinter.getTitle());
        assertTrue("Returned value should be instance of ConfigurationPrinter!", configurationPrinter.getParentLogger() instanceof Logger);
        assertEquals("Expected and actual values are not the same!", ConfigurationPrinter.LogLevel.INFO, configurationPrinter.getLogLevel());
    }

    @Test
    public void printLogTest() {
        Object[] objects = new Object[]{"[string]", 1, 1.1234, 1123456789L, 1.123456D, 123912839F, true, false, 'a', (byte) 100, (short) 10,};
        configurationPrinter.withLogger(logger);
        configurationPrinter.withTitle("Title");
        configurationPrinter.addHeader("Header");
        ConfigurationPrinter.LogLevel[] enumArray = new ConfigurationPrinter.LogLevel[]{
                ConfigurationPrinter.LogLevel.DEBUG,
                ConfigurationPrinter.LogLevel.ERROR,
                ConfigurationPrinter.LogLevel.INFO,
                ConfigurationPrinter.LogLevel.TRACE,
                ConfigurationPrinter.LogLevel.WARN};
        for (Object object:objects){
            for(ConfigurationPrinter.LogLevel enumArrayVal:enumArray) {
                configurationPrinter.withLogLevel(enumArrayVal);
                configurationPrinter.withTitleAlignment(ConfigurationPrinter.TitleAlignment.CENTER);
                configurationPrinter.addParameter("parameter",object);
                configurationPrinter.printLog();
            }
        }
        for (Object object:objects) {
            for (ConfigurationPrinter.LogLevel enumArrayVal : enumArray) {
                configurationPrinter.withLogLevel(enumArrayVal);
                configurationPrinter.withTitleAlignment(ConfigurationPrinter.TitleAlignment.LEFT);
                configurationPrinter.addParameter("parameter", object);
                configurationPrinter.printLog();
            }
        }
        for (Object object:objects) {
            for (ConfigurationPrinter.LogLevel enumArrayVal : enumArray) {
                configurationPrinter.withLogLevel(enumArrayVal);
                configurationPrinter.withTitleAlignment(ConfigurationPrinter.TitleAlignment.RIGHT);
                configurationPrinter.addParameter("parameter", object);
                configurationPrinter.printLog();
            }
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