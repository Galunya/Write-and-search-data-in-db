package net.messaging;

import gui.UIManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Dictionary;
import shop.DictionariesListDelete;
import shop.DictionaryList;

public class ChatMessage extends Message {

    private static final long serialVersionUID = ChatMessage.class.getName().hashCode();

    private final String message;

    public void setArrList(ArrayList listQuery) {
        
        this.arrList = listQuery;  
    }

    public static enum Command {
        Send, Search
    }
    private Command command;
   // private DictionaryList arrList;
    private ArrayList arrList;

    public ChatMessage(String text, String command) {
        this.message = text;
        this.command = Command.valueOf(command);
    }

    public ChatMessage(String text, Command command) {
        this.message = text;
        this.command = command;
    }

    public ArrayList getArrList() {
        return new ArrayList((Collection) arrList);
    }


    public ChatMessage(String message) {

        this.message = message;
        this.command = null;
    }

    public String getMessage() {
        System.err.println("MESSAGE alina ChatMesssage");
        System.err.println(message);
        return message;
    }

    public Command getCommand() {
        return command;
    }
}
