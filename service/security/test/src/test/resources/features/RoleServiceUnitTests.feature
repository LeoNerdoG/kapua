###############################################################################
# Copyright (c) 2017, 2018 Eurotech and/or its affiliates and others
#
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#     Eurotech
###############################################################################
@role
@unit
Feature: Role Service
  Role Service is responsible for CRUD operations on Roles. This service is currently
  used to attach roles to Users.

  Scenario: Creating regular role
    Create a tag entry, with specified name and description. Name is only role specific attribute.
    Once created search for it and is should been created.

    Given I prepare a role creator with name "roleName" and description "roleDescription"
    When I create a new role entity from the existing creator
#    Then I find a role with name "roleName"
#    And No exception was thrown
