package net;

import java.io.IOException;
import java.net.Socket;
import net.messaging.Message;
import shop.DictionaryList;
import shop.RoleList;
import threading.CloseableThread;

public class TcpClient extends CloseableThread {

    public interface Listener extends Message.Listener {
        void onDisconnect(TcpClient client);
    }
    
    private final Socket socket;
    private final Listener listener;

    public TcpClient(Socket socket, Listener listener) {
        this.socket = socket;
        this.listener = listener;
    }
    
    @Override
    protected void doInBackground() throws Exception {
   //     DictionaryList list= new DictionaryList("а");
//        System.out.println("AAAAAAAA");
//        System.out.println(list);
        Message message = Message.deserialize(socket.getInputStream());
        
        listener.onMessage(socket.getRemoteSocketAddress(), message);
    }

    @Override
    protected void onInterrupt() {
        listener.onDisconnect(this);
    }
    
    public final void send(Message message) throws IOException {
        message.serialize(socket.getOutputStream());
        
    }

    @Override
    public void close() throws IOException {
        super.close();
        socket.close();
    }
    
}