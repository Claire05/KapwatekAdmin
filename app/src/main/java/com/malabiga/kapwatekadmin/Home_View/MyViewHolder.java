package com.malabiga.kapwatekadmin.Home_View;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.malabiga.kapwatekadmin.R;

class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;
    TextView author;
    View v;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.profileImage);
        textView=itemView.findViewById(R.id.user_name);
        author = itemView.findViewById(R.id.author);
        v=itemView;
    }
}
