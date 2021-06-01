package com.example.realestate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.realestate.adapters.RealEstateAdapter;
import com.example.realestate.models.RealEstateClass;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView myRecycler;
    RecyclerView.LayoutManager layoutManager;
    RealEstateAdapter realEstateAdapter;
    ArrayList<RealEstateClass> propertyList = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == 0) {
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
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        runQuery();
    }

    public void runQuery() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("property_list");

        query.setLimit(200); // limit to at most 10 results
//        query.setSkip(10); // skip the first 10 results

        query.findInBackground((objects, e) -> {
            if(e == null){
                int x = 0;
                for (ParseObject result : objects) {
                    Log.d("Object found " + x++, result.getString("location"));
                    propertyList.add(new RealEstateClass(result.getObjectId(), result.getInt("property_id"), result.getInt(""), result.getString("location"),
                            result.getInt(""), result.getInt("first_corner"), result.getInt("second_corner"), result.getInt("single"),
                            result.getInt(""), result.getInt(""), result.getInt(""), result.getInt(""),
                            result.getInt(""), result.getInt(""), result.getInt(""), result.getInt(""),
                            result.getInt(""), result.getInt(""), result.getInt(""), result.getInt(""),
                            result.getInt(""), result.getInt(""), result.getInt(""), result.getInt(""),
                            result.getInt(""), result.getInt(""), result.getInt(""), result.getInt(""),
                            result.getInt("dollar"), result.getInt("sdg")));
                }

                myRecycler = findViewById(R.id.recycler_list);
                myRecycler.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(this);
                realEstateAdapter = new RealEstateAdapter(propertyList, getApplicationContext());

                myRecycler.setLayoutManager(layoutManager);
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