package com.vandit.generalstore.model;

public class Sell {
    String  id2;
    String pro_id;
    String Item_name;
    String Itemqty;
    String Itembuy;
    String ItemSell;
    String date1;
    Double profit,loss;

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getLoss() {
        return loss;
    }

    public void setLoss(Double loss) {
        this.loss = loss;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getPro_id() {
        return pro_id;
    }

    public void setPro_id(String pro_id) {
        this.pro_id = pro_id;
    }

    public String getItem_name() {
        return Item_name;
    }

    public void setItem_name(String item_name) {
        Item_name = item_name;
    }

    public String getItemqty() {
        return Itemqty;
    }

    public void setItemqty(String itemqty) {
        Itemqty = itemqty;
    }

    public String getItembuy() {
        return Itembuy;
    }

    public void setItembuy(String itembuy) {
        Itembuy = itembuy;
    }

    public String getItemSell() {
        return ItemSell;
    }

    public void setItemSell(String itemSell) {
        ItemSell = itemSell;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }


}
