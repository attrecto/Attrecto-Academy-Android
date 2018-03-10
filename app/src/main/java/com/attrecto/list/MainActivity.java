package com.attrecto.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.attrecto.list.data.ShoppingListItem;
import com.attrecto.list.data.ShoppingListProvider;
import com.attrecto.list.databinding.ActivityMainBinding;
import com.attrecto.list.databinding.ViewShoppingItemBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ShoppingListProvider shoppingListProvider = new ShoppingListProvider();

    private static final int LIST_ITEM_NUMBER = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        LayoutInflater layoutInflater = getLayoutInflater();

        for (ShoppingListItem item : shoppingListProvider.getShoppingList(LIST_ITEM_NUMBER)) {
            ViewShoppingItemBinding itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.view_shopping_item, binding.content, true);
            itemBinding.setContent(item);
        }
    }
}
