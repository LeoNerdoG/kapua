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

import org.eclipse.kapua.transport.mqtt.pooling.setting.MqttClientPoolSetting;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MqttClientPoolSettingTest extends Assert {

    @Test
    public void privateConstructorTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<MqttClientPoolSetting> constructor = MqttClientPoolSetting.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        MqttClientPoolSetting mqttClientPoolSetting = constructor.newInstance();
        assertThat("Instance of MqttClientPoolSetting expected!", mqttClientPoolSetting, IsInstanceOf.instanceOf(MqttClientPoolSetting.class));
    }

    @Test
    public void getInstanceTest() {
        assertNotNull("Instance of MqttClientPoolSetting expected!", MqttClientPoolSetting.getInstance());
    }
}