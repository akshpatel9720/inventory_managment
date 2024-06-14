package com.inventory.controller;

import com.inventory.dto.AddInventoryDto;
import com.inventory.dto.ResponseDto;
import com.inventory.dto.UpdateInventoryDto;
import com.inventory.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping
    private ResponseDto add(@Valid @RequestBody AddInventoryDto addInventoryDto) {
        return inventoryService.add(addInventoryDto);
    }

    @PutMapping
    private ResponseDto updateInventoryStatus(@Valid @RequestBody UpdateInventoryDto updateInventoryDto,
                                              @RequestParam("Id") Long id) {
        return inventoryService.updateInventoryStatus(updateInventoryDto, id);
    }

    @GetMapping
    private ResponseDto getInventoryDetails(@RequestParam("Id") Long id) {
        return inventoryService.getInventoryDetails(id);
    }


    @DeleteMapping
    private ResponseDto removeItem(@RequestParam("Id") Long id) {
        return inventoryService.removeItem(id);
    }


    @GetMapping("/getAll")
    private ResponseDto getAllInventory() {
        return inventoryService.getAllInventory();
    }


}
