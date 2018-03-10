package com.attrecto.list;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.attrecto.list.data.ShoppingListHeader;
import com.attrecto.list.data.ShoppingListItem;
import com.attrecto.list.data.ShoppingListProvider;
import com.attrecto.list.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ShoppingListProvider shoppingListProvider = new ShoppingListProvider();

    private static final int LIST_ITEM_NUMBER = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        RecyclerView recyclerView = binding.recyclerView;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ShoppingListAdapter(createItems(shoppingListProvider.getShoppingList(LIST_ITEM_NUMBER)), new ShoppingListAdapter.Observer() {
            @Override
            public void onClick(ShoppingListItem item) {
                Toast.makeText(MainActivity.this, item.name, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private List<ShoppingListAdapter.Item> createItems(List<ShoppingListItem> items){
        List<ShoppingListAdapter.Item> result = new ArrayList<>();

        for(int i= 0; i<items.size(); i+=10){
            result.add(new ShoppingListHeader(Integer.toString(i)));
            result.addAll(items.subList(i, Math.min(items.size(), i+10)));
        }

        return result;
    }
}
