package com.example.realestate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

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

    Button buy;
    Button call;

    GridView serviceGrid;

    List<String> serviceItems = new ArrayList<>();


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

        buy = findViewById(R.id.buy);
        call = findViewById(R.id.call);

        priceUSD = findViewById(R.id.property_price_usd);
        priceSDG = findViewById(R.id.property_price_sdg);

        Log.d("DEBUG", getIntent().getExtras().getString("objectID"));
        getPropertyInfo(getIntent().getExtras().getString("objectID"));

        buy.setOnClickListener(v -> {

            ParseObject buyHistory = new ParseObject("buy_history");
            buyHistory.put("buy_id", "OBJECT_ID");

            buyHistory.saveInBackground(e -> {
                if (e == null) {

                } else {
                    //error
                }
            });
        });

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

                    //Service list
                    if (result.getInt("masjid") == 1)
                        serviceItems.add("مسجد");

                    if (result.getInt("hospital") == 1)
                        serviceItems.add("مركز صحي");

                    if (result.getInt("police_station") == 1)
                        serviceItems.add("مركز شرطة");

                    if (result.getInt("school") == 1)
                        serviceItems.add("مدرسة");

                    if (result.getInt("mall") == 1)
                        serviceItems.add("مركز تجاري");

                    if (result.getInt("main_road") == 1)
                        serviceItems.add("شارع رئيسي");

                    if (result.getInt("branch_road") == 1)
                        serviceItems.add("شارع فرعي");

                    if (result.getInt("bakery") == 1)
                        serviceItems.add("مخبز");

                    if (result.getInt("station") == 1)
                        serviceItems.add("محطة");

                    if (result.getInt("pharmacy") == 1)
                        serviceItems.add("صيدلية");

                    if (result.getInt("park") == 1)
                        serviceItems.add("منتزه");

                    if (result.getInt("petrol_station") == 1)
                        serviceItems.add("محطة وقود");

                    if (result.getInt("venue") == 1)
                        serviceItems.add("صالة مناسبات");

                    if (result.getInt("atm") == 1)
                        serviceItems.add("صراف آلي");

                    if (result.getInt("square") == 1)
                        serviceItems.add("ميدان");

                    if (result.getInt("laundry") == 1)
                        serviceItems.add("مغسلة");

                    if (result.getInt("barber_shop") == 1)
                        serviceItems.add("حلاق");

                    serviceGrid.setAdapter(new ArrayAdapter<>(getApplicationContext(), R.layout.gridcell, R.id.service_name, serviceItems));
                    
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