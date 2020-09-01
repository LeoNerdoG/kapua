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
package org.eclipse.kapua.transport.mqtt.mqtt.pooling.setting;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.transport.mqtt.pooling.setting.MqttClientPoolSettingKeys;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class MqttClientPoolSettingKeysTest extends Assert {

    @Test
    public void clientPoolClientIdPrefixTest() {
        assertEquals("Expected and actual values should be the same!", "CLIENT_POOL_CLIENT_ID_PREFIX", MqttClientPoolSettingKeys.CLIENT_POOL_CLIENT_ID_PREFIX.name());
        assertEquals("Expected and actual values should be the same!", "client.pool.client.id.prefix", MqttClientPoolSettingKeys.CLIENT_POOL_CLIENT_ID_PREFIX.key());
    }

    @Test
    public void clientPoolSizeIdleMinTest() {
        assertEquals("Expected and actual values should be the same!", "CLIENT_POOL_SIZE_IDLE_MIN", MqttClientPoolSettingKeys.CLIENT_POOL_SIZE_IDLE_MIN.name());
        assertEquals("Expected and actual values should be the same!", "client.pool.size.idle.min", MqttClientPoolSettingKeys.CLIENT_POOL_SIZE_IDLE_MIN.key());
    }

    @Test
    public void clientPoolSizeIdleMaxTest() {
        assertEquals("Expected and actual values should be the same!", "CLIENT_POOL_SIZE_IDLE_MAX", MqttClientPoolSettingKeys.CLIENT_POOL_SIZE_IDLE_MAX.name());
        assertEquals("Expected and actual values should be the same!", "client.pool.size.idle.max", MqttClientPoolSettingKeys.CLIENT_POOL_SIZE_IDLE_MAX.key());
    }

    @Test
    public void clientPoolSizeTotalMaxTest() {
        assertEquals("Expected and actual values should be the same!", "CLIENT_POOL_SIZE_TOTAL_MAX", MqttClientPoolSettingKeys.CLIENT_POOL_SIZE_TOTAL_MAX.name());
        assertEquals("Expected and actual values should be the same!", "client.pool.size.total.max", MqttClientPoolSettingKeys.CLIENT_POOL_SIZE_TOTAL_MAX.key());
    }

    @Test
    public void clientPoolBorrowWaitMax() {
        assertEquals("Expected and actual values should be the same!", "CLIENT_POOL_BORROW_WAIT_MAX", MqttClientPoolSettingKeys.CLIENT_POOL_BORROW_WAIT_MAX.name());
        assertEquals("Expected and actual values should be the same!", "client.pool.borrow.wait.max", MqttClientPoolSettingKeys.CLIENT_POOL_BORROW_WAIT_MAX.key());
    }

    @Test
    public void clientPoolEvictionIntervalTest() {
        assertEquals("Expected and actual values should be the same!", "CLIENT_POOL_EVICTION_INTERVAL", MqttClientPoolSettingKeys.CLIENT_POOL_EVICTION_INTERVAL.name());
        assertEquals("Expected and actual values should be the same!", "client.pool.eviction.interval", MqttClientPoolSettingKeys.CLIENT_POOL_EVICTION_INTERVAL.key());
    }

    @Test
    public void clientPoolExhaustedBlock() {
        assertEquals("Expected and actual values should be the same!", "CLIENT_POOL_WHEN_EXAUSTED_BLOCK", MqttClientPoolSettingKeys.CLIENT_POOL_WHEN_EXAUSTED_BLOCK.name());
        assertEquals("Expected and actual values should be the same!", "client.pool.when.exhausted.block", MqttClientPoolSettingKeys.CLIENT_POOL_WHEN_EXAUSTED_BLOCK.key());
    }

    @Test
    public void clientPoolWhenIdleTest() {
        assertEquals("Expected and actual values should be the same!", "CLIENT_POOL_WHEN_IDLE_TEST", MqttClientPoolSettingKeys.CLIENT_POOL_WHEN_IDLE_TEST.name());
        assertEquals("Expected and actual values should be the same!", "client.pool.when.idle.test", MqttClientPoolSettingKeys.CLIENT_POOL_WHEN_IDLE_TEST.key());
    }

    @Test
    public void clientPoolOnBorrowTest() {
        assertEquals("Expected and actual values should be the same!", "CLIENT_POOL_ON_BORROW_TEST", MqttClientPoolSettingKeys.CLIENT_POOL_ON_BORROW_TEST.name());
        assertEquals("Expected and actual values should be the same!", "client.pool.on.borrow.test", MqttClientPoolSettingKeys.CLIENT_POOL_ON_BORROW_TEST.key());
    }

    @Test
    public void clientPoolOnReturnTest() {
        assertEquals("Expected and actual values should be the same!", "CLIENT_POOL_ON_RETURN_TEST", MqttClientPoolSettingKeys.CLIENT_POOL_ON_RETURN_TEST.name());
        assertEquals("Expected and actual values should be the same!", "client.pool.on.return.test", MqttClientPoolSettingKeys.CLIENT_POOL_ON_RETURN_TEST.key());
    }
}