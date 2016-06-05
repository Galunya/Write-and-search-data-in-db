package net.messaging;

import gui.UIManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;

public class SearchMessageqqq extends Message {

    private static final long serialVersionUID = SearchMessageqqq.class.getName().hashCode();

    private static final SimpleDateFormat dateFormat;

    static {
        dateFormat = new SimpleDateFormat("HH:mm");
    }

    private final String alias;

    private final String message;

    private final Date timestamp;

    private Dictionary dictionary;
    
   

    public SearchMessageqqq(String message) {
        this.alias = System.getProperty("user.name");
        this.message = message;
        timestamp = new Date(System.currentTimeMillis());


    }

    public String getAlias() {
        return alias;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return dateFormat.format(timestamp);
    }

}
