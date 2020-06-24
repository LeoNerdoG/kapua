// COMMENT: Please delete all the "RedHat" stuff from the header:
///*******************************************************************************
// * Copyright (c) 2020 Eurotech and/or its affiliates and others
// *
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the Eclipse Public License v1.0
// * which accompanies this distribution, and is available at
// * http://www.eclipse.org/legal/epl-v10.html
// *
// * Contributors:
// *     Eurotech - initial API and implementation
// *******************************************************************************/

/*******************************************************************************
 * Copyright (c) 2020 Red Hat Inc and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Inc - initial API and implementation
 *     Eurotech
 *******************************************************************************/
package org.eclipse.kapua.commons.cache;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;

@Category(JUnitTests.class)
public class LocalCacheTest extends Assert {

    @Test
    // COMMENT: Please rename the test to LocalCache1Test - you should always use the original method name
    // with the keyword "test" in the end (if you have several constructors, just name them 1,2,3...or
    // something similar.
    public void constructor1Test() {
        int[] sizeMax = new int[]{0, 10, 1000, 10000, 2147483647};
        int[] expireAfter = new int[]{0, 10, 1000, 10000, 2147483647};
        Object[] defaultValue = new Object[]{0, 10, 100000, "String", 'c', -10, -1000000000, -100000000000L, 10L, 10.0f, null, 10.10d, true, false,};
        int invalidSizeMax = -1;
        int invalidExpireAfter = -1;
        IllegalArgumentException illegalArgumentExceptionSizeMax = new IllegalArgumentException("maximum size must not be negative");
        IllegalArgumentException illegalArgumentExceptionExpireAfter = new IllegalArgumentException("duration cannot be negative: " + invalidExpireAfter + " SECONDS");

        // COMMENT: Can you use the shorter version of for loop? for (int i: sizeMax)
        for (int i = 0; i < sizeMax.length; i++) {
            // COMMENT: Can you use the shorter version of for loop? for (int j: expireAfter)
            for (int j = 0; j < expireAfter.length; j++) {
                // COMMENT: Can you use the shorter version of for loop? for (int k: defaultValue)
                for (int k = 0; k < defaultValue.length; k++) {
                    LocalCache<Object, Object> localCache = new LocalCache<>(sizeMax[i], expireAfter[j], defaultValue[k]);
                    // COMMENT: can you use getter method to retrieve the actual values of "defaultValue" and "cache"?
                    assertNotNull(localCache);
                }
            }
        }
        // COMMENT: Can you use the shorter version of for loop? for (int i: expireAfter)
        for (int i = 0; i < expireAfter.length; i++) {
            // COMMENT: Can you use the shorter version of for loop? for (int j: defaultValue)
            for (int j = 0; j < defaultValue.length; j++) {
                try {
                    LocalCache<Object, Object> localCache = new LocalCache<>(invalidSizeMax, expireAfter[i], defaultValue[j]);
                } catch (Exception e) {
                    assertEquals("Expected java.lang.IllegalArgumentException: maximum size must not be negative", illegalArgumentExceptionSizeMax.toString(), e.toString());
                }
            }
        }
        // COMMENT: Can you use the shorter version of for loop? for (int i: sizeMax)
        for (int i = 0; i < sizeMax.length; i++) {
            // COMMENT: Can you use the shorter version of for loop? for (int j: defaultValue)
            for (int j = 0; j < defaultValue.length; j++) {
                try {
                    LocalCache<Object, Object> localCache = new LocalCache<>(sizeMax[i], invalidExpireAfter, defaultValue[j]);
                } catch (Exception e) {
                    assertEquals("Expected java.lang.IllegalArgumentException: duration cannot be negative: " + invalidExpireAfter + " SECONDS", illegalArgumentExceptionExpireAfter.toString(), e.toString());
                }
            }
        }
    }

    @Test
    // COMMENT: Please rename the test to LocalCache2Test
    public void constructor2Test() {
        int[] sizeMax = new int[]{0, 10, 1000, 10000, 2147483647};
        Object[] defaultValue = new Object[]{0, 10, 100000, "String", 'c', -10, -1000000000, -100000000000L, 10L, 10.0f, null, 10.10d, true, false,};
        int invalidSizeMax = -1;
        IllegalArgumentException illegalArgumentExceptionSizeMax = new IllegalArgumentException("maximum size must not be negative");
        // COMMENT: Can you use the shorter version of for loop? for (int i: sizeMax)
        for (int i = 0; i < sizeMax.length; i++) {
            // COMMENT: Can you use the shorter version of for loop? for (int j: defaultValue)
            for (int j = 0; j < defaultValue.length; j++) {
                LocalCache<Object, Object> localCache = new LocalCache<>(sizeMax[i], defaultValue[j]);                    // COMMENT: can you use getter method to retrieve the actual values of "defaultValue" and "cache"?
                // COMMENT: can you use getter method to retrieve the actual values of "defaultValue" and "cache"?
                assertNotNull(localCache);
            }
        }
        // COMMENT: Can you use the shorter version of for loop? for (int i: defaultValue)
        for (int i = 0; i < defaultValue.length; i++) {
            try {
                LocalCache<Object, Object> localCache = new LocalCache<>(invalidSizeMax, defaultValue[i]);
            } catch (Exception e) {
                assertEquals("Expected java.lang.IllegalArgumentException: maximum size must not be negative", illegalArgumentExceptionSizeMax.toString(), e.toString());
            }
        }
    }

    // COMMENT: This test is basically the same then the first one. Can you please use this getter in the first
    // test and we can leave this one out, as it is just a copy of the first test?
    @Test
    public void getNamespaceTest() {
        int[] sizeMax = new int[]{0, 10, 1000, 10000, 2147483647};
        Object[] defaultValue = new Object[]{0, 10, 100000, "String", 'c', -10, -1000000000, -100000000000L, 10L, 10.0f, null, 10.10d, true, false,};
        for (int i = 0; i < sizeMax.length; i++) {
            for (int j = 0; j < defaultValue.length; j++) {
                LocalCache<Object, Object> localCache = new LocalCache<>(sizeMax[i], defaultValue[j]);
                assertNull("Null expected", localCache.getNamespace());
            }
        }
    }

    @Test
    public void setNamespaceTest() {
        int[] sizeMax = new int[]{0, 10, 1000, 10000, 2147483647};
        Object[] defaultValue = new Object[]{0, 10, 100000, "String", 'c', -10, -1000000000, 1000000000000L, 1000000, -100000000000L, 10L, 10.0f, null, 10.10d, true, false,};
        String namespace = "namespace";
        // COMMENT: Can you use the shorter version of for loop? for (int i: sizeMax)
        for (int i = 0; i < sizeMax.length; i++) {
            // COMMENT: Can you use the shorter version of for loop? for (int j: defaultValue)
            for (int j = 0; j < defaultValue.length; j++) {
                LocalCache<Object, Object> localCache = new LocalCache<>(sizeMax[i], defaultValue[j]);
                localCache.setNamespace(namespace);
                assertEquals("Exception not expected", namespace, localCache.getNamespace());
                localCache.setNamespace(null);
                assertNull("Null expected", localCache.getNamespace());
            }
        }
    }

    @Test
    public void getTest() {
        int[] sizeMax = new int[]{10, 1000, 10000, 2147483647};
        Object[] defaultValue = new Object[]{0, 10, 100000, "String", 'c', -10, -1000000000, 1000000000000L, 1000000, -100000000000L, 10L, 10.0f, null, 10.10d, true, false,};

        String[] key = {"Key", "Second Key"};
        String[] keyValue = {"Key Value", "Second Key Value"};
        // COMMENT: Can you use the shorter version of for loop? for (int i: sizeMax)
        for (int i = 0; i < sizeMax.length; i++) {
            // COMMENT: Can you use the shorter version of for loop? for (int j: defaultValue)
            for (int j = 0; j < defaultValue.length; j++) {
                LocalCache<Object, Object> localCache = new LocalCache<>(sizeMax[i], defaultValue[j]);
                assertEquals(defaultValue[j], localCache.get("Key"));
                localCache.put(key[0], keyValue[0]);
                assertEquals(keyValue[0], localCache.get("Key"));
                localCache.put(key[1], keyValue[1]);
                assertEquals(keyValue[1], localCache.get("Second Key"));
                assertEquals(keyValue[0], localCache.get("Key"));
            }
        }
    }

    @Test
    public void getAllKeysTest() {
        int[] sizeMax = new int[]{10, 1000, 10000, 2147483647};
        Object[] defaultValue = new Object[]{0, 10, 100000, "String", 'c', -10, -1000000000, 1000000000000L, 1000000, -100000000000L, 10L, 10.0f, null, 10.10d, true, false,};
        String[] key = {"Key", "Second Key", "Third Key"};
        String[] keyValue = {"Key Value", "Second Key Value", "Third Key Value"};
        ArrayList<String> expectedKeyList = new ArrayList<>();
        expectedKeyList.add("Second Key");
        expectedKeyList.add("Third Key");
        expectedKeyList.add("Key");

        // COMMENT: Can you use the shorter version of for loop? for (int i: sizeMax)
        for (int i = 0; i < sizeMax.length; i++) {
            // COMMENT: Can you use the shorter version of for loop? for (int j: defaultValue)
            for (int j = 0; j < defaultValue.length; j++) {
                LocalCache<Object, Object> localCache = new LocalCache<>(sizeMax[i], defaultValue[j]);
                localCache.put(key[0], keyValue[0]);
                localCache.put(key[1], keyValue[1]);
                localCache.put(key[2], keyValue[2]);
                assertEquals(expectedKeyList, localCache.getAllKeys());
            }
        }
    }

    @Test
    public void putTest() {
        int[] sizeMax = new int[]{10, 1000, 10000, 2147483647};
        Object[] defaultValue = new Object[]{0, 10, 100000, "String", 'c', -10, -1000000000, 1000000000000L, 1000000, -100000000000L, 10L, 10.0f, null, 10.10d, true, false,};
        String[] key = {"Key", "Second Key", "Third Key"};
        String[] keyValue = {"Key Value", "Second Key Value", "Third Key Value"};

        // COMMENT: Can you use the shorter version of for loop? for (int i: sizeMax)
        for (int i = 0; i < sizeMax.length; i++) {
            // COMMENT: Can you use the shorter version of for loop? for (int j: defaultValue)
            for (int j = 0; j < defaultValue.length; j++) {
                LocalCache<Object, Object> localCache = new LocalCache<>(sizeMax[i], defaultValue[j]);
                for (int k = 0; k < key.length; k++) {
                    localCache.put(key[k], keyValue[k]);
                    // COMMENT: There is no assertion here, you are just calling the method, which is
                    // basically the same as the original thing does - please add an assertion.
                }
            }
        }
    }

    @Test
    public void removeTest() {
        int[] sizeMax = new int[]{10, 1000, 10000, 2147483647};
        Object[] defaultValue = new Object[]{0, 10, 100000, "String", 'c', -10, -1000000000, 1000000000000L, 1000000, -100000000000L, 10L, 10.0f, null, 10.10d, true, false,};
        String[] key = {"Key", "Second Key", "Third Key"};
        String[] keyValue = {"Key Value", "Second Key Value", "Third Key Value"};

        // COMMENT: Can you use the shorter version of for loop? for (int i: sizeMax)
        for (int i = 0; i < sizeMax.length; i++) {
            // COMMENT: Can you use the shorter version of for loop? for (int j: defaultValue)
            for (int j = 0; j < defaultValue.length; j++) {
                LocalCache<Object, Object> localCache = new LocalCache<>(sizeMax[i], defaultValue[j]);
                localCache.put(key[2], keyValue[2]);
                localCache.put(key[1], keyValue[1]);
                localCache.put(key[0], keyValue[0]);

                ArrayList<String> expectedKeyList = new ArrayList<>();
                expectedKeyList.add("Second Key");
                expectedKeyList.add("Third Key");
                expectedKeyList.add("Key");

                localCache.remove("Key");
                expectedKeyList.remove("Key");
                assertEquals(expectedKeyList, localCache.getAllKeys());
                localCache.remove("Second Key");
                expectedKeyList.remove("Second Key");
                assertEquals(expectedKeyList, localCache.getAllKeys());
                localCache.remove("Third Key");
                expectedKeyList.remove("Third Key");
                assertEquals(expectedKeyList, localCache.getAllKeys());
            }
        }
    }

    @Test
    public void invalidateAllTest() {
        int[] sizeMax = new int[]{10, 1000, 10000, 2147483647};
        Object[] defaultValue = new Object[]{0, 10, 100000, "String", 'c', -10, -1000000000, 1000000000000L, 1000000, -100000000000L, 10L, 10.0f, null, 10.10d, true, false,};

        // COMMENT: Can you use the shorter version of for loop? for (int i: sizeMax)
        for (int i = 0; i < sizeMax.length; i++) {
            // COMMENT: Can you use the shorter version of for loop? for (int j: defaultValue)
            for (int j = 0; j < defaultValue.length; j++) {
                LocalCache<Object, Object> localCache = new LocalCache<>(sizeMax[i], defaultValue[j]);
                localCache.invalidateAll();
                // COMMENT: Please check if the "cache" variable is empty after this operation.
            }
        }
    }
}