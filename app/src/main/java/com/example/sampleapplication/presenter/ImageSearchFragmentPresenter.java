package com.example.sampleapplication.presenter;

import com.example.sampleapplication.ImageSearchContract;
import com.example.sampleapplication.data.ImageRepository;
import com.example.sampleapplication.modal.Photo;
import com.example.sampleapplication.utils.DataCallbackListener;
import com.example.sampleapplication.utils.ErrorCode;
import com.example.sampleapplication.utils.NetworkStatus;

import java.util.List;

public class ImageSearchFragmentPresenter implements ImageSearchContract.Presenter {

    private ImageSearchContract.View view;
    private ImageRepository imageRepository;

    public ImageSearchFragmentPresenter(ImageSearchContract.View view,
                                        ImageRepository imageRepository) {
        this.view = view;
        this.imageRepository = imageRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void searchImages(NetworkStatus networkStatus, final String imageSearchQuery) {
        view.showLoadingIndicator(true);
        if (imageSearchQuery != null && !imageSearchQuery.isEmpty()) {
            imageRepository.searchImages(true, networkStatus, imageSearchQuery,
                    new DataCallbackListener<List<Photo>>() {
                        @Override
                        public void onSuccess(List<Photo> response) {
                            view.showLoadingIndicator(false);
                            view.ShowImages(response);
                        }

                        @Override
                        public void onError(int errorCode) {
                            view.showLoadingIndicator(false);
                            view.showError(errorCode);
                        }
                    });

        } else {
            view.showError(ErrorCode.INVALID_QUERY);
        }
    }

}
