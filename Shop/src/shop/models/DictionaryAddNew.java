package shop.models;

import data.DataContext;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DictionaryAddNew extends ArrayList<Dictionary> implements DataContext.Listener {

    private static DictionaryAddNew instance;

    public static DictionaryAddNew all(Dictionary dictionary) throws SQLException, IOException {
        return instance == null ? (instance = new DictionaryAddNew(dictionary)) : instance;
    }

    private DictionaryAddNew(Dictionary dictionary) throws SQLException, IOException {
        DataContext.getInstance().insert("INSERT INTO Dictionary (NAME, DESCRIPTION) VALUES('" + dictionary.getName().trim() + "', '" + dictionary.getValueDictionary().trim() + "')", this);
    }

    @Override
    public void onSelect(ResultSet result) throws SQLException, IOException {
        System.err.println("Ответ");
        System.err.println(result);

    }

//    private void add(Dictionary dictionary) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public void onInsert(int i) throws SQLException, IOException {
        System.err.println("Ответ");
        System.err.println(i);
    }

}
