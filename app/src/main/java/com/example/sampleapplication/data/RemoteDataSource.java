package com.example.sampleapplication.data;

import com.example.sampleapplication.modal.ImageSearchResult;
import com.example.sampleapplication.utils.DataCallbackListener;
import com.example.sampleapplication.utils.ErrorCode;
import com.example.sampleapplication.utils.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {

    private static RemoteDataSource remoteDataSource;
    private ImageServices imageServices;

    private RemoteDataSource() {
        imageServices = RetrofitClient.getClient().create(ImageServices.class);
    }

    public static RemoteDataSource getInstance() {
        if (remoteDataSource == null) {
            remoteDataSource = new RemoteDataSource();
        }
        return remoteDataSource;
    }

    private static int getErrorCode(Throwable t) {
        if (t instanceof IOException) {
            return ErrorCode.NETWORK_ERROR;
        } else {
            return ErrorCode.SERVER_ERROR;
        }
    }

    public void searchImages(
            String imageSearchQuery,
            final DataCallbackListener<ImageSearchResult> callbackListener) {
        imageServices.searchImges(imageSearchQuery).enqueue(new Callback<ImageSearchResult>() {
            @Override
            public void onResponse(Call<ImageSearchResult> call,
                                   Response<ImageSearchResult> response) {
                if (response.isSuccessful()) {
                    callbackListener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<ImageSearchResult> call, Throwable t) {
                callbackListener.onError(getErrorCode(t));
            }
        });
    }
}