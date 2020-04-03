/*******************************************************************************
 * Copyright (c) 2016, 2020 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.translator.kura.kapua;

import org.eclipse.kapua.KapuaEntityNotFoundException;
import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.message.device.data.KapuaDataChannel;
import org.eclipse.kapua.message.device.data.KapuaDataMessage;
import org.eclipse.kapua.message.device.data.KapuaDataMessageFactory;
import org.eclipse.kapua.message.device.data.KapuaDataPayload;
import org.eclipse.kapua.service.account.Account;
import org.eclipse.kapua.service.account.AccountService;
import org.eclipse.kapua.service.device.call.message.kura.data.KuraDataChannel;
import org.eclipse.kapua.service.device.call.message.kura.data.KuraDataMessage;
import org.eclipse.kapua.service.device.call.message.kura.data.KuraDataPayload;
import org.eclipse.kapua.service.device.registry.Device;
import org.eclipse.kapua.service.device.registry.DeviceRegistryService;
import org.eclipse.kapua.translator.Translator;
import org.eclipse.kapua.translator.exception.InvalidChannelException;
import org.eclipse.kapua.translator.exception.InvalidMessageException;
import org.eclipse.kapua.translator.exception.InvalidPayloadException;
import org.eclipse.kapua.translator.exception.TranslateException;

import java.util.HashMap;

/**
 * {@link Translator} implementation from {@link KuraDataMessage} to {@link KapuaDataMessage}
 *
 * @since 1.0
 */
public class TranslatorDataKuraKapua extends Translator<KuraDataMessage, KapuaDataMessage> {

    private static final KapuaLocator LOCATOR = KapuaLocator.getInstance();
    private static final KapuaDataMessageFactory KAPUA_DATA_MESSAGE_FACTORY = LOCATOR.getFactory(KapuaDataMessageFactory.class);

    @Override
    public KapuaDataMessage translate(KuraDataMessage kuraMessage) throws TranslateException {
        KapuaLocator locator = KapuaLocator.getInstance();
        AccountService accountService = locator.getService(AccountService.class);

        try {
            //
            // Kapua Channel
            KapuaDataChannel kapuaDataChannel = translate(kuraMessage.getChannel());

            //
            // Kapua payload
            KapuaDataPayload kapuaDataPayload = translate(kuraMessage.getPayload());

            //
            // Kapua message
            Account account = accountService.findByName(kuraMessage.getChannel().getScope());

            if (account == null) {
                throw new KapuaEntityNotFoundException(Account.TYPE, kuraMessage.getChannel().getScope());
            }

            DeviceRegistryService deviceRegistryService = locator.getService(DeviceRegistryService.class);
            Device device = deviceRegistryService.findByClientId(account.getId(), kuraMessage.getChannel().getClientId());

            KapuaDataMessage kapuaDataMessage = KAPUA_DATA_MESSAGE_FACTORY.newKapuaDataMessage();
            kapuaDataMessage.setScopeId(account.getId());
            kapuaDataMessage.setDeviceId(device != null ? device.getId() : null);
            kapuaDataMessage.setClientId(kuraMessage.getChannel().getClientId());
            kapuaDataMessage.setChannel(kapuaDataChannel);
            kapuaDataMessage.setPayload(kapuaDataPayload);
            kapuaDataMessage.setCapturedOn(kuraMessage.getPayload().getTimestamp());
            kapuaDataMessage.setSentOn(kuraMessage.getPayload().getTimestamp());
            kapuaDataMessage.setReceivedOn(kuraMessage.getTimestamp());
            kapuaDataMessage.setPosition(TranslatorKuraKapuaUtils.translate(kuraMessage.getPayload().getPosition()));

            // Return Kapua Message
            return kapuaDataMessage;
        } catch (InvalidChannelException | InvalidPayloadException te) {
            throw te;
        } catch (Exception e) {
            throw new InvalidMessageException(e, kuraMessage);
        }
    }

    private KapuaDataChannel translate(KuraDataChannel kuraChannel) {
        KapuaDataChannel kapuaChannel = KAPUA_DATA_MESSAGE_FACTORY.newKapuaDataChannel();
        kapuaChannel.setSemanticParts(kuraChannel.getSemanticParts());

        // Return Kapua Channel
        return kapuaChannel;
    }

    private KapuaDataPayload translate(KuraDataPayload kuraPayload) {
        KapuaDataPayload kapuaPayload = KAPUA_DATA_MESSAGE_FACTORY.newKapuaDataPayload();

        if (kuraPayload.getMetrics() != null) {
            kapuaPayload.setMetrics(new HashMap<>(kuraPayload.getMetrics()));
        }

        if (kuraPayload.hasBody()) {
            kapuaPayload.setBody(kuraPayload.getBody());
        }

        // Return Kapua payload
        return kapuaPayload;
    }

    @Override
    public Class<KuraDataMessage> getClassFrom() {
        return KuraDataMessage.class;
    }

    @Override
    public Class<KapuaDataMessage> getClassTo() {
        return KapuaDataMessage.class;
    }

}
