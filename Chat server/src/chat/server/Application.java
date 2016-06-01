package chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketAddress;
import net.Settings;
import net.UDP;
import net.messaging.EmptyMessage;
import net.messaging.Message;
import net.messaging.ServerSettings;


public class Application extends ChatServer {

    private final String name;
    private final int port;
    
    static String readLine() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        return in.readLine();
    }
    
    public static void main(String[] args) throws IOException {
        System.out.print("Enter server name: ");
        String name = readLine();
        try (Application app = new Application(name, 8181)){
            app.start();
            try (UDP.Receiver receiver = new UDP.Receiver(Settings.SERVER_PORT, app)) {
                receiver.start();
                System.out.println("Server started. To exit press ENTER >>");
                System.in.read();
            } 
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } 
    }

    public Application(String name, int port) throws IOException {
        super(port);
        this.port = port;
        this.name = name;
        broadcastSettings();
    }
    
    private void broadcastSettings() {
        try {
            UDP.broadcast(Settings.CLIENT_PORT, new ServerSettings(name, port));
        } catch (IOException e) {}
    }
    
    @Override
    public void onMessage(SocketAddress address, Message message) {
        if (message instanceof Message) {
            try {
                UDP.send(address, Settings.CLIENT_PORT, new ServerSettings(name, port));
            } catch(IOException e) {
                e.printStackTrace(System.err);
            }
        } else {
            super.onMessage(address, message);
        }
    }
}
