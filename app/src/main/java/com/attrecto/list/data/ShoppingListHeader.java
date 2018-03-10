package com.attrecto.list.data;

import com.attrecto.list.ShoppingListAdapter;

/**
 * Created by balazsnemeth on 2018. 03. 10..
 */

public class ShoppingListHeader implements ShoppingListAdapter.Item  {
    public final String name;

    public ShoppingListHeader(String name) {
        this.name = name;
    }
}
