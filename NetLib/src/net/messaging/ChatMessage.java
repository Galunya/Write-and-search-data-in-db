package net.messaging;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatMessage extends Message {
    private static final long serialVersionUID = ChatMessage.class.getName().hashCode();
    
    private static final SimpleDateFormat dateFormat;
    
    static {
        dateFormat = new SimpleDateFormat("HH:mm");
    }
    
    private final String alias;
    
    private final String message;
    
    private final Date timestamp;

    public ChatMessage(String message) {
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