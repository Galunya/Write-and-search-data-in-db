package gui;

import gui.core.UiBuilder;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ButtonBuilder extends UiBuilder<JButton> {

    private String title, action;
    private Dimension dimension;
    private Point location;
    private boolean focusable = true;
    
    public ButtonBuilder title(String title) {
        this.title = title;
        return this;
    }
    
    public ButtonBuilder action(String action) {
        this.action = action;
        return this;
    }
    
    public ButtonBuilder action(Action action){
        return action(action.name());
    }
    
    public ButtonBuilder focusable(boolean focusable) {
        this.focusable = focusable;
        return this;
    }
    
    public ButtonBuilder dimension(Dimension dimension) {
        this.dimension = dimension;
        return this;
    }
    
    public ButtonBuilder location(Point location) {
        this.location = location;
        return this;
    }
    
    @Override
    public JButton build(ActionListener listener) {
        if (action != null) {
            JButton button = new JButton();
            button.setActionCommand(action);
            button.addActionListener(listener);
            button.setFocusable(focusable);
            if (title != null) button.setText(title);
            if (location != null && dimension != null) {
                button.setBounds(new Rectangle(location, dimension));
            } else {
                if (dimension != null) button.setPreferredSize(dimension);
                if (location != null) button.setLocation(location);
            }
            return button;
        }
        throw new IllegalArgumentException();
    }
}