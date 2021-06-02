package com.example.realestate.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;
import com.example.realestate.models.OrderClass;
import com.example.realestate.models.RealEstateClass;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {

    private ArrayList<OrderClass> ordersList;
    OrderClass current;
    Context context;
    AlertDialog adb;

    class OrderViewHolder extends RecyclerView.ViewHolder{

        TextView gasName;
        TextView gasPrice;
        TextView gasLoc;
        TextView phone;
        Button cancelOrder;
        Button confirm;
        Button act;


        OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            gasName = itemView.findViewById(R.id.row_title);
            gasPrice = itemView.findViewById(R.id.row_price);
            gasLoc = itemView.findViewById(R.id.row_loc_title);
            phone = itemView.findViewById(R.id.phone);
            cancelOrder = itemView.findViewById(R.id.row_cancel_btn);
            confirm = itemView.findViewById(R.id.confirm_row_btn);
            act = itemView.findViewById(R.id.idfab);

            cancelOrder.setOnClickListener(v ->  {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("buy_history");
                    // Query parameters based on the item name
                    query.getInBackground(ordersList.get(getAdapterPosition()).getObjectID(), (object, e) -> {
                        if (e == null) {
                            object.deleteInBackground(e2 -> {
                                if (e2 == null) {
                                    ordersList.remove(getAdapterPosition());
                                    notifyDataSetChanged();
                                    Toast.makeText(v.getContext(), "Delete Successful", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(v.getContext(), e2.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                    });

            confirm.setOnClickListener(v -> {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("buy_history");
                    // Query parameters based on the item name
                    query.getInBackground(ordersList.get(getAdapterPosition()).getObjectID(), ((object, e) -> {
                        if (e == null) {
                            object.put("status", 1);
                            object.saveInBackground();
                            ParseQuery<ParseObject> property = ParseQuery.getQuery("property_list");
                            property.getInBackground(ordersList.get(getAdapterPosition()).getBuyID(), ((object1, e1) -> {
                                if (e1 == null) {
                                    object1.put("sold", 1);
                                    object1.saveInBackground();
                                } else {
                                    Log.d("ERR12", e1.getMessage());
                                }
                            }));
                            adb.dismiss();
                            Toast.makeText(v.getContext(), "تمت عملية الشراء بنجاح", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }));

                });
        }
    }

    public OrdersAdapter(ArrayList<OrderClass> oList, Context ctx, androidx.appcompat.app.AlertDialog adb){
        ordersList = oList;
        context = ctx;
        this.adb = adb;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_accept, parent, false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderViewHolder holder, int position) {
        current = ordersList.get(position);

        holder.gasName.setText(current.getName());
        holder.gasPrice.setText(current.getPropertyNumber());
        holder.phone.setText(current.getPhone());
        if (current.getStatus() == 0) {
            holder.gasLoc.setText("في إنتظار الموافقة");
        } else if (current.getStatus()== 1) {
            holder.gasLoc.setText("تم التأكيد على البيع");
            holder.gasLoc.setTypeface(null, Typeface.BOLD);
        }
        if (current.getStatus() == 0) {
            holder.act.setBackgroundColor(context.getResources().getColor(R.color.notok));
        } else {
            holder.confirm.setEnabled(false);
            holder.cancelOrder.setEnabled(false);
            holder.act.setBackgroundColor(context.getResources().getColor(R.color.ok));
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }
}
