package chat.server;

import queries.DictionaryList;
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
import queries.models.Dictionary;

public class ChatServer extends TcpServer {

    private enum Command {
        Send, Search, Add
    };

    private Command command;
    private Dictionary dictionary;

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

                    switch (command) {
                        case Add:
                            String nameFiels = ((ChatMessage) message).getNameFieldAdd();
                            String descrField ="";
                            if (nameFiels.length() > 0) {
                                descrField = ((ChatMessage) message).getDescriptionFieldAdd().length() > 0 ? ((ChatMessage) message).getDescriptionFieldAdd() : "";
                             listQuery = new DictionaryList(nameFiels, descrField);
                            ((ChatMessage) message).setArrList(listQuery.getArrList());
                            }
                           

                            break;
                        case Search:
                            listQuery = new DictionaryList(((ChatMessage) message).getMessage());
                            ((ChatMessage) message).setArrList(listQuery.getArrList());

                            break;
                    }

                }
                client.send(message);

            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }
    }

}
