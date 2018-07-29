package com.vandit.generalstore.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.vandit.generalstore.ProductItemActivity;
import com.vandit.generalstore.model.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by ECLR-01 on 06-10-2017.
 */

public class Database extends SQLiteOpenHelper
{
    Double profit=0.0,loss=0.0;
    String TableProduct = "Product", // table_name
            id = "id", ProductName = "ProductName"; // autoincrement value in product_table

    String TableItem = "Item", // table_name
            id1 = "id1",                        // autoincrement value in item_table
            ItemName = "ItemName",              // show item_nae
            ItemQuantity = "ItemQuantity",      // show item_quantity
            ItemBuyPrice = "ItemBuyPrice",      // show item_buy_price
            ItemSellPrice = "ItemSellPrice",    // show item_sell_price
            proId = "proId";
                // add integer value
    String TableSell = "Sell",
            id2="SellId",
            pro_id  =  "proId",
            Item_Name = "ItemName",
            Itemqty = "Itemqty",
            Itembuy="ItemBuyPrice",
            ItemSell="ItemSellPrice",
            date1 = "selldate";

    public Database(Context context)
    {
        super(context, "ABC", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) // create two table
    {
        String food = " create table " + TableProduct + "("
                + id + " integer primary key autoincrement, "
                + ProductName + " text)";
        sqLiteDatabase.execSQL(food);

        String item = " create table " + TableItem + "("
                + id1 + " integer primary key autoincrement, "
                + proId + " integer, "
                + ItemName + " text, "
                + ItemQuantity + " text, "
                + ItemBuyPrice + " text, "
                + ItemSellPrice + " text)";
        sqLiteDatabase.execSQL(item);
        String sell = " create table " + TableSell + "("
                + id2 + " integer primary key autoincrement, "
                + pro_id + " integer, "
                + Item_Name + " text, "
                + Itemqty + " text, "
                + Itembuy  +" text, "
                + ItemSell + " text,"
                + date1 + " text)";
        sqLiteDatabase.execSQL(sell);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {

    }

    public void insertProduct(Product product) // add record in product_table
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductName, product.getProductName());
        database.insert(TableProduct,null,contentValues);
        database.close();
    }
    public void insertsell(Sell sell){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues  contentValues=new ContentValues();
        contentValues.put(pro_id,sell.getPro_id());
        contentValues.put(Item_Name,sell.getItem_name());
        contentValues.put(Itemqty,sell.getItemqty());
        contentValues.put(Itembuy,sell.getItembuy());
        contentValues.put(ItemSell,sell.getItemSell());
        contentValues.put(date1,sell.getDate1());
        database.insert(TableSell,null,contentValues);
        database.close();
    }

    public void insertItem(Item item) // add record in item_table
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(proId,item.getProId());
        contentValues.put(ItemName,item.getItem_name());
        contentValues.put(ItemQuantity,item.getItem_quantity());
        contentValues.put(ItemBuyPrice,item.getItem_buy_price());
        contentValues.put(ItemSellPrice,item.getItem_sell_price());

        database.insert(TableItem,null,contentValues);
        database.close();
    }

    public ArrayList<Product> display_ProductName() // show all record in product_table
    {
        ArrayList<Product> arrayList=new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String sql = " select * from " + TableProduct;
        Cursor cursor = database.rawQuery(sql,null);

        while (cursor.moveToNext())
        {
            Product product = new Product();
            product.setId(cursor.getString(0));
            product.setProductName(cursor.getString(1));
            arrayList.add(product);
        }
        database.close();
        return arrayList;
    }

    public ArrayList<Item> display_ItemName(String pid) // show  perticular item record
    {
        ArrayList<Item> arrayList=new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String sql = " select * from " + TableItem + " where "+proId+"="+pid;
        Cursor cursor = database.rawQuery(sql,null);

        while (cursor.moveToNext())
        {
            Item item = new Item();
            item.setId1(cursor.getString(0));
            item.setItem_name(cursor.getString(2));
            item.setItem_quantity(cursor.getString(3));
            item.setItem_buy_price(cursor.getString(4));
            item.setItem_sell_price(cursor.getString(5));
            arrayList.add(item);
        }
        database.close();
        return arrayList;
    }


    public void Update_Product(Product product) // update perticular product
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ProductName, product.getProductName());
        database.update(TableProduct,contentValues,id+"="+product.getId(),null);
        database.close();
    }
    public ArrayList<Sell> monthly_report(String today,int pid){
        ArrayList<Sell> arrayList=new ArrayList<>();
        SQLiteDatabase   database = getReadableDatabase();
        String sql = " select * from " + TableSell +" where "+ pro_id +" = "+pid;
        Cursor cursor = database.rawQuery(sql,null);

        while (cursor.moveToNext())
        {
            Sell sell=new Sell();

            String dat=cursor.getString(6);
            try {
                Date d1=new SimpleDateFormat("dd-MMM-yyyy").parse(dat);
                Date d2=new SimpleDateFormat("dd-MMM-yyyy").parse(today);
                long days=d2.getTime()-d1.getTime();
               long days1= TimeUnit.MILLISECONDS.toDays(days);
               if(days1<31){
                   double p1=Double.parseDouble(cursor.getString(5)) ;
                   String p=cursor.getString(4);
                   double p2 = Double.parseDouble(cursor.getString(4));
                   double  p3=Double.parseDouble(cursor.getString(3));
                   if (p1-p2 > 0) {
                       profit += p3 * (p1 - p2);
                   } else {
                       loss += p3 * (p2 - p1);

                   }

               }
              //  Toast.makeText(context, "Days:"+days1, Toast.LENGTH_SHORT).show();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sell.setProfit(profit);
            sell.setLoss(loss);
            arrayList.add(sell);
        }

        database.close();
        return arrayList;
    }
    public ArrayList<Sell> daily_report(String today,int pid){
        ArrayList<Sell> arrayList=new ArrayList<>();
        SQLiteDatabase   database = getReadableDatabase();
        String sql = " select * from " + TableSell +" where "+ pro_id +" = "+pid;
        Cursor cursor = database.rawQuery(sql,null);

        while (cursor.moveToNext())
        {
            Sell sell=new Sell();
            String dat=cursor.getString(6);
            if(today.equals(dat)) {
               double p1=Double.parseDouble(cursor.getString(5)) ;
               String p=cursor.getString(4);
               double p2 = Double.parseDouble(cursor.getString(4));
               double  p3=Double.parseDouble(cursor.getString(3));
                if (p1-p2 > 0) {
                    profit += p3 * (p1 - p2);
                } else {
                    loss += p3 * (p2 - p1);

                }

            }
            sell.setProfit(profit);
            sell.setLoss(loss);
            arrayList.add(sell);
        }

        database.close();
        return arrayList;
    }
    public void Update_Item(Item item) // update perticular item
    {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ItemName,item.getItem_name());
        contentValues.put(ItemQuantity,item.getItem_quantity());
        contentValues.put(ItemBuyPrice,item.getItem_buy_price());
        contentValues.put(ItemSellPrice,item.getItem_sell_price());
        database.update(TableItem,contentValues,id1+"="+item.getId1(),null);
        database.close();
    }
    public void Delete_Product(String id) // delete perticular record
    {
        SQLiteDatabase database=getReadableDatabase();
        database.delete(TableProduct,this.id+"="+id,null);
        database.close();
    }
    public void Delete_Item(String id1)
    {
        SQLiteDatabase database=getReadableDatabase();
        database.delete(TableItem,this.id1+"="+id1,null);
        database.close();
    }
}
