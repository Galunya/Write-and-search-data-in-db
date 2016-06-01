package net.messaging;

import gui.UIManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import shop.RoleList;

public class ChatMessage extends Message {

    private static final long serialVersionUID = ChatMessage.class.getName().hashCode();

    private static final SimpleDateFormat dateFormat;

    static {
        dateFormat = new SimpleDateFormat("HH:mm");
    }

    private final String alias;

    private final String message;

    private final Date timestamp;

    private Dictionary dictionary;
    
   

    public ChatMessage(String message) {
        this.alias = System.getProperty("user.name");
        this.message = message;
        timestamp = new Date(System.currentTimeMillis());

// private RoleList   list= new RoleList("а");
//        System.err.println("rrr");
       // list.setVisible(true);
//        ArrayList<shop.models.Dictionary> l = list.getArrList();
//        for (shop.models.Dictionary d : l) {
//            System.err.println("App d");
//            System.err.println(d.getName());
//        }
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
