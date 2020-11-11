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
package org.eclipse.kapua.integration.misc;

import org.eclipse.kapua.model.config.metatype.KapuaTad;
import org.eclipse.kapua.model.config.metatype.KapuaTicon;
import org.eclipse.kapua.model.config.metatype.KapuaTocd;
import org.eclipse.kapua.model.config.metatype.KapuaToption;
import org.eclipse.kapua.model.config.metatype.KapuaTmetadata;
import org.eclipse.kapua.model.config.metatype.KapuaTdesignate;
import org.eclipse.kapua.model.config.metatype.KapuaTobject;

import org.eclipse.kapua.model.config.metatype.MetatypeXmlRegistry;
import org.eclipse.kapua.qa.markers.Categories;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;


@Category(value = Categories.junitTests.class)
public class MetatypeXmlRegistryTest extends Assert {

    MetatypeXmlRegistry metatypeXmlRegistry;

    @Before
    public void createInstanceOfClass() {
        metatypeXmlRegistry = new MetatypeXmlRegistry();
    }

    @Test
    public void newKapuaTocdTest() {
        assertThat("Instance of KapuaTocd expected.", metatypeXmlRegistry.newKapuaTocd(), IsInstanceOf.instanceOf(KapuaTocd.class));
    }

    @Test
    public void newKapuaTadTest() {
        assertThat("Instance of KapuaTad expected.", metatypeXmlRegistry.newKapuaTad(), IsInstanceOf.instanceOf(KapuaTad.class));
    }

    @Test
    public void newKapuaTiconTest() {
        assertThat("Instance of KapuaTicon expected.", metatypeXmlRegistry.newKapuaTicon(), IsInstanceOf.instanceOf(KapuaTicon.class));
    }

    @Test
    public void newKapuaToptionTest() {
        assertThat("Instance of KapuaToption expected.", metatypeXmlRegistry.newKapuaToption(), IsInstanceOf.instanceOf(KapuaToption.class));
    }

    @Test
    public void newKapuaTmetadataTest() {
        assertThat("Instance of KapuaTmetadata expected.", metatypeXmlRegistry.newKapuaTmetadata(), IsInstanceOf.instanceOf(KapuaTmetadata.class));
    }

    @Test
    public void newKapuaTdesignateTest() {
        assertThat("Instance of KapuaTdesignate expected.", metatypeXmlRegistry.newKapuaTdesignate(), IsInstanceOf.instanceOf(KapuaTdesignate.class));
    }

    @Test
    public void newKapuaTobjectTest() {
        assertThat("Instance of KapuaTobject expected.", metatypeXmlRegistry.newKapuaTobject(), IsInstanceOf.instanceOf(KapuaTobject.class));
    }
}