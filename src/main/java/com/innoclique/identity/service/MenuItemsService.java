package com.innoclique.identity.service;


import com.innoclique.identity.DTO.MenuItems;
import com.innoclique.identity.repository.MenuItemsRepository;
import com.innoclique.identity.responses.MenuItemsResponse;
import com.innoclique.identity.responses.StatusResponse;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Transactional
@Log4j2
public class MenuItemsService {

    @Autowired
    MenuItemsRepository menuItemRepository;

    public MenuItemsResponse getMenuItemsByParentID(int plid, int parentID) {
        StatusResponse statusResponse = new StatusResponse();
        List<MenuItems> menuItems = null;
        try {
            // Log a message indicating the start of the method
            log.info("Get MenuItems Method Started in Service");

            // Attempt to retrieve menu items from the repository
            menuItems = menuItemRepository.findMenuItemsByParentID(plid, parentID);

            // Iterate over each menu item retrieved from the repository
            if (menuItems != null) {
                statusResponse.setStatusCode(200);
                statusResponse.setMessage("MenuItems Fetch Successful");
            } else {
                statusResponse.setStatusCode(400);
                statusResponse.setMessage("MenuItems Fetch Failed");
            }
        } catch (Exception e) {
            // If an exception occurs during the retrieval process:
            statusResponse.setStatusCode(500);
            statusResponse.setMessage("Internal Server Error");
            // Return null to indicate that the retrieval failed
            log.info("Exception is " + e);
        }
        // Return the list of MenuItemsDTO objects
        log.info("Get Menu Items Method End in Service");
        return new MenuItemsResponse(statusResponse, menuItems);
    }

}
