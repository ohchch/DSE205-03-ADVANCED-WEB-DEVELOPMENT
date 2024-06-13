package com.example.billingservice.controllers;

import com.example.billingservice.dto.StoreDTO;
import com.example.billingservice.exceptions.ResourceNotFoundException;
import com.example.billingservice.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/all")
    public String getAllStores(Model model) {
        List<StoreDTO> stores = storeService.getAllStores();
        model.addAttribute("stores", stores);
        return "storeList"; // 对应模板文件名为 storeList.html
    }

    @GetMapping("/add")
    public String showAddStoreForm(Model model) {
        model.addAttribute("store", new StoreDTO());
        return "addStore"; // 对应模板文件名为 addStore.html
    }

    @PostMapping("/add")
    public String addStore(@ModelAttribute("store") StoreDTO storeDTO, Model model) {
        try {
            storeService.addStore(storeDTO);
            model.addAttribute("message", "Store added successfully!");
            return "success"; // 对应模板文件名为 success.html，用于显示添加成功的页面
        } catch (Exception e) {
            model.addAttribute("message", "Failed to add store.");
            return "addStore"; // 返回添加商店的页面
        }
    }


    @GetMapping("/edit/{id}")
    public String showEditStoreForm(@PathVariable Long id, Model model) {
        try {
            StoreDTO storeDTO = storeService.getStoreById(id);
            model.addAttribute("store", storeDTO);
            return "edit"; // 对应模板文件名为 editStore.html
        } catch (ResourceNotFoundException e) {
            // Handle resource not found exception
            model.addAttribute("message", "Store not found.");
            return "error"; // 对应模板文件名为 error.html，用于显示错误信息
        } catch (Exception e) {
            // Handle other exceptions
            model.addAttribute("message", "Failed to retrieve store.");
            return "error"; // 对应模板文件名为 error.html，用于显示错误信息
        }
    }

    @PostMapping("/edit/{id}")
    public String updateStore(@PathVariable Long id, @ModelAttribute("store") StoreDTO storeDTO, Model model) {
        try {
            storeDTO.setId(id); // 确保 ID 一致性
            storeService.updateStore(storeDTO);
            model.addAttribute("message", "Store updated successfully!");
            return "edit"; // 对应模板文件名为 editStore.html
        } catch (ResourceNotFoundException e) {
            // Handle resource not found exception
            model.addAttribute("message", "Store not found.");
            return "error"; // 对应模板文件名为 error.html，用于显示错误信息
        } catch (Exception e) {
            // Handle other exceptions
            model.addAttribute("message", "Failed to update store.");
            return "error"; // 对应模板文件名为 error.html，用于显示错误信息
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteStore(@PathVariable Long id, Model model) {
        try {
            storeService.deleteStoreById(id);
            model.addAttribute("message", "Store deleted successfully.");
            return "delete"; // 对应模板文件名为 delete.html，用于显示删除成功的信息
        } catch (ResourceNotFoundException e) {
            // Handle resource not found exception
            model.addAttribute("message", "Store not found.");
            return "error"; // 对应模板文件名为 error.html，用于显示错误信息
        } catch (Exception e) {
            // Handle other exceptions
            model.addAttribute("message", "Failed to delete store.");
            return "error"; // 对应模板文件名为 error.html，用于显示错误信息
        }
    }
}
