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
package org.eclipse.kapua.app.api.web;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import javax.servlet.ServletContextEvent;

@Category(JUnitTests.class)
public class RestApiListenerTest extends Assert {

    RestApiListener restApiListener;
    ServletContextEvent servletContextEvent;

    @Before
    public void initialize() {
        restApiListener = new RestApiListener();
        servletContextEvent = Mockito.mock(ServletContextEvent.class);
    }

    @Test
    public void contextInitializedFalseSchemaUpdateTest() {
        System.setProperty("commons.db.schema.update", "false");
        try {
            restApiListener.contextInitialized(servletContextEvent);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test
    public void contextInitializedTest() {
        System.setProperty("commons.db.schema.update", "true");
        System.setProperty("commons.db.jdbc.driver", "org.h2.Driver");
        System.setProperty("commons.db.jdbcConnectionUrlResolver", "H2");
        try {
            restApiListener.contextInitialized(servletContextEvent);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test
    public void contextInitializedDriverClassNotFoundTest() {
        System.setProperty("commons.db.schema.update", "true");
        System.setProperty("commons.db.jdbcConnectionUrlResolver", "H2");
        System.setProperty("commons.db.jdbc.driver", "name");
        try {
            restApiListener.contextInitialized(servletContextEvent);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test(expected = ExceptionInInitializerError.class)
    public void contextInitializedExceptionInInitializerTest() {
        System.setProperty("commons.db.schema.update", "true");
        System.setProperty("commons.db.jdbcConnectionUrlResolver", "DEFAULT");
        System.setProperty("commons.db.jdbc.driver", "org.h2.Driver");
        restApiListener.contextInitialized(servletContextEvent);
    }

    @Test
    public void contextDestroyedTest() {
        System.setProperty("commons.db.schema.update", "true");
        System.setProperty("commons.db.jdbcConnectionUrlResolver", "H2");
        System.setProperty("commons.db.jdbc.driver", "org.h2.Driver");
        try {
            restApiListener.contextInitialized(servletContextEvent);
            restApiListener.contextDestroyed(servletContextEvent);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }

    @Test
    public void contextDestroyedNullModuleBundleTest() {
        System.setProperty("commons.db.schema.update", "true");
        System.setProperty("commons.db.jdbcConnectionUrlResolver", "H2");
        System.setProperty("commons.db.jdbc.driver", "org.h2.Driver");
        try {
            restApiListener.contextDestroyed(servletContextEvent);
        } catch (Exception e) {
            fail("Exception not expected.");
        }
    }
}