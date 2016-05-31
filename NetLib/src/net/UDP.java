package net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import net.messaging.Message;
import threading.CloseableThread;

public final class UDP {
    private UDP() {}
    
    public static class Receiver extends CloseableThread {
        
        private final DatagramSocket socket;
        
        private final Message.Listener listener;

        private final byte[] data;
        
        private final DatagramPacket packet;
            
        public Receiver(int port, Message.Listener listener) 
                        throws SocketException, UnknownHostException, IOException {
            this.listener = listener;
            socket = new DatagramSocket(port);
            //socket.joinGroup(Settings.GROUP);
            data = new byte[1024];
            packet = new DatagramPacket(data, data.length);
        }

        @Override
        protected void doInBackground() throws Exception {
            socket.receive(packet);
            Message message = Message.deserialize(data, packet.getLength());
            listener.onMessage(packet.getSocketAddress(), message);
        }

        @Override
        public void close() throws IOException {
            super.close();
            //socket.leaveGroup(Settings.GROUP);
            socket.close();
        }
        
    }
    
    private static DatagramPacket getPacket(SocketAddress address, Message message) throws IOException {
        byte[] data = message.serialize();
        return new DatagramPacket(data, data.length, address);
    }
    
    public static void send(SocketAddress address, Message message) throws IOException {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.send(getPacket(address, message));
        }
    }
    
    public static void send(SocketAddress address, int port, Message message) throws IOException {
        try (DatagramSocket socket = new DatagramSocket()) {
            DatagramPacket packet = getPacket(address, message);
            packet.setPort(port);
            socket.send(packet);
        }
    }
    
    public static void send(String host, int port, Message message) throws IOException {
        SocketAddress address = new InetSocketAddress(host, port);
        send(address, message);
    }
    
    public static void broadcast(int port, Message message) throws IOException {
        SocketAddress address = new InetSocketAddress("255.255.255.255", port);
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true);
            socket.send(getPacket(address, message));
        }
    }
    
}