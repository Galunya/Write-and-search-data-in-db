package gui;

import gui.core.UiBuilder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class MenuBuilder extends UiBuilder<JMenu> {

    private String title;
    private List<UiBuilder<? extends JMenuItem>> items;
    
    public MenuBuilder() {
        items = new ArrayList<>();
    }
    
    public MenuBuilder(String title) {
        this();
        this.title = title;
    }
    
    public void clear(){
        items.clear();
    }
    
    public MenuBuilder title(String title) {
        this.title = title;
        return this;
    }
    
    public MenuBuilder append(UiBuilder<? extends JMenuItem> builder) {
        items.add(builder);
        return this;
    }
    
    public MenuBuilder append(String title, String action) {
        return append(new MenuItemBuilder().title(title).action(action));
    }
    
    public MenuBuilder append(String title, Action action) {
        return append(title, action.name());
    }
    
    @Override
    public JMenu build(ActionListener listener) {
        JMenu menu = new JMenu();
        if (title != null) menu.setText(title);
        if (items.isEmpty()) {
            throw new IllegalStateException("Empty menu!");
        }
        for (UiBuilder<? extends JMenuItem> builder: items) {
            menu.add(builder.build(listener));
        }
        return menu;
    }
    
    public JPopupMenu buildPopup(ActionListener listener) {
        JPopupMenu menu = new JPopupMenu();
        for (UiBuilder<? extends JMenuItem> item : items) {
            menu.add(item.build(listener));
        }
        return menu; 
    }
}