/*******************************************************************************
 * Copyright (c) 2017, 2020 Eurotech and/or its affiliates and others
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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Category(JUnitTests.class)
public class ConfigurationPrinterTest extends Assert {

    ConfigurationPrinter configurationPrinter;
    Logger mockedLogger;

    @Before
    public void setUp() {
        configurationPrinter = new ConfigurationPrinter();
        mockedLogger = Mockito.mock(Logger.class);
    }


    @Test
    public void getAndWithParentLoggerTest() {
        Logger logger = LoggerFactory.getLogger(ConfigurationPrinter.class);

        assertNull("Null expected", configurationPrinter.getParentLogger());
        configurationPrinter.withLogger(logger);
        assertEquals("Expected and actual values should be the same", logger, configurationPrinter.getParentLogger());
        configurationPrinter.withLogger(null);
        assertNull("Null expected", configurationPrinter.getParentLogger());
    }

    @Test
    public void getAndWithLogLevelTest() {
        ConfigurationPrinter.LogLevel[] logLevels = new ConfigurationPrinter.LogLevel[]{
                ConfigurationPrinter.LogLevel.DEBUG,
                ConfigurationPrinter.LogLevel.ERROR,
                ConfigurationPrinter.LogLevel.INFO,
                ConfigurationPrinter.LogLevel.TRACE,
                ConfigurationPrinter.LogLevel.WARN
        };
        for (ConfigurationPrinter.LogLevel logLevel : logLevels) {
            configurationPrinter.withLogLevel(logLevel);
            assertEquals("Expected and actual values should be the same", logLevel,
                    configurationPrinter.getLogLevel());
        }
    }


    //    @Test
//    public void getAndWithTitleTest() {
//        String title = "Title";
//
//        assertNull("Null expected", configurationPrinter.getTitle());
//
//        configurationPrinter.withTitle(title);
//
//        assertEquals("Expected and actual values should be the same", "Title",
//                configurationPrinter.getTitle());
//
//    }

    @Test
//    tukaj moras spremenit, da dodas se razlicne stringe noter
    public void withTitleTest() {
        String[] specialSymbols = new String[]{"!", "\"", "#", "$", "%", "&", "'", "(", ")", "=", "/", "?", "+", "*", "<", ">", ",", ";",
                ".", ":", "-", "_", "⁄", "@", "‹", "›", "€", "–", "°", "·", "", "Œ", "„", "‰", "”", "’", "Ø", "∏", "{", "}", "Æ", "æ", "Ò", "", "Å",
                "Í", "Î", "~", "«", "◊", "Ñ", "¯", "È", "ˇ", " "};
        String[] title = new String[]{"", "a", "qwertyuiiopasdfghjklšđčćžzxcvbnm!", "1234567890", "QWERTYUIOPŠĐASDFGHJKLČĆŽZXCVBNM"};
        for (String titleVal : title) {
            for (String specSymVal : specialSymbols) {
                ConfigurationPrinter
                        .create()
                        .withLogger(mockedLogger)
                        .withLogLevel(ConfigurationPrinter.LogLevel.INFO)
                        .withTitle(titleVal + specSymVal)
                        .printLog();
                Mockito.verify(mockedLogger, Mockito.times(1)).info("=================== {} ===================", titleVal + specSymVal);
            }
        }

    }

    @Test
    public void getAndWithTitleAlignmentTest() {
        ConfigurationPrinter.TitleAlignment[] titleAlignment = new ConfigurationPrinter.TitleAlignment[]{
                ConfigurationPrinter.TitleAlignment.CENTER,
                ConfigurationPrinter.TitleAlignment.LEFT,
                ConfigurationPrinter.TitleAlignment.RIGHT
        };

        for (ConfigurationPrinter.TitleAlignment tA : titleAlignment) {
            configurationPrinter.withTitleAlignment(tA);
            assertEquals("Expected and actual values should be the same", tA, configurationPrinter.getTitleAlignment());
        }
    }

    @Test
    public void withTitleAlignmentNullTest() {
        ConfigurationPrinter
                .create()
                .withLogger(mockedLogger)
                .withLogLevel(ConfigurationPrinter.LogLevel.INFO)
                .withTitle("Title")
                .printLog();
        Mockito.verify(mockedLogger).info("=================== {} ===================", "Title");
    }

    @Test
    public void withTitleAlignmentCenterTest() {
        ConfigurationPrinter
                .create()
                .withLogger(mockedLogger)
                .withLogLevel(ConfigurationPrinter.LogLevel.INFO)
                .withTitleAlignment(ConfigurationPrinter.TitleAlignment.CENTER)
                .withTitle("Title")
                .printLog();
        Mockito.verify(mockedLogger).info("=================== {} ===================", "Title");
    }

    @Test
    public void withTitleAlignmentRightTest() {
        ConfigurationPrinter
                .create()
                .withLogger(mockedLogger)
                .withLogLevel(ConfigurationPrinter.LogLevel.INFO)
                .withTitleAlignment(ConfigurationPrinter.TitleAlignment.RIGHT)
                .withTitle("Title")
                .printLog();
        Mockito.verify(mockedLogger).info("===================================== {} =", "Title");
    }

    @Test
    public void withTitleAlignmentLeftTest() {
        ConfigurationPrinter
                .create()
                .withLogger(mockedLogger)
                .withLogLevel(ConfigurationPrinter.LogLevel.INFO)
                .withTitleAlignment(ConfigurationPrinter.TitleAlignment.LEFT)
                .withTitle("Title")
                .printLog();
        Mockito.verify(mockedLogger).info("= {} =====================================", "Title");
    }

//    @Test
////    tole bos morala razdelit - pomoc koda od Leonarda
//    public void getConfigurationAddHeaderAndParameterTest() {
//        List list = new ArrayList();
//        ConfigurationPrinter configurationPrinterAddHeader = configurationPrinter;
//        ConfigurationPrinter configurationPrinterAddParameter = configurationPrinter;
//
//        assertEquals("Expected and actual values should be the same", list, configurationPrinter.getConfigurations());
//
//        configurationPrinter.addHeader("Header");
//        configurationPrinterAddParameter.addParameter("Parameter", null);
//
//        assertNotEquals("Expected and actual values should not be the same", list, configurationPrinterAddHeader.getConfigurations());
//        assertNotEquals("Expected and actual values should not be the same", list, configurationPrinterAddParameter.getConfigurations());
//    }

    @Test
    public void getConfigurationsNullTest() {
        assertEquals("Expected and actual values are not the same!", new ArrayList<>(), configurationPrinter.getConfigurations());
    }

    @Test
    public void addHeaderTest() throws NoSuchFieldException {
        String[] specialSymbols = new String[]{"!", "\"", "#", "$", "%", "&", "'", "(", ")", "=", "/", "?", "+", "*", "<", ">", ",", ";",
                ".", ":", "-", "_", "⁄", "@", "‹", "›", "€", "€", "–", "°", "·", "", "Œ", "„", "‰", "”", "’", "Ø", "∏", "{", "}", "Æ", "æ", "Ò", "", "Å",
                "Í", "Î", "~", "«", "◊", "Ñ", "¯", "È", "ˇ", " "};
        String[] title = new String[]{"", "a", "qwertyuiiopasdfghjklšđčćžzxcvbnm!", "1234567890", "QWERTYUIOPŠĐASDFGHJKLČĆŽZXCVBNM"};
        for (String titleVal : title) {
            for (String specSymVal : specialSymbols) {
                assertTrue("Returned value should an instance of ConfigurationPrinter!", configurationPrinter.addHeader(titleVal + specSymVal) instanceof ConfigurationPrinter);
            }
        }
    }

    @Test
    public void addParameterNullTests() {
        Object object = new Object();
        assertTrue("Returned value should be an instance of ConfigurationPrinter", configurationPrinter.addParameter(null, null) instanceof ConfigurationPrinter);
        assertTrue("Returned value should be an instance of ConfigurationPrinter", configurationPrinter.addParameter("string", null) instanceof ConfigurationPrinter);
        assertTrue("Returned value should be an instance of ConfigurationPrinter", configurationPrinter.addParameter(null, object) instanceof ConfigurationPrinter);
    }

    @Test
    public void addParameterTest() {
        Object[] objects = new Object[]{"[string]", 1, 1.1234, 1123456789L, 1.123456D, 123912839F, true, false, 'a', (byte) 100, (short) 10,};
        String[] stringArray = new String[]{"", "a", "abc", "ABC", "123",
                "qwertyuiopšđasdfghjklčćžzxcvbnmQWERTYUIOPŠĐASDFGHJKLČĆŽZXCVBNM123457890"};
        for (String stringArrayVal : stringArray) {
            for (Object object : objects) {
                assertTrue("Returned value should be an instance of ConfigurationPrinter", configurationPrinter.addParameter(stringArrayVal, object) instanceof ConfigurationPrinter);
            }
        }
    }

    @Test
    public void increaseIndentationTest() {
        for (int i = 0; i < 10; i++) {
            assertTrue("Expected and actual values are not the same!", configurationPrinter.increaseIndentation() instanceof ConfigurationPrinter);
        }
    }

    @Test
    public void decreaseIndentationTest() {
        for (int i = 0; i < 10; i++) {
            configurationPrinter.increaseIndentation();
        }
        for (int i = 10; i >= 0; i--) {
            assertTrue("Expected and actual values are not the same!", configurationPrinter.decreaseIndentation() instanceof ConfigurationPrinter);
        }
    }

    @Test
    public void printLogParentLoggerNullTest() {
        configurationPrinter.printLog();
        assertNotNull(configurationPrinter.getParentLogger());
    }

    @Test
    public void printLogLogLevelNullTest() {
        ConfigurationPrinter
                .create()
                .withLogger(mockedLogger)
                .withTitle("Title")
                .printLog();
        Mockito.verify(mockedLogger).info("=================== {} ===================", "Title");
    }


    @Test
    public void printLogLogLevelDebugTest() {
        ConfigurationPrinter
                .create()
                .withLogger(mockedLogger)
                .withLogLevel(ConfigurationPrinter.LogLevel.DEBUG)
                .withTitle("Title")
                .printLog();
        Mockito.verify(mockedLogger).debug("=================== {} ===================", "Title");
    }

    @Test
    public void printLogLogLevelErrorTest() {
        ConfigurationPrinter
                .create()
                .withLogger(mockedLogger)
                .withLogLevel(ConfigurationPrinter.LogLevel.ERROR)
                .withTitle("Title")
                .printLog();
        Mockito.verify(mockedLogger).error("=================== {} ===================", "Title");
    }

    @Test
    public void printLogLogLevelInfoTest() {
        ConfigurationPrinter
                .create()
                .withLogger(mockedLogger)
                .withLogLevel(ConfigurationPrinter.LogLevel.INFO)
                .withTitle("Title")
                .printLog();
        Mockito.verify(mockedLogger).info("=================== {} ===================", "Title");
    }

    @Test
    public void printLogLogLevelTraceTest() {
        ConfigurationPrinter
                .create()
                .withLogger(mockedLogger)
                .withLogLevel(ConfigurationPrinter.LogLevel.TRACE)
                .withTitle("Title")
                .printLog();
        Mockito.verify(mockedLogger).trace("=================== {} ===================", "Title");
    }

    @Test
    public void printLogLogLevelWarnTest() {
        ConfigurationPrinter
                .create()
                .withLogger(mockedLogger)
                .withLogLevel(ConfigurationPrinter.LogLevel.WARN)
                .withTitle("Title")
                .printLog();
        Mockito.verify(mockedLogger).warn("=================== {} ===================", "Title");
    }

    @Test
    public void printLogTitleNullTest() {
        ConfigurationPrinter
                .create()
                .withLogger(mockedLogger)
                .withLogLevel(ConfigurationPrinter.LogLevel.INFO)
                .printLog();
        Mockito.verify(mockedLogger).info("=================== {} ===================", "Info");
    }

    @Test
    public void createTest() {
        assertThat("Instance of ConfigurationPrinter expected.", ConfigurationPrinter.create(), IsInstanceOf.instanceOf(ConfigurationPrinter.class));
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
    public void titleAlignmentEnumTest() {
        assertNotNull(ConfigurationPrinter.TitleAlignment.valueOf("LEFT"));
        assertNotNull(ConfigurationPrinter.TitleAlignment.valueOf("RIGHT"));
        assertNotNull(ConfigurationPrinter.TitleAlignment.valueOf("CENTER"));
    }

}