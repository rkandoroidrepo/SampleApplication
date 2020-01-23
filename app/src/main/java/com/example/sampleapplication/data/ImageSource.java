package com.example.sampleapplication.data;

import com.example.sampleapplication.modal.ImageSearchResult;
import com.example.sampleapplication.utils.DataCallbackListener;
import com.example.sampleapplication.utils.NetworkStatus;

public interface ImageSource {

    void searchImages(NetworkStatus networkStatus, String imageSearchQuery,
                      DataCallbackListener<ImageSearchResult> callbackListener);
}
