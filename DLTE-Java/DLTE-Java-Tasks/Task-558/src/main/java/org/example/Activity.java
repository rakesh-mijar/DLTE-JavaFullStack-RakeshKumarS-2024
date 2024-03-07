package org.example;
import java.util.Arrays;
public interface  Activity<T> { //Generic interface with crud methods written
     //T[] myObjects;
     String create(T objectExample);
     T read(int index);
     void update(int index,T object);
     String delete(int index);
}
