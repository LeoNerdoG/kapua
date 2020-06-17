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
package org.eclipse.kapua.commons.metric;

import com.codahale.metrics.MetricRegistry;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.lang.reflect.Constructor;

@Category(JUnitTests.class)
public class MetricsServiceImplTest extends Assert {

    @Before
    public void createInstanceOfClasses() {
        metricServiceImpl = new MetricsServiceImpl();
        metricRegistry = new MetricRegistry();
    }

    MetricRegistry metricRegistry;
    MetricsServiceImpl metricServiceImpl;

    @Test
    public void constructorLogicTest() throws Exception {
        Constructor<MetricsServiceImpl> metricsServiceConstructor = MetricsServiceImpl.class.getDeclaredConstructor();
        metricsServiceConstructor.setAccessible(true);
        metricsServiceConstructor.newInstance();
    }

    @Test
    public void getMetricRegistry() {
        assertNotNull(metricServiceImpl.getMetricRegistry());
    }

    @Test
    public void getCounterTest() {
        assertNotNull("null", metricServiceImpl.getCounter("module", "component", "name1", "name2", "name3"));
    }

    @Test
    public void getHistogramTest() {
        assertNotNull("null", metricServiceImpl.getHistogram("module", "component", "name1", "name2", "name3"));
    }

    @Test
    public void getTimerTest() {
        assertNotNull("null", metricServiceImpl.getTimer("module", "component", "name1", "name2", "name3"));
    }
}