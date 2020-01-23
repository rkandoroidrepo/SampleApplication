package com.example.sampleapplication.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sampleapplication.R;
import com.example.sampleapplication.data.ImageRepository;
import com.example.sampleapplication.data.RemoteDataSource;
import com.example.sampleapplication.presenter.ImageSearchFragmentPresenter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            loadListFragment();
        }
    }

    private void loadListFragment() {
        ImageSearchFragment imageSearchFragment = ImageSearchFragment.newInstance();
        imageSearchFragment.setRetainInstance(true);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, imageSearchFragment)
                .commit();
        new ImageSearchFragmentPresenter(imageSearchFragment,
                ImageRepository.getInstance(RemoteDataSource.getInstance()));

    }
}
