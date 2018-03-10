package com.attrecto.list.data;

import com.attrecto.list.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by balazsnemeth on 2018. 03. 10..
 */

public class ShoppingListProvider {
    public List<ShoppingListItem> getShoppingList(int n){
        List<ShoppingListItem> result = new ArrayList<>();
        for(int i=0; i<n;i++){
            result.add(new ShoppingListItem("item #"+i, "1", R.drawable.ic_local_florist_black_24dp));
        }
        return result;
    }
}
