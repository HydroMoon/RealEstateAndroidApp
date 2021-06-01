package com.example.realestate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;

public class PropertyInfo extends AppCompatActivity {

    TextView number;
    TextView priceUSD;
    TextView priceSDG;
    TextView type;
    TextView status;
    TextView area;
    TextView location;
    TextView addedDate;
    TextView soldDate;

    GridView serviceGrid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_info);

        Toolbar toolbar = findViewById(R.id.toolbar_item);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        serviceGrid = findViewById(R.id.services_list);

        number = findViewById(R.id.property_number);
        type = findViewById(R.id.property_type);
        status = findViewById(R.id.property_status);
        area = findViewById(R.id.property_surface);
        location = findViewById(R.id.property_location);

        priceUSD = findViewById(R.id.property_price_usd);
        priceSDG = findViewById(R.id.property_price_sdg);

        Log.d("DEBUG", getIntent().getExtras().getString("objectID"));
        getPropertyInfo(getIntent().getExtras().getString("objectID"));

    }

    private void getPropertyInfo(String objectID) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("property_list");

        query.whereEqualTo("objectId", objectID);

        query.findInBackground(((objects, e) -> {
            if (e == null) {
                for (ParseObject result : objects) {
                    //Property number
                    number.setText("قطعة رقم: " + String.valueOf(result.getInt("property_id")));

                    //Property type
                    if (result.getInt("single") == 1) {
                        type.setText("سنقل");
                    } else if (result.getInt("first_corner") == 1) {
                        type.setText("الناصية الأولى");
                    } else if (result.getInt("second_corner") == 1) {
                        type.setText("الناصية الثانية");
                    }

                    //Status (Sold, Not Sold)
                    if (result.getInt("sold") == 1) {
                        status.setText("Sold");
                    }

                    //Area
                    area.setText(String.valueOf(result.getInt("area")));
                    //location
                    location.setText(result.getString("location"));

                    //Prices
                    priceUSD.setText(String.valueOf(result.getInt("dollar")));
                    priceSDG.setText(String.valueOf(result.getInt("sdg")));
                }
            } else {

            }
        }));

    }
}