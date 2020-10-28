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

import org.eclipse.kapua.app.api.core.exception.model.EntityNotFoundExceptionInfo;
import org.eclipse.kapua.model.config.metatype.KapuaTocd;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.eclipse.kapua.service.account.Account;
import org.eclipse.kapua.service.authentication.credential.Credential;
import org.eclipse.kapua.service.authentication.token.AccessToken;
import org.eclipse.kapua.service.authorization.group.Group;
import org.eclipse.kapua.service.authorization.role.Role;
import org.eclipse.kapua.service.datastore.model.ChannelInfo;
import org.eclipse.kapua.service.device.call.kura.model.deploy.KuraDeploymentPackages;
import org.eclipse.kapua.service.device.management.message.request.KapuaRequestMessage;
import org.eclipse.kapua.service.device.management.message.response.KapuaResponseMessage;
import org.eclipse.kapua.service.device.management.registry.operation.DeviceManagementOperation;
import org.eclipse.kapua.service.device.registry.Device;
import org.eclipse.kapua.service.job.Job;
import org.eclipse.kapua.service.user.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class JaxbContextResolverTest extends Assert {

    JaxbContextResolver jaxbContextResolver;

    @Before
    public void initialize() {
        jaxbContextResolver = new JaxbContextResolver();
    }

    @Test
    public void getContextTest() {
        Class[] classes = {String.class, Account.class, KapuaTocd.class, Exception.class, EntityNotFoundExceptionInfo.class, ChannelInfo.class, Device.class, Credential.class, Job.class,
                KuraDeploymentPackages.class, KapuaRequestMessage.class, KapuaResponseMessage.class, DeviceManagementOperation.class, AccessToken.class, Role.class, Group.class, User.class};

        for (Class clazz : classes) {
            assertNotNull("NotNull expected.", jaxbContextResolver.getContext(clazz));
        }
    }

    @Test
    public void getContextNullTest() {
        assertNotNull("NotNull expected.", jaxbContextResolver.getContext(null));
    }
}