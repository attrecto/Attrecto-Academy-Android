package com.attrecto.room.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.attrecto.room.data.ShoppingListItem;

import java.util.List;

@Dao
public interface ShoppingListItemDao {

    @Query("SELECT * FROM ShoppingListItem")
    List<ShoppingListItem> getAll();

    @Query("SELECT * FROM shoppinglistitem WHERE id IN (:itemIds)")
    List<ShoppingListItem> loadAllByIds(int[] itemIds);

    @Query("SELECT * FROM ShoppingListItem WHERE name LIKE :name LIMIT 1")
    ShoppingListItem findByName(String name);

    @Insert
    void insertAll(ShoppingListItem... items);

    @Query("DELETE FROM ShoppingListItem")
    public void deleteAll();

}
