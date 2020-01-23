package com.example.sampleapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.sampleapplication.R;

public class FullImageActivity extends AppCompatActivity {

    public static final String IMAGE_URL = "imageURL";
    public static final String OWNER_ID = "ownerId";
    public static final String IMAGE_ID = "imageId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_actvity);

        Intent intent = getIntent();
        String imageURL = intent.getStringExtra(IMAGE_URL);
        String ownerId = intent.getStringExtra(OWNER_ID);
        String imageId = intent.getStringExtra(IMAGE_ID);

        ImageView imageView = findViewById(R.id.image_view);
        TextView ownerIdText = findViewById(R.id.owner);
        TextView imageIdText = findViewById(R.id.image_id);

        if (imageURL != null) {
            Glide.with(this)
                    .load(imageURL)
                    .centerCrop()
                    .into(imageView);
        }

        if (ownerId != null) {
            ownerIdText.setText("OwnerId-" + ownerId);
        }

        if (imageId != null) {
            imageIdText.setText("ImageId" + imageId);
        }
    }

}
