package ru.cronfire.tcpchat.client.resources;

/*
 * COPYRIGHT (c) 2016 Haarolean (Roman Zabaluev)
 * This file is part of tcp-chat
 * Package: ru.cronfire.tcpchat.client.resources
 * Date: 12.05.2016
 * Time: 17:55
 * DO NOT DISTRIBUTE.
 */

import java.io.InputStream;
import java.net.URL;

public class Resources {

    public static InputStream getResourceAsStream(final String name) {
        return Resources.class.getResourceAsStream(name);
    }

    public static URL getResource(final String name) {
        return Resources.class.getResource(name);
    }

}
