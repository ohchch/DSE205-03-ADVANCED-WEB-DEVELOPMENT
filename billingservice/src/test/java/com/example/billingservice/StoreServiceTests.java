package com.example.billingservice;

import com.example.billingservice.dto.StoreDTO;
import com.example.billingservice.exceptions.ResourceNotFoundException;
import com.example.billingservice.repositories.StoreRepository;
import com.example.billingservice.services.StoreServiceImpl;
import com.example.billingservice.stores.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class StoreServiceTests {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreServiceImpl storeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getStoreById_ExistingId_ReturnsStore() {
        // Mock data
        Store store = new Store();
        store.setId(1L);
        store.setName("Mock Store");
        store.setPhoneNumber("1234567890");
        store.setEmail("mock@store.com");

        // Mock repository method
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));

        // Call service method
        StoreDTO result = storeService.getStoreById(1L);

        // Verify
        assertEquals("Mock Store", result.getName());
        assertEquals("1234567890", result.getPhoneNumber());
        assertEquals("mock@store.com", result.getEmail());
    }

    @Test
    public void getStoreById_NonExistingId_ThrowsResourceNotFoundException() {
        // Mock repository method
        when(storeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Call service method and verify exception
        assertThrows(ResourceNotFoundException.class, () -> {
            storeService.getStoreById(999L);
        });
    }
}
