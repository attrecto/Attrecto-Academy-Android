package com.attrecto.room.data;

import com.attrecto.room.ShoppingListAdapter;

public class ShoppingListHeader implements ShoppingListAdapter.Item  {
    public final String name;

    public ShoppingListHeader(String name) {
        this.name = name;
    }
}
