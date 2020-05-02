package util;

public class KeyValueContainer<K, V> {
    private K key;
    private V value;

    public KeyValueContainer(){} // this is required by Jackson
    public KeyValueContainer(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public K getKey(){
        return key;
    }
    public V getValue(){
        return value;
    }
    public void setKey(K key){
        this.key = key;
    }
    public void setValue(V value){
        this.value = value;
    }
}