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
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;

@Category(JUnitTests.class)
public class ConfigurationPrinterTest extends Assert {

    @Test
    public void getAndWithParentLoggerTest() {
        ConfigurationPrinter configurationPrinter = new ConfigurationPrinter();
        Logger logger = LoggerFactory.getLogger(ConfigurationPrinter.class);

        assertNull("Null expected", configurationPrinter.getParentLogger());
        configurationPrinter.withLogger(logger);
        assertEquals("Expected and actual values should be the same", logger, configurationPrinter.getParentLogger());
        configurationPrinter.withLogger(null);
        assertNull("Null expected", configurationPrinter.getParentLogger());
    }

    @Test
    public void getAndWithLogLevelTest() {
        ConfigurationPrinter configurationPrinter = new ConfigurationPrinter();
        ConfigurationPrinter.LogLevel[] logLevel = new ConfigurationPrinter.LogLevel[]{
                ConfigurationPrinter.LogLevel.DEBUG,
                ConfigurationPrinter.LogLevel.ERROR,
                ConfigurationPrinter.LogLevel.INFO,
                ConfigurationPrinter.LogLevel.TRACE,
                ConfigurationPrinter.LogLevel.WARN
                };
        assertNull("Null expected", configurationPrinter.getLogLevel());
        for (ConfigurationPrinter.LogLevel level : logLevel) {
            configurationPrinter.withLogLevel(level);
            assertEquals("Expected and actual values should be the same", level,
                    configurationPrinter.getLogLevel());
        }
    }

    @Test
    public void getAndWithTitleTest() {
        ConfigurationPrinter configurationPrinter = new ConfigurationPrinter();
        String title = "string";

        assertNull("Null expected", configurationPrinter.getTitle());

        configurationPrinter.withTitle(title);

        assertEquals("Expected and actual values should be the same", "string",
                configurationPrinter.getTitle());

    }

    @Test
    public void getAndWithTitleAlignmentTest() {
        ConfigurationPrinter configurationPrinter = new ConfigurationPrinter();
        ConfigurationPrinter.TitleAlignment[] titleAlignment  = new ConfigurationPrinter.TitleAlignment[] {
                ConfigurationPrinter.TitleAlignment.CENTER,
                ConfigurationPrinter.TitleAlignment.LEFT,
                ConfigurationPrinter.TitleAlignment.RIGHT
        };

        assertEquals("Expected and actual values should be the same",
                ConfigurationPrinter.TitleAlignment.CENTER, configurationPrinter.getTitleAlignment());

        for (ConfigurationPrinter.TitleAlignment tA : titleAlignment) {
            configurationPrinter.withTitleAlignment(tA);
            assertEquals("Expected and actual values should be the same",tA,
                    configurationPrinter.getTitleAlignment());
        }

    }

    @Test
    public void getConfigurationTest() {
        ConfigurationPrinter configurationPrinter = new ConfigurationPrinter();

        assertEquals("Expected and actual values should be the same",
                new ArrayList<>(), configurationPrinter.getConfigurations());

    }




}