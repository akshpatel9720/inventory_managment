package com.inventory.service;

import com.inventory.dto.AddInventoryDto;
import com.inventory.dto.ResponseDto;
import com.inventory.dto.UpdateInventoryDto;

public interface InventoryService {

    ResponseDto add(AddInventoryDto addInventoryDto);

    ResponseDto updateInventoryStatus(UpdateInventoryDto updateInventoryDto, Long id);

    ResponseDto getInventoryDetails(Long id);

    ResponseDto removeItem(Long id);

    ResponseDto getAllInventory();
}
