package net.messaging;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.SocketAddress;

public abstract class Message implements Serializable{
    private static final long serialVersionUID = Message.class.getName().hashCode();
    
    public interface Listener {
        void onMessage(SocketAddress address, Message message);
    }
    
    public void serialize(OutputStream stream) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(stream);
        out.writeObject(this);
    }
    
    public byte[] serialize() throws IOException {
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            serialize(buffer);
            return buffer.toByteArray();
        }
    }
    
    public static Message deserialize(InputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(stream);
        return (Message) in.readObject();
    }
    
    public static Message deserialize(byte[] data, int len) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream in = new ByteArrayInputStream(data, 0, len)) {
            return deserialize(in);
        }
    }
    
    public static Message deserialize(byte[] data) throws IOException, ClassNotFoundException {
        return deserialize(data, data.length);
    }
}