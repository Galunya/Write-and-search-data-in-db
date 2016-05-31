package net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Settings {
    private Settings() {}
    public static InetAddress GROUP;
    public static final int SERVER_PORT = 23456;
    public static final int CLIENT_PORT = 12345;
    static {
        try {
            GROUP = InetAddress.getByName("234.0.1.0");
        } catch (UnknownHostException e) {
            e.printStackTrace(System.err);
        }
    }
}