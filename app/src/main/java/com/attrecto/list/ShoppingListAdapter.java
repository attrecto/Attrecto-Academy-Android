package com.attrecto.list;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.attrecto.list.data.ShoppingListHeader;
import com.attrecto.list.data.ShoppingListItem;
import com.attrecto.list.databinding.ViewShoppingHeaderBinding;
import com.attrecto.list.databinding.ViewShoppingItemBinding;

import java.util.List;

/**
 * Created by balazsnemeth on 2018. 03. 10..
 */

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ViewHolder> {

    public interface Observer {
        void onClick(ShoppingListItem item);
    }

    public interface Item {
    }

    private List<Item> items;
    private Observer observer;

    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_HEADER = 2;

    public ShoppingListAdapter(@NonNull List<Item> items, Observer observer) {
        super();
        this.items = items;
        this.observer = observer;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                return ItemViewHolder.create(parent, observer);
            case VIEW_TYPE_HEADER:
                return HeaderViewHolder.create(parent);
            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof ShoppingListHeader) {
            return VIEW_TYPE_HEADER;
        } else if (items.get(position) instanceof ShoppingListItem) {
            return VIEW_TYPE_ITEM;
        }

        throw new ClassCastException();
    }

    public static abstract class ViewHolder<T extends Item> extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bind(T item);
    }

    public static class ItemViewHolder extends ViewHolder<ShoppingListItem> {
        private ViewShoppingItemBinding binding;
        private Observer observer;

        private ItemViewHolder(ViewShoppingItemBinding binding, Observer observer) {
            super(binding.getRoot());
            this.binding = binding;
            this.observer = observer;
        }

        public static ItemViewHolder create(ViewGroup parent, Observer observer) {
            ViewShoppingItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.view_shopping_item, parent, false);
            return new ItemViewHolder(binding, observer);
        }

        @Override
        public void bind(ShoppingListItem item) {
            binding.setContent(item);
            binding.setObserver(observer);
        }
    }

    public static class HeaderViewHolder extends ViewHolder<ShoppingListHeader> {
        private ViewShoppingHeaderBinding binding;

        private HeaderViewHolder(ViewShoppingHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public static HeaderViewHolder create(ViewGroup parent) {
            ViewShoppingHeaderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.view_shopping_header, parent, false);
            return new HeaderViewHolder(binding);
        }

        @Override
        public void bind(ShoppingListHeader item) {
            binding.setContent(item);
        }
    }
}
