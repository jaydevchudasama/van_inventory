package com.vandit.generalstore.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.vandit.generalstore.model.*;
import com.vandit.generalstore.R;
import java.util.ArrayList;

/**
 * Created by ECLR-01 on 09-10-2017.
 */

public class ListItemAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Item> arrayList;

    public ListItemAdapter(Context context,ArrayList<Item> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public int getCount()
    {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.partial_item_list,null);

        AppCompatTextView labelItemName;
        AppCompatTextView labelItemQuantity;
        AppCompatTextView labelItemPrice;
        AppCompatTextView labelItemSell;

        labelItemName = view.findViewById(R.id.labelItemName);
        labelItemQuantity = view.findViewById(R.id.labelItemQuantity);
        labelItemPrice = view.findViewById(R.id.labelItemPrice);
        labelItemSell = view.findViewById(R.id.labelItemSell);

        labelItemName.setText(arrayList.get(i).getItem_name());
        labelItemQuantity.setText(arrayList.get(i).getItem_quantity());
        labelItemPrice.setText("Rs"+arrayList.get(i).getItem_buy_price());
        labelItemSell.setText("Rs"+arrayList.get(i).getItem_sell_price());
        return view;
    }
}
