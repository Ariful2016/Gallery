package com.cit.hadis.gallery2202;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GalleryViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView textView;

    public GalleryViewHolder(@NonNull View itemView) {

        super(itemView);

        imageView = itemView.findViewById(R.id.img);
        textView = itemView.findViewById(R.id.name);
    }
}
