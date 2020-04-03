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
package org.eclipse.kapua.translator.kapua.kura;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.kapua.service.device.call.kura.app.AssetMetrics;
import org.eclipse.kapua.service.device.call.kura.model.asset.KuraAsset;
import org.eclipse.kapua.service.device.call.kura.model.asset.KuraAssetChannel;
import org.eclipse.kapua.service.device.call.kura.model.asset.KuraAssets;
import org.eclipse.kapua.service.device.call.message.kura.app.request.KuraRequestChannel;
import org.eclipse.kapua.service.device.call.message.kura.app.request.KuraRequestMessage;
import org.eclipse.kapua.service.device.call.message.kura.app.request.KuraRequestPayload;
import org.eclipse.kapua.service.device.management.asset.DeviceAssets;
import org.eclipse.kapua.service.device.management.asset.message.internal.AssetRequestChannel;
import org.eclipse.kapua.service.device.management.asset.message.internal.AssetRequestMessage;
import org.eclipse.kapua.service.device.management.asset.message.internal.AssetRequestPayload;
import org.eclipse.kapua.translator.exception.InvalidChannelException;
import org.eclipse.kapua.translator.exception.InvalidPayloadException;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * {@link org.eclipse.kapua.translator.Translator} implementation from {@link AssetRequestMessage} to {@link KuraRequestMessage}
 *
 * @since 1.0.0
 */
public class TranslatorAppAssetKapuaKura extends AbstractTranslatorKapuaKura<AssetRequestChannel, AssetRequestPayload, AssetRequestMessage> {

    @Override
    protected KuraRequestChannel translateChannel(AssetRequestChannel kapuaChannel) throws InvalidChannelException {
        try {
            KuraRequestChannel kuraRequestChannel = new KuraRequestChannel();
            kuraRequestChannel.setMessageClassification(getControlMessageClassifier());
            kuraRequestChannel.setAppId(AssetMetrics.APP_ID + "-" + AssetMetrics.APP_VERSION);
            kuraRequestChannel.setMethod(MethodDictionaryKapuaKura.translate(kapuaChannel.getMethod()));

            // Build resources
            String[] resources = new String[1];
            if (kapuaChannel.isRead()) {
                resources[0] = "read";
            } else if (kapuaChannel.isWrite()) {
                resources[0] = "write";
            } else {
                resources[0] = "assets";
            }
            kuraRequestChannel.setResources(resources);

            // Return Kura Channel
            return kuraRequestChannel;
        } catch (Exception e) {
            throw new InvalidChannelException(e, kapuaChannel);
        }
    }

    @Override
    protected KuraRequestPayload translatePayload(AssetRequestPayload kapuaPayload) throws InvalidPayloadException {
        try {
            DeviceAssets deviceAssets;
            try {
                deviceAssets = kapuaPayload.getDeviceAssets();
            } catch (UnsupportedEncodingException | JAXBException | XMLStreamException | FactoryConfigurationError | SAXException e) {
                throw new InvalidPayloadException(e, kapuaPayload);
            }

            if (deviceAssets.getAssets().isEmpty()) {
                return new KuraRequestPayload();
            }

            KuraAssets kuraAssets = new KuraAssets();
            deviceAssets.getAssets().forEach(deviceAsset -> {
                KuraAsset kuraAsset = new KuraAsset();
                kuraAsset.setName(deviceAsset.getName());
                deviceAsset.getChannels().forEach(deviceAssetChannel -> {
                    KuraAssetChannel kuraAssetChannel = new KuraAssetChannel();
                    kuraAssetChannel.setName(deviceAssetChannel.getName());
                    kuraAssetChannel.setType(deviceAssetChannel.getType());
                    kuraAssetChannel.setValue(deviceAssetChannel.getValue());

                    Date timestamp = deviceAssetChannel.getTimestamp();
                    kuraAssetChannel.setTimestamp(timestamp != null ? timestamp.getTime() : null);

                    kuraAsset.getChannels().add(kuraAssetChannel);
                });
                kuraAssets.getAssets().add(kuraAsset);
            });

            KuraRequestPayload kuraRequestPayload = new KuraRequestPayload();
            try (StringWriter sw = new StringWriter()) {
                ObjectMapper mapper = new ObjectMapper();
                JsonGenerator jsonGenerator = mapper.getFactory().createGenerator(sw);

                try {
                    kuraAssets.writeJsonNode(jsonGenerator);
                } finally {
                    jsonGenerator.close();
                }

                kuraRequestPayload.setBody(sw.toString().getBytes());
            } catch (IOException e) {
                throw new InvalidPayloadException(e, kapuaPayload);
            }

            return kuraRequestPayload;
        } catch (InvalidPayloadException ipe) {
            throw ipe;
        } catch (Exception e) {
            throw new InvalidPayloadException(e, kapuaPayload);
        }
    }

    @Override
    public Class<AssetRequestMessage> getClassFrom() {
        return AssetRequestMessage.class;
    }

    @Override
    public Class<KuraRequestMessage> getClassTo() {
        return KuraRequestMessage.class;
    }
}
