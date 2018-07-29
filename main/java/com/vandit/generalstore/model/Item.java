package com.vandit.generalstore.model;

import java.util.Date;

/**
 * Created by ECLR-01 on 09-10-2017.
 */

public class Item
{
    String item_name;
    String item_quantity;
    String item_buy_price;
    String item_sell_price;
    String id1;
    String proId;
    String Item_date;
    public String getItem_date() {
        return Item_date;
    }

    public void setItem_date(String idate) {
        this.Item_date = idate;
    }


    public String getId1()
    {
        return id1;
    }

    public void setId1(String id1)
    {
        this.id1 = id1;
    }

    public String getProId()
    {
        return proId;
    }

    public void setProId(String proId)
    {
        this.proId = proId;
    }

    public String getItem_sell_price()
    {
        return item_sell_price;
    }

    public void setItem_sell_price(String item_sell_price)
    {
        this.item_sell_price = item_sell_price;
    }

    public String getItem_name()
    {
        return item_name;
    }

    public void setItem_name(String item_name)
    {
        this.item_name = item_name;
    }

    public String getItem_quantity()
    {
        return item_quantity;
    }

    public void setItem_quantity(String item_quantity)
    {
        this.item_quantity = item_quantity;
    }

    public String getItem_buy_price()
    {
        return item_buy_price;
    }

    public void setItem_buy_price(String item_buy_price)
    {
        this.item_buy_price = item_buy_price;
    }
}
