package chat.client;

import javax.swing.UIManager;


public class Application{
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new ServerList().setVisible(true);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

}
