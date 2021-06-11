package com.example.realestate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    Button delete;

    GridView serviceGrid;

    String objectID;

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
        addedDate = findViewById(R.id.added_date);
        soldDate = findViewById(R.id.sold_date);

        buy = findViewById(R.id.buy);
        call = findViewById(R.id.call);
        delete = findViewById(R.id.del_land);

        priceUSD = findViewById(R.id.property_price_usd);
        priceSDG = findViewById(R.id.property_price_sdg);

        objectID = getIntent().getExtras().getString("objectID");

        Log.d("DEBUG", objectID);
        getPropertyInfo(objectID);

        ParseUser.getCurrentUser().fetchInBackground(((object, e) -> {
            Log.d("BH ERR:", String.valueOf(object.getInt("admin")));
            if (object.getInt("admin") == 1) {
                delete.setVisibility(View.VISIBLE);
            } else {
                delete.setVisibility(View.GONE);
            }
        }));

        delete.setOnClickListener(view -> {
            AlertDialog.Builder adb = new AlertDialog.Builder(view.getContext());
            adb.setTitle("Confirm");
            adb.setMessage("Are you sure you want to delete?");
            adb.setNegativeButton("Cancel", (dialog, which) -> {
                dialog.dismiss();
            });

            adb.setPositiveButton("Ok", (dialog, which) -> {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("property_list");
                query.getInBackground(objectID, ((object, e) -> {
                    if (e == null) {
                        object.deleteInBackground(e2 -> {
                            if (e2 == null) {
                                Toast.makeText(this, "Deleted Successfully.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }));
            });
            adb.show();
        });

        buy.setOnClickListener(v -> {

            AlertDialog.Builder adb = new AlertDialog.Builder(v.getContext(), R.style.recycleDialog);
            adb.setTitle(getString(R.string.payment));


            View customAlertView = getLayoutInflater().inflate(R.layout.payment, null);

            final EditText cc_number = customAlertView.findViewById(R.id.credit_number_edit);
            final EditText exp_date = customAlertView.findViewById(R.id.exp_date_edit);
            final EditText ipin = customAlertView.findViewById(R.id.ipin_edit);

            final Button confirm = customAlertView.findViewById(R.id.pay_confirm);

            adb.setView(customAlertView);

            final AlertDialog dialog = adb.create();

            exp_date.setOnClickListener(view -> {
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(view.getContext(), (datepicker, selectedyear, selectedmonth, selectedday) -> {
                    
                    selectedmonth = selectedmonth + 1;
                    exp_date.setText(selectedday + "/" + selectedmonth + "/" + selectedyear);
                }, mYear, mMonth, mDay);
                mDatePicker.getDatePicker().setMinDate(new Date().getTime());
                mDatePicker.setTitle(getString(R.string.select_date));
                mDatePicker.show();
            });

            dialog.setOnShowListener(dialogInterface -> confirm.setOnClickListener(view -> {

                if (cc_number.getText().toString().length() == 0) {
                    cc_number.requestFocus();
                    cc_number.setError(getString(R.string.cc_error));
                } else if (exp_date.getText().toString().length() == 0) {
                    exp_date.requestFocus();
                    exp_date.setError(getString(R.string.exp_cc));
                } else if (cc_number.getText().toString().length() != 14) {
                    cc_number.requestFocus();
                    cc_number.setError(getString(R.string.cc_digit));
                } else if (ipin.getText().toString().length() != 4) {
                    ipin.requestFocus();
                    ipin.setError(getString(R.string.pin_digit));
                } else if (ipin.getText().toString().length() == 0) {
                    ipin.requestFocus();
                    ipin.setError(getString(R.string.cc_pin));
                } else {

                    ParseObject buyHistory = new ParseObject("buy_history");
                    buyHistory.put("buy_id", objectID);
                    buyHistory.put("user_name", ParseUser.getCurrentUser().get("name"));
                    buyHistory.put("user_id", ParseUser.getCurrentUser().getObjectId());
                    buyHistory.put("status", 0);
                    buyHistory.put("property_number", number.getText().toString());
                    buyHistory.put("phone", ParseUser.getCurrentUser().getUsername());

                    buyHistory.saveInBackground(e -> {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(), "Purchased Successfully", Toast.LENGTH_LONG).show();
                        } else {
                            //error
                            Log.d("ERROR", e.getMessage());
                        }
                    });
                    dialog.dismiss();
                }
            }));

            dialog.show();

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
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy", Locale.US);
                    //Status (Sold, Not Sold)
                    if (result.getInt("sold") == 1) {
                        status.setText("Sold");
                        soldDate.setText(sdf.format(result.getUpdatedAt()));
                        buy.setEnabled(false);
                    } else if (result.getInt("sold") == 0) {
                        status.setText("Available");
                    }

                    //Area
                    area.setText(String.valueOf(result.getInt("area")));
                    //Location
                    location.setText(result.getString("location"));
                    //Added date

                    addedDate.setText(sdf.format(result.getCreatedAt()));

                    //Prices
                    priceUSD.setText(String.valueOf(result.getInt("dollar")));
                    priceSDG.setText(String.valueOf(result.getInt("sdg")));
                }
            } else {

            }
        }));

    }
}