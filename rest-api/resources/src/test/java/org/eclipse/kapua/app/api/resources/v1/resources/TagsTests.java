/*******************************************************************************
 * Copyright (c) 2018, 2019 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.app.api.resources.v1.resources;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class TagsTests extends RestAssured {

    @Test
    public void tagsTests() {

        given().when().get("http://www.google.com").then().statusCode(200);

        Response response = (Response) given().
                auth().
                preemptive().
                basic("kapua-sys", "kapua-password").
                contentType(ContentType.JSON).
                get("http://localhost:8081/v1/authentication/user").
                then().
                assertThat().
                statusCode(200);
    }
}
