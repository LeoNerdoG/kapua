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
    public void constructor1Test() {
        int[] sizeMax = new int[]{0, 10, 1000, 10000, 2147483647};
        int[] expireAfter = new int[]{0, 10, 1000, 10000, 2147483647};
        Object[] defaultValue = new Object[]{0, 10, 100000, "String", 'c', -10, -1000000000, -100000000000L, 10L, 10.0f, null, 10.10d, true, false,};
        int invalidSizeMax = -1;
        int invalidExpireAfter = -1;
        IllegalArgumentException illegalArgumentExceptionSizeMax = new IllegalArgumentException("maximum size must not be negative");
        IllegalArgumentException illegalArgumentExceptionExpireAfter = new IllegalArgumentException("duration cannot be negative: " + invalidExpireAfter + " SECONDS");

        for (int i = 0; i < sizeMax.length; i++) {
            for (int j = 0; j < expireAfter.length; j++) {
                for (int k = 0; k < defaultValue.length; k++) {
                    LocalCache<Object, Object> localCache = new LocalCache<>(sizeMax[i], expireAfter[j], defaultValue[k]);
                    assertNotNull(localCache);
                }
            }
        }
        for (int i = 0; i < expireAfter.length; i++) {
            for (int j = 0; j < defaultValue.length; j++) {
                try {
                    LocalCache<Object, Object> localCache = new LocalCache<>(invalidSizeMax, expireAfter[i], defaultValue[j]);
                } catch (Exception e) {
                    assertEquals("Expected java.lang.IllegalArgumentException: maximum size must not be negative", illegalArgumentExceptionSizeMax.toString(), e.toString());
                }
            }
        }
        for (int i = 0; i < sizeMax.length; i++) {
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
    public void constructor2Test() {
        int[] sizeMax = new int[]{0, 10, 1000, 10000, 2147483647};
        Object[] defaultValue = new Object[]{0, 10, 100000, "String", 'c', -10, -1000000000, -100000000000L, 10L, 10.0f, null, 10.10d, true, false,};
        int invalidSizeMax = -1;
        IllegalArgumentException illegalArgumentExceptionSizeMax = new IllegalArgumentException("maximum size must not be negative");

        for (int i = 0; i < sizeMax.length; i++) {
            for (int j = 0; j < defaultValue.length; j++) {
                LocalCache<Object, Object> localCache = new LocalCache<>(sizeMax[i], defaultValue[j]);
                assertNotNull(localCache);
            }
        }
        for (int i = 0; i < defaultValue.length; i++) {
            try {
                LocalCache<Object, Object> localCache = new LocalCache<>(invalidSizeMax, defaultValue[i]);
            } catch (Exception e) {
                assertEquals("Expected java.lang.IllegalArgumentException: maximum size must not be negative", illegalArgumentExceptionSizeMax.toString(), e.toString());
            }
        }
    }

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

        for (int i = 0; i < sizeMax.length; i++) {
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

        for (int i = 0; i < sizeMax.length; i++) {
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

        for (int i = 0; i < sizeMax.length; i++) {
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

        for (int i = 0; i < sizeMax.length; i++) {
            for (int j = 0; j < defaultValue.length; j++) {
                LocalCache<Object, Object> localCache = new LocalCache<>(sizeMax[i], defaultValue[j]);
                for (int k = 0; k < key.length; k++) {
                    localCache.put(key[k], keyValue[k]);
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

        for (int i = 0; i < sizeMax.length; i++) {
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

        for (int i = 0; i < sizeMax.length; i++) {
            for (int j = 0; j < defaultValue.length; j++) {
                LocalCache<Object, Object> localCache = new LocalCache<>(sizeMax[i], defaultValue[j]);
                localCache.invalidateAll();
            }
        }
    }
}