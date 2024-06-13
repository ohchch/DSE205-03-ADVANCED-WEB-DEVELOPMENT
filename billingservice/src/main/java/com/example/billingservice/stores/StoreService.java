package com.example.billingservice.stores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class StoreService {
    @Autowired
    private final StoreRepository storeRepository;

    
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    // Method to add a store to the database
    public void addStore(Store store) {
        storeRepository.save(store);
    }

    // Method to retrieve all stores from the database
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }
}
