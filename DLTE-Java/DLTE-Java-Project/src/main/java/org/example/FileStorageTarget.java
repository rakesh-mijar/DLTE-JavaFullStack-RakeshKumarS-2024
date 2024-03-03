package org.example;

public class FileStorageTarget implements StorageTarget {

    @Override
    public UserFileRepository getUserFileRepository() {
        return new UserFileRepository("user.doc");
    }
}
