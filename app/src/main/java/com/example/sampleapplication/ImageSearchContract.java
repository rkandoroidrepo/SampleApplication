package com.example.sampleapplication;

import com.example.sampleapplication.modal.Photo;
import com.example.sampleapplication.utils.NetworkStatus;

import java.util.List;

public interface ImageSearchContract {

    interface View extends BaseView<Presenter> {
        void showLoadingIndicator(boolean show);

        void showError(int errorCode);

        void ShowImages(List<Photo> photoList);

        void initUI();

    }

    interface Presenter extends BasePresenter {
        void searchImages(NetworkStatus networkStatus,
                          String imageSearchQuery);
    }
}
