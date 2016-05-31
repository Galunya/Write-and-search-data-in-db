package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Window extends JFrame implements ActionListener {

    private static final String actionPostfix = "Action";

    public Window(String title) {
        super(title);
        setLocationByPlatform(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand() + actionPostfix;
        try {
            Method method = getClass().getMethod(name, Object.class);
            method.invoke(this, e.getSource());
        } catch (Exception ignore) {
        }
    }

    protected void error(String message) {
        JOptionPane.showMessageDialog(this, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    
}
