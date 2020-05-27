/*******************************************************************************
 * Copyright (c) 2020 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.commons.about;

import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@Category(JUnitTests.class)
public class AboutEntryTest extends Assert {

    @Test
    public void constructorTest() throws MalformedURLException {
        String validSpec = "https://www.google.com";
        URL[] url = {new URL(validSpec), null};
        String[] name = {"Name", null};
        String[] text = {"Text", null};
        ArrayList<AboutEntry.License> licenseList = new ArrayList<>();

        for (int i = 0; i < name.length; i++) {
            for (int j = 0; j < text.length; j++) {
                for (int k = 0; k < url.length; k++) {
                    AboutEntry.License license = new AboutEntry.License(name[i], text[j], url[k]);
                    licenseList.add(license);
                }
            }
        }

        for (int i = 0; i < licenseList.size(); i++) {
            assertNotNull(licenseList.get(i));
        }
    }

    @Test
    public void getNameTest() throws MalformedURLException {
        String validSpec = "https://www.google.com";
        URL[] url = {new URL(validSpec), null};
        String[] name = {"Name", null};
        String[] text = {"Text", null};
        ArrayList<AboutEntry.License> licenseList = new ArrayList<>();

        for (int i = 0; i < name.length; i++) {
            for (int j = 0; j < text.length; j++) {
                for (int k = 0; k < url.length; k++) {
                    AboutEntry.License license = new AboutEntry.License(name[i], text[j], url[k]);
                    licenseList.add(license);
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            assertEquals("Exception not expected", "Name", licenseList.get(i).getName());
        }
        for (int i = 4; i < 7; i++) {
            assertNull("Null expected", licenseList.get(i).getName());
        }
    }

    @Test
    public void getTextTest() throws MalformedURLException {
        String validSpec = "https://www.google.com";
        URL[] url = {new URL(validSpec), null};
        String[] name = {"Name", null};
        String[] text = {"Text", null};
        ArrayList<AboutEntry.License> licenseList = new ArrayList<>();

        for (int i = 0; i < text.length; i++) {
            for (int j = 0; j < name.length; j++) {
                for (int k = 0; k < url.length; k++) {
                    AboutEntry.License license = new AboutEntry.License(name[j], text[i], url[k]);
                    licenseList.add(license);
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            assertEquals("Exception not expected", "Text", licenseList.get(i).getText());
        }
        for (int i = 4; i < 7; i++) {
            assertNull("Null expected", licenseList.get(i).getText());
        }
    }

    @Test
    public void getUrlTest() throws MalformedURLException {
        String validSpec = "https://www.google.com";
        URL[] url = {new URL(validSpec), null};
        String[] name = {"Name", null};
        String[] text = {"Text", null};
        ArrayList<AboutEntry.License> licenseList = new ArrayList<>();

        for (int i = 0; i < url.length; i++) {
            for (int j = 0; j < name.length; j++) {
                for (int k = 0; k < text.length; k++) {
                    AboutEntry.License license = new AboutEntry.License(name[j], text[k], url[i]);
                    licenseList.add(license);
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            assertEquals("Exception not expected", url[0], licenseList.get(i).getUrl());
        }
        for (int i = 4; i < 7; i++) {
            assertNull("Null expected", licenseList.get(i).getUrl());
        }
    }

    @Test
    public void setIdTest() throws NoSuchFieldException, IllegalAccessException {
        String id = "valid_id";
        final AboutEntry aboutEntry = new AboutEntry();

        aboutEntry.setId(id);

        final Field field = aboutEntry.getClass().getDeclaredField("id");
        field.setAccessible(true);
        assertEquals("Fields didn't match", id, field.get(aboutEntry));
    }

    @Test
    public void getIdTest() throws NoSuchFieldException, IllegalAccessException {
        final AboutEntry aboutEntry = new AboutEntry();
        final Field field = aboutEntry.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(aboutEntry, "valid_id");

        final String result = aboutEntry.getId();
        assertEquals("Field wasn't retrieved properly", result, "valid_id");
    }

    @Test
    public void setNameTest() throws NoSuchFieldException, IllegalAccessException {
        String name = "valid_name";
        final AboutEntry aboutEntry = new AboutEntry();

        aboutEntry.setName(name);

        final Field field = aboutEntry.getClass().getDeclaredField("name");
        field.setAccessible(true);
        assertEquals("Fields didn't match", name, field.get(aboutEntry));
    }

    @Test
    public void aboutEntryGetNameTest() throws NoSuchFieldException, IllegalAccessException {
        final AboutEntry aboutEntry = new AboutEntry();
        final Field field = aboutEntry.getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(aboutEntry, "valid_name");

        final String result = aboutEntry.getName();
        assertEquals("Field wasn't retrieved properly", result, "valid_name");

    }

    @Test
    public void setVersionTest() throws NoSuchFieldException, IllegalAccessException {
        String version = "valid_version";
        final AboutEntry aboutEntry = new AboutEntry();

        aboutEntry.setVersion(version);

        final Field field = aboutEntry.getClass().getDeclaredField("version");
        field.setAccessible(true);
        assertEquals("Fields didn't match", version, field.get(aboutEntry));
    }

    @Test
    public void getVersionTest() throws NoSuchFieldException, IllegalAccessException {
        final AboutEntry aboutEntry = new AboutEntry();
        final Field field = aboutEntry.getClass().getDeclaredField("version");
        field.setAccessible(true);
        field.set(aboutEntry, "valid_version");

        final String result = aboutEntry.getVersion();
        assertEquals("Field wasn't retrieved properly", result, "valid_version");
    }

    @Test
    public void setLicenseTest() throws MalformedURLException, NoSuchFieldException, IllegalAccessException {
        String validSpec = "https://www.google.com";
        URL[] url = {new URL(validSpec), null};
        String[] name = {"Name", null};
        String[] text = {"Text", null};
        ArrayList<AboutEntry.License> licenseList = new ArrayList<>();

        for (int i = 0; i < name.length; i++) {
            for (int j = 0; j < text.length; j++) {
                for (int k = 0; k < url.length; k++) {
                    AboutEntry.License license = new AboutEntry.License(name[i], text[j], url[k]);
                    licenseList.add(license);
                }
            }
        }

        for (int i = 0; i < licenseList.size(); i++) {
            final AboutEntry aboutEntry = new AboutEntry();

            aboutEntry.setLicense(licenseList.get(i));

            final Field field = aboutEntry.getClass().getDeclaredField("license");
            field.setAccessible(true);
            assertEquals("Fields didn't match", licenseList.get(i), field.get(aboutEntry));
        }
    }

    @Test
    public void getLicenseTest() throws MalformedURLException, NoSuchFieldException, IllegalAccessException {
        URL url = new URL("https://www.google.com");
        String name = "Name";
        String text = "Text";
        AboutEntry.License license = new AboutEntry.License(name, text, url);

        final AboutEntry aboutEntry = new AboutEntry();
        final Field field = aboutEntry.getClass().getDeclaredField("license");
        field.setAccessible(true);
        field.set(aboutEntry, license);

        final AboutEntry.License result = aboutEntry.getLicense();
        assertEquals("Field wasn't retrieved properly", result, license);
    }

    @Test
    public void setNoticeTest() throws IllegalAccessException, NoSuchFieldException {
        String notice = "valid_notice";
        final AboutEntry aboutEntry = new AboutEntry();

        aboutEntry.setNotice(notice);

        final Field field = aboutEntry.getClass().getDeclaredField("notice");
        field.setAccessible(true);
        assertEquals("Fields didn't match", notice, field.get(aboutEntry));
    }

    @Test
    public void getNoticeTest() throws NoSuchFieldException, IllegalAccessException {
        final AboutEntry aboutEntry = new AboutEntry();
        final Field field = aboutEntry.getClass().getDeclaredField("notice");
        field.setAccessible(true);
        field.set(aboutEntry, "valid_notice");

        final String result = aboutEntry.getNotice();
        assertEquals("Field wasn't retrieved properly", result, "valid_notice");
    }
}