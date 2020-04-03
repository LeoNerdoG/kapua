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
package org.eclipse.kapua.translator.kura.mqtt;

import org.eclipse.kapua.service.device.call.message.kura.data.KuraDataChannel;
import org.eclipse.kapua.service.device.call.message.kura.data.KuraDataMessage;
import org.eclipse.kapua.service.device.call.message.kura.data.KuraDataPayload;
import org.eclipse.kapua.translator.Translator;
import org.eclipse.kapua.translator.exception.InvalidChannelException;
import org.eclipse.kapua.translator.exception.InvalidMessageException;
import org.eclipse.kapua.translator.exception.InvalidPayloadException;
import org.eclipse.kapua.translator.exception.TranslateException;
import org.eclipse.kapua.transport.message.mqtt.MqttMessage;
import org.eclipse.kapua.transport.message.mqtt.MqttPayload;
import org.eclipse.kapua.transport.message.mqtt.MqttTopic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * {@link Translator} implementation from {@link KuraDataMessage} to {@link MqttMessage}
 *
 * @since 1.0.0
 */
public class TranslatorDataKuraMqtt extends Translator<KuraDataMessage, MqttMessage> {

    @Override
    public MqttMessage translate(KuraDataMessage kuraDataMessage) throws TranslateException {
        try {
            // Mqtt request topic
            MqttTopic mqttRequestTopic = translate(kuraDataMessage.getChannel());

            // Mqtt payload
            MqttPayload mqttPayload = translate(kuraDataMessage.getPayload());

            // Return Mqtt message
            return new MqttMessage(mqttRequestTopic, new Date(), mqttPayload);
        } catch (InvalidChannelException | InvalidPayloadException te) {
            throw te;
        } catch (Exception e) {
            throw new InvalidMessageException(e, kuraDataMessage);
        }
    }

    private MqttTopic translate(KuraDataChannel kuraDataChannel) throws InvalidChannelException {
        try {
            List<String> topicTokens = new ArrayList<>();

            topicTokens.add(kuraDataChannel.getScope());
            topicTokens.add(kuraDataChannel.getClientId());

            if (!kuraDataChannel.getSemanticParts().isEmpty()) {
                topicTokens.addAll(kuraDataChannel.getSemanticParts());
            }

            return new MqttTopic(topicTokens.toArray(new String[0]));
        } catch (Exception e) {
            throw new InvalidChannelException(e, kuraDataChannel);
        }
    }

    private MqttPayload translate(KuraDataPayload kuraDataPayload) throws InvalidPayloadException {
        try {
            return new MqttPayload(kuraDataPayload.toByteArray());
        } catch (Exception e) {
            throw new InvalidPayloadException(e, kuraDataPayload);
        }
    }

    @Override
    public Class<KuraDataMessage> getClassFrom() {
        return KuraDataMessage.class;
    }

    @Override
    public Class<MqttMessage> getClassTo() {
        return MqttMessage.class;
    }
}
