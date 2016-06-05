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
import queries.models.Dictionary;

public class ChatDictionaryCellRenderer implements ListCellRenderer<Dictionary>{

    private static final int OFFSET = 5;
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Dictionary> list, Dictionary message, int index, boolean selected, boolean focused) {
        JLabel alias = new JLabel(message.getName());
        //JLabel time = new JLabel(message.getTime(), JLabel.RIGHT);
        alias.setForeground(Color.BLUE);
       // time.setForeground(Color.LIGHT_GRAY);
        JLabel content = new JLabel(message.getValueDictionary());
        JPanel header = new JPanel(new GridLayout(1, 2));
        header.setBackground(Color.WHITE);
        header.add(alias);
      //  header.add(time);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(OFFSET, OFFSET, 0, OFFSET));
        panel.add(header, BorderLayout.NORTH);
        panel.add(content);
        panel.setBackground(Color.WHITE);
        return panel;
    }
    
}