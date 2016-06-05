package chat.server;

import java.io.IOException;
import java.io.NotSerializableException;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.TcpClient;
import net.TcpServer;
import net.messaging.ChatMessage;
import net.messaging.Message;
import shop.*;


public class ChatServer extends TcpServer {

    private enum Command {
        Send, Search
    };

    private Command command;

    public ChatServer(int port) throws IOException {
        super(port);
    }

    private DictionaryList listQuery;

    @Override
    public void onMessage(SocketAddress address, Message message) {

        for (TcpClient client : clients) {
            try {
                if (message instanceof ChatMessage) {
                    command = Command.valueOf(((ChatMessage) message).getCommand().name());
                    System.out.println("COMAND---------------------");
                    System.out.println(command.name());

                    listQuery = new DictionaryList("а");
                    ((ChatMessage) message).setArrList(listQuery.getArrList());
                  //  System.err.println(listQuery);

                }
               //  client.send((Message) message);
                 client.send((ChatMessage) message);
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }
    }

}
