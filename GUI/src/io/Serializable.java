package io;

public interface Serializable {
    String serialize();
    void deserialize(String data);
}