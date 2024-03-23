package com.innoclique.identity.controller;


import com.innoclique.identity.DTO.RequestMenuItems;
import com.innoclique.identity.responses.MenuItemsResponse;
import com.innoclique.identity.service.MenuItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/menu")
public class MenuItemsController {

    @Autowired
    private MenuItemsService menuItemService;

    //Get All MenuItems
    @CrossOrigin
    @PostMapping("/fetchMenus")
    public MenuItemsResponse getMenuItems(@RequestBody RequestMenuItems menuItemsDTO) {

        // Call the service method to retrieve menu items based on parent ID from the DTO
        return menuItemService.getMenuItemsByParentID(menuItemsDTO.getPlid(), menuItemsDTO.getParentID());


    }
}

