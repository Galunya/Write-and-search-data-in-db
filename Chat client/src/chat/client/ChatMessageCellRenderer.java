package chat.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import net.messaging.ChatMessage;

public class ChatMessageCellRenderer implements ListCellRenderer<ChatMessage>{

    private static final int OFFSET = 5;
    
    @Override
    public Component getListCellRendererComponent(JList<? extends ChatMessage> list, ChatMessage message, int index, boolean selected, boolean focused) {
      //  JLabel alias = new JLabel(message.getAlias());
        //JLabel time = new JLabel(message.getTime(), JLabel.RIGHT);
        JLabel alias = new JLabel("test1");
        JLabel time = new JLabel("test1");
        alias.setForeground(Color.BLUE);
        time.setForeground(Color.LIGHT_GRAY);
        JLabel content = new JLabel(message.getMessage());
        JPanel header = new JPanel(new GridLayout(1, 2));
        header.setBackground(Color.WHITE);
        header.add(alias);
        header.add(time);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(OFFSET, OFFSET, 0, OFFSET));
        panel.add(header, BorderLayout.NORTH);
        panel.add(content);
        panel.setBackground(Color.WHITE);
        return panel;
    }
    
}