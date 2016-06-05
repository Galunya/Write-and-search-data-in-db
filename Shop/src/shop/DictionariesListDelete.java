package shop;

import gui.Window;
import java.awt.Dimension;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import shop.models.DictionariesQuery;
import shop.models.Dictionary;
import shop.models.DictionaryAddNew;

public class DictionariesListDelete extends Window {

    private DefaultListModel<Dictionary> model;
    private JList<Dictionary> list;
   
    private ArrayList<Dictionary> arrList =new ArrayList<>();
    
    private String query;

    public DictionariesListDelete(String query) {
        super("Роли");
        model = new DefaultListModel<>();
        initComponent();
        if (query.trim().length() > 0) {
            load(query.trim());
            
        }

    }

    public DictionariesListDelete(Dictionary query) {
        super("Роли");
        model = new DefaultListModel<>();
        initComponent();
        addElement(query);
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
                arrList.add(dictionary);
            }
        } catch (SQLException | IOException ex) {
            error(ex.getLocalizedMessage());
            System.exit(0);
        }
    }
    

    private void addElement(Dictionary dictionary) {
        try {
            //  model.addElement(testDic);
            DictionaryAddNew.all(dictionary);
        } catch (SQLException | IOException ex) {
            System.err.println("Ооошибка");
            System.err.println(ex);
            //  error(ex.getLocalizedMessage());
            System.exit(0);
        }
    }

    public ArrayList<Dictionary> getArrList() {
        return arrList;
    }

}
