###############################################################################
# Copyright (c) 2020 Eurotech and/or its affiliates and others
#
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#     Eurotech
###############################################################################
@unit
@translator

Feature: Translator Service
  The Translator Service is responsible for messaging operations between Kura and Kapua.

  #KapuaTranslatorApi

  Scenario: Finding translator "TranslatorAppCommandKapuaKura"
  Trying to make translation from CommandRequestMessage to KuraRequestMessage.

    When I try to translate from "org.eclipse.kapua.service.device.management.command.message.internal.CommandRequestMessage" to "org.eclipse.kapua.service.device.call.message.kura.app.request.KuraRequestMessage"
    Then Translator "TranslatorAppCommandKapuaKura" is found
    And No exception was thrown

  Scenario: Finding null translator
  Trying to do translation without messages. NPE exception should be thrown.

    Given I expect the exception "NullPointerException" with the text "*"
    And I try to translate from "" to ""
    Then An exception was thrown

  Scenario: Finding wrong translator
  Trying to make translation from AssetRequestMessage to AssetResponseMessage.
  Translator doesn't exist.

    Given I expect the exception "TranslatorNotFoundException" with the text "*"
    And I try to translate from "org.eclipse.kapua.service.device.management.asset.message.internal.AssetRequestMessage" to "org.eclipse.kapua.service.device.management.asset.message.internal.AssetResponseMessage"
    Then An exception was thrown

  #TranslatorDataMqttKura //new test needed
#  - Payload:
#  - Providing an invalid `MqttPayload` (ie `null`) throws `InvalidPayloadException` with message :`Invalid payload: {} `
#  - Providing a `MqttPayload` with non-protobuf `body` will result in a `KuraDataPayload` with the original content in the `KuraPayload.body` and empty `KuraPayload.metrics`.
#  - Providing a `MqttPayload` with protobuf `body` will result in a `KuraDataPayload` with matching `KuraDataPayload.body` and `KuraDataPayload.metrics`.
#
#  - Channel
#  - Providing an invalid `MqttTopic` (ie `null`, a topic like `accountName` ) throws `InvalidChannelException` with message: `Invalid channel: {}`
#  - Providing a MqttTopic with 3 tokens will produce a `KuraDataChannel` with:
#  1. token as `KuraDataPayload.scope`
#  2. token as `KuraDataPayload.client`
#  3. token as `KuraDataPayload.semanticParts`
#
#  - Message:
#  - Providing an invalid `MqttMessage` (ie `null`) throws `InvalidMessageException` with message: `Invalid message: {}`
#  - Providing a valid `MqttMesage` will return a `KuraDataMessage` with matching data.

  Scenario: Translating of mqtt message with invalid payload and valid topic into kura data message
  Creating mqtt message with invalid payload and valid topic. Trying to translate it into kura data message.
  Checking if kura data message with valid channel and byte[] payload is received.

    Given I create mqtt message with valid payload "invalidPayload" and valid topic "kapua-sys/rpione3/DEPLOY-V2/GET/packages"
    And I try to translate mqtt message to kura data message
    Then I got kura data message with "byte[]" payload body
    And I got kura data message channel with "kapua-sys" and "rpione3" data
    And No exception was thrown

  Scenario: Translating of mqtt message with valid payload and valid topic into kura data message
  Creating mqtt message with valid payload and valid topic. Trying to translate it into kura data message.
  Checking if mqtt message with valid channel and proper payload metrics is received.

    Given I create mqtt message with valid payload "response.code" and valid topic "kapua-sys/rpione3/DEPLOY-V2/GET/packages"
    And I try to translate mqtt message to kura data message
    Then I got kura data message with proper payload metrics
    And I got kura data message channel with "kapua-sys" and "rpione3" data
    And No exception was thrown

  Scenario: Translating of mqtt message with empty payload into kura data message
  Creating mqtt message with empty payload and valid topic. Trying to translate it into kura data message.
  Checking if mqtt message with valid channel and empty payload is received.

    Given I create mqtt message with empty payload "" and valid topic "kapua-sys/rpione3/DEPLOY-V2/GET/packages"
    And I try to translate mqtt message to kura data message
    Then I got kura data message with empty payload
    And I got kura data message channel with "kapua-sys" and "rpione3" data
    And No exception was thrown

  #TranslatorResponseMqttKura //add more tests for this translator

  Scenario: Translating of mqtt message with invalid payload and invalid topic into kura response
  Creating mqtt message with invalid payload and invalid topic. Trying to translate it into kura response.

    Given I expect the exception "InvalidChannelException" with the text "Invalid channel: DEPLOY-V2/GET/packages"
    And I create mqtt message with invalid payload "invalidPayload" and invalid topic "DEPLOY-V2/GET/packages"
    When I try to translate mqtt response
    Then An exception was thrown

  Scenario: Translating of mqtt message with invalid payload and valid topic into kura response
  Creating mqtt message with invalid payload and valid topic. Trying to translate it into kura response.
  Check if kura response with byte[] body and correct channel is received.

    Given I create mqtt message with invalid payload "invalidPayload" and valid topic "$EDC/kapua-sys/rpione3/DEPLOY-V2/GET/packages"
    When I try to translate mqtt response
    Then I got kura response message with "byte[]" payload body
    And I got kura response message channel with "GET", "packages", "DEPLOY-V2", "$EDC", "kapua-sys" and "rpione3" data
    And No exception was thrown

  Scenario: Translating of mqtt message with valid payload and invalid topic into kura response
  Creating mqtt message with valid payload and invalid topic. Trying to translate it into kura response.

    Given I expect the exception "InvalidChannelException" with the text "Invalid channel: DEPLOY-V2/GET/packages"
    And I create mqtt message with valid payload "response.code" and invalid topic "DEPLOY-V2/GET/packages"
    When I try to translate mqtt response
    Then An exception was thrown

  Scenario: Translating of mqtt message with valid payload and valid topic into kura response
  Creating mqtt message with valid payload and valid topic. Trying to translate it into kura response.
  Check if kura response with proper payload, metrics and correct channel is received.

    Given I create mqtt message with valid payload "response.code" and valid topic "$EDC/kapua-sys/rpione3/DEPLOY-V2/GET/packages"
    When I try to translate mqtt response
    Then I got kura response message with proper payload metrics
    And I got kura response message channel with "GET", "packages", "DEPLOY-V2", "$EDC", "kapua-sys" and "rpione3" data
    And No exception was thrown

  #TranslatorDataKuraMqtt //new test needed with null
  #- Providing an invalid `KuraPayload` (ie `null`) throws `InvalidPayloadException` with message: `Invalid payload: {}`
  #- Providing an invalid `KuraChannel` (ie `null`) throws `InvalidChannelException` with message: `Invalid channel: {}`
  #- Providing an invalid `KuraMessage` (ie `null`) throws `InvalidMessageException` with message: `Invalid message: {}`

  Scenario: Translating kura data message with valid channel and without body and metrics into mqtt message
  Creating kura data message with valid channel and without body and metrics. Trying to translate it into mqtt message.
  Check if mqtt message with valid topic and empty body is received.

    Given I create kura data message with channel with scope "kapua-sys", client id "rpione3" and payload without body and metrics
    When I try to translate kura data message to mqtt message
    Then I got mqtt message with channel with scope "kapua-sys", client id "rpione3" and empty body
    And No exception was thrown

  Scenario: Translating kura data message with valid channel, metrics and without body into mqtt message
  Creating kura data message with valid channel, metrics and without body. Trying to translate it into mqtt message.
  Check if mqtt message with valid topic and encoded body is received.

    Given I create kura data message with channel with scope "kapua-sys", client id "rpione3" and payload without body and with metrics
    When I try to translate kura data message to mqtt message
    Then I got mqtt message with channel with scope "kapua-sys", client id "rpione3" and non empty body
    And No exception was thrown

  Scenario: Translating kura data message with valid channel, body and metrics into mqtt message
  Creating kura data message with valid channel, body and metrics. Trying to translate it into mqtt message.
  Check if mqtt message with valid topic and encoded body is received.

    Given I create kura data message with channel with scope "kapua-sys", client id "rpione3" and payload with body and metrics
    And I try to translate kura data message to mqtt message
    Then I got mqtt message with channel with scope "kapua-sys", client id "rpione3" and non empty body
    And No exception was thrown

