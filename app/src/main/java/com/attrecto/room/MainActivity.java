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
import com.attrecto.room.db.AppDatabase;
import com.attrecto.room.db.ShoppingListItemDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView recyclerView;

    private AppDatabase db;
    private ShoppingListItemDao itemDao;

    private ShoppingListAdapter adapter;

    private Random r = new Random(System.currentTimeMillis());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        recyclerView = binding.recyclerView;
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "shopping-list-items").build();
        itemDao = db.shoppingListItemDao();

        recyclerView.setLayoutManager(layoutManager);
        new GetItems().execute("");
    }

    @Override
    protected void onPause() {
        super.onPause();
        db.close();
    }

    private List<ShoppingListAdapter.Item> createItems(List<ShoppingListItem> items) {
        List<ShoppingListAdapter.Item> result = new ArrayList<>();
        for (int i = 0; i < items.size(); i += 10) {
            result.add(new ShoppingListHeader(Integer.toString(i)));
            result.addAll(items.subList(i, Math.min(items.size(), i + 10)));
        }
        return result;
    }

    public void onNewItemClick(View view) {
        new UploadItem().execute("");
    }

    public void onClearItems(View view) {
        new ClearItems().execute("");
    }

    private class ClearItems extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            itemDao.deleteAll();
            adapter.deleteAllItems();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            adapter.notifyDataSetChanged();
        }
    }

    private class UploadItem extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            ShoppingListAdapter.Item item = new ShoppingListItem(
                    "#" + r.nextInt(100000) + 1,
                    "" + r.nextInt(10) + 1,
                    R.drawable.ic_local_florist_black_24dp
            );
            itemDao.insertAll((ShoppingListItem) item);
            // header + 10 elements
            if (adapter.getItemCount() % 11 == 0) {
                adapter.addItem(new ShoppingListHeader(Integer.toString(adapter.getItemCount() * 10 / 11)));
            }
            adapter.addItem(item);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            adapter.notifyItemRangeChanged(adapter.getItemCount() - 1, adapter.getItemCount());
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);
        }
    }

    private class GetItems extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            if (adapter == null) {
                adapter = new ShoppingListAdapter(createItems(itemDao.getAll()), new ShoppingListAdapter.Observer() {
                    @Override
                    public void onClick(ShoppingListItem item) {
                        Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            recyclerView.setAdapter(adapter);
            return null;
        }
    }
}
