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
 *     Red Hat Inc
 *******************************************************************************/
package org.eclipse.kapua.translator.kura.kapua;

import org.eclipse.kapua.service.device.call.kura.app.CommandMetrics;
import org.eclipse.kapua.service.device.call.message.kura.app.response.KuraResponseChannel;
import org.eclipse.kapua.service.device.call.message.kura.app.response.KuraResponseMessage;
import org.eclipse.kapua.service.device.call.message.kura.app.response.KuraResponseMetrics;
import org.eclipse.kapua.service.device.call.message.kura.app.response.KuraResponsePayload;
import org.eclipse.kapua.service.device.management.command.internal.CommandAppProperties;
import org.eclipse.kapua.service.device.management.command.message.internal.CommandResponseChannel;
import org.eclipse.kapua.service.device.management.command.message.internal.CommandResponseMessage;
import org.eclipse.kapua.service.device.management.command.message.internal.CommandResponsePayload;
import org.eclipse.kapua.translator.exception.InvalidChannelException;
import org.eclipse.kapua.translator.exception.InvalidPayloadException;
import org.eclipse.kapua.translator.exception.TranslatorErrorCodes;
import org.eclipse.kapua.translator.exception.TranslatorException;

import java.util.Map;

/**
 * {@link org.eclipse.kapua.translator.Translator} implementation from {@link KuraResponseMessage} to {@link CommandResponseMessage}
 *
 * @since 1.0.0
 */
public class TranslatorAppCommandKuraKapua extends AbstractSimpleTranslatorResponseKuraKapua<CommandResponseChannel, CommandResponsePayload, CommandResponseMessage> {

    public TranslatorAppCommandKuraKapua() {
        super(CommandResponseMessage.class);
    }

    @Override
    protected CommandResponseChannel translateChannel(KuraResponseChannel kuraChannel) throws InvalidChannelException {
        try {
            if (!getControlMessageClassifier().equals(kuraChannel.getMessageClassification())) {
                throw new TranslatorException(TranslatorErrorCodes.INVALID_CHANNEL_CLASSIFIER, null, kuraChannel.getMessageClassification());
            }

            String[] appIdTokens = kuraChannel.getAppId().split("-");

            if (!CommandMetrics.APP_ID.getName().equals(appIdTokens[0])) {
                throw new TranslatorException(TranslatorErrorCodes.INVALID_CHANNEL_APP_NAME, null, appIdTokens[0]);
            }

            if (!CommandMetrics.APP_VERSION.getName().equals(appIdTokens[1])) {
                throw new TranslatorException(TranslatorErrorCodes.INVALID_CHANNEL_APP_VERSION, null, appIdTokens[1]);
            }

            CommandResponseChannel kapuaChannel = new CommandResponseChannel();
            kapuaChannel.setAppName(CommandAppProperties.APP_NAME);
            kapuaChannel.setVersion(CommandAppProperties.APP_VERSION);

            // Return Kapua Channel
            return kapuaChannel;
        } catch (Exception e) {
            throw new InvalidChannelException(e, kuraChannel);
        }
    }

    @Override
    protected CommandResponsePayload translatePayload(KuraResponsePayload kuraPayload) throws InvalidPayloadException {
        try {
            CommandResponsePayload commandResponsePayload = new CommandResponsePayload();

            Map<String, Object> metrics = kuraPayload.getMetrics();
            commandResponsePayload.setStderr((String) metrics.get(CommandMetrics.APP_METRIC_STDERR.getName()));
            commandResponsePayload.setStdout((String) metrics.get(CommandMetrics.APP_METRIC_STDOUT.getName()));
            commandResponsePayload.setExitCode((Integer) metrics.get(CommandMetrics.APP_METRIC_EXIT_CODE.getName()));

            Boolean timedout = (Boolean) metrics.get(CommandMetrics.APP_METRIC_TIMED_OUT.getName());
            if (timedout != null) {
                commandResponsePayload.setTimedout(timedout);
            }

            commandResponsePayload.setExceptionMessage((String) metrics.get(KuraResponseMetrics.EXCEPTION_MESSAGE.getName()));
            commandResponsePayload.setExceptionStack((String) metrics.get(KuraResponseMetrics.EXCEPTION_STACK.getName()));

            // Return Kapua Payload
            return commandResponsePayload;
        } catch (Exception e) {
            throw new InvalidPayloadException(e, kuraPayload);
        }
    }
}
