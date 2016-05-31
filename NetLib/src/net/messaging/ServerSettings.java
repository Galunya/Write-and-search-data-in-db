package net.messaging;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class ServerSettings extends Message {
    private static final long serialVersionUID = ServerSettings.class.getName().hashCode();
    
    private final int port;

    private final String name;
    
    private final InetAddress address;
    
    public ServerSettings(String name, int port) throws UnknownHostException {
        this.port = port;
        this.name = name;
        address = InetAddress.getLocalHost();
    }

    public InetAddress getAddress() {
        return address;
    }
    
    public SocketAddress getEndPoint() {
        return new InetSocketAddress(address, port);
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return address.getHostAddress().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return hashCode() == o.hashCode();
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}