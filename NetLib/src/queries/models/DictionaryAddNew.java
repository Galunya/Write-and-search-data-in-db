package queries.models;

import data.DataContext;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DictionaryAddNew extends ArrayList<Dictionary> implements DataContext.Listener {

    private static final long serialVersionUID = DictionaryAddNew.class.getName().hashCode();

    public DictionaryAddNew(String... query) throws SQLException, IOException {
        DataContext.getInstance().insert("INSERT INTO Dictionary (NAME, DESCRIPTION) VALUES('" + query[0].trim() + "', '" + query[1].trim() + "')", this);
    }

    @Override
    public void onSelect(ResultSet result) throws SQLException, IOException {
    }

    @Override
    public void onInsert(int i) throws SQLException, IOException {
    }

}
