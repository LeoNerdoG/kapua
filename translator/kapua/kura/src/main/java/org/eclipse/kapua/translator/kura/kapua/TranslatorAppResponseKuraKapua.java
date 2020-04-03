/*******************************************************************************
 * Copyright (c) 2017, 2020 Eurotech and/or its affiliates and others
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

import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.service.device.call.message.kura.app.response.KuraResponseChannel;
import org.eclipse.kapua.service.device.call.message.kura.app.response.KuraResponsePayload;
import org.eclipse.kapua.service.device.management.request.GenericRequestFactory;
import org.eclipse.kapua.service.device.management.request.internal.GenericAppProperties;
import org.eclipse.kapua.service.device.management.request.message.response.GenericResponseChannel;
import org.eclipse.kapua.service.device.management.request.message.response.GenericResponseMessage;
import org.eclipse.kapua.service.device.management.request.message.response.GenericResponsePayload;
import org.eclipse.kapua.translator.exception.InvalidChannelException;
import org.eclipse.kapua.translator.exception.InvalidPayloadException;
import org.eclipse.kapua.translator.exception.TranslatorErrorCodes;
import org.eclipse.kapua.translator.exception.TranslatorException;

/**
 * {@link org.eclipse.kapua.translator.Translator} implementation from {@link org.eclipse.kapua.service.device.call.message.kura.app.response.KuraResponseMessage} to {@link GenericResponseMessage}
 *
 * @since 1.0.0
 */
public class TranslatorAppResponseKuraKapua extends AbstractSimpleTranslatorResponseKuraKapua<GenericResponseChannel, GenericResponsePayload, GenericResponseMessage> {

    private static final KapuaLocator LOCATOR = KapuaLocator.getInstance();

    public TranslatorAppResponseKuraKapua() {
        super(GenericResponseMessage.class);
    }

    @Override
    protected GenericResponseChannel translateChannel(KuraResponseChannel kuraChannel) throws InvalidChannelException {
        try {
            GenericRequestFactory genericRequestFactory = LOCATOR.getFactory(GenericRequestFactory.class);

            if (!getControlMessageClassifier().equals(kuraChannel.getMessageClassification())) {
                throw new TranslatorException(TranslatorErrorCodes.INVALID_CHANNEL_CLASSIFIER, null, kuraChannel.getMessageClassification());
            }

            GenericResponseChannel genericResponseChannel = genericRequestFactory.newResponseChannel();
            String[] appIdTokens = kuraChannel.getAppId().split("-");

            genericResponseChannel.setAppName(new GenericAppProperties(appIdTokens[0]));
            if (appIdTokens.length > 1) {
                genericResponseChannel.setVersion(new GenericAppProperties(appIdTokens[1]));
            }

            return genericResponseChannel;
        } catch (Exception e) {
            throw new InvalidChannelException(e, kuraChannel);
        }
    }

    @Override
    protected GenericResponsePayload translatePayload(KuraResponsePayload kuraPayload) throws InvalidPayloadException {
        try {
            GenericRequestFactory genericRequestFactory = LOCATOR.getFactory(GenericRequestFactory.class);

            GenericResponsePayload genericResponsePayload = genericRequestFactory.newResponsePayload();
            genericResponsePayload.setBody(kuraPayload.getBody());
            genericResponsePayload.setMetrics(kuraPayload.getMetrics());
            genericResponsePayload.setExceptionMessage(kuraPayload.getExceptionMessage());
            genericResponsePayload.setExceptionStack(kuraPayload.getExceptionStack());
            return genericResponsePayload;
        } catch (Exception e) {
            throw new InvalidPayloadException(e, kuraPayload);
        }
    }
}
