package com.example.billingservice.services;

import com.example.billingservice.dto.StoreDTO;
import com.example.billingservice.exceptions.ResourceNotFoundException;
import com.example.billingservice.repositories.StoreRepository;
import com.example.billingservice.stores.Store;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {
  
    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addStore(StoreDTO storeDTO) {
        Store store = convertToEntity(storeDTO);
        storeRepository.save(store);
    }

    @Override
    public void updateStore(StoreDTO storeDTO) {
        Store store = convertToEntity(storeDTO);
        storeRepository.save(store); // Assuming save method handles update as well
    }

    @Override
    public void deleteStoreById(Long id) {
        storeRepository.deleteById(id);
    }

    @Override
    public StoreDTO getStoreById(Long id) {
        Optional<Store> optionalStore = storeRepository.findById(id);
        Store store = optionalStore.orElseThrow(() -> new ResourceNotFoundException("Store not found with id: " + id));
        return convertToDTO(store);
    }

    private StoreDTO convertToDTO(Store store) {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(store.getId());
        storeDTO.setName(store.getName());
        storeDTO.setPhoneNumber(store.getPhoneNumber());
        storeDTO.setEmail(store.getEmail());
        storeDTO.setUnitNumber(store.getUnitNumber());
        storeDTO.setStreetName(store.getStreetName());
        storeDTO.setResidentialArea(store.getResidentialArea());
        storeDTO.setPostalCode(store.getPostalCode());
        storeDTO.setCity(store.getCity());
        storeDTO.setState(store.getState());
        storeDTO.setCountry(store.getCountry());
        return storeDTO;
    }

    private Store convertToEntity(StoreDTO storeDTO) {
        Store store = new Store();
        store.setId(storeDTO.getId());
        store.setName(storeDTO.getName());
        store.setPhoneNumber(storeDTO.getPhoneNumber());
        store.setEmail(storeDTO.getEmail());
        store.setUnitNumber(storeDTO.getUnitNumber());
        store.setStreetName(storeDTO.getStreetName());
        store.setResidentialArea(storeDTO.getResidentialArea());
        store.setPostalCode(storeDTO.getPostalCode());
        store.setCity(storeDTO.getCity());
        store.setState(storeDTO.getState());
        store.setCountry(storeDTO.getCountry());
        return store;
    }
}
