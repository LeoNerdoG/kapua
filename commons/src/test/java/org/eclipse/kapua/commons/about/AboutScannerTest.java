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
package org.eclipse.kapua.commons.about;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.reflections.util.ClasspathHelper;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

@Category(JUnitTests.class)
public class AboutScannerTest extends Assert {

    @Test
    public void scan1Test() {
        final AboutScanner scanner = AboutScanner.scan();
        assertNotNull("Null not expected", scanner);
    }

    @Test
    public void getEntriesTest() throws NoSuchFieldException, IllegalAccessException {
        final AboutScanner aboutScanner = AboutScanner.scan();
        List<AboutEntry> aboutEntry = new LinkedList<>();
        final Field field = aboutScanner.getClass().getDeclaredField("entries");
        field.setAccessible(true);
        field.set(aboutScanner, aboutEntry);

        final List<AboutEntry> result = aboutScanner.getEntries();
        assertEquals("Field wasn't retrieved properly", result, aboutEntry);
    }

    /*
    @Test
    public void getEntriesTest() {
        AboutScanner scanner = AboutScanner.scan();
        AboutScanner nullScanner = null;
        String[] expectedNames = new String[]{"listenablefuture", "netty-all", "reflections",
                "Logback Classic Module", "Logback Core Module", "Guava InternalFutureFailureAccess and InternalFutures",
                "Guava: Google Core Libraries for Java", "guice", "H2 Database Engine", "FindbugsAnnotations",
                "Metrics Core", "Javassist", "javax.annotation API", "JSR107 API and SPI", "Java Persistence API 2.1",
                "Bean Validation API", "jcl-over-slf4j", "Joda-Time", "Commons Configuration", "Commons IO", "Apache Commons Lang",
                "Commons Lang", "Apache Commons Pool", "Apache Geronimo JMS Spec 2.0", "QpidJMS Client", "Proton-J", "AssertJ fluent assertions",
                "EclipseLink ANTLR", "EclipseLink ASM", "EclipseLink Core", "EclipseLink Hermes Parser", "EclipseLink JPA",
                "EclipseLink MOXy", "JSR 353 (JSON Processing) Default Provider", "Liquibase Core",
                "Mockito Mock Library for Java. Core bundle requires Hamcrest-core and Objenesis. ", "Objenesis", "SnakeYAML", "slf4j-api"};
        List<AboutEntry> entries = scanner.getEntries();
        NullPointerException nullPointerException = new NullPointerException();

        for (int i = 0; i < expectedNames.length; i++) {
            assertEquals("Exception not expected", expectedNames[i], entries.get(i).getName());
        }

        try {
            nullScanner.getEntries();
        } catch (Exception e) {
            assertEquals("NullPointerException expected", nullPointerException.toString(), e.toString());
        }
    } */

    @Test
    public void scan2Test() {
        AboutScanner scanner = AboutScanner.scan(ClasspathHelper.forClassLoader().stream());
        assertNotNull("Null not expected", scanner);

        NullPointerException nullPointerException = new NullPointerException();
        try {
            AboutScanner invalidScanner = AboutScanner.scan(null);
        } catch (Exception e) {
            assertEquals("NullPointerException expected", nullPointerException.toString(), e.toString());
        }
    }
}