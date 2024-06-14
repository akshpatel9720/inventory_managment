package com.inventory.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddInventoryDto {
    @NotEmpty(message = "name must not be empty")
    @NotNull(message = "name must not be null")
    private String name;
    @NotNull(message = "Category must not be null")
    private Integer categoryId;
    @NotNull(message = "shop must not be null")
    private Long shopId;
    @NotNull(message = "quantity must not be null")
    private Integer quantity;
}
