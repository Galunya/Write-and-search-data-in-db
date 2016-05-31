package shop;

import gui.Window;
import java.awt.Dimension;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import shop.models.DictionariesQuery;
import shop.models.Dictionary;
import shop.models.DictionaryAddNew;


public class RoleList extends Window {

    private DefaultListModel<Dictionary> model;
    private JList<Dictionary> list;
    private Dictionary testDic =new Dictionary("name","dic");

    public RoleList() {
        super("Роли");
        model = new DefaultListModel<>();
        initComponent();
        load("а");
        addElement(testDic);
    }

    private void initComponent() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        list = new JList<>(model);
        list.setPreferredSize(new Dimension(150, 300));

        add(list);
        
        pack();

    }

    private void load(String query) {
        try {
            for (Dictionary dictionary : DictionariesQuery.all(query)) {
                model.addElement(dictionary);
            }
        } catch (SQLException | IOException ex) {
            error(ex.getLocalizedMessage());
            System.exit(0);
        }
    }
    
    private void addElement(Dictionary dictionary){
         try {
           //  model.addElement(testDic);
            DictionaryAddNew.all(dictionary);
        } catch (SQLException | IOException ex) {
            error(ex.getLocalizedMessage());
            System.exit(0);
        }
    }

}
