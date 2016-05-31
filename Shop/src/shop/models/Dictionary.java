package shop.models;

public class Dictionary {
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
