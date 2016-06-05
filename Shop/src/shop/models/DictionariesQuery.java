package shop.models;

import data.DataContext;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DictionariesQuery extends ArrayList<Dictionary> implements DataContext.Listener {

    private static final long serialVersionUID = DictionariesQuery.class.getName().hashCode();

    private  DictionariesQuery instance;

    public  DictionariesQuery all(String query) throws SQLException, IOException {
      //  return instance == null ? (instance = new DictionariesQuery(query)) : instance;
        return instance = new DictionariesQuery(query);
    }

   // private DictionariesQuery(String query) throws SQLException, IOException {
    public DictionariesQuery(String query) throws SQLException, IOException {
        System.err.println("QUERYYY ");
        System.err.println(query);
        System.err.println("INSTANCE");
        System.err.println(instance);
        DataContext.getInstance().select("select * from DICTIONARY WHERE LOWER(NAME) LIKE LOWER('%" + query.trim() + "%') OR   LOWER(DESCRIPTION) LIKE LOWER('%" + query.trim() + "%')\n"
                + "order by name", this);
       
        //DataContext.getInstance().select("select * from DICTIONARY WHERE LOWER(NAME) LIKE LOWER('%" + query.trim() + "%') OR   LOWER(DESCRIPTION) LIKE LOWER('%" + query.trim() + "%')\n"
          //      + "order by name", this);
        
    }
    
   

    @Override
    public void onSelect(ResultSet result) throws SQLException, IOException {
        while (result.next()) {
            //int id = result.getInt("ID");
            String name = result.getString("NAME");
            String valueDictionary = result.getString("DESCRIPTION");

            add(new Dictionary(name, valueDictionary));

        }
    }

    @Override
    public void onInsert(int i) throws SQLException, IOException {
    }

}
