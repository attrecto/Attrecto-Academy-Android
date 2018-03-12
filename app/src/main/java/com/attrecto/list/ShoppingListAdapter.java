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

    public interface Observer{
        void onClick(ShoppingListItem item);
    }

    private List<ShoppingListItem> items;
    private Observer observer;

    public ShoppingListAdapter(@NonNull List<ShoppingListItem> items, Observer observer) {
        super();
        this.items = items;
        this.observer = observer;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ViewShoppingItemBinding binding;

        private ViewHolder( ViewShoppingItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public static ViewHolder create(ViewGroup parent){
            ViewShoppingItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.view_shopping_item, parent, false);
            return new ViewHolder(binding);
        }

        public void bind(ShoppingListItem item){
            binding.setContent(item);
        }
    }
}
