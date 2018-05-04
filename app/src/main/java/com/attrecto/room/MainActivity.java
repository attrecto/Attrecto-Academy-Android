package com.attrecto.room;

import android.arch.persistence.room.Room;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.attrecto.room.data.ShoppingListHeader;
import com.attrecto.room.data.ShoppingListItem;
import com.attrecto.room.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView recyclerView;

    private ShoppingListAdapter adapter;

    private Random r = new Random(System.currentTimeMillis());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        recyclerView = binding.recyclerView;
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
    }

    private List<ShoppingListAdapter.Item> createItems(List<ShoppingListItem> items) {
        List<ShoppingListAdapter.Item> result = new ArrayList<>();
        for (int i = 0; i < items.size(); i += 10) {
            result.add(new ShoppingListHeader(Integer.toString(i)));
            result.addAll(items.subList(i, Math.min(items.size(), i + 10)));
        }
        return result;
    }

    private class ClearItems extends AsyncTask<String, Void, String> {

    }

    private class UploadItem extends AsyncTask<String, Void, String> {

    }

    private class GetItems extends AsyncTask<String, Void, String> {

    }
}
