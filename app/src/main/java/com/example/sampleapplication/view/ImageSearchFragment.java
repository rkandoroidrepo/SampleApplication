package com.example.sampleapplication.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleapplication.ImageSearchContract;
import com.example.sampleapplication.R;
import com.example.sampleapplication.modal.Photo;
import com.example.sampleapplication.presenter.ImageSearchFragmentPresenter;
import com.example.sampleapplication.utils.AppNetworkStatus;
import com.example.sampleapplication.utils.ErrorCode;

import java.util.List;


public class ImageSearchFragment extends Fragment implements ImageSearchContract.View {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private View rootView;
    private ImageAdapter imageAdapter;
    private ImageSearchFragmentPresenter presenter;
    private FrameLayout progressLoader;

    public ImageSearchFragment() {
        // Required empty public constructor
    }

    public static ImageSearchFragment newInstance() {
        ImageSearchFragment fragment = new ImageSearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_image_search_fragmnet, container, false);
        initUI();
        return rootView;
    }

    @Override
    public void showLoadingIndicator(boolean show) {
        if (show) {
            progressLoader.setVisibility(View.VISIBLE);
        } else {
            progressLoader.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(int errorCode) {
        String message = "";
        if (errorCode == ErrorCode.NETWORK_ERROR) {
            message = "Please check network connection";

        } else if (errorCode == ErrorCode.INVALID_QUERY) {
            message = "Invalid query";

        } else if (errorCode == ErrorCode.SERVER_ERROR) {
            message = "Server error";
        }

        Toast.makeText(getContext(), message,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void ShowImages(List<Photo> photoList) {
        if (imageAdapter != null) {
            imageAdapter.update(photoList);
        }
    }

    public void initUI() {
        searchView = rootView.findViewById(R.id.search_view);
        progressLoader = rootView.findViewById(R.id.progress_loader);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (presenter != null) {
                    presenter.searchImages(new AppNetworkStatus(getContext()), s.toLowerCase());
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        recyclerView = rootView.findViewById(R.id.image_recycler_view);
        imageAdapter = new ImageAdapter(null);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imageAdapter);
    }

    @Override
    public void setPresenter(ImageSearchContract.Presenter presenter) {
        this.presenter = (ImageSearchFragmentPresenter) presenter;
    }
}
