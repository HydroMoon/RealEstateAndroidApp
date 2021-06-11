package com.example.realestate.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestate.R;
import com.example.realestate.models.UserClass;
import com.parse.ParseCloud;

import java.util.ArrayList;
import java.util.HashMap;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private ArrayList<UserClass> usersList;
    UserClass current;
    Context context;

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView UserName;
        TextView Phone;

        Button deleteButton;


        UserViewHolder(@NonNull View itemView) {
            super(itemView);

            UserName = itemView.findViewById(R.id.row_title);
            Phone = itemView.findViewById(R.id.phone);
            deleteButton = itemView.findViewById(R.id.row_cancel_btn);


            deleteButton.setOnClickListener(v ->  {
                HashMap<String, String> params = new HashMap<>();
                params.put("objectId", usersList.get(getAdapterPosition()).getObjectID());
                Log.d("CLD", usersList.get(getAdapterPosition()).getObjectID());
                ParseCloud.callFunctionInBackground("deleteUserWithId", params, ((object, e) -> {
                    if (e == null) {
                        Toast.makeText(context, "User deleted successfuly.", Toast.LENGTH_LONG).show();
                    } else {
                        Log.d("CLD", e.getMessage());
                    }
                }));
            });
        }
    }

    public UsersAdapter(ArrayList<UserClass> uList, Context ctx){
        usersList = uList;
        context = ctx;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_rec, parent, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {
        current = usersList.get(position);

        holder.UserName.setText(current.getUsername());
        holder.Phone.setText(current.getPhone());
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
