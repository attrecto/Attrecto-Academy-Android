package com.attrecto.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.attrecto.list.data.ShoppingListItem;
import com.attrecto.list.databinding.ViewShoppingItemBinding;

import java.util.List;

/**
 * Created by balazsnemeth on 2018. 03. 10..
 */

public class ShoppingListAdapter extends ArrayAdapter<ShoppingListItem> {
    public ShoppingListAdapter(@NonNull Context context, List<ShoppingListItem> items) {
        super(context, -1, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewShoppingItemBinding itemBinding;
        if(convertView == null){
            itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.view_shopping_item, parent, false);
            itemBinding.getRoot().setTag(itemBinding);
        }else{
            itemBinding = (ViewShoppingItemBinding) convertView.getTag();
        }

        itemBinding.setContent(getItem(position));

        return itemBinding.getRoot();
    }
}
