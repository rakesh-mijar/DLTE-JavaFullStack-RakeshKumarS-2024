package org.example;
import org.example.UserRepository;
import org.example.StorageTarget;

public  class FileStorageTarget implements StorageTarget{

    @Override
    public UserRepository getUserRepository() {
        return new UserFileRepository("user.doc");
    }
}
