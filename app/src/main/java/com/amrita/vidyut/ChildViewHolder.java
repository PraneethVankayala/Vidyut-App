package com.amrita.vidyut;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ChildViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;
    TextView description;
   // TextView availability;
    CardView layout;

    public ChildViewHolder(View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.imageView);
        textView=itemView.findViewById(R.id.workshop_tittle);
        description=itemView.findViewById(R.id.workshop_desc);
      //  availability=itemView.findViewById(R.id.workshop_availability);
        layout = itemView.findViewById(R.id.cardview2);

    }
}
