package com.innoclique.identity.responses;

import com.innoclique.identity.DTO.MenuItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
public class MenuItemsResponse {
    private StatusResponse statusResponse;
    private List<MenuItems> menuItems;
}
