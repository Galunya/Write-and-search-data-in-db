package queries;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.messaging.Message;
import queries.models.DictionariesQuery;
import queries.models.Dictionary;
import queries.models.DictionaryAddNew;

public class DictionaryList extends Message {

    private static final long serialVersionUID = DictionaryList.class.getName().hashCode();

    public ArrayList<Dictionary> arrList = new ArrayList<>();

    private String query;

    public DictionaryList(String query) {
        if (query.trim().length() > 0) {
            load(query.trim());

        }

    }

   
    public DictionaryList(String... query) {
        addElement(query);
    }

    private void load(String query) {
        try {
            DictionariesQuery dictionariesQuery = new DictionariesQuery(query);
            for (Dictionary dictionary : dictionariesQuery) {
                arrList.add(dictionary);
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }

    private void addElement(String... dictionary) {
        try {
           DictionaryAddNew dictionariesQuery = new DictionaryAddNew(dictionary);
        } catch (SQLException | IOException ex) {
            System.err.println(ex);
            System.exit(0);
        }
    }

    public ArrayList<Dictionary> getArrList() {
        return arrList;
    }
}
