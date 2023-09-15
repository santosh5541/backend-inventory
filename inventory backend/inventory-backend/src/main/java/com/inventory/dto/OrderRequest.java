package com.inventory.dto;

import com.inventory.model.Address;
import com.inventory.model.LocalUser;
import com.inventory.model.WebOrderQuantities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private LocalUser user;
    private Address address;
    private List<WebOrderQuantities> quantities;
}
