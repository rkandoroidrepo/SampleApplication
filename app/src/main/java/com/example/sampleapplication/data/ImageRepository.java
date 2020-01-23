package com.example.sampleapplication.data;

import com.example.sampleapplication.modal.ImageSearchResult;
import com.example.sampleapplication.modal.Photo;
import com.example.sampleapplication.utils.DataCallbackListener;
import com.example.sampleapplication.utils.ErrorCode;
import com.example.sampleapplication.utils.NetworkStatus;

import java.util.ArrayList;
import java.util.List;

public class ImageRepository {

    private static ImageRepository imageRepository;
    private RemoteDataSource remoteDataSource;
    private List<Photo> photos;

    private ImageRepository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static ImageRepository getInstance(RemoteDataSource remoteDataSource) {
        if (imageRepository == null) {
            imageRepository = new ImageRepository(remoteDataSource);
        }
        return imageRepository;
    }

    public void searchImages(final boolean isNewQuery, NetworkStatus networkStatus,
                             final String imageSearchQuery,
                             final DataCallbackListener callbackListener) {
        if (networkStatus.isOnline()) {
            remoteDataSource.searchImages(imageSearchQuery, new DataCallbackListener<ImageSearchResult>() {
                @Override
                public void onSuccess(ImageSearchResult imageSearchResult) {
                    if (imageSearchResult.getStat().equalsIgnoreCase("ok") &&
                            imageSearchResult.getPhotos() != null
                            && !imageSearchResult.getPhotos().getPhoto().isEmpty()) {
                        if (photos == null) {
                            photos = new ArrayList<>();
                        }
                        if (isNewQuery) {
                            photos.clear();
                        }
                        photos.addAll(imageSearchResult.getPhotos().getPhoto());
                        callbackListener.onSuccess(photos);
                    } else {
                        callbackListener.onError(ErrorCode.INVALID_QUERY);
                    }
                }

                @Override
                public void onError(int errorCode) {

                }
            });
        } else {
            callbackListener.onError(ErrorCode.NETWORK_ERROR);
        }
    }

    public void reset() {
        photos = null;
    }

}
