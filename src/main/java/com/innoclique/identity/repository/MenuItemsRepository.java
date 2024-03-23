package com.innoclique.identity.repository;

import com.innoclique.identity.DTO.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemsRepository extends JpaRepository<MenuItems, Integer> {

    @Procedure(procedureName = " USP_GetMenuList")
    List<MenuItems> findMenuItemsByParentID(@Param("piPLID") int plid, @Param("piParentID") int parentID);

}
