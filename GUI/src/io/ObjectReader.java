package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ObjectReader <E extends Serializable> extends BufferedReader {
    
    public static interface Listener <E> {
        void onDeserialized(E obj);
        E createObject();
    }
    
    public ObjectReader(InputStream input) {
        super(new InputStreamReader(input));
    }
    
    public void run(Listener<E> listener) throws IOException{
        String line;
        while ((line = readLine()) != null) {
            E obj = listener.createObject();
            obj.deserialize(line);
            listener.onDeserialized(obj);
        }
    }
    
}