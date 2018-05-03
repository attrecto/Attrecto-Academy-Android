package com.attrecto.room.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.attrecto.room.data.ShoppingListItem;

@Database(entities = {ShoppingListItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShoppingListItemDao shoppingListItemDao();
}
