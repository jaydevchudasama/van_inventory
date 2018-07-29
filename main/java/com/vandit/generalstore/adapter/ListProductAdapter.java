package com.vandit.generalstore.adapter;

import android.annotation.SuppressLint;
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
 * Created by ECLR-01 on 06-10-2017.
 */

public class ListProductAdapter extends BaseAdapter
{
    private  ArrayList<Product> arrayList = new ArrayList<>();
    private  Context context;

    public ListProductAdapter(Context context, ArrayList<Product> arrayList)
    {
        this.context  = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount()
    {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return i;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.partial_product_list,null);

        AppCompatTextView productName;

        productName = view.findViewById(R.id.labelProductName);

        productName.setText(arrayList.get(i).getProductName());

        return view;
    }
}
