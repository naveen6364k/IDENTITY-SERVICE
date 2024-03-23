package com.innoclique.identity.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MenuItems {
        @Id
        private int menuId;
        private String Menuname;
        private String menuType;
        private int parentId;
        private Integer isChildExist;
        private String SeqOrder;

}
