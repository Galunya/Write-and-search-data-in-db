package chat.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.TcpClient;
import net.messaging.ChatMessage;
import net.messaging.Message;
import queries.models.Dictionary;

public class Chat extends JFrame
        implements TcpClient.Listener,
        DocumentListener,
        ActionListener {

    private enum Command {
       Search, Add
    }

    private final TcpClient client;
    private final JList<Dictionary> messages;
    private final DefaultListModel<Dictionary> model;
    private final JTextArea message;
    private final JTextArea resultSearch;
    private final JTextField nameField;
    private final JTextField descriptionField;
    private final JButton searchButton;
    private final JButton addButton;
    private JScrollPane scrollPane;
    private ArrayList<Dictionary> listQuery;
    private JLabel aliasAddDictionary;

    public Chat(Socket socket) {
        super("Dictionary");
        model = new DefaultListModel<>();
        messages = new JList<>(model);
        message = new JTextArea();
        searchButton = new JButton("Search");
        addButton = new JButton("Add");
        resultSearch = new JTextArea();
        client = new TcpClient(socket, this);
        listQuery = new ArrayList<>();
        nameField = new JTextField();
        descriptionField = new JTextField();
        initComponents();
        client.start();
    }

    @Override
    public void onDisconnect(TcpClient client) {
        JOptionPane.showMessageDialog(this, "Server has gone!", "Error", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    @Override
    public void onMessage(SocketAddress address, Message messageClient) {
        if (messageClient instanceof ChatMessage) {
            System.err.println("Chat.java");
            System.err.println(messageClient);
            listQuery = ((ChatMessage) messageClient).getArrList();
            model.clear();
            for (Dictionary dictionary : listQuery) {
                model.addElement(dictionary);

            }
            if (listQuery.size() > 0) {
                for (int i = 0; i < model.size(); i++) {
                    JLabel alias = new JLabel("Определение :");
                    JLabel time = new JLabel(model.elementAt(i).getValueDictionary());
                    alias.setForeground(Color.BLUE);
                    time.setForeground(Color.LIGHT_GRAY);
                    JLabel content = new JLabel(model.elementAt(i).getValueDictionary());
                    JPanel header = new JPanel(new GridLayout(1, 2));
                    header.setBackground(Color.WHITE);
                    header.add(alias);
                    header.add(time);
                    JPanel panel = new JPanel(new BorderLayout());
                    //  panel.setBorder(BorderFactory.createEmptyBorder(OFFSET, OFFSET, 0, OFFSET));
                    panel.add(header, BorderLayout.NORTH);
                    panel.add(content);
                    panel.setBackground(Color.WHITE);
                    message.add(panel);
                }
            }

        }
    }

    private void initComponents() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        message.getDocument().addDocumentListener(this);
        message.setPreferredSize(new Dimension(200, 60));

        searchButton.addActionListener(this);
        searchButton.setActionCommand(Command.Search.name());
        searchButton.setEnabled(false);

        addButton.addActionListener(this);
        addButton.setActionCommand(Command.Add.name());
        addButton.setEnabled(false);

        scrollPane = new JScrollPane(messages);
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(250, 300));
        add(scrollPane);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
        panel.add(message);
        panel.add(searchButton, BorderLayout.EAST);

        nameField.getDocument().addDocumentListener(this);
        descriptionField.getDocument().addDocumentListener(this);
        JPanel addPanel = new JPanel(new BorderLayout());
        aliasAddDictionary = new JLabel("Добавить запись :");
        addPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
        addPanel.add(aliasAddDictionary);
        JPanel header = new JPanel(new GridLayout(1, 2));
        header.setBackground(Color.WHITE);
        header.add(nameField);
        header.add(descriptionField);
        addPanel.add(header, BorderLayout.NORTH);

        addPanel.add(addButton, BorderLayout.EAST);

        JPanel res = new JPanel(new BorderLayout());
        res.add(panel, BorderLayout.NORTH);
        res.add(addPanel, BorderLayout.EAST);

        add(res, BorderLayout.SOUTH);

        pack();
    }

    private void updateButton() {
        searchButton.setEnabled(message.getText().trim().length() > 0);
        aliasAddDictionary.setText("Добавить запись :");

    }

    private void updateButtonAdd() {
        addButton.setEnabled(nameField.getText().trim().length() > 0);
        addButton.setEnabled(descriptionField.getText().trim().length() > 0);
        aliasAddDictionary.setText("Добавить запись :");

    }

    @Override
    public void insertUpdate(DocumentEvent de) {
        updateButton();
        updateButtonAdd();
    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        updateButton();
        updateButtonAdd();

    }

    @Override
    public void changedUpdate(DocumentEvent evt) {
        updateButton();
        updateButtonAdd();

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Command command = Command.valueOf(evt.getActionCommand());
        switch (command) {
            case Search:
                try {
                    client.send(new ChatMessage(message.getText(), evt.getActionCommand()));
                    message.setText(null);
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case Add:
                try {
                    if (nameField.getText().trim().length() > 0) {
                        client.send(new ChatMessage(ChatMessage.Command.Add, nameField.getText(), descriptionField.getText()));
                    } else {
                        addButton.setBackground(Color.RED);
                        aliasAddDictionary.setText("Запись не добавлена");
                    }
                    nameField.setText(null);
                    descriptionField.setText(null);
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
                break;
        }
    }

}
