package chat.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.TcpClient;
import net.messaging.ChatMessage;
import net.messaging.Message;

public class Chat   extends     JFrame 
                    implements  TcpClient.Listener, 
                                DocumentListener, 
                                ActionListener {

    private enum Command { Send }
    
    private final TcpClient client;
    private final JList<ChatMessage> messages;
    private final DefaultListModel<ChatMessage> model;
    private final JTextArea message;
    private final JButton sendButton;
    
    public Chat(Socket socket) {
        super("Chat");
        model = new DefaultListModel<>();
        messages = new JList<>(model);
        message = new JTextArea();
        sendButton = new JButton("Send");
        client = new TcpClient(socket, this);
        initComponents();
        client.start();
    }

    @Override
    public void onDisconnect(TcpClient client) {
        JOptionPane.showMessageDialog(this, "Server has gone!", "Error", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    @Override
    public void onMessage(SocketAddress address, Message message) {
        if (message instanceof ChatMessage) {
            model.addElement((ChatMessage) message);
        }
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        
        messages.setCellRenderer(new ChatMessageCellRenderer());
        
        message.getDocument().addDocumentListener(this);
        message.setPreferredSize(new Dimension(200, 60));
        
        sendButton.addActionListener(this);
        sendButton.setActionCommand(Command.Send.name());
        sendButton.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(messages);
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(250, 300));
        add(scrollPane);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
        panel.add(message);
        panel.add(sendButton, BorderLayout.EAST);
        
        add(panel, BorderLayout.SOUTH);
        
        pack();
    }

    private void updateButton(){
        sendButton.setEnabled(message.getText().trim().length() > 0);
    }
    
    @Override
    public void insertUpdate(DocumentEvent de) {
       updateButton();
    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        updateButton();
    }

    @Override
    public void changedUpdate(DocumentEvent evt) {
        updateButton();
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        Command command = Command.valueOf(evt.getActionCommand());
        switch (command) {
            case Send:
                try {
                    client.send(new ChatMessage(message.getText()));
                    message.setText(null);
                } catch (IOException e) {}
                break;
        }
    }
    
}