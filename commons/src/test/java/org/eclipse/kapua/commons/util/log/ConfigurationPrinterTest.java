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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Category(JUnitTests.class)
public class ConfigurationPrinterTest extends Assert {

    ConfigurationPrinter configurationPrinter;
    Logger mockLogger;
    ConfigurationPrinter cf;

    @Before
    public void initialize() {
        configurationPrinter = new ConfigurationPrinter();
        mockLogger = Mockito.mock(Logger.class);
        cf = Mockito.mock((ConfigurationPrinter.class));
    }

    @Test
    public void createTest() {
        assertNotNull("Null not expected", configurationPrinter);
        assertThat("Instance of ConfigurationPrinter expected.", ConfigurationPrinter.create(), IsInstanceOf.instanceOf(ConfigurationPrinter.class));
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
    public void getAndWithLogLevelNotNullTest() {
        ConfigurationPrinter.LogLevel[] logLevel = new ConfigurationPrinter.LogLevel[]{
                ConfigurationPrinter.LogLevel.DEBUG,
                ConfigurationPrinter.LogLevel.ERROR,
                ConfigurationPrinter.LogLevel.INFO,
                ConfigurationPrinter.LogLevel.TRACE,
                ConfigurationPrinter.LogLevel.WARN
        };
        for (ConfigurationPrinter.LogLevel level : logLevel) {
            configurationPrinter.withLogLevel(level);
            assertEquals("Expected and actual values should be the same", level,
                    configurationPrinter.getLogLevel());
        }
    }

    @Test
    public void withLogLevelNullTest() {
        ConfigurationPrinter
                .create()
                .withLogger(mockLogger)
                .withTitle("Title")
                .printLog();
        Mockito.verify(mockLogger).info("=================== {} ===================", "Title");
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
    public void withTitleTest() {
        ConfigurationPrinter
                .create()
                .withLogger(mockLogger)
                .withLogLevel(ConfigurationPrinter.LogLevel.INFO)
                .withTitle("Title")
                .printLog();
        Mockito.verify(mockLogger).info("=================== {} ===================", "Title");
    }

    @Test
    public void withTitleNullTest() {
        ConfigurationPrinter
                .create()
                .withLogger(mockLogger)
                .withLogLevel(ConfigurationPrinter.LogLevel.INFO)
                .printLog();
        Mockito.verify(mockLogger).info("=================== {} ===================", "Info");
    }

//    @Test
//    public void withLogLevelTest() {
//        ConfigurationPrinter.LogLevel[] logLevel = new ConfigurationPrinter.LogLevel[]{
//                ConfigurationPrinter.LogLevel.DEBUG,
//                ConfigurationPrinter.LogLevel.ERROR,
//                ConfigurationPrinter.LogLevel.INFO,
//                ConfigurationPrinter.LogLevel.TRACE,
//                ConfigurationPrinter.LogLevel.WARN
//        };
//        for (ConfigurationPrinter.LogLevel level : logLevel) {
//            ConfigurationPrinter
//                    .create()
//                    .withLogger(mockLogger)
//                    .withLogLevel(level)
//                    .withTitle("Title")
//                    .printLog();
//            Mockito.verify(mockLogger).("=================== {} ===================", "Title");
//        }
//    }


    @Test
    public void getAndWithTitleAlignmentTest() {
        ConfigurationPrinter.TitleAlignment[] titleAlignment = new ConfigurationPrinter.TitleAlignment[]{
                ConfigurationPrinter.TitleAlignment.CENTER,
                ConfigurationPrinter.TitleAlignment.LEFT,
                ConfigurationPrinter.TitleAlignment.RIGHT
        };

        for (ConfigurationPrinter.TitleAlignment tA : titleAlignment) {
            configurationPrinter.withTitleAlignment(tA);
            assertEquals("Expected and actual values should be the same", tA,
                    configurationPrinter.getTitleAlignment());
        }

    }

    @Test
    public void getConfigurationAddHeaderAndParameterTest() {
        List list = new ArrayList();
        ConfigurationPrinter configurationPrinterAddHeader = configurationPrinter;
        ConfigurationPrinter configurationPrinterAddParameter = configurationPrinter;

        assertEquals("Expected and actual values should be the same", list, configurationPrinter.getConfigurations());

        configurationPrinter.addHeader("Header");
        configurationPrinterAddParameter.addParameter("Parameter", null);

        assertNotEquals("Expected and actual values should not be the same", list, configurationPrinterAddHeader.getConfigurations());
        assertNotEquals("Expected and actual values should not be the same", list, configurationPrinterAddParameter.getConfigurations());
    }

    @Test
    public void addParameterTest() {
        ConfigurationPrinter
                .create()
                .withLogger(mockLogger)
                .withLogLevel(ConfigurationPrinter.LogLevel.INFO)
                .addParameter("Liquibase Version", "3.6.3")
                .printLog();
        Mockito.verify(mockLogger).info("=================== {} ===================", "Info");
        Mockito.verify(mockLogger).info("|  {}", "Liquibase Version: 3.6.3");
//        tu ne dela ker so ti narekovaji prevec nimam pojma zakaj
    }

    @Test
    public void addHeaderTest() {
        String header = "DB connection info";
        ConfigurationPrinter
                .create()
                .withLogger(mockLogger)
                .withLogLevel(ConfigurationPrinter.LogLevel.INFO)
                .addHeader("DB connection info")
                .printLog();
        Mockito.verify(mockLogger).info("=================== {} ===================", "Info");
        Mockito.verify(mockLogger).info("|\t{}", (String) "DB connection info");
//        tu ne dela ker so ti narekovaji prevec nimam pojma zakaj
    }


    @Test
    public void increaseIndentationTest() {

    }

    @Test
    public void printLogTest() {
        System.out.println();
    }


}