package org.example;

public interface  Activity<T> {//interface with abstract create method
    abstract void create(T object);
}