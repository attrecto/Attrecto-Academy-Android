package com.attrecto.list;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.attrecto.list.data.ShoppingListItem;
import com.attrecto.list.databinding.ViewShoppingItemBinding;

import java.util.List;

/**
 * Created by balazsnemeth on 2018. 03. 10..
 */

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    private List<ShoppingListItem> items;

    public ShoppingListAdapter(@NonNull List<ShoppingListItem> items) {
        super();
        this.items = items;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

    }
}
