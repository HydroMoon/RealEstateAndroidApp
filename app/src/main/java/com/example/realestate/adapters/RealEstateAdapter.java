package com.example.realestate.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.PropertyInfo;
import com.example.realestate.R;
import com.example.realestate.models.RealEstateClass;

import java.util.ArrayList;

public class RealEstateAdapter extends RecyclerView.Adapter<RealEstateAdapter.RealEstateViewHolder> {

    private ArrayList<RealEstateClass> propertyProbs;
    RealEstateClass current;
    Context context;


    public RealEstateAdapter(ArrayList<RealEstateClass> propertyProbs, Context ctx) {
        this.propertyProbs = propertyProbs;
        context = ctx;
    }

    @NonNull
    @Override
    public RealEstateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_estate_row, parent, false);
        return new RealEstateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RealEstateViewHolder holder, int position) {
        current = propertyProbs.get(position);

        holder.ctx = context;
        holder.objectID = current.getObjectID();

        holder.price.setText("$" + current.getPropertyPriceUSD());
        holder.title.setText("قطعة رقم: " + current.getPropertyID());
        if (current.getPropertySingle() == 1) {
            holder.type.setText("سنقل");
        } else if (current.getProperty1stCorner() == 1) {
            holder.type.setText("الناصية الأولى");
        } else if (current.getProperty2ndCorner() == 1) {
            holder.type.setText("الناصية الثانية");
        }

        if (current.getPropertySold() == 0) {
            holder.sold.setText("متوفرة");
        } else if (current.getPropertySold() == 1) {
            holder.sold.setText("تم البيع");
        } else if (current.getPropertySold() == 2) {
            holder.sold.setText("الناصية الثانية");
        }
    }

    public void clear() {
        propertyProbs.clear();
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<RealEstateClass> items) {
        propertyProbs.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return propertyProbs.size();
    }

    static class RealEstateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView price;
        TextView type;
        TextView title;
        TextView sold;
        String objectID;
        Context ctx;

        RealEstateViewHolder(@NonNull View itemView) {
            super(itemView);

            price = itemView.findViewById(R.id.price);
            type = itemView.findViewById(R.id.type);
            title = itemView.findViewById(R.id.title_land);
            sold = itemView.findViewById(R.id.sold_status);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), PropertyInfo.class);
            intent.putExtra("objectID", objectID);

            v.getContext().startActivity(intent);
        }
    }
}

