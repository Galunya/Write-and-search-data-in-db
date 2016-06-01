package shop;

import data.DataContext;
import gui.UIManager;
import gui.Window;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import shop.models.Dictionary;

public class Application {

    private static Dictionary testDic = new Dictionary("alyyyina","");

    public static void main(String[] args) {
        UIManager.setDefaultLookAndFeel();
        RoleList list =new RoleList("i");
        list.setVisible(true);
        ArrayList<Dictionary> l =list.getArrList();
        for(Dictionary d:l){
            System.err.println("App d");
            System.err.println(d.getName());
        }
    }

}
