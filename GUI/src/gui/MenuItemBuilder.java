package gui;

import gui.core.UiBuilder;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

public class MenuItemBuilder extends UiBuilder<JMenuItem> {

    private String title;
    private String action;
    
    public MenuItemBuilder title(String title) {
        this.title = title;
        return this;
    }
    
    public MenuItemBuilder action(String action) {
        this.action = action;
        return this;
    }
    
    @Override
    public JMenuItem build(ActionListener listener) {
        if (title == null) {
            throw new IllegalArgumentException("Menu items without title is depricated!");
        }
        JMenuItem item = new JMenuItem(title);
        if (action != null) item.setActionCommand(action);
        item.addActionListener(listener);
        return item;
    }
    
}