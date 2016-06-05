package shop;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import shop.models.DictionariesQuery;
import shop.models.Dictionary;
import shop.models.DictionaryAddNew;

public class DictionaryList  {
       private static final long serialVersionUID = DictionaryList.class.getName().hashCode();

    public ArrayList<Dictionary> arrList =new ArrayList<>();
    
    private String query;

    public DictionaryList(String query) {
        System.out.println("!!!!!!!!!");
        if (query.trim().length() > 0) {
            load(query.trim());
            
        }

    }

    public DictionaryList(Dictionary query) {
      
        addElement(query);
    }


    private void load(String query) {
        try {
             System.out.println("Удача дикшанари");
            for (Dictionary dictionary : DictionariesQuery.all(query)) {
                arrList.add(dictionary);
            }
        } catch (SQLException | IOException ex) {
             System.out.println("Ооошибка дикшанари");
             System.out.println(ex.getMessage());
            System.exit(0);
        }
    }
    

    private void addElement(Dictionary dictionary) {
        try {
            DictionaryAddNew.all(dictionary);
        } catch (SQLException | IOException ex) {
            System.err.println("Ооошибка");
            System.err.println(ex);
            System.exit(0);
        }
    }

//    public ArrayList<Dictionary> getArrList() {
//        return arrList;
//    }
public ArrayList getArrList() {
        return arrList;
    }
}
