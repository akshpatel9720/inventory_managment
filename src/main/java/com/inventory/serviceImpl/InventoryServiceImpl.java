package com.inventory.serviceImpl;

import com.inventory.dto.AddInventoryDto;
import com.inventory.dto.GetInventoryDto;
import com.inventory.dto.ResponseDto;
import com.inventory.dto.UpdateInventoryDto;
import com.inventory.entity.Category;
import com.inventory.entity.Inventory;
import com.inventory.entity.ShopEntity;
import com.inventory.exception.CategoryNotFoundException;
import com.inventory.exception.InventoryNotFoundException;
import com.inventory.exception.ShopNotFoundException;
import com.inventory.repo.CategoryRepo;
import com.inventory.repo.InventroryRepo;
import com.inventory.repo.ShopRepo;
import com.inventory.service.InventoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    private InventroryRepo inventroryRepo;

    @Autowired
    private ShopRepo shopRepo;

    @Override
    public ResponseDto add(AddInventoryDto addInventoryDto) {
        Inventory inventory = new Inventory();
        Category existingCategory = existingCategory(addInventoryDto.getCategoryId());
        ShopEntity existingShop = existingShop(addInventoryDto.getShopId());
        if (existingCategory != null && existingShop != null) {
            BeanUtils.copyProperties(addInventoryDto, inventory);
            inventory.setCategoryId(existingCategory);
            inventory.setShopId(existingShop);
            inventory.setIsCreated(new Date());
            inventory.setIsUpdated(new Date());
            inventory.setIsDeleted(false);
            inventroryRepo.save(inventory);
            return new ResponseDto(HttpStatus.OK.value(), "Inventory added successfully", null);
        } else {
            return new ResponseDto(HttpStatus.BAD_GATEWAY.value(), "Category not found", null);
        }
    }

    private ShopEntity existingShop(Long shopId) {
        Optional<ShopEntity> existingShop = shopRepo.findById(shopId);
        if (!existingShop.isPresent())
            throw new ShopNotFoundException("Shop not found");

        return existingShop.get();
    }

    private Category existingCategory(Integer categoryId) {
        Optional<Category> existingCategory = categoryRepo.findById(categoryId);
        if (!existingCategory.isPresent())
            throw new CategoryNotFoundException("Category not found");

        return existingCategory.get();
    }

    @Override
    public ResponseDto updateInventoryStatus(UpdateInventoryDto updateInventoryDto, Long id) {
        Optional<Inventory> existingInventory = inventroryRepo.findById(id);
        Category category = existingCategory(updateInventoryDto.getCategoryId());
        ShopEntity existingShop = existingShop(updateInventoryDto.getShopId());
        if (existingInventory.isPresent()) {
            Inventory inventory = existingInventory.get();
            inventory.setName(updateInventoryDto.getName());
            inventory.setQuantity(updateInventoryDto.getQuantity());
            inventory.setIsDeleted(updateInventoryDto.getIsDeleted());
            inventroryRepo.save(inventory);
            return new ResponseDto(HttpStatus.OK.value(), "Data updated successfully", null);
        } else {
            throw new InventoryNotFoundException("Inventory not found");
        }
    }

    @Override
    public ResponseDto getInventoryDetails(Long id) {
        Optional<Inventory> existingInventory = inventroryRepo.findById(id);
        if (existingInventory.isPresent()) {
            GetInventoryDto inventory = inventroryRepo.findDetailsById(id);
            return new ResponseDto(HttpStatus.OK.value(), "Data fetched succesfully", inventory);
        } else {
            throw new InventoryNotFoundException("Inventory not found");
        }
    }

    @Override
    public ResponseDto removeItem(Long id) {
        Optional<Inventory> existingInventory = inventroryRepo.findById(id);
        if (existingInventory.isPresent()) {
            inventroryRepo.deleteById(id);
            return new ResponseDto(HttpStatus.OK.value(), "Data deleted succesfully", null);
        } else {
            throw new InventoryNotFoundException("Inventory not found");
        }
    }

    @Override
    public ResponseDto getAllInventory() {
        List<Inventory> inventoryList = inventroryRepo.findAll();
        if (inventoryList != null) {
            return new ResponseDto(HttpStatus.OK.value(), "Data fetched successfully", inventroryRepo.findAllDetails());
        } else {
            return new ResponseDto(HttpStatus.OK.value(), "No data in inventory", inventoryList);
        }
    }
}
