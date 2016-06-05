package net.messaging;


import gui.UIManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import shop.DictionariesList;

public class ChatMessage extends Message {

    private static final long serialVersionUID = ChatMessage.class.getName().hashCode();

    private final String message;
    private final String command;

    public ChatMessage(String message) {

        this.message = message;
        this.command = null;

// private DictionariesList   list= new DictionariesList("а");
//        System.err.println("rrr");
        // list.setVisible(true);
//        ArrayList<shop.models.Dictionary> l = list.getArrList();
//        for (shop.models.Dictionary d : l) {
//            System.err.println("App d");
//            System.err.println(d.getName());
//        }
    }

    public ChatMessage(String message, String command) {
        this.message = message;
        this.command = command;
    }

    public String getMessage() {
        System.err.println("MESSAGE alina ChatMesssage");
        System.err.println(message);
        return message;
    }

}
