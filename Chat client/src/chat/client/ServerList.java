package chat.client;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import net.Settings;
import net.UDP;
import net.messaging.EmptyMessage;
import net.messaging.Message;
import net.messaging.ServerSettings;

public class ServerList extends     JFrame 
                        implements  Message.Listener {

    private final JList<ServerSettings> servers;
    private final DefaultListModel<ServerSettings> model;
    private final UDP.Receiver receiver;
    
    private final WindowAdapter windowAdapter = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent evt) {
            try {
                receiver.close();
            } catch (IOException e) {}
        }
    };
    
    private final MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() > 1) {
                ServerSettings settings = servers.getSelectedValue();
                if (settings != null) openChat(settings);
            }
        }
    };
    
    private void error(String message) {
        JOptionPane.showInternalMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void openChat(ServerSettings settings) {
        try {
            Socket socket = new Socket(settings.getAddress(), settings.getPort());
            if (socket.isConnected()) {
                new Chat(socket).setVisible(true);
                dispose();
            } else {
                error("Error connecting server: " + settings.getName() + "!");
            }
        } catch (IOException e) {
            error(e.getMessage());
        }
    }
    
    public ServerList() throws UnknownHostException, IOException {
        super("Chat servers");
        model = new DefaultListModel<>();
        servers = new JList<>(model);
        initComponents();
        receiver = new UDP.Receiver(Settings.CLIENT_PORT, this);
        receiver.start();
        UDP.broadcast(Settings.SERVER_PORT, new EmptyMessage());
    }

    @Override
    public void onMessage(SocketAddress address, Message message) {
        if (message instanceof ServerSettings && !model.contains(message)) {
            model.addElement((ServerSettings) message);
        }
    }

    private void initComponents() {
        addWindowStateListener(windowAdapter);
        servers.addMouseListener(mouseAdapter);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        
        JScrollPane scrollPane = new JScrollPane(servers);
        scrollPane.setPreferredSize(new Dimension(150, 300));
        scrollPane.setBorder(null);
        add(scrollPane);
        pack();
    }
    
}