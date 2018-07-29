package com.vandit.generalstore;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.vandit.generalstore.model.Product;
import com.vandit.generalstore.adapter.ListProductAdapter;
import com.vandit.generalstore.database.Database;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> arrayList;   // get size in record
    private ListView listView;              // show list of record in listview

    private AppCompatButton Product;        // actiion add product
    private AppCompatButton Report;         // action add report
    private AppCompatImageView Logout;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listProduct);
        Product = findViewById(R.id.btnProduct);
      //  Report = findViewById(R.id.btnReport);
       // Logout = findViewById(R.id.imgLogout);

        registerForContextMenu(listView);

        Database database=new Database(MainActivity.this);
        arrayList = new ArrayList<>();
        arrayList = database.display_ProductName();


        listView.setAdapter(new ListProductAdapter(MainActivity.this,arrayList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent intent=new Intent(MainActivity.this,ProductItemActivity.class);
                intent.putExtra("id",arrayList.get(i).getId());
                startActivity(intent);

            }
        });

        View.OnClickListener clickListener=new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                switch (view.getId())
                {
                    case R.id.btnProduct:
                        final Dialog dialog=new Dialog(MainActivity.this);
                        dialog.setTitle("Add Product");
                        dialog.setContentView(R.layout.partial_product_name);
                        final AppCompatEditText ProductName = dialog.findViewById(R.id.textProductName);
                        AppCompatButton Add = dialog.findViewById(R.id.btnAdd);

                        Add.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                                String name=ProductName.getText().toString();
                                if (TextUtils.isEmpty(name))
                                {
                                    ProductName.setError("Enter ProductName");
                                    ProductName.requestFocus();
                                    return;
                                }
                                else {
                                    Product product = new Product();

                                    product.setProductName(ProductName.getText().toString());
                                    Database database = new Database(MainActivity.this);
                                    database.insertProduct(product);

                                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    dialog.dismiss();
                                }
                            }
                        });
                        dialog.show();
                        break;

               /*     case R.id.btnReport:
                        startActivity(new Intent(MainActivity.this,ReportActivity.class));
                        break;

                    case R.id.imgLogout:
                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
*/
                }
            }
        };
        Product.setOnClickListener(clickListener);
    //    Report.setOnClickListener(clickListener);
     //   Logout.setOnClickListener(clickListener);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        menu.add("Delete Product");
        menu.add("Update Product");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position=adapterContextMenuInfo.position;

        Product product=arrayList.get(position);

        final String id=product.getId();
        String name=product.getProductName();


        if (item.getTitle().equals("Delete Product"))
        {
            Database database=new Database(MainActivity.this);
            database.Delete_Product(product.getId());

            arrayList = database.display_ProductName();
            listView.setAdapter(new ListProductAdapter(MainActivity.this,arrayList));
        }

        if (item.getTitle().equals("Update Product"))
        {
            final Dialog dialog=new Dialog(MainActivity.this);
            dialog.setTitle("Update Product");
            dialog.setContentView(R.layout.partial_product_update);
            final AppCompatEditText ProductName = dialog.findViewById(R.id.textNameUpdate);
            AppCompatButton Add = dialog.findViewById(R.id.btnUpdate);

            ProductName.setText(name);
            Add.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    String name=ProductName.getText().toString();
                    if (TextUtils.isEmpty(name))
                    {
                        ProductName.setError("Enter ProductName");
                        ProductName.requestFocus();
                        return;
                    }
                    else {
                        Product product = new Product();
                        product.setId(id);
                        product.setProductName(ProductName.getText().toString());
                        Database database = new Database(MainActivity.this);
                        database.Update_Product(product);

                        arrayList = database.display_ProductName();
                        listView.setAdapter(new ListProductAdapter(MainActivity.this,arrayList));
                        dialog.dismiss();
                    }
                }
            });
            dialog.show();
        }
        return super.onContextItemSelected(item);
    }
}
