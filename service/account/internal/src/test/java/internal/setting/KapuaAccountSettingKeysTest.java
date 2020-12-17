/*******************************************************************************
 * Copyright (c) 2021 Eurotech and/or its affiliates and others
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package internal.setting;


import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.service.account.internal.setting.KapuaAccountSettingKeys;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.lang.reflect.InvocationTargetException;

@Category(JUnitTests.class)
public class KapuaAccountSettingKeysTest extends Assert {

    String accountKey = "account.key";
    String accountEventAddress = "account.eventAddress";
    KapuaAccountSettingKeys kapuaAccountSettingKeys;

    @Test
    public void kapuaAccountSettingKeysEnumTest() {
        assertEquals("Expected and actual values should be the same!", "ACCOUNT_KEY", kapuaAccountSettingKeys.ACCOUNT_KEY.toString());
        assertEquals("Expected and actual values should be the same!", "ACCOUNT_EVENT_ADDRESS", kapuaAccountSettingKeys.ACCOUNT_EVENT_ADDRESS.toString());
    }

    @Test
    public void kapuaAccountSettingKeysTest() {
//        String[] stringArray = new String[]{"", "a", "abc", "123", "123456790/+đpoiuytrewasdfghjklčćž-,mnbcxz<!#$%&'()=?*ĐPOIUYTREWASDFGHJKLČĆŽ>ZXCVBNM;:_ '"};
//        assertNull(kapuaAccountSettingKeys.key());
//        for(String string:stringArray){
//            kapuaAccountSettingKeys = new KapuaAccountSettingKeys(string);
//        }
    }

    @Test
    public void kapuaAccountSettingKeysConstructorTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        Constructor<KapuaAccountSettingKeys> privateMethod = KapuaAccountSettingKeys.class.getDeclaredConstructor();
//        privateMethod.setAccessible(true);
//        privateMethod.newInstance("string");
//
//        assertEquals("string", kapuaAccountSettingKeys.key());

//        Constructor<ArgumentValidator> argvalidator = ArgumentValidator.class.getDeclaredConstructor();
//        argvalidator.setAccessible(true);
//        argvalidator.newInstance();

    }
}
