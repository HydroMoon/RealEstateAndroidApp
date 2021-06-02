package com.example.realestate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.realestate.adapters.OrdersAdapter;
import com.example.realestate.adapters.RealEstateAdapter;
import com.example.realestate.models.OrderClass;
import com.example.realestate.models.RealEstateClass;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRL;
    RecyclerView myRecycler;
    RecyclerView.LayoutManager layoutManager;
    RealEstateAdapter realEstateAdapter;
    ArrayList<RealEstateClass> propertyList = new ArrayList<>();
    ArrayList<OrderClass> orderList = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.orders_m);

        ParseUser.getCurrentUser().fetchInBackground(((object, e) -> {
            Log.d("BH ERR:", String.valueOf(object.getInt("admin")));
            if (object.getInt("admin") == 1) {
                item.setVisible(true);
            } else {
                item.setVisible(false);
            }
        }));



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.orders_m) {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("buy_history");

            query.findInBackground((objects, e) -> {
                if (e == null) {
                    orderList.clear();
                    for (ParseObject object: objects) {
                        orderList.add(new OrderClass(object.getString("user_name"), object.getObjectId(), object.getString("buy_id"),
                                object.getString("user_id"), object.getInt("status"), object.getString("property_number")));
                    }
                    final AlertDialog adb = new AlertDialog.Builder(MainActivity.this, R.style.AlertDiagTheme).create();
                    LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                    View view = inflater.inflate(R.layout.orders_recycle, null);
                    adb.setView(view);

                    RecyclerView order_rv = view.findViewById(R.id.recycler_orders);
                    order_rv.setHasFixedSize(true);
                    RecyclerView.LayoutManager lom = new LinearLayoutManager(adb.getContext());
                    order_rv.setLayoutManager(lom);

                    OrdersAdapter ordersAdapter = new OrdersAdapter(orderList, adb.getContext(), adb);
                    order_rv.setAdapter(ordersAdapter);

                    adb.show();
                } else {
                    Log.d("BH ERR:", e.getMessage());
                }
            });

        } else if (id == R.id.logout_m) {
            ParseUser.logOut();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        swipeRL = findViewById(R.id.swipeContainer);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        myRecycler = findViewById(R.id.recycler_list);
        myRecycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(layoutManager);

        runQuery();

        swipeRL.setOnRefreshListener(this::refreshQuery);

        swipeRL.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public void refreshQuery() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("property_list");

        query.setLimit(200); // limit to at most 10 results
//        query.setSkip(10); // skip the first 10 results
        propertyList.clear();
        realEstateAdapter.clear();

        query.findInBackground((objects, e) -> {
            if(e == null){
                int x = 0;
                for (ParseObject result : objects) {
                    propertyList.add(new RealEstateClass(result.getObjectId(), result.getInt("property_id"), result.getInt(""), result.getString("location"),
                            result.getInt(""), result.getInt("first_corner"), result.getInt("second_corner"), result.getInt("single"),
                            result.getInt(""), result.getInt(""), result.getInt(""), result.getInt(""),
                            result.getInt(""), result.getInt(""), result.getInt(""), result.getInt(""),
                            result.getInt(""), result.getInt(""), result.getInt(""), result.getInt(""),
                            result.getInt(""), result.getInt(""), result.getInt(""), result.getInt(""),
                            result.getInt(""), result.getInt(""), result.getInt(""), result.getInt(""),
                            result.getInt("dollar"), result.getInt("sdg"), result.getInt("sold")));
                }


                realEstateAdapter.addAll(propertyList);
                swipeRL.setRefreshing(false);
            }else{
                Log.d("Object error ", e.getMessage());
            }
        });
    }

    public void runQuery() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("property_list");

        query.setLimit(200); // limit to at most 10 results
//        query.setSkip(10); // skip the first 10 results

        query.findInBackground((objects, e) -> {
            if(e == null){
                int x = 0;
                for (ParseObject result : objects) {
                    propertyList.add(new RealEstateClass(result.getObjectId(), result.getInt("property_id"), result.getInt(""), result.getString("location"),
                            result.getInt(""), result.getInt("first_corner"), result.getInt("second_corner"), result.getInt("single"),
                            result.getInt(""), result.getInt(""), result.getInt(""), result.getInt(""),
                            result.getInt(""), result.getInt(""), result.getInt(""), result.getInt(""),
                            result.getInt(""), result.getInt(""), result.getInt(""), result.getInt(""),
                            result.getInt(""), result.getInt(""), result.getInt(""), result.getInt(""),
                            result.getInt(""), result.getInt(""), result.getInt(""), result.getInt(""),
                            result.getInt("dollar"), result.getInt("sdg"), result.getInt("sold")));
                }


                realEstateAdapter = new RealEstateAdapter(propertyList, getApplicationContext());
                myRecycler.setAdapter(realEstateAdapter);
            }else{
                Log.d("Object error ", e.getMessage());
            }
        });
    }



    @Override
    public void onBackPressed() {
    }
}