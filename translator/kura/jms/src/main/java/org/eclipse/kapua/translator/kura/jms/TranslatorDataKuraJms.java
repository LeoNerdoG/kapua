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
package org.eclipse.kapua.translator.kura.jms;

import org.eclipse.kapua.service.device.call.message.kura.data.KuraDataChannel;
import org.eclipse.kapua.service.device.call.message.kura.data.KuraDataMessage;
import org.eclipse.kapua.service.device.call.message.kura.data.KuraDataPayload;
import org.eclipse.kapua.translator.Translator;
import org.eclipse.kapua.translator.exception.InvalidChannelException;
import org.eclipse.kapua.translator.exception.InvalidMessageException;
import org.eclipse.kapua.translator.exception.InvalidPayloadException;
import org.eclipse.kapua.translator.exception.TranslateException;
import org.eclipse.kapua.transport.message.jms.JmsMessage;
import org.eclipse.kapua.transport.message.jms.JmsPayload;
import org.eclipse.kapua.transport.message.jms.JmsTopic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * {@link Translator} implementation from {@link KuraDataMessage} to {@link JmsMessage}
 *
 * @since 1.0.0
 */
public class TranslatorDataKuraJms extends Translator<KuraDataMessage, JmsMessage> {

    @Override
    public JmsMessage translate(KuraDataMessage kuraDataMessage) throws TranslateException {
        try {
            JmsTopic jmsRequestTopic = translate(kuraDataMessage.getChannel());
            JmsPayload jmsPayload = translate(kuraDataMessage.getPayload());
            return new JmsMessage(jmsRequestTopic, new Date(), jmsPayload);
        } catch (InvalidChannelException | InvalidPayloadException te) {
            throw te;
        } catch (Exception e) {
            throw new InvalidMessageException(e, kuraDataMessage);
        }
    }

    private JmsTopic translate(KuraDataChannel kuraDataChannel) throws InvalidChannelException {
        try {
            List<String> topicTokens = new ArrayList<>();
            topicTokens.add(kuraDataChannel.getScope());
            topicTokens.add(kuraDataChannel.getClientId());
            if (!kuraDataChannel.getSemanticParts().isEmpty()) {
                topicTokens.addAll(kuraDataChannel.getSemanticParts());
            }

            return new JmsTopic(topicTokens.toArray(new String[0]));
        } catch (Exception e) {
            throw new InvalidChannelException(e, kuraDataChannel);
        }
    }

    private JmsPayload translate(KuraDataPayload kuraDataPayload) throws InvalidPayloadException {
        try {
            return new JmsPayload(kuraDataPayload.toByteArray());
        } catch (Exception e) {
            throw new InvalidPayloadException(e, kuraDataPayload);
        }
    }

    @Override
    public Class<KuraDataMessage> getClassFrom() {
        return KuraDataMessage.class;
    }

    @Override
    public Class<JmsMessage> getClassTo() {
        return JmsMessage.class;
    }
}
