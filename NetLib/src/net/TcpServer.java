package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import threading.CloseableThread;

public abstract class TcpServer extends     CloseableThread 
                                implements  TcpClient.Listener {

    private final ServerSocket socket;
    protected final List<TcpClient> clients;
    
    public TcpServer(int port) throws IOException {
        socket = new ServerSocket(port);
        clients = new ArrayList<>();
    }

    @Override
    protected void doInBackground() throws Exception {
        TcpClient client = new TcpClient(socket.accept(), this);
        client.start();
        onConnect(client);
        
        
    }
    
    protected void onConnect(TcpClient client) {
        clients.add(client);
    }

    @Override
    public void onDisconnect(TcpClient client) {
        clients.remove(client);
    }

    @Override
    public void close() throws IOException {
        super.close();
        socket.close();
        for (TcpClient client: clients) client.close();
    }
    
}