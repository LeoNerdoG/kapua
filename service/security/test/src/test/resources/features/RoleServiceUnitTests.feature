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

   ### CREATE ###
   Scenario: Creating a regular role
     Create a role entry, with specified name and description. Name is only specific attribute for role.
     Once created search for it and is should been created.
     Kapua should not return any error.

     Given I prepare a role creator with name "roleName" and description "roleDescription"
     When I create a new role entity from the existing creator
     Then I find a role with name "roleName"
     And No exception was thrown

   Scenario: Creating a role with null name
     Create a role entry, with null name and description e.g "roleDescription".
     Kapua should return an error.

     Given I prepare a role creator with name "" and description "roleDescription"
     But I expect the exception "KapuaIllegalNullArgumentException" with the text "An illegal null value was provided for the argument roleCreator.name."
     When I create a new role entity from the existing creator
     And An exception was thrown

   Scenario: Creating a role with name only
     Create a role with name "roleName" only.
     Kapua should not return any error.

     Given I prepare a role creator with name "roleName" and description ""
     When I create a new role entity from the existing creator
     Then I find a role with name "roleName"
     And No exception was thrown

   Scenario:  Creating a role with too short name
     Create a role with too short name e.g "ro" and description "roleDescription"
     Kapua should return an error.

     Given I prepare a role creator with name "ro" and description "roleDescription"
     But I expect the exception "KapuaIllegalArgumentException" with the text "Value less than allowed min length. Min length is 3."
     When I create a new role entity from the existing creator
     And An exception was thrown

   Scenario: Creating a role with regular name and too short description
     Create a role with name e.g "roleName" and description e.g "de".
     Kapua should not return any error.
     
     Given I prepare a role creator with name "roleName" and description "de"
     When I create a new role entity from the existing creator
     Then I find a role with name "roleName"
     And No exception was thrown
     
   Scenario: Creating a role name with 255 characters
     Create a role name that contains 255 character and with a description e.g "roleDescription".
     Kapua should not return any error.
     
     Given I prepare a role creator with name "roleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNam" and description "roleDescription"
     When I create a new role entity from the existing creator
     Then I find a role with name "roleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNam"
     And No exception was thrown

   Scenario: Creating a role with name over 255 characters
     Create a role name that contains over 255 character and description e.g "roleDescription".
     Kapua should return an error.

     Given I prepare a role creator with name "roleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleName" and description "roleDescription"
     But I expect the exception "KapuaIllegalArgumentException" with the text "Value over than allowed max length. Max length is 255."
     Then I create a new role entity from the existing creator
     And An exception was thrown

   Scenario: Creating a role with a description that contains 255 character
     Creating a role with regular name e.g "roleName" and with 255 character long description.
     Kapua should not return any error.
     
     Given I prepare a role creator with name "roleName" and description "roleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescription"
     When I create a new role entity from the existing creator
     Then I find a role with name "roleName"
     And No exception was thrown

   Scenario: Creating a role with regular name and with description over 255 characters
     Create a role with regular name e.g "roleName" and with description over 255 characters.
     Kapua should return an error.

     Given I prepare a role creator with name "roleName" and description "roleDescrsdsdsdsdsdsiptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescriptionroleDescription"
     When I create a new role entity from the existing creator

   Scenario: Creating a role with a name that contains digits
     Create a role with name e.g "role123" and with description e.g "roleDescription".
     Kapua should not return any error.

     Given I prepare a role creator with name "role123" and description "roleDescription"
     When I create a new role entity from the existing creator
     Then I find a role with name "role123"
     And No exception was thrown

   Scenario: Creating a role with special characters in the name
     Create a role with name e.g "roleName%$#" and with description e.g "roleDescription".
     Kapua should return an error.

     Given I prepare a role creator with name "roleName%$#" and description "roleDescription"
     But I expect the exception "KapuaIllegalArgumentException" with the text "An illegal value was provided for the argument roleCreator.name"
     When I create a new role entity from the existing creator
     And An exception was thrown

   Scenario: Creating a role with special character in the description
     Create a role with name e.g "roleName" and with description e.g "descripti@n!@#".
     Kapua should not return any error.

     Given I prepare a role creator with name "roleName" and description "descripti@n!@#"
     When I create a new role entity from the existing creator
     Then I find a role with name "roleName"
     And No exception was thrown

   Scenario: Creating a role with a name and description that contains digits only
     Create a role with name e.g "123" and with description e.g "123".
     Kapua should not return any error.

     Given I prepare a role creator with name "123" and description "123"
     When I create a new role entity from the existing creator
     Then I find a role with name "123"
     And No exception was thrown

   Scenario: Creating two roles with the same name and description
     Create first role with name e.g "roleName" and with description "roleDescription".
     Create second role with same name "roleName" and with description "roleDescription".
     Kapua should return an error.

     Given I prepare a role creator with name "roleName" and description "roleDescription"
     When I create a new role entity from the existing creator
     Then I prepare a role creator with name "roleName" and description "roleDescription"
     But I expect the exception "KapuaDuplicateNameException" with the text "An entity with the same name roleName already exists."
     Then I create a new role entity from the existing creator
     And An exception was thrown

   Scenario: Creating a role name with an allowed special symbol in the name
     Create a role with a name that contains allowed symbol e.g "role_Name-123".
     Kapua should not return any error.
     
     Given I prepare a role creator with name "role_Name-123" and description "roleDescription"
     When I create a new role entity from the existing creator
     Then I find a role with name "role_Name-123"

   Scenario: Counting created roles items in the DB
     Create 15 roles and find all of them in the DB.
     Kapua should not return any error.

     Given I create 15 roles
     When I count the roles in the database
     Then I count 15
     And No exception was thrown

   ### EDIT-UPDATE ###

   Scenario: Editing role name to regular one
     Create a role with name e.g "roleName" and try to edit name to e.g "roleName2".
     Kapua should not return any error.
     
     Given I prepare a role creator with name "roleName" and description "roleDescription"
     When I create a new role entity from the existing creator
     And I update the role name to "roleName2"
     Then I find a role with name "roleName2"
     And No exception was thrown

   Scenario: Editing role description to regular one
     Create a role with name e.g "roleName" and try to edit role description to e.g "roleDescription123".
     Kapua should not return any error.

     Given I prepare a role creator with name "roleName" and description "roleDescription"
     When I create a new role entity from the existing creator
     And I update the role description to "roleDescription123"
     Then I find a role with name "roleName"
     And No exception was thrown

   Scenario: Editing role that doesn't exist
     Create a role with name e.g "roleName" and with description e.g "roleDescription", delete it and try to
     update non-existing role name.
     Kapua should return an error.
     
     Given I prepare a role creator with name "roleName" and description "roleDescription"
     When I create a new role entity from the existing creator
     Then I delete the role with name "roleName"
     But I expect the exception "KapuaEntityNotFoundException" with the text "*"
     And I update the last created role name to "roleName2"
     Then An exception was thrown
     
   Scenario: Editing role name to null
     Create a role with name e.g "roleName" and with description e.g "roleDescription",
     then try to update role name to null.
     Kapua should return an error.

     Given I prepare a role creator with name "roleName" and description "roleDescription"
     When I create a new role entity from the existing creator
     But I expect the exception "KapuaIllegalNullArgumentException" with the text "An illegal null value was provided for the argument role.name."
     Then I update the role name to ""
     And An exception was thrown

   Scenario: Editing role name with special character
     Create a role with name e.g "roleName" and with description e.g "roleDescription",
     then try to update role name with special characters e.g "roleName$%^".
     Kapua should return an error.

     Given I prepare a role creator with name "roleName" and description "roleDescription"
     When I create a new role entity from the existing creator
     But I expect the exception "KapuaIllegalArgumentException" with the text "An illegal value was provided for the argument role.name: roleName!@#."
     Then I update the role name to "roleName!@#"
     And An exception was thrown
     
   Scenario: Editing role with too short name
     Create a role with name e.g "roleName" and with description e.g "roleDescription",
     then try to update role name e.g "ro".
     Kapua should return an error.

     Given I prepare a role creator with name "roleName" and description "roleDescription"
     When I create a new role entity from the existing creator
     But I expect the exception "KapuaIllegalArgumentException" with the text "Value less than allowed min length. Min length is 3."
     Then I update the role name to "ro"
     And An exception was thrown

   Scenario: Editing role with too long name
     Create a role with name e.g "roleName" and with description e.g "roleDescription",
     then try to update role name over 255 characters.
     Kapua should return an error.

     Given I prepare a role creator with name "roleName" and description "roleDescription"
     When I create a new role entity from the existing creator
     But I expect the exception "KapuaIllegalArgumentException" with the text "Value over than allowed max length. Max length is 255."
     Then I update the role name to "roleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleNameroleName"
     And An exception was thrown

   ### DELETE ###

   Scenario: Deleting existing role
     Create a role with name e.g "roleName" and with description e.g "roleDescription",
     then try to delete role with name "roleName"
     Kapua should not return any error.

     Given I prepare a role creator with name "roleName" and description "roleDescription"
     When I create a new role entity from the existing creator
     Then I delete the role with name "roleName"
     And I search for the role with name "roleName"
     But I find no roles

   Scenario: Deleting role twice
     Create a role with name e.g "roleName" and with description e.g "roleDescription",
     then try to delete role with name "roleName" twice
     Kapua should return an error.

     Given I prepare a role creator with name "roleName" and description "roleDescription"
     When I create a new role entity from the existing creator
     Then I delete the role with name "roleName"
     But I expect the exception "KapuaEntityNotFoundException" with the text "*"
     Then I delete the role with name "roleName"
     And An exception was thrown