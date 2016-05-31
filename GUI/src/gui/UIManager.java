package gui;

public class UIManager extends javax.swing.UIManager {
    
    public static void setDefaultLookAndFeel() {
        try {
            setLookAndFeel(getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } 
    }
    
}