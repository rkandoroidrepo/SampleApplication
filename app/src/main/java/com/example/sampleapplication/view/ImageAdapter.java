package com.example.sampleapplication.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sampleapplication.R;
import com.example.sampleapplication.modal.Photo;

import java.util.List;

import static com.example.sampleapplication.view.FullImageActivity.IMAGE_URL;
import static com.example.sampleapplication.view.FullImageActivity.OWNER_ID;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private static final String IMAGE_ID = "imageId";
    private List<Photo> photoList;
    private Context context;

    public ImageAdapter(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public void update(List<Photo> photoList) {
        this.photoList = photoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item,
                parent, false);
        this.context = parent.getContext();
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        final Photo photo = photoList.get(position);
        String imageUrl = "http://farm" + photo.getFarm() + ".static.flickr.com/" + photo.getServer()
                + "/" + photo.getId() + "_" + photo.getSecret() + ".jpg";
        Glide.with(context)
                .load(imageUrl)
                .centerCrop()
                .into(holder.imageView);

        holder.imageIdText.setText(IMAGE_ID + photo.getId());

        holder.imageView.setOnClickListener(view -> {
            Intent intent = new Intent(context, FullImageActivity.class);
            intent.putExtra(IMAGE_ID, photo.getId());
            intent.putExtra(IMAGE_URL, imageUrl);
            intent.putExtra(OWNER_ID, photo.getOwner());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return photoList == null ? 0 : photoList.size();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView imageIdText;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            imageIdText = itemView.findViewById(R.id.image_id);
        }
    }
}
