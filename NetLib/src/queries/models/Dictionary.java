package queries.models;

import net.messaging.Message;

public class Dictionary extends Message{
    private static final long serialVersionUID = Dictionary.class.getName().hashCode();
    private String name;
    private String valueDictionary;

    
    public String getName() {
        return name;
    }

    public String getValueDictionary() {
        return valueDictionary;
    }

    public Dictionary(String name, String valueDictionary) {
        this.name = name;
        this.valueDictionary = valueDictionary;
    }
    
    public String toString() {
        return name+":"+valueDictionary;
    }
    
}
