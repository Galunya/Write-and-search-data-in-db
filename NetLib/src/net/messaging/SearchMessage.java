package net.messaging;

import gui.UIManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import shop.RoleList;

public class SearchMessage extends Message {

    private static final long serialVersionUID = SearchMessage.class.getName().hashCode();

    private static final SimpleDateFormat dateFormat;

    static {
        dateFormat = new SimpleDateFormat("HH:mm");
    }

    private final String alias;

    private final String message;

    private final Date timestamp;

    private Dictionary dictionary;
    
   

    public SearchMessage(String message) {
        this.alias = System.getProperty("user.name");
        this.message = message;
        timestamp = new Date(System.currentTimeMillis());


    }

    public String getAlias() {
        return alias;
    }

    public String getMessage() {
        System.err.println("MESSAGE alina");
        System.err.println(message);
        return message;
    }

    public String getTime() {
        return dateFormat.format(timestamp);
    }

}
