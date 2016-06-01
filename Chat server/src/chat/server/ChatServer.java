package chat.server;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.TcpClient;
import net.TcpServer;
import net.messaging.Message;

public class ChatServer extends TcpServer {

    public ChatServer(int port) throws IOException {
        super(port);
    }

    @Override
    public void onMessage(SocketAddress address, Message message) {
        for (TcpClient client: clients) {
            try {
                System.err.println("!!!!!!!!!!ChatServer");
                System.err.println(client);
                System.err.println(message);
                System.err.println("");
                client.send((Message) message);
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }
    }

}