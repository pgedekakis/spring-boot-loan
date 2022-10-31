package com.knowledge.spring.loan.model;


import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;

@Data
@Entity
@Table(name = "menu_entity")
@NoArgsConstructor
public class MenuEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="menu_id" , referencedColumnName = "id")
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "entity_id", referencedColumnName = "id")
    private EntityTable entityTable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public EntityTable getEntityTable() {
        return entityTable;
    }

    public void setEntityTable(EntityTable entityTable) {
        this.entityTable = entityTable;
    }
}
