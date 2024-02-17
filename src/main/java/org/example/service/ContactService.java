package org.example.service;

import java.util.Set;

public interface ContactService {
    void saveInMemory();
    void delete(String email);
    void findAll();
    void saveAllInFile();
}
