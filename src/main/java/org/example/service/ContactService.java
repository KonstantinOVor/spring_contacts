package org.example.service;

public interface ContactService {
    void saveInMemory();
    void delete(String email);
    void findAll();
    void saveAllInFile();
}
