package shop;

import data.DataContext;
import gui.UIManager;
import gui.Window;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    public static void main(String[] args) {
        UIManager.setDefaultLookAndFeel();
        new RoleList().setVisible(true);
    }

}
