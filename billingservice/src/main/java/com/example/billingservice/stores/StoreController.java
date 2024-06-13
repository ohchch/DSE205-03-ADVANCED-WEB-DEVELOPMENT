package com.example.billingservice.stores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/stores")
public class StoreController {
    private static final Logger logger = LoggerFactory.getLogger(StoreController.class); // Logger for logging store activities
    
    @Autowired
    private final StoreService storeService; // Service layer dependency

    // Constructor to inject StoreService
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    // Handler for displaying the add store form
    @GetMapping("/add")
    public String showAddStoreForm() {
        return "addStore"; // Return view name for adding store
    }

    // Handler for adding a store
    @PostMapping("/add")
    public String addStore(@RequestParam("name") String name,
                           @RequestParam("phoneNumber") String phoneNumber,
                           @RequestParam("localities") String localities,
                           Model model) {
        // Convert comma-separated localities string to list
        List<String> localitiesList = Arrays.asList(localities.split(","));
        
        // Create Store object and set its properties
        Store store = new Store(name, phoneNumber, localitiesList);
        
        // Log received store information
        logger.info("Received store: {}", store);
        
        // Call StoreService to add store
        storeService.addStore(store);
        
        // Set success message (optional, if you want to show a success message on the view page)
        model.addAttribute("message", "Store added successfully!");
        
        // Redirect to view all stores
        return "redirect:/stores";
    }

    // Handler for getting all stores
    @GetMapping
    public String getAllStores(Model model) {
        // Retrieve list of all stores
        List<Store> stores = storeService.getAllStores();
        
        // Add stores to model
        model.addAttribute("stores", stores);
        
        // Return view name for displaying store list
        return "storeList";
    }
}
