package data;

import io.Configurable;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public final class DataContext  extends Configurable{

    private static DataContext instance;

    public static DataContext getInstance() throws SQLException, IOException {
        if (instance == null) {
            instance = new DataContext();
        }
        return instance;
    }

    private final Connection connection;

    private String getUrl() {
        return String.format("jdbc:derby://%s:%s/%s", 
                settings.get("host"),
                settings.get("port"),
                settings.get("database"));
    }

    private DataContext() throws SQLException, IOException {
        super(new File("database.ini"));
        
        Properties properties = new Properties();
        properties.put("user", settings.get("user"));
        properties.put("password", settings.get("password"));
          connection = DriverManager.getConnection(getUrl(), properties);
//        connection = DriverManager.getConnection(getUrl());
       // connection.setSchema("TESTTASK");
        //   System.out.println("connection");
        // System.out.println(connection);

    }

    public interface Listener {

        void onSelect(ResultSet result) throws SQLException,IOException;
    }

    public void select(String query, Listener listener) throws SQLException,IOException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            listener.onSelect(resultSet);
        }
    }
}
