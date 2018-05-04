package com.attrecto.room.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.attrecto.room.ShoppingListAdapter;

public class ShoppingListItem implements ShoppingListAdapter.Item {

    private int id;
    private String name;
    private String quantity;
    private int icon;

    public ShoppingListItem(String name, String quantity, int icon) {
        this.name = name;
        this.quantity = quantity;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public int getIcon() {
        return icon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
