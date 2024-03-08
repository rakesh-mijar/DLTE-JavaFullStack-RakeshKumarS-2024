package org.example;

import java.io.IOException;

public interface  Activity<T> {//interface with abstract create method
    abstract void create(T object) throws IOException;
}


