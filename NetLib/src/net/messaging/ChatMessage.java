package net.messaging;

import gui.UIManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Dictionary;
import queries.DictionaryList;

public class ChatMessage extends Message {

    private static final long serialVersionUID = ChatMessage.class.getName().hashCode();

    private String message;
    private Dictionary messageDictionary;
    private Command command;
    private ArrayList arrList;

    private String nameFieldAdd;
    private String descriptionFieldAdd;

    public void setArrList(ArrayList listQuery) {

        this.arrList = listQuery;
    }

    public static enum Command {
        Send, Search, Add
    }

    public ChatMessage(String text, String command) {
        this.message = text;
        this.command = Command.valueOf(command);
    }

    public ChatMessage(String text, Command command) {
        this.message = text;
        this.command = command;

    }

    public ChatMessage(Command command, String... arg) {
        this.command = command;
        if (arg[0].length() > 0) {
            nameFieldAdd = arg[0];
        }
        if (arg[0].length() > 1) {
            descriptionFieldAdd = arg[1];
        }

    }

    public String getNameFieldAdd() {
        return nameFieldAdd;
    }

    public String getDescriptionFieldAdd() {
        return descriptionFieldAdd;
    }

    public ArrayList getArrList() {
        return new ArrayList((Collection) arrList);
    }

    public ChatMessage(String message) {

        this.message = message;
        this.command = null;
    }

    public String getMessage() {
        return message;
    }

    public Command getCommand() {
        return command;
    }
}
