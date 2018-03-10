package com.attrecto.list.data;

/**
 * Created by balazsnemeth on 2018. 03. 10..
 */

public class ShoppingListItem {
    public final String name;
    public final String quantity;
    public final int icon;

    ShoppingListItem(String name, String quantity, int icon) {
        this.name = name;
        this.quantity = quantity;
        this.icon = icon;
    }
}
