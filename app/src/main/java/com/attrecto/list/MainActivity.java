package com.attrecto.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.attrecto.list.data.ShoppingListProvider;
import com.attrecto.list.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ShoppingListProvider shoppingListProvider = new ShoppingListProvider();

    private static final int LIST_ITEM_NUMBER = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.listView.setAdapter(new ShoppingListAdapter(this, shoppingListProvider.getShoppingList(LIST_ITEM_NUMBER)));
    }
}
