package com.cit.hadis.gallery2202;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GalleryAdapetr extends RecyclerView.Adapter<GalleryViewHolder> {

    Context context;
    List<Gallery> galleryList;

    public GalleryAdapetr(Context context, List<Gallery> galleryList) {
        this.context = context;
        this.galleryList = galleryList;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {

        Gallery gallery = galleryList.get(position);
        holder.textView.setText(gallery.getName());
        holder.imageView.setImageURI(gallery.getUri());
    }

    @Override
    public int getItemCount() {
        return galleryList.size();
    }
}
